package com.admin.firebase.fcm.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class MsgMultiEntity implements Serializable {


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

    @ApiModelProperty(name = "fcmOptions")
    private FcmOptionsEntity fcmOptions;

    @ApiModelProperty(name = "tokens")
    private List<String> tokens;

    @ApiModelProperty(name = "设备编号")
    private List<String> uid;
}
