package com.example.onlinedocumentsystem.service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
public interface FileService {
    Map<String,Object> fileUpload(MultipartFile[] file);
}
