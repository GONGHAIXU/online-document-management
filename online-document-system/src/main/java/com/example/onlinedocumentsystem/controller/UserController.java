package com.example.onlinedocumentsystem.controller;

import com.alibaba.fastjson.JSON;
import com.example.onlinedocumentsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;
import com.example.onlinedocumentsystem.domain.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@EnableAutoConfiguration
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/loginByMail",method = RequestMethod.POST)
    String loginByMail(@RequestBody User user){
        JSONObject result = new JSONObject();
        if(userService.loginByMail(user.getMail(), user.getPassword()) != null) {
            result = (JSONObject) JSON.toJSON(userService.loginByMail(user.getMail(), user.getPassword()));
            result.put("result","true");
        }
        else{
            result.put("result","false");
        }
        return result.toJSONString();
    }
    @RequestMapping(value = "/loginByPhoneNumber",method = RequestMethod.POST)
    String loginByPhoneNumber(@RequestBody User user){
        JSONObject result = new JSONObject();
        if(userService.loginByPhoneNumber(user.getPhoneNumber(), user.getPassword()) != null) {
            result = (JSONObject) JSON.toJSON(userService.loginByPhoneNumber(user.getPhoneNumber(), user.getPassword()));
            result.put("result","true");
        }
        else{
            result.put("result","false");
        }
        return result.toJSONString();
    }
    @RequestMapping(value = "/loginByUsername",method = RequestMethod.POST)
    String loginByUsername(@RequestBody User user){
        JSONObject result = new JSONObject();
        if(null != userService.loginByUsername(user.getUsername(), user.getPassword())) {
            result = (JSONObject) JSON.toJSON(userService.loginByUsername(user.getUsername(), user.getPassword()));
            result.put("result","true");
        }
        else{
            result.put("result","false");
        }
        return result.toJSONString();
    }
    @RequestMapping(value = "/registerByMail",method = RequestMethod.POST)
    String registerByMail(@RequestBody User user){
        JSONObject result = new JSONObject();
        String  registerMessage = userService.register(user);
        if(!registerMessage.equals("邮箱已被占用"))
            userService.sendMail(user);
        result.put("result",registerMessage);
        return result.toJSONString();
    }
    @RequestMapping(value = "/registerByPhoneNumber",method = RequestMethod.POST)
    String registerByPhoneNumber(HttpServletRequest request,@RequestBody User user){
        JSONObject result = new JSONObject();
        String  registerMessage = userService.register(user);
        if(registerMessage.equals("true")){
            String code = userService.sendMessage(user.getPhoneNumber());
            HttpSession session = request.getSession();
            session.setAttribute("code",code);
            session.setMaxInactiveInterval(60*30);
        }
        result.put("result",registerMessage);
        return result.toJSONString();
    }
    @RequestMapping(value = "/verify",method = RequestMethod.POST)
    String verify(@RequestBody User user){
        JSONObject result = new JSONObject();
        if (null != userService.verifyByCode(user.getActivationCode())){
            result = (JSONObject) JSON.toJSON(user);
            result.put("result","true");
        }
        else {
            result.put("result","false");
        }
        return result.toJSONString();
    }
}
