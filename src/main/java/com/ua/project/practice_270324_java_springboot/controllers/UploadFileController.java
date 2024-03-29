package com.ua.project.practice_270324_java_springboot.controllers;

import com.ua.project.practice_270324_java_springboot.drivers.storages.DriverEnum;
import com.ua.project.practice_270324_java_springboot.responces.UploadFileResponse;
import com.ua.project.practice_270324_java_springboot.services.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/files")
@AllArgsConstructor
public class UploadFileController {
    private final StorageService storageService;

    @PostMapping
    public ResponseEntity<UploadFileResponse> uploadFile(@RequestParam("file")MultipartFile file) {
        final String bucketName = "avatars";
        UploadFileResponse response = new UploadFileResponse();

        if(file.isEmpty()) {
            return ResponseEntity.badRequest().body(response);
        }

        response.setFileName(file.getOriginalFilename());
        response.setContentType(file.getContentType());
        response.setSize(file.getSize());

        response.setFileUrl(storageService.disk(DriverEnum.LOCAL).put(bucketName, file.getOriginalFilename(), file));

        return ResponseEntity.ok(response);
    }
}
