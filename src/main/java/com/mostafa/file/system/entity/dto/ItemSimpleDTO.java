package com.mostafa.file.system.entity.dto;

import com.mostafa.file.system.constants.ItemType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemSimpleDTO {
    private Long id;
    private ItemType type;
    private String name;

    public ItemSimpleDTO(Long id) {
        this.id = id;
    }
}
