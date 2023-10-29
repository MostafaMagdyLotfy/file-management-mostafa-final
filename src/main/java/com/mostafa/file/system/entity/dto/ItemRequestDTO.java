package com.mostafa.file.system.entity.dto;

import com.mostafa.file.system.constants.ItemType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemRequestDTO {
    private Long id;
    @NotNull
    private String userEmail;
    @NotNull
    private ItemType type;
    @NotNull
    private String name;
    private PermissionGroupDTO permissionGroup;
    private ItemSimpleDTO parentItem;

    public ItemRequestDTO(Long id) {
        this.id = id;
    }
}
