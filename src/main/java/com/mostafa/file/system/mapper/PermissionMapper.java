package com.mostafa.file.system.mapper;

import com.mostafa.file.system.entity.dto.PermissionDTO;
import com.mostafa.file.system.entity.jpa.Permission;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper {
    private final PermissionGroupMapper permissionGroupMapper;

    public PermissionMapper(PermissionGroupMapper permissionGroupMapper) {
        this.permissionGroupMapper = permissionGroupMapper;
    }

    public PermissionDTO permissionToPermissionDTO(Permission permission) {
        if (permission != null) {
            return PermissionDTO.builder()
                    .id(permission.getId())
                    .userEmail(permission.getUserEmail())
                    .permissionLevel(permission.getPermissionLevel())
                    .group(permissionGroupMapper
                            .permissionGroupToPermissionGroupDTO(permission.getGroup()))
                    .build();
        }
        return null;
    }
}
