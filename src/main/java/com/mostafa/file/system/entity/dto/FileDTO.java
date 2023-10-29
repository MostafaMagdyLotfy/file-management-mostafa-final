package com.mostafa.file.system.entity.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDTO {

    private Long id;
    private byte[] binaryData;
    private ItemDTO item;

    public FileDTO(Long id) {
        this.id = id;
    }
}
