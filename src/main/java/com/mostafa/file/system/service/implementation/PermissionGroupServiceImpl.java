package com.mostafa.file.system.service.implementation;

import com.mostafa.file.system.entity.dto.PermissionGroupDTO;
import com.mostafa.file.system.entity.jpa.PermissionGroup;
import com.mostafa.file.system.exception.PermissionGroupNotFoundException;
import com.mostafa.file.system.mapper.PermissionGroupMapper;
import com.mostafa.file.system.repository.PermissionGroupRepository;
import com.mostafa.file.system.service.PermissionGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PermissionGroupServiceImpl implements PermissionGroupService {
    private final PermissionGroupRepository permissionGroupRepository;
    private final PermissionGroupMapper permissionGroupMapper;

    public PermissionGroupServiceImpl(PermissionGroupRepository permissionGroupRepository,
                                      PermissionGroupMapper permissionGroupMapper) {
        this.permissionGroupRepository = permissionGroupRepository;
        this.permissionGroupMapper = permissionGroupMapper;
    }

    @Override
    public PermissionGroupDTO save(PermissionGroup permissionGroup) {
        return permissionGroupMapper.permissionGroupToPermissionGroupDTO(
                permissionGroupRepository.save(permissionGroup));
    }

    @Override
    public PermissionGroupDTO findById(Long id) {
        Optional<PermissionGroup> permissionGroup = permissionGroupRepository.findById(id);
        if (permissionGroup.isEmpty()) {
            log.error("PermissionGroup with id: {} could not be found!", id);
            throw new PermissionGroupNotFoundException("PermissionGroup with id " + id + " could not be found!");
        }
        return permissionGroupMapper.permissionGroupToPermissionGroupDTO(permissionGroup.get());
    }

    @Override
    public List<PermissionGroupDTO> findAll() {
        List<PermissionGroup> permissionGroups = permissionGroupRepository.findAll();
        if (permissionGroups.isEmpty()) {
            log.error("No PermissionGroups were found!");
            throw new PermissionGroupNotFoundException("No PermissionGroups were found!");
        }
        return permissionGroups.stream()
                .map(permissionGroupMapper::permissionGroupToPermissionGroupDTO)
                .collect(Collectors.toList());
    }
}
