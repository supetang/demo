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

    public String userNameSet(String userName){
        this.userName = userName;
        return userName;
    }

    public Integer sexSet(Integer sex){
        this.sex = sex;
        return sex;
    }

    public String phoneNumBerSet(String phoneNumBer){
        this.phoneNumBer = phoneNumBer;
        return phoneNumBer;
    }
}
