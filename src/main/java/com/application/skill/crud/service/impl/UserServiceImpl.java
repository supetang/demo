package com.application.skill.crud.service.impl;

import com.application.skill.crud.mapper.UserMapper;
import com.application.skill.crud.model.User;
import com.application.skill.crud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author tangchao
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public  User user() {
        return userMapper.selectById("123456");
    }

    @Override
    public User selectUserById(String id) {
        return userMapper.selectUserById(id);
    }
}
