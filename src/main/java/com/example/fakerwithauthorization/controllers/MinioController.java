package com.example.fakerwithauthorization.controllers;

import com.example.fakerwithauthorization.services.MinioAdapterService;
import io.minio.MinioClient;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;

import static org.springframework.web.servlet.HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE;

@RestController
@RequestMapping("api/minio")
public class MinioController {

    private final MinioAdapterService minioAdapterService;

    public MinioController(MinioAdapterService minioAdapterService) {
        this.minioAdapterService = minioAdapterService;

    }

    @GetMapping("objects/**")
    public ResponseEntity<Object> getFile(HttpServletRequest request) throws IOException {
        String pattern = (String) request.getAttribute(BEST_MATCHING_PATTERN_ATTRIBUTE);
        String filename = new AntPathMatcher().extractPathWithinPattern(pattern, request.getServletPath());
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(IOUtils.toByteArray(minioAdapterService.getObject(filename)));
    }

    @PostMapping
    public ResponseEntity<Object> uploadFile(@ModelAttribute MultipartFile file) {
        System.out.println(minioAdapterService.getAllBuckets());
        minioAdapterService.uploadFile(file);
        return ResponseEntity.ok("Ok");
    }

    @DeleteMapping("/{filename}")
    public ResponseEntity deleteObject(@PathVariable String filename) {
        minioAdapterService.deleteFile(filename);
        return ResponseEntity.ok("Successful deleted");
    }


}
