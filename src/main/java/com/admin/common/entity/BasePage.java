package com.admin.common.entity;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@ApiModel
public class BasePage implements Serializable {

    @ApiModelProperty("当前要查询的页码")
    private Long pageNo;

    @ApiModelProperty("当前页要查询的条数")
    private Long pageSize;
}
