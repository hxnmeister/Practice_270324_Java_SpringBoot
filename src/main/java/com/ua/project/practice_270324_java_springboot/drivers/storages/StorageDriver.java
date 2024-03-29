package com.ua.project.practice_270324_java_springboot.drivers.storages;

import org.springframework.web.multipart.MultipartFile;

public interface StorageDriver {
    String put(String bucketName, String fileName, MultipartFile file);
}
