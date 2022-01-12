package com.application.skill.crud.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author tangchao
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("t_user")
public class User {

    @Max(32)
    @Min(0)
    @TableId("id")
    private String id;

    @TableField("userName")
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @TableField("phoneNumBer")
    @NotNull(message = "phoneNumBer不能为空")
    @Valid
    private String phoneNumBer;

    @Max(3)
    @Min(0)
    @NotNull
    @TableField("sex")
    private Integer sex;

    @TableField("isDelete")
    private Integer isDelete;

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
