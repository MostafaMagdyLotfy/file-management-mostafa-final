package com.mostafa.file.system.entity.dto;

import com.mostafa.file.system.constants.ItemType;
import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemWithFileDTO {

    private Long id;
    private ItemType type;
    private String name;
    private PermissionGroupDTO permissionGroup;
    private ItemSimpleDTO parentItem;
    private List<FileSimpleDTO> files;

    public ItemWithFileDTO(Long id) {
        this.id = id;
    }
}
