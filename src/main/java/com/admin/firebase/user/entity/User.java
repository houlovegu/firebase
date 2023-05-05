package com.admin.firebase.user.entity;

import com.admin.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 用户表
 * @TableName user
 */
@TableName(value ="user")
@ApiModel
@Data
public class User extends BaseEntity {
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty("id")
    private Long id;

//    /**
//     * 用户名
//     */
//    @ApiModelProperty("用户名")
//    private String userName;

    /**
     * 邮箱
     */
    @NotEmpty(message = "邮箱不能为空", groups = {Add.class})
    @Email(message = "邮箱格式错误", groups = {Add.class})
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空", groups = {Add.class})
    @ApiModelProperty("密码")
    private String password;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public interface Add{}
}