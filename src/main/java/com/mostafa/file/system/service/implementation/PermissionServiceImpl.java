package com.mostafa.file.system.service.implementation;

import com.mostafa.file.system.entity.dto.PermissionDTO;
import com.mostafa.file.system.entity.jpa.Permission;
import com.mostafa.file.system.exception.PermissionNotFoundException;
import com.mostafa.file.system.mapper.PermissionMapper;
import com.mostafa.file.system.repository.PermissionRepository;
import com.mostafa.file.system.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    public PermissionServiceImpl(PermissionRepository permissionRepository,
                                 PermissionMapper permissionMapper) {
        this.permissionRepository = permissionRepository;
        this.permissionMapper = permissionMapper;
    }

    @Override
    public PermissionDTO save(Permission permission) {
        return permissionMapper.permissionToPermissionDTO(permissionRepository.save(permission));
    }

    @Override
    public PermissionDTO findById(Long id) {
        Optional<Permission> permission = permissionRepository.findById(id);
        if (permission.isEmpty()) {
            log.error("Permission with id: {} could not be found!", id);
            throw new PermissionNotFoundException("Permission with id " + id + " could not be found!");
        }
        return permissionMapper.permissionToPermissionDTO(permission.get());
    }

    @Override
    public List<PermissionDTO> findAll() {
        List<Permission> permissions = permissionRepository.findAll();
        if (permissions.isEmpty()) {
            log.error("No Permissions were found!");
            throw new PermissionNotFoundException("No Permissions were found!");
        }
        return permissions.stream()
                .map(permissionMapper::permissionToPermissionDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PermissionDTO findByUserEmail(String userEmail) {
        Optional<Permission> permission = permissionRepository.findByUserEmail(userEmail);
        if (permission.isEmpty()) {
            log.error("User with email: {} could not be found!", userEmail);
            throw new PermissionNotFoundException("User with email " + userEmail + " could not be found!");
        }
        return permissionMapper.permissionToPermissionDTO(permission.get());
    }
}
