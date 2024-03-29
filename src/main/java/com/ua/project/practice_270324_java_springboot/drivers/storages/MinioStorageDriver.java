package com.ua.project.practice_270324_java_springboot.drivers.storages;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MinioStorageDriver implements StorageDriver {
    private MinioClient minioClient;

    public MinioStorageDriver(String minioUrl, String user, String password) {
        minioClient = MinioClient
                .builder()
                .endpoint(minioUrl)
                .credentials(user, password)
                .build();
    }

    @Override
    public String put(String bucketName, String fileName, MultipartFile file) {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(file.getBytes())) {
            final int PART_SIZE = -1;

            createBucketIfNotExists(bucketName);

            minioClient.putObject(PutObjectArgs
                    .builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .stream(byteArrayInputStream, byteArrayInputStream.available(), PART_SIZE)
                    .contentType(file.getContentType())
                    .build());

            return fileName;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "";
    }

    private void createBucketIfNotExists(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        if(!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }
}
