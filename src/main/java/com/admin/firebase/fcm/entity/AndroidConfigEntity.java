package com.admin.firebase.fcm.entity;

import com.google.firebase.messaging.AndroidFcmOptions;
import com.google.firebase.messaging.AndroidNotification;
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
public class AndroidConfigEntity implements Serializable {

    @ApiModelProperty(name = "collapseKey")
    private String collapseKey;

    @ApiModelProperty(name = "priority")
    private String priority;

    @ApiModelProperty(name = "ttl")
    private String ttl;

    @ApiModelProperty(name = "restrictedPackageName")
    private String restrictedPackageName;

    @ApiModelProperty(name = "data")
    private Map<String, String> data;

    @ApiModelProperty(name = "notification")
    private AndroidNotification notification;

    @ApiModelProperty(name = "fcm_options")
    private AndroidFcmOptions fcmOptions;

    @ApiModelProperty(name = "directBootOk")
    private Boolean directBootOk;
}
