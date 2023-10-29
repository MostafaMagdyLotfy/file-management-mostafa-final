package com.mostafa.file.system.service;

import com.mostafa.file.system.entity.dto.PermissionGroupDTO;
import com.mostafa.file.system.entity.jpa.PermissionGroup;

import java.util.List;

public interface PermissionGroupService {
    PermissionGroupDTO save(PermissionGroup permissionGroup);

    PermissionGroupDTO findById(Long id);

    List<PermissionGroupDTO> findAll();
}
