package com.admin.firebase.fcm.entity;

import com.google.firebase.messaging.WebpushFcmOptions;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class WebpushConfigEntity implements Serializable {

    @ApiModelProperty(name = "headers")
    private Map<String, String> headers;

    @ApiModelProperty(name = "data")
    private Map<String, String> data;

    @ApiModelProperty(name = "notification")
    private Map<String, Object> notification;

    @ApiModelProperty(name = "fcmOptions")
    private WebpushFcmOptions fcmOptions;
}
