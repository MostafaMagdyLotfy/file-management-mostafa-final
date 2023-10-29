package com.mostafa.file.system.repository;

import com.mostafa.file.system.entity.jpa.PermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionGroupRepository extends JpaRepository<PermissionGroup, Long> {
}
