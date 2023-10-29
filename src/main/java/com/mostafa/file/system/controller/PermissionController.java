package com.mostafa.file.system.controller;

import com.mostafa.file.system.entity.dto.PermissionDTO;
import com.mostafa.file.system.entity.jpa.Permission;
import com.mostafa.file.system.service.PermissionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/permissions")
public class PermissionController {
    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping
    public ResponseEntity<PermissionDTO> createPermission(@Valid @RequestBody Permission permission) {
        log.info("Creating permission for user: {}, with permission level: {}, and group id: {}",
                permission.getUserEmail(), permission.getPermissionLevel(), permission.getGroup());
        PermissionDTO createdPermission = permissionService.save(permission);
        log.info("Permission created with id: {}", createdPermission.getId());
        return ResponseEntity.ok(createdPermission);
    }

    @GetMapping("/{permissionId}")
    public ResponseEntity<PermissionDTO> findPermission(@PathVariable Long permissionId) {
        PermissionDTO permissionResponse = permissionService.findById(permissionId);
        log.info("Returning permission with id: {}", permissionResponse.getId());
        return ResponseEntity.ok(permissionResponse);
    }

    @GetMapping
    public ResponseEntity<List<PermissionDTO>> findAllPermissions() {
        List<PermissionDTO> permissions = permissionService.findAll();
        log.info("Returning all permissions");
        return ResponseEntity.ok(permissions);
    }
}
