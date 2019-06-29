package com.example.onlinedocumentsystem.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.UUID;

@RestController
@EnableAutoConfiguration
public class FileController {
    JSONObject result = new JSONObject();
    SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
    @RequestMapping(value = "/import",method = RequestMethod.POST)
    public String importData(@RequestParam("file") MultipartFile file){
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf('.'));
        String newFileName = new Date().getTime() + suffix;
        String path = "C:/Users/Administrator/Desktop/test";
        File newFile = new File(path + newFileName);
        try {
            file.transferTo(newFile);
            return "成功";
        }
        catch (Exception e){
            e.printStackTrace();
            return "失败";
        }
    }
}
