package com.admin.firebase.device.entity;

import com.admin.common.entity.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
public class DeviceListRequest extends BasePage {

    @ApiModelProperty("设备编号")
    private String deviceNo;

}
