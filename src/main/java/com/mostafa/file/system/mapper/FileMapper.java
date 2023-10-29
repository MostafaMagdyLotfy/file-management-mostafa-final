package com.mostafa.file.system.mapper;

import com.mostafa.file.system.entity.dto.FileDTO;
import com.mostafa.file.system.entity.dto.FileResponseDTO;
import com.mostafa.file.system.entity.dto.FileSimpleDTO;
import com.mostafa.file.system.entity.jpa.File;
import org.springframework.stereotype.Component;

@Component
public class FileMapper {
    private final ItemMapper itemMapper;

    public FileMapper(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    public FileDTO fileToFileDTO(File file) {
        if (file != null) {
            return FileDTO.builder()
                    .id(file.getId())
                    .item(itemMapper.itemToItemDTO(file.getItem()))
                    .binaryData(file.getBinaryData())
                    .build();
        }
        return null;
    }

    public FileSimpleDTO fileToFileSimpleDTO(File file) {
        if (file != null) {
            return FileSimpleDTO.builder()
                    .id(file.getId())
                    .binaryData(file.getBinaryData())
                    .build();
        }
        return null;
    }

    public FileResponseDTO fileToFileResponseDTO(File file) {
        if (file != null) {
            return FileResponseDTO.builder()
                    .id(file.getId())
                    .item(itemMapper.itemToItemDTO(file.getItem()))
                    .build();
        }
        return null;
    }
}
