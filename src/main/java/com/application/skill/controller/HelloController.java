package com.application.skill.controller;

import com.application.skill.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/index")
    public String sayHello(){
//        User user = userMapper.selectById("1");
//        System.out.println(user);
        return "index";
    }
}
