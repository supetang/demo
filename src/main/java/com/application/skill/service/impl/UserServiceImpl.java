package com.application.skill.service.impl;

import com.application.skill.mapper.UserMapper;
import com.application.skill.model.User;
import com.application.skill.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public  User user() {
        return userMapper.selectById("123456");
    }
}
