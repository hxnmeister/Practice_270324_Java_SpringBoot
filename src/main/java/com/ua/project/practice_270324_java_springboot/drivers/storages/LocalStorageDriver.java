package com.ua.project.practice_270324_java_springboot.drivers.storages;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@AllArgsConstructor
public class LocalStorageDriver implements StorageDriver {
    private String localStoragePath;

    @Override
    public String put(String bucketName, String fileName, MultipartFile file) {
        try {
            final Path UPLOAD_DIR_PATH = Paths.get(localStoragePath + File.separator + bucketName);
            final Path FILE_PATH = Paths.get(UPLOAD_DIR_PATH + File.separator + file.getOriginalFilename());
            File directory = new File(UPLOAD_DIR_PATH.toUri());
            byte[] fileBytes = file.getBytes();

            if(!directory.exists()) {
                directory.mkdirs();
            }

            Files.write(FILE_PATH, fileBytes);
            return FILE_PATH.toString();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            return "";
        }
    }
}
