package com.admin.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {

    @TableField(value = "create_user" ,fill = FieldFill.INSERT)
    private Long createUser;

    @TableField(value = "create_time" ,fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_user" ,fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    @TableField(value = "update_time" ,fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
