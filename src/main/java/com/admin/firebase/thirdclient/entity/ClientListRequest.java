package com.admin.firebase.thirdclient.entity;

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
public class ClientListRequest extends BasePage {

    @ApiModelProperty("客户端ID")
    private String clientId;

}
