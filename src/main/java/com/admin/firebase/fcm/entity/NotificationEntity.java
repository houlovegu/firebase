package com.admin.firebase.fcm.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class NotificationEntity implements Serializable {

    @ApiModelProperty(name = "主题")
    private String title;

    @ApiModelProperty(name = "内容")
    private String body;

    @ApiModelProperty(name = "图片地址")
    private String image;
}
