package com.example.onlinedocumentsystem.utils;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
@Component
@Data
public class FileUtil {
    private  String origName;
   // private final String targetFile = ""
    public String uploadImage(HttpServletRequest request, String path_deposit, MultipartFile file,boolean isRandomName){
        try{
         //   String fileSrc = path_deposit;
           File targetFile=new File(path_deposit,origName);
            file.transferTo(targetFile);
            String[] typeImg = {"gif","png","jpg","docx","doc","pdf"};
            if(file != null){
                origName = file.getOriginalFilename();
                System.out.println("上传文件名称：" + origName);
                String type = origName.indexOf("." ) != -1?origName.substring(origName.lastIndexOf(".")+1, origName.length()):null;
                if (type != null){
                    boolean booIsType = false;
                    for (int i = 0; i < typeImg.length; i++) {
                        if (typeImg[i].equals(type.toLowerCase())) {
                            booIsType = true;
                        }
                    }
                    if(booIsType){
                        String fileSrc = path_deposit;
                        if(isRandomName){
                        }
                    }
                }
            }
            return path_deposit;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public String formateString(Date date){
                 SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
                 String list[] = dateFormater.format(date).split("-");
                 String result = "";
                 for (int i=0;i<list.length;i++) {
                     result += list[i];
                     }
               return result;
             }
}
