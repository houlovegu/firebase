package com.admin.firebase.thirdclient.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName AddClientRequest
 * @Description TODO
 * @Author sky
 * @Date 2023/5/22 17:26
 * @Version 1.0
 **/
@ApiModel
@Data
public class AddClientRequest {
    /**
     * 唯一标识
     */
    @ApiModelProperty("ID")
    private Long id;


    /**
     * 客户端 ID
     */
    @ApiModelProperty("客户端 ID")
    private String clientId;

    /**
     * 客户端密码
     */
    @ApiModelProperty("客户端密码")
    private String clientSecret;

    /**
     * 客户端密码过期时间
     */
    @ApiModelProperty("客户端密码过期时间")
    private Date clientSecretExpiresAt;

    /**
     * 客户端名称
     */
    @ApiModelProperty("客户端名称")
    private String clientName;

    /**
     * 客户端认证方式（client_secret_basic，client_secret_post，private_key_jwt，client_secret_jwt，none）
     */
    @ApiModelProperty("客户端认证方式[（]client_secret_basic，client_secret_post，private_key_jwt，client_secret_jwt，none]")
    private String clientAuthenticationMethods;

    /**
     * 授权类型（authorization_code,client_credentials,refresh_token,password）
     */
    @ApiModelProperty("授权类型[authorization_code,client_credentials,refresh_token,password]")
    private List<String> authorizationGrantTypes;

    /**
     * 重定向 URI
     */
    @ApiModelProperty("重定向 URI")
    private String redirectUris;

    /**
     * SCOPE
     */
    @ApiModelProperty("SCOPE")
    private String scopes;

    /**
     * 是否要求授权许可（1:=是；0:=否）
     */
    @ApiModelProperty("是否要求授权许可[1:=是；0:=否]")
    private Integer requireAuthorizationConsent;

    /**
     * 访问令牌有效期（单位：s）
     */
    @ApiModelProperty("访问令牌有效期单位：s")
    private Integer accessTokenTimeToLive;

    /**
     * 访问令牌格式（self-contained, reference）
     */
    @ApiModelProperty("访问令牌格式[self-contained, reference]")
    private String accessTokenFormat;

    /**
     * 刷新令牌有效期（单位：s）
     */
    @ApiModelProperty("刷新令牌有效期单位：s")
    private Integer refreshTokenTimeToLive;

    /**
     * 是否删除（0:=未删除;null:=已删除）
     */
    @ApiModelProperty("是否删除[0:=未删除;null:=已删除]")
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
