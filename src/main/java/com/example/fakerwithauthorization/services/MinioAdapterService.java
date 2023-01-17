package com.example.fakerwithauthorization.services;

import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
public class MinioAdapterService {

    private Logger logger = LoggerFactory.getLogger(MinioAdapterService.class);

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

    public InputStream getObject(String filename) {
        InputStream stream;
        try {
            stream = minioClient.getObject(
                    GetObjectArgs
                            .builder()
                            .bucket(defaultBucketName)
                            .object(filename)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return stream;
    }

    public void uploadFile(MultipartFile file) {
        try {
            minioClient.putObject(
                    PutObjectArgs
                            .builder()
                            .bucket(defaultBucketName)
                            .object(file.getOriginalFilename())
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void uploadMultiple(MultipartFile[] files) {
        logger.info(files.toString());
        List<MultipartFile> fileList = Arrays.stream(files).toList();
        List<SnowballObject> objects = new ArrayList<>();

        fileList.forEach(multipartFile -> {
            try {
                objects.add(
                        new SnowballObject(
                        multipartFile.getOriginalFilename(),
                        new ByteArrayInputStream(multipartFile.getBytes()),
                        multipartFile.getSize(),
                        null));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        try {
            minioClient.uploadSnowballObjects(
                    UploadSnowballObjectsArgs
                            .builder()
                            .bucket(defaultBucketName)
                            .objects(objects)
                            .compression(true)
                            .build()
            );
        } catch (ErrorResponseException e) {
            throw new RuntimeException(e);
        } catch (ServerException e) {
            throw new RuntimeException(e);
        } catch (InsufficientDataException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (InvalidResponseException e) {
            throw new RuntimeException(e);
        } catch (XmlParserException e) {
            throw new RuntimeException(e);
        } catch (InternalException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteFile(String file) {
        List<DeleteObject> objects = new LinkedList<>();
        objects.add(new DeleteObject(file));

        Iterable<Result<DeleteError>> results = minioClient.removeObjects(
                RemoveObjectsArgs.builder().bucket(defaultBucketName).objects(objects).build()
        );

        for (Result<DeleteError> result : results) {
            DeleteError error = null;
            try {
                error = result.get();
            } catch (ErrorResponseException e) {
                throw new RuntimeException(e);
            } catch (InsufficientDataException e) {
                throw new RuntimeException(e);
            } catch (InternalException e) {
                throw new RuntimeException(e);
            } catch (InvalidKeyException e) {
                throw new RuntimeException(e);
            } catch (InvalidResponseException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (ServerException e) {
                throw new RuntimeException(e);
            } catch (XmlParserException e) {
                throw new RuntimeException(e);
            }

        }
        return true;
    }


}
