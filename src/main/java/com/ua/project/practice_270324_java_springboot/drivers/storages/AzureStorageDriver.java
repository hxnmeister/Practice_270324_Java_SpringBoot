package com.ua.project.practice_270324_java_springboot.drivers.storages;

import org.springframework.web.multipart.MultipartFile;

public class AzureStorageDriver implements StorageDriver {
    @Override
    public String put(String bucketName, String fileName, MultipartFile file) {
        return null;
    }
}
