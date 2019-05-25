package com.example.onlinedocumentsystem.utils;

import com.example.onlinedocumentsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class StringUtils {
    @Autowired
    private UserService userService;
    public static boolean  isExistNum(String str){
        boolean x = false;
        for(int i = 0 ; i < str.length() ; i ++){
            if(str.charAt(i) >= '0' && str.charAt(i) <= '9'){
                x = true;
                break;
            }
        }
        return x;
    }
    public static boolean isExistLower(String str){
        boolean x = false;
        for(int i = 0 ; i < str.length() ; i ++){
            if(str.charAt(i) >= 'a' && str.charAt(i) <= 'z'){
                x = true;
                break;
            }
        }
        return x;
    }
    public static boolean isExistUpper(String str){
        boolean x = false;
        for(int i = 0 ; i < str.length() ; i ++){
            if(str.charAt(i) >= 'A' && str.charAt(i) <= 'Z'){
                x = true;
                break;
            }
        }
        return x;
    }
    public static String getRegisterMessage(int x){
        String registerMessage;
        switch (x){
            case 0:
                registerMessage = "true";
                break;
            case 1:
                registerMessage = "邮箱已被占用";
                break;
            case 2:
                registerMessage = "用户名已被占用";
                break;
            case 3:
                registerMessage = "手机号已被占用";
                break;
            case 4:
                registerMessage = "密码长度不符";
                break;
            case 5:
                registerMessage = "密码需包含小写字母";
                break;
            case 6:
                registerMessage = "密码需包含大写字母";
                break;
            case 7:
                registerMessage = "密码需包含数字";
                break;
            default:
                registerMessage = "false";
        }
        return registerMessage;
    }
}
