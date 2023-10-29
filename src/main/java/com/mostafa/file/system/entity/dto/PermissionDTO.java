package com.mostafa.file.system.entity.dto;

import com.mostafa.file.system.constants.PermissionLevel;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionDTO {

    private Long id;

    private String userEmail;

    private PermissionLevel permissionLevel;

    private PermissionGroupDTO group;

    public PermissionDTO(Long id) {
        this.id = id;
    }
}
