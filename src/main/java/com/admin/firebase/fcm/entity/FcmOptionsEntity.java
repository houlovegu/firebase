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
public class FcmOptionsEntity implements Serializable {

    @ApiModelProperty(name = "analyticsLabel")
    private String analyticsLabel;
}
