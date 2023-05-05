package com.admin.firebase.device.entity;

import com.admin.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static com.baomidou.mybatisplus.annotation.IdType.ASSIGN_ID;

@Data
@TableName("device")
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
public class DeviceEntity extends BaseEntity {

    @TableId(type = ASSIGN_ID)
    private Long id;

    /**
     * 唯一标识[mac地址]
     */
    @NotEmpty(message = "uid不能为空", groups = {Report.class})
    @TableField(value = "uid")
    private String uid;

    /**
     * firebase注册token
     */
    @TableField(value = "registry_token")
    @NotEmpty(message = "registry_token不能为空", groups = {Report.class})
    private String registryToken;

    public interface Report {}
}
