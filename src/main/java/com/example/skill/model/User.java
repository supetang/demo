package com.example.skill.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class User {

    private String userName;

    private String phoneNumBer;

    private Integer sex;

    public User() {

    }
    
    /**
     *  java实现调用链路方式
     * */
    public User userNameSet(String userName){
        this.userName = userName;
        return this;
    }

    public User sexSet(Integer sex){
        this.sex = sex;
        return this;
    }

    public User phoneNumBerSet(String phoneNumBer){
        this.phoneNumBer = phoneNumBer;
        return this;
    }
}
