package com.application.skill.crud.controller;

import com.application.skill.cache.UserCache;
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
    
    @Autowired
    private UserCache userCache;

    @RequestMapping("/index")
    public String sayHello(){
        User user = userMapper.selectById("1");
        System.out.println(user);
        return "index";
    }

    @RequestMapping("/index1")
    public Result<User> result() {
        User user = userMapper.selectById("1");
        Result<User> result = new Result<>();
        result.setData(user);
        result.setCode(1);
        result.setMessage("success");
        return result;
    }

    @RequestMapping("/index2")
    public Result<User> result2() throws Exception{
        System.out.println(userCache.loadCacheUser("1").toString());
        System.out.println(userCache.loadCacheUser("1").get("1"));
        Result<User> result = new Result<>();
        result.setData(null);
        result.setCode(1);
        result.setMessage("success");
        return result;
    }
}
