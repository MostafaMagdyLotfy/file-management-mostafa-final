package com.mostafa.file.system.entity.dto;

import com.mostafa.file.system.constants.ItemType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDTO {

    private Long id;
    private ItemType type;
    private String name;
    private PermissionGroupDTO permissionGroup;
    private ItemSimpleDTO parentItem;

    public ItemDTO(Long id) {
        this.id = id;
    }
}
