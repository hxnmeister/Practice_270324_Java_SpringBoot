package com.ua.project.practice_270324_java_springboot.services;

import com.ua.project.practice_270324_java_springboot.drivers.storages.LocalStorageDriver;
import com.ua.project.practice_270324_java_springboot.drivers.storages.MinioStorageDriver;
import com.ua.project.practice_270324_java_springboot.drivers.storages.StorageDriver;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {
    @Value("${storage.local.path}")
    private String localStoragePath;

    @Value("${minio.url}")
    private String minioUrl;

    @Value("${minio.user}")
    private String minioUser;

    @Value("${minio.password}")
    private String minioPassword;

    private StorageDriver storageDriver;

    @PostConstruct
    public void initialize() {
//        storageDriver = new LocalStorageDriver(localStoragePath);
        storageDriver = new MinioStorageDriver(minioUrl, minioUser, minioPassword);
    }

    public String put(String bucketName, String fileName, MultipartFile file) {
        return storageDriver.put(bucketName, fileName, file);
    }
}
