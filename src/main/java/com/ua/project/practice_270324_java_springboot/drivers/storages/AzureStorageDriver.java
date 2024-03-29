package com.ua.project.practice_270324_java_springboot.drivers.storages;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class AzureStorageDriver implements StorageDriver {
    private final BlobServiceClient blobServiceClient;

    public AzureStorageDriver(String connectionString) {
        blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();
    }

    @Override
    public String put(String containerName, String fileName, MultipartFile file) {
        BlobClient blobClient = blobServiceClient.createBlobContainerIfNotExists(containerName).getBlobClient(fileName);

        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(file.getBytes())) {
            blobClient.upload(inputStream, file.getSize(), true);
            return fileName;
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return "";
    }
}
