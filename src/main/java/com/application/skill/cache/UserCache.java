package com.application.skill.cache;

import com.application.skill.crud.mapper.UserMapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
@Component
@Slf4j
/*
* tangchao
* */
public class UserCache {

    @Autowired
    private UserMapper userMapper;

    public LoadingCache loadCacheUser(String id){
        return  CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build(
                        new CacheLoader() {

                            @Override
                            public Object load(Object o) throws Exception {
                                return userMapper.selectById("1");
                            }
                        }
                );
    }
}
