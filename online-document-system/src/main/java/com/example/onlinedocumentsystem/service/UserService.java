package com.example.onlinedocumentsystem.service;

import com.aliyuncs.exceptions.ClientException;
import com.example.onlinedocumentsystem.dao.UserRepository;
import com.example.onlinedocumentsystem.domain.User;
import com.example.onlinedocumentsystem.utils.AliyunSmsUtils;
import com.example.onlinedocumentsystem.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.onlinedocumentsystem.utils.StringUtils;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    //判断用户名、电话号码、邮箱是否已被注册
    public  Boolean verifyByMail(String mail){
        if(!userRepository.findByMail(mail).isEmpty()) return false;
        else return true;
    }
    public  Boolean verifyByPhoneNumber(String phoneNumber){
        if(!userRepository.findByPhoneNumber(phoneNumber).isEmpty()) return false;
        else return true;
    }
    public  Boolean verifyByUsername(String username){
        if(!userRepository.findByUsername(username).isEmpty()) return false;
        else return true;
    }
    //判断用于登录的邮箱、电话号码、用户名以及对应的密码是否正确
    public  Boolean verifyByMailAndPassword(String mail,String password){
        if(userRepository.findByMailAndPassword(mail,password).isEmpty()) return false;
        else return true;
    }
    public  Boolean verifyByPhoneNumberAndPassword(String phoneNumber,String password){
        if(userRepository.findByPhoneNumberAndPassword(phoneNumber,password).isEmpty()) return false;
        else return true;
    }
    public  Boolean verifyByUsernameAndPassword(String username,String password){
        if(userRepository.findByUsernameAndPassword(username,password).isEmpty()) return false;
        else return true;
    }
    //登录失败返回null，成功则返回对应的user对象
    public  User loginByMail(String mail,String password){
        User user = null;
        if(verifyByMailAndPassword(mail,password)) {
            user = userRepository.findByMailAndPassword(mail,password).get(0);
        }
        return user;
    }
    public  User loginByPhoneNumber(String phoneNumber,String password){
        User user = null;
        if(verifyByPhoneNumberAndPassword(phoneNumber,password)){
            user = userRepository.findByPhoneNumber(phoneNumber).get(0);
        }
        return user;
    }
    public  User loginByUsername(String username,String password){
        User user = null;
        if(verifyByUsernameAndPassword(username,password)){
            user = userRepository.findByMailAndPassword(username,password).get(0);
        }
        return user;
    }
    //注册
    public String register(User user){
        int x = 0;
        //判断用户名、电话号码、邮箱未被使用
        if(user.getMail()!= null && !verifyByMail(user.getMail())) x = 1;
        else if(!verifyByUsername(user.getUsername())) x = 2;
        else if(user.getPhoneNumber()!= null && !verifyByPhoneNumber(user.getPhoneNumber())) x = 3;
        //判断密码是否符合格式
        else if(user.getPassword().length() < 6 || user.getPassword().length() > 12) x= 4;
        else if(!StringUtils.isExistLower(user.getPassword())) x = 5;
        else if (!StringUtils.isExistUpper(user.getPassword())) x= 6;
        else if (!StringUtils.isExistNum(user.getPassword()))    x = 7;
        return StringUtils.getRegisterMessage(x);
    }
    //发送邮件
    public void sendMail(User user){
        try {
            int code = MailUtils.sendMail(user.getMail());
            user.setActivationCode(code);
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public User verifyByMail(int code){
        User user = null;
        if(!userRepository.findByActivationCode(code).isEmpty()){
            user = userRepository.findByActivationCode(code).get(0);
            user.setActivationCode(1);
            userRepository.save(user);
        }
        return user;
    }
    public String sendMessage(String phoneNumber){
        String code = null;
        try {
            code = AliyunSmsUtils.createVerifiedMessage(phoneNumber);
        } catch (ClientException e) {
            e.printStackTrace();
        }finally {
            return code;
        }
    }
}
