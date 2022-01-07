package com.example.skill.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Max(32)
    @Min(0)
    private String id;
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotNull(message = "phoneNumBer不能为空")
    @Valid
    private String phoneNumBer;
    @Max(3)
    @Min(0)
    @NotNull
    private Integer sex;

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
