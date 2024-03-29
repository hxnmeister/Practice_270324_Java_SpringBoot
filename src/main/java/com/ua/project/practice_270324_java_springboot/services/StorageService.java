package com.ua.project.practice_270324_java_springboot.services;

import com.ua.project.practice_270324_java_springboot.drivers.storages.LocalStorageDriver;
import com.ua.project.practice_270324_java_springboot.drivers.storages.StorageDriver;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {
    @Value("${storage.local.path}")
    private String localStoragePath;

    private StorageDriver storageDriver;

    @PostConstruct
    public void initialize() {
        storageDriver = new LocalStorageDriver(localStoragePath);
    }

    public String put(String bucketName, String fileName, MultipartFile file) {
        return storageDriver.put(bucketName, fileName, file);
    }
}
