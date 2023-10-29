package com.mostafa.file.system.service.implementation;

import com.mostafa.file.system.constants.ItemType;
import com.mostafa.file.system.constants.PermissionLevel;
import com.mostafa.file.system.entity.dto.ItemDTO;
import com.mostafa.file.system.entity.dto.ItemRequestDTO;
import com.mostafa.file.system.entity.dto.ItemWithFileDTO;
import com.mostafa.file.system.entity.dto.PermissionDTO;
import com.mostafa.file.system.entity.jpa.File;
import com.mostafa.file.system.entity.jpa.Item;
import com.mostafa.file.system.entity.jpa.PermissionGroup;
import com.mostafa.file.system.exception.ItemDomainException;
import com.mostafa.file.system.exception.ItemNotFoundException;
import com.mostafa.file.system.mapper.ItemMapper;
import com.mostafa.file.system.repository.ItemRepository;
import com.mostafa.file.system.service.ItemService;
import com.mostafa.file.system.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final PermissionService permissionService;

    public ItemServiceImpl(ItemRepository itemRepository,
                           ItemMapper itemMapper,
                           PermissionService permissionService) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
        this.permissionService = permissionService;
    }


    @Override
    public ItemDTO save(ItemRequestDTO item) {
        validateAndVerifyUser(item.getUserEmail(), item.getPermissionGroup().getId());
        return itemMapper.itemToItemDTO(itemRepository
                .save(itemMapper.itemRequestDTOToItem(item)));
    }

    @Override
    public ItemDTO saveItemWithFile(MultipartFile multipartFile,
                                    String userEmail,
                                    Long permissionGroupId,
                                    Long parentItemId) {
        validateAndVerifyUser(userEmail, permissionGroupId);
        Item item = initItemData(multipartFile, permissionGroupId, parentItemId);
        return itemMapper.itemToItemDTO(itemRepository
                .save(item));
    }

    @Override
    public ItemDTO findById(Long id) {
        Optional<Item> itemResponse = itemRepository.findById(id);
        if (itemResponse.isEmpty()) {
            log.error("Item with id: {} could not be found!", id);
            throw new ItemNotFoundException("Item with id " + id + " could not be found!");
        }
        return itemMapper.itemToItemDTO(itemResponse.get());
    }

    @Override
    public ItemWithFileDTO findItemAndDownloadFile(Long itemId, String userEmail) {
        PermissionDTO permissionDTO = permissionService.findByUserEmail(userEmail);
        Optional<Item> itemResponse = itemRepository.findById(itemId);
        if (itemResponse.isEmpty()) {
            log.error("Item with id: {} could not be found!", itemId);
            throw new ItemNotFoundException("Item with id " + itemId + " could not be found!");
        }
        if (!itemResponse.get().getPermissionGroup().getId().equals(permissionDTO.getGroup().getId())) {
            log.error("This user: {} is not part of this permission group!", permissionDTO.getUserEmail());
            throw new ItemDomainException("This user: " + permissionDTO.getUserEmail() + " is not part of this permission group!");
        }
        return itemMapper.itemToItemWithFileDTO(itemResponse.get());
    }

    @Override
    public List<ItemDTO> findAll() {
        List<Item> items = itemRepository.findAll();
        if (items.isEmpty()) {
            log.error("No Items were found!");
            throw new ItemNotFoundException("No Items were found!");
        }
        return items.stream()
                .map(itemMapper::itemToItemDTO)
                .collect(Collectors.toList());
    }

    private Item initItemData(MultipartFile multipartFile, Long permissionGroupId, Long parentItemId) {
        Item item = new Item();
        item.setType(ItemType.File);
        item.setParentItem(new Item(parentItemId));
        item.setPermissionGroup(new PermissionGroup(permissionGroupId));
        item.setName(multipartFile.getOriginalFilename());
        File file = new File();
        file.setItem(item);
        try {
            file.setBinaryData(multipartFile.getBytes());
        } catch (IOException e) {
            log.error("Error creating file");
            throw new RuntimeException(e);
        }
        item.setFiles(Arrays.asList(file));
        return item;
    }

    private void validateAndVerifyUser(String userEmail, Long permissionGroupId) {
        PermissionDTO permissionDTO = permissionService.findByUserEmail(userEmail);
        if (!permissionGroupId.equals(permissionDTO.getGroup().getId())) {
            log.error("This user: {} is not part of this permission group!", permissionDTO.getUserEmail());
            throw new ItemDomainException("This user: " + permissionDTO.getUserEmail() + " is not part of this permission group!");
        }
        if (permissionDTO.getPermissionLevel() != PermissionLevel.EDIT) {
            log.error("This user: {} is not allowed to edit this item!", permissionDTO.getUserEmail());
            throw new ItemDomainException("This user: " + permissionDTO.getUserEmail() + " is not allowed to edit this item!");
        }
    }


}
