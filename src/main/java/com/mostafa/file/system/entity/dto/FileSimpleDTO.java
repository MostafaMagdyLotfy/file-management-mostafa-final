package com.mostafa.file.system.entity.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileSimpleDTO {

    private Long id;
    private byte[] binaryData;

    public FileSimpleDTO(Long id) {
        this.id = id;
    }
}