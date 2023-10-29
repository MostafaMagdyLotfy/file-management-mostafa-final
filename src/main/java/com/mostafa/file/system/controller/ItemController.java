package com.mostafa.file.system.controller;

import com.mostafa.file.system.constants.ItemType;
import com.mostafa.file.system.entity.dto.ItemDTO;
import com.mostafa.file.system.entity.dto.ItemRequestDTO;
import com.mostafa.file.system.entity.dto.ItemWithFileDTO;
import com.mostafa.file.system.exception.ItemDomainException;
import com.mostafa.file.system.service.ItemService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<ItemDTO> createItem(@Valid @RequestBody ItemRequestDTO item) {
        log.info("Creating item with name: {}, and type: {}", item.getName(), item.getType());
        ItemDTO createdItem = itemService.save(item);
        log.info("Item created with id: {}", createdItem.getId());
        return ResponseEntity.ok(createdItem);
    }

    @PostMapping("/space")
    public ResponseEntity<ItemDTO> createSpace(@Valid @RequestBody ItemRequestDTO item) {
        item.setType(ItemType.Space);
        log.info("Creating space with name: {}, and type: {}", item.getName(), item.getType());
        ItemDTO createdItem = itemService.save(item);
        log.info("Space created with id: {}", createdItem.getId());
        return ResponseEntity.ok(createdItem);
    }

    @PostMapping("/folder")
    public ResponseEntity<ItemDTO> createFolder(@Valid @RequestBody ItemRequestDTO item) {
        item.setType(ItemType.Folder);
        log.info("Creating folder with name: {}, and type: {}", item.getName(), item.getType());
        ItemDTO createdItem = itemService.save(item);
        log.info("Folder created with id: {}", createdItem.getId());
        return ResponseEntity.ok(createdItem);
    }

    @PostMapping("/file/upload")
    public ResponseEntity<ItemDTO> uploadFile(@RequestParam("file") MultipartFile uploadedFile,
                                              @RequestParam("userEmail") String userEmail,
                                              @RequestParam("permissionGroup") Long permissionGroup,
                                              @RequestParam("parentItem") Long parentItem) {
        validateDataForUpload(uploadedFile, userEmail, permissionGroup, parentItem);
        log.info("Creating file with name: {}, and type: {}", uploadedFile.getName(), ItemType.File);
        ItemDTO createdItem = itemService.saveItemWithFile(uploadedFile, userEmail, permissionGroup, parentItem);
        log.info("File created with id: {}", createdItem.getId());
        return ResponseEntity.ok(createdItem);
    }


    @GetMapping("/file/download/{itemId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long itemId,
                                                 @RequestParam("userEmail") String userEmail) {

        validateDataForDownload(itemId, userEmail);
        log.info("Downloading file with item id: {}", itemId);
        ItemWithFileDTO itemWithFile = itemService.findItemAndDownloadFile(itemId, userEmail);
        log.info("Returning file with item id: {}", itemWithFile.getId());
        return ResponseEntity.ok().
                header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + itemWithFile.getName() + "\"").
                body(new ByteArrayResource(itemWithFile.getFiles().get(0).getBinaryData()));
    }


    @GetMapping("/{itemId}")
    public ResponseEntity<ItemDTO> findItem(@PathVariable Long itemId) {
        ItemDTO itemResponse = itemService.findById(itemId);
        log.info("Returning item with id: {}", itemResponse.getId());
        return ResponseEntity.ok(itemResponse);
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> findAllItems() {
        List<ItemDTO> items = itemService.findAll();
        log.info("Returning all items");
        return ResponseEntity.ok(items);
    }

    private void validateDataForDownload(Long itemId, String userEmail) {
        if (itemId == null) {
            log.error("No itemId found in parameters, please add itemId.");
            throw new ItemDomainException("No itemId found in parameters, please add itemId.");
        }
        if (userEmail == null) {
            log.error("No userEmail found in parameters, please add userEmail.");
            throw new ItemDomainException("No userEmail found in parameters, please add userEmail.");
        }
    }

    private void validateDataForUpload(MultipartFile uploadedFile, String userEmail, Long permissionGroup, Long parentItem) {
        if (userEmail == null) {
            log.error("No userEmail found in parameters, please add userEmail.");
            throw new ItemDomainException("No userEmail found in parameters, please add userEmail.");
        }
        if (permissionGroup == null) {
            log.error("No permissionGroup found in parameters, please add permissionGroup.");
            throw new ItemDomainException("No permissionGroup found in parameters, please add permissionGroup.");
        }
        if (parentItem == null) {
            log.error("No parentItem found in parameters, please add parentItem.");
            throw new ItemDomainException("No parentItem found in parameters, please add parentItem.");
        }
        if (uploadedFile == null) {
            log.error("No file found in parameters, please add file.");
            throw new ItemDomainException("No file found in parameters, please add file.");
        }
    }
}
