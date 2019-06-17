package com.example.onlinedocumentsystem.controller;

import com.example.onlinedocumentsystem.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

@RestController
@EnableAutoConfiguration
public class FileController {
    @Autowired
    private FileService fileService;
    @RequestMapping(value = "/UpLoadImage")
    public Map<String,Object> fileUpload(@RequestParam("file") MultipartFile[] file) throws Exception {
                 Map<String, Object> fileUpload = fileService.fileUpload(file);
                 return fileUpload;
             }
}
