package com.mostafa.file.system.entity.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileResponseDTO {
    private Long id;
    private ItemDTO item;
}
