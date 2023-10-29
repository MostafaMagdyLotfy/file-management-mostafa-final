package com.mostafa.file.system.controller;

import com.mostafa.file.system.entity.dto.FileDTO;
import com.mostafa.file.system.entity.dto.FileResponseDTO;
import com.mostafa.file.system.entity.jpa.File;
import com.mostafa.file.system.entity.jpa.Item;
import com.mostafa.file.system.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/files")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<FileResponseDTO> createFile(@RequestParam("file") MultipartFile uploadedFile,
                                                      @RequestParam("item") Long itemId) {
        System.out.println("item id " + itemId);
        log.info("Creating file");
        File file = new File();
        try {
            file.setBinaryData(uploadedFile.getBytes());
        } catch (IOException e) {
            log.error("Error creating file");
            throw new RuntimeException(e);
        }
        file.setItem(new Item(itemId));
        FileResponseDTO createdFile = fileService.save(file);

        return ResponseEntity.ok(createdFile);
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
        FileDTO file = fileService.findById(fileId);
        log.info("Returning file with id: {}", file.getId());
        return ResponseEntity.ok().
                header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + file.getId() + "\"").
                body(new ByteArrayResource(file.getBinaryData()));
    }


    @GetMapping
    public ResponseEntity<List<FileDTO>> findAllFiles() {
        List<FileDTO> files = fileService.findAll();
        log.info("Returning all files");
        return ResponseEntity.ok(files);
    }
}
