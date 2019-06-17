package com.example.onlinedocumentsystem.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import com.example.onlinedocumentsystem.utils.FileUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
@Service
public class FileServiceImpl implements FileService{
    @Value("${file.filepath}")
     private String filepath;
Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
//会将上传信息存入此处，根据需求自行调整
              List<String> fileName =new ArrayList<String>();
             //必须注入，不可以创建对象，否则配置文件引用的路径属性为null
             @Autowired
     FileUtil fileUtil;

             @Override
     public Map<String, Object> fileUpload(MultipartFile[] file) {

                 HttpServletRequest request = null;
                 HttpServletResponse response;

                 resultMap.put("status", 400);
                 if(file!=null&&file.length>0){
                         //组合image名称，“;隔开”
             //            List<String> fileName =new ArrayList<String>();
                         PrintWriter out = null;
                         //图片上传

                         try {
                             for (int i = 0; i < file.length; i++) {
                                 if (!file[i].isEmpty()) {
                                     //上传文件，随机名称，","分号隔开
                                                 fileName.add(fileUtil.uploadImage(request, filepath+"upload/"+ fileUtil.formateString(new Date())+"/", file[i], true)+fileUtil.getOrigName());
                                             }
                                     }

}catch (Exception e){
                         e.printStackTrace();}
                 }
return resultMap;
             }
}