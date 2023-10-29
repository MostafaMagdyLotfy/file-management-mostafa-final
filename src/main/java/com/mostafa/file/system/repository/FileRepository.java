package com.mostafa.file.system.repository;

import com.mostafa.file.system.entity.jpa.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
