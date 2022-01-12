/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.application.skill.crud.service;

import com.application.skill.crud.model.User;

/**
 * @author tangchao
 */
public interface UserService {

    /**
     * 获取用户信息根据用户
     * <p>
     * @return User 用户对象
     */
    public User user();

    /**
     * 获取用户信息根据用户id
     * <p>
     * @param id 请求id
     * @return User 用户对象
     */
    User selectUserById(String id);
}
