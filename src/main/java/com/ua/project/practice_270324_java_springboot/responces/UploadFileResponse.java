package com.ua.project.practice_270324_java_springboot.responces;

import lombok.Data;

@Data
public class UploadFileResponse {
    private long size;
    private String fileName;
    private String fileUrl;
    private String contentType;
}
