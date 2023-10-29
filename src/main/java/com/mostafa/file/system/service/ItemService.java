package com.mostafa.file.system.service;

import com.mostafa.file.system.entity.dto.ItemDTO;
import com.mostafa.file.system.entity.dto.ItemRequestDTO;
import com.mostafa.file.system.entity.dto.ItemWithFileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService {
    ItemDTO save(ItemRequestDTO item);

    ItemDTO saveItemWithFile(MultipartFile file, String userEmail, Long permissionGroupId, Long parentItemId);

    ItemDTO findById(Long id);

    ItemWithFileDTO findItemAndDownloadFile(Long itemId, String userEmail);

    List<ItemDTO> findAll();

}
