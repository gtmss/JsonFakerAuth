package com.example.fakerwithauthorization.services;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.messages.Bucket;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Service
public class MinioAdapterService {

    private final MinioClient minioClient;
    String defaultBucketName = "mesio";

    public MinioAdapterService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public List<Bucket> getAllBuckets() {
        try {
            return minioClient.listBuckets();
        } catch (Exception e) {
            throw new RuntimeException("Error occured while file upload", e);
        }
    }

    public void uplaodFile(MultipartFile file) {
        try {
            minioClient.putObject(
                    PutObjectArgs
                            .builder()
                            .bucket(defaultBucketName)
                            .object("test")
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
