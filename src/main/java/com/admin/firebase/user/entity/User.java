package com.admin.firebase.user.entity;

import com.admin.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

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

    /**
     * 区域
     */
    @ApiModelProperty("区域")
    private String addrarea;

    /**
     * 市
     */
    @ApiModelProperty("市")
    private String addrcity;

    /**
     * 省
     */
    @ApiModelProperty("省")
    private String addrprovince;

    /**
     * 生日
     */
    @ApiModelProperty("生日")
    private String birthday;

    /**
     * 行业
     */
    @ApiModelProperty("行业")
    private String industry;

    /**
     * 简介
     */
    @ApiModelProperty("简介")
    private String intro;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * 位置
     */
    @ApiModelProperty("位置")
    private String position;

    /**
     * 密码盐
     */
    @ApiModelProperty("密码盐")
    private String salt;

    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private String sex;

    /**
     * 电话
     */
    @ApiModelProperty("电话")
    private String telephone;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 邮箱
     */
    @NotEmpty(message = "邮箱不能为空", groups = {Add.class})
    @Email(message = "邮箱格式错误", groups = {Add.class})
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 头像地址
     */
    @ApiModelProperty("头像地址")
    private String imageurl;

    /**
     * 注册时间
     */
    @ApiModelProperty("注册时间")
    private String registertime;

    /**
     * 最后登录时间
     */
    @ApiModelProperty("最后登录时间")
    private String lastlogintime;

    /**
     * editortype
     */
    @ApiModelProperty("editortype")
    private Integer editortype;

    /**
     * userstateid
     */
    @ApiModelProperty("userstateid")
    private Integer userstateid;

    /**
     * 是否可用
     */
    @ApiModelProperty("是否可用")
    private Integer available;

    /**
     * 编辑时间
     */
    @ApiModelProperty("编辑时间")
    private String modifytime;

    /**
     * 编辑人
     */
    @ApiModelProperty("编辑人")
    private Long modifyuserid;

    /**
     * openid
     */
    @ApiModelProperty("openid")
    private String openid;

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String realname;

//    /**
//     * 用户名
//     */
//    @ApiModelProperty("用户名")
//    private String userName;
//
//    /**
//     * 邮箱
//     */
//    @NotEmpty(message = "邮箱不能为空", groups = {Add.class})
//    @Email(message = "邮箱格式错误", groups = {Add.class})
//    @ApiModelProperty("邮箱")
//    private String email;
//
//    /**
//     * 密码
//     */
//    @NotEmpty(message = "密码不能为空", groups = {Add.class})
//    @ApiModelProperty("密码")
//    private String password;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public interface Add{}
}