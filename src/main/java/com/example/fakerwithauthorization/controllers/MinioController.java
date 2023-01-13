package com.example.fakerwithauthorization.controllers;

import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("api/minio")
public class MinioController {

    private final MinioClient minioClient;

    public MinioController(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @GetMapping
    ResponseEntity<List<String>> listBuckets() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        List<String> bucketList = minioClient.listObjects(ListObjectsArgs.builder().build())
        return ResponseEntity.ok().body(bucketList.stream().map(bucket -> bucket.name()).toList());
    }
}
