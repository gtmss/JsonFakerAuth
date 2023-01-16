package com.example.fakerwithauthorization.controllers;

import com.example.fakerwithauthorization.services.MinioAdapterService;
import io.minio.MinioClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@RestController
@RequestMapping("api/minio")
public class MinioController {

    private final MinioAdapterService minioAdapterService;

    public MinioController(MinioAdapterService minioAdapterService) {
        this.minioAdapterService = minioAdapterService;

    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> uploadFile(@ModelAttribute MultipartFile file) {
        minioAdapterService.uplaodFile(file);
        return ResponseEntity.ok("Ok");
    }
}
