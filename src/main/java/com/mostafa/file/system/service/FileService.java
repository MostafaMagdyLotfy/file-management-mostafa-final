package com.mostafa.file.system.service;

import com.mostafa.file.system.entity.dto.FileDTO;
import com.mostafa.file.system.entity.dto.FileResponseDTO;
import com.mostafa.file.system.entity.jpa.File;

import java.util.List;

public interface FileService {
    FileResponseDTO save(File item);

    FileDTO findById(Long id);

    List<FileDTO> findAll();
}
