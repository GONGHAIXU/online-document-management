package com.example.onlinedocumentsystem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.onlinedocumentsystem.service.UserService;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.onlinedocumentsystem.domain.User;
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
        if(null != userService.loginByUsername(user.getMail(), user.getPassword())) {
            result = (JSONObject) JSON.toJSON(userService.loginByUsername(user.getMail(), user.getPassword()));
            result.put("result","true");
        }
        else{
            result.put("result","false");
        }
        return result.toJSONString();
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    String registerByMail(@RequestBody User user){
        JSONObject result = new JSONObject();
        String  registerMessage = userService.register(user);
        userService.sendMail(user);
        result.put("result",registerMessage);
        return result.toJSONString();
    }
    @RequestMapping(value = "/verify",method = RequestMethod.GET)
    String verify(@RequestParam int code){
        JSONObject result = new JSONObject();
        User user;
        if (null != userService.verify(code)){
            user = userService.verify(code);
            System.out.println(user.toString());
            result = (JSONObject) JSON.toJSON(user);
            result.put("result","true");
        }
        else {
            result.put("result","false");
        }
        return result.toJSONString();
    }
}
