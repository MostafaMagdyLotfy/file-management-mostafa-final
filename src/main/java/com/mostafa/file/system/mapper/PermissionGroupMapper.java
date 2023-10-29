package com.mostafa.file.system.mapper;

import com.mostafa.file.system.entity.dto.PermissionGroupDTO;
import com.mostafa.file.system.entity.jpa.PermissionGroup;
import org.springframework.stereotype.Component;

@Component
public class PermissionGroupMapper {
    public PermissionGroupDTO permissionGroupToPermissionGroupDTO(PermissionGroup permissionGroup) {
        if (permissionGroup != null) {
            return PermissionGroupDTO.builder()
                    .id(permissionGroup.getId())
                    .groupName(permissionGroup.getGroupName())
                    .build();
        }
        return null;
    }
}
