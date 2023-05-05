package com.admin.firebase.fcm.entity;

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
public class MsgSingleEntity implements Serializable {

    @ApiModelProperty(name = "自定义数据")
    private Map<String, String> data;

    @ApiModelProperty(name = "notification")
    private NotificationEntity notification;

    @ApiModelProperty(name = "androidConfig")
    private AndroidConfigEntity androidConfig;

    @ApiModelProperty(name = "webpushConfig")
    private WebpushConfigEntity webpushConfig;

    @ApiModelProperty(name = "apnsConfig")
    private ApnsConfigEntity apnsConfig;

    @ApiModelProperty(name = "token")
    private String token;

    @ApiModelProperty(name = "topic")
    private String topic;

    @ApiModelProperty(name = "condition")
    private String condition;

    @ApiModelProperty(name = "fcmOptions")
    private FcmOptionsEntity fcmOptions;
}
