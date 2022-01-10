package com.application.skill.crud.controller;

import com.application.skill.common.Result;
import com.application.skill.crud.mapper.UserMapper;
import com.application.skill.crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/index")
    public String sayHello(){
        User user = userMapper.selectById("1");
        System.out.println(user);
        return "index";
    }

    @RequestMapping("/index1")
    public Result result() {
        User user = userMapper.selectById("1");
        Result result = new Result();
        result.setData(user);
        result.setCode(1);
        result.setMessage("success");
        return result;
    }
}
