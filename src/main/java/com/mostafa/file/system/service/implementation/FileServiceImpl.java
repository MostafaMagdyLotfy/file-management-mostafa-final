package com.mostafa.file.system.service.implementation;

import com.mostafa.file.system.entity.dto.FileDTO;
import com.mostafa.file.system.entity.dto.FileResponseDTO;
import com.mostafa.file.system.entity.jpa.File;
import com.mostafa.file.system.exception.FileNotFoundException;
import com.mostafa.file.system.mapper.FileMapper;
import com.mostafa.file.system.repository.FileRepository;
import com.mostafa.file.system.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    private final FileMapper fileMapper;

    public FileServiceImpl(FileRepository fileRepository, FileMapper fileMapper) {
        this.fileRepository = fileRepository;
        this.fileMapper = fileMapper;
    }

    @Override
    public FileResponseDTO save(File file) {
        return fileMapper.fileToFileResponseDTO(fileRepository.save(file));
    }


    @Override
    public FileDTO findById(Long id) {
        Optional<File> fileResponse = fileRepository.findById(id);
        if (fileResponse.isEmpty()) {
            log.error("File with id: {} could not be found!", id);
            throw new FileNotFoundException("File with id " + id + " could not be found!");
        }
        return fileMapper.fileToFileDTO(fileResponse.get());
    }

    @Override
    public List<FileDTO> findAll() {
        List<File> files = fileRepository.findAll();
        if (files.isEmpty()) {
            log.error("No Files were found!");
            throw new FileNotFoundException("No Files were found!");
        }
        return files.stream()
                .map(fileMapper::fileToFileDTO)
                .collect(Collectors.toList());
    }
}
