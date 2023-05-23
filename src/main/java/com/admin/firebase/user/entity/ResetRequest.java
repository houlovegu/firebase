package com.admin.firebase.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ApiModel
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResetRequest implements Serializable {

    @Email(message = "邮箱格式错误")
    @ApiModelProperty("邮箱")
    private String email;

    @NotEmpty(message = "密码不能为空")
    @ApiModelProperty("密码")
    private String password;

    @NotEmpty(message = "uuid")
    @ApiModelProperty("uuid")
    private String uuid;

    @NotEmpty(message = "code")
    @ApiModelProperty("code")
    private String code;
}
