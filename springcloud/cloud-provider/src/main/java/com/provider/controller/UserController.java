package com.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.provider.domain.User;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping(value = "/getUser/{id}")
    public User getUser(@PathVariable Long id){
        User user=new User();
        user.setId(id);
        user.setName("张三");
        user.setAge(18);
        return user;
    }
    @GetMapping(value = "/getName")
    public String getName(){
        return "张三";
    }
}
