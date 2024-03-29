package com.ua.project.practice_270324_java_springboot.services;

import com.ua.project.practice_270324_java_springboot.drivers.storages.DriverEnum;
import com.ua.project.practice_270324_java_springboot.drivers.storages.LocalStorageDriver;
import com.ua.project.practice_270324_java_springboot.drivers.storages.MinioStorageDriver;
import com.ua.project.practice_270324_java_springboot.drivers.storages.StorageDriver;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

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
    private final Map<DriverEnum, StorageDriver> DRIVERS = new HashMap<>();

    @PostConstruct
    public void initialize() {
        DRIVERS.put(DriverEnum.LOCAL, new LocalStorageDriver(localStoragePath));
        DRIVERS.put(DriverEnum.MINIO, new MinioStorageDriver(minioUrl, minioUser, minioPassword));

        storageDriver = DRIVERS.get(DriverEnum.MINIO);
    }

    public StorageDriver disk(DriverEnum disk) {
        return DRIVERS.get(disk);
    }

    public String put(String bucketName, String fileName, MultipartFile file) {
        return storageDriver.put(bucketName, fileName, file);
    }
}
