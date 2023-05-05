package com.admin.firebase.fcm.entity;

import com.google.firebase.messaging.ApnsFcmOptions;
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
public class ApnsConfigEntity implements Serializable {

    @ApiModelProperty(name = "headers")
    private Map<String, String> headers;

    @ApiModelProperty(name = "payload")
    private Map<String, Object> payload;

    @ApiModelProperty(name = "fcmOptions")
    private ApnsFcmOptions fcmOptions;
}
