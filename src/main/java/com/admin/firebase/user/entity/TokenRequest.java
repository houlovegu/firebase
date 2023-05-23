package com.admin.firebase.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName TokenRequest
 * @Description TODO
 * @Author sky
 * @Date 2023/5/23 15:26
 * @Version 1.0
 **/
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TokenRequest implements Serializable {

    @NotNull(message = "accessToken不能为空")
    @ApiModelProperty("accessToken")
    private String accessToken;
}
