package com.mostafa.file.system.service;

import com.mostafa.file.system.entity.dto.PermissionDTO;
import com.mostafa.file.system.entity.jpa.Permission;

import java.util.List;

public interface PermissionService {
    PermissionDTO save(Permission Permission);

    PermissionDTO findById(Long id);

    List<PermissionDTO> findAll();

    PermissionDTO findByUserEmail(String userEmail);
}
