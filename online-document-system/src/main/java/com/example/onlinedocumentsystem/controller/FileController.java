package com.example.onlinedocumentsystem.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.onlinedocumentsystem.domain.FileMessage;
import com.example.onlinedocumentsystem.service.FileService;
import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;
import com.example.onlinedocumentsystem.domain.User;
@RestController
@EnableAutoConfiguration
public class FileController {
    @Autowired
    private FileService fileService;
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String uploadData(@RequestParam("file") MultipartFile file){
        SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf('.'));
        String newFileName = new Date().getTime() + suffix;
        String path = "C:/Users/Administrator/Desktop/test/";
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
    @RequestMapping(value = "/downloadFile",method = RequestMethod.POST)
    public String downloadFile(HttpServletResponse response){
        String filePath = "C:/Users/Administrator/Desktop/" + "test" +"/";
        String fileName = "beverage-blank-break-997719.jpg";
        File file = new File(filePath + fileName);
        if(file.exists()){
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream outputStream = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                return "下载成功";
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "下载失败";
    }
    @RequestMapping(value = "/getAllFile",method = RequestMethod.POST)
    public String getAllFile(@RequestBody User user){
        String uploadPerson = user.getUsername();
        if(null != fileService.getFileMessageByUploadPerson(uploadPerson)){
            return  JSONArray.toJSONString(fileService.getFileMessageByUploadPerson(uploadPerson));
         }
        else
            return "暂无文件";
    }
    @RequestMapping(value = "/changeName",method = RequestMethod.POST)
    public String changeName(@RequestBody FileMessage fileMessage){
        fileService.changeName(fileMessage);
        return "修改成功！";
    }
}
