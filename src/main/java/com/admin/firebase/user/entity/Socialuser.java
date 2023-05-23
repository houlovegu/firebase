package com.admin.firebase.user.entity;

import com.admin.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @TableName socialuser
 */
@TableName(value ="socialuser")
@Data
@ApiModel
public class Socialuser extends BaseEntity {
    /**
     * id
     */
    @ApiModelProperty("id")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 个别平台的授权信息
     */
    @ApiModelProperty("个别平台的授权信息")
    private String accesscode;

    /**
     * 用户的授权令牌
     */
    @ApiModelProperty("用户的授权令牌")
    private String accesstoken;

    /**
     * 用户的授权code
     */
    @ApiModelProperty("用户的授权code")
    private String code;

    /**
     * 第三方用户的授权令牌的有效期
     */
    @ApiModelProperty("第三方用户的授权令牌的有效期")
    private Integer expirein;

    /**
     * id token
     */
    @ApiModelProperty("idtoken")
    private String idtoken;

    /**
     * 小米平台用户的附带属性
     */
    @ApiModelProperty("小米平台用户的附带属性")
    private String macalgorithm;

    /**
     * 小米平台用户的福袋属性
     */
    @ApiModelProperty("小米平台用户的福袋属性")
    private String mackey;

    /**
     * Twitter平台用户的附带属性
     */
    @ApiModelProperty("Twitter平台用户的附带属性")
    private String oauthtoken;

    /**
     * Twitter平台用户的附带属性
     */
    @ApiModelProperty("Twitter平台用户的附带属性")
    private String oauthtokensecret;

    /**
     * 第三方用户的openId
     */
    @ApiModelProperty("第三方用户的openId")
    private String openid;

    /**
     * 刷新令牌
     */
    @ApiModelProperty("刷新令牌")
    private String refreshtoken;

    /**
     * 第三方用户授予的权限
     */
    @ApiModelProperty("第三方用户授予的权限")
    private String scope;

    /**
     * 第三方用户来源 GITHUB,QQ,GITEE,具体参考 AuthDefaultSource
     */
    @ApiModelProperty("第三方用户来源")
    private String source;

    /**
     * 个别平台的授权信息
     */
    @ApiModelProperty("个别平台的授权信息")
    private String tokentype;

    /**
     * 第三方用户的ID
     */
    @ApiModelProperty("第三方用户的ID")
    private String uid;

    /**
     * 第三方用户的unionid
     */
    @ApiModelProperty("第三方用户的unionid")
    private String unionid;

    /**
     * 第三方系统的唯一ID
     */
    @ApiModelProperty("第三方系统的唯一ID")
    private String uuid;

    /**
     * 第三方用户的nickname
     */
    @ApiModelProperty("第三方用户的nickname")
    private String nickname;

    /**
     * 第三方用户的username
     */
    @ApiModelProperty("第三方用户的username")
    private String username;

    /**
     * 第三方用户的头像
     */
    @ApiModelProperty("第三方用户的头像")
    private String avatar;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}