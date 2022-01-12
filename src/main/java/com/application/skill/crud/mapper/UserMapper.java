package com.application.skill.crud.mapper;

import com.application.skill.crud.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author tangchao
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 获取用户信息根据用户id
     *
     * @param id 请求id
     * @return User 用户对象
     */
    User selectUserById(@Param("id") String id);
}
