package com.mostafa.file.system.controller;

import com.mostafa.file.system.entity.dto.PermissionGroupDTO;
import com.mostafa.file.system.entity.jpa.PermissionGroup;
import com.mostafa.file.system.service.PermissionGroupService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/permission-groups")
public class PermissionGroupController {
    private final PermissionGroupService permissionGroupService;

    public PermissionGroupController(PermissionGroupService permissionGroupService) {
        this.permissionGroupService = permissionGroupService;
    }

    @PostMapping
    public ResponseEntity<PermissionGroupDTO> createPermissionGroup(@Valid @RequestBody PermissionGroup permissionGroup) {
        log.info("Creating permissionGroup with name: {}", permissionGroup.getGroupName());
        PermissionGroupDTO createdPermissionGroup = permissionGroupService.save(permissionGroup);
        log.info("PermissionGroup created with id: {}", createdPermissionGroup.getId());
        return ResponseEntity.ok(createdPermissionGroup);
    }

    @GetMapping("/{permissionGroupId}")
    public ResponseEntity<PermissionGroupDTO> findPermissionGroup(@PathVariable Long permissionGroupId) {
        PermissionGroupDTO permissionGroupResponse = permissionGroupService.findById(permissionGroupId);
        log.info("Returning permissionGroup with id: {}", permissionGroupResponse.getId());
        return ResponseEntity.ok(permissionGroupResponse);
    }

    @GetMapping
    public ResponseEntity<List<PermissionGroupDTO>> findAllPermissionGroups() {
        List<PermissionGroupDTO> permissionGroups = permissionGroupService.findAll();
        log.info("Returning all permissionGroups");
        return ResponseEntity.ok(permissionGroups);
    }
}
