package com.example.fakerwithauthorization.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {
    @Value("${minio.access-key}")
    String accesKey;
    @Value("${minio.secret-key}")
    String secretKey;
    @Value("${minio.bucket}")
    String bucket;
    @Value("${minio.url}")
    String minioUrl;

    @Bean
    public MinioClient generateMinioClient() {
        try {
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(minioUrl)
                    .credentials(accesKey, secretKey)
                    .build();

            return minioClient;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
