package com.admin.common.response;


import com.admin.common.constant.ResultCode;
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
public class Result implements Serializable {

    /**
     * 状态码，比如200代表响应成功
     */
    @ApiModelProperty(value = "响应信息", required = true)
    private Integer code;

    /**
     * 响应信息，用来说明响应情况
     */
    @ApiModelProperty(value = "响应信息")
    private String msg;

    /**
     * 响应的具体数据
     */
    @ApiModelProperty(value = "响应的数据")
    private Object data;

    /**
     * 返回成功，不带数据
     * @return
     */
    public static Result ok() {
        Result resp = new Result();

        resp.setCode(ResultCode.SUCCESS.getCode());

        return resp;
    }

    /**
     * 返回成功，带数据
     * @param data
     * @return
     */
    public static Result ok(Object data) {
        Result resp = new Result();

        resp.setCode(ResultCode.SUCCESS.getCode());
        resp.setData(data);
        return resp;
    }

    /**
     * 返回失败
     * @param code 错误码
     * @param message 错误信息
     * @return
     */
    public static Result fail(Integer code, String message) {
        Result resp = new Result();

        resp.setCode(code);
        resp.setMsg(message);
        return resp;
    }

    public static Result internalFail(String message) {
        Result resp = new Result();

        resp.setCode(ResultCode.INTERNAL_FAIL.getCode());
        resp.setMsg(message);

        return resp;
    }

    public static Result operateFail(String message) {
        Result resp = new Result();

        resp.setCode(ResultCode.OPERATE_FAIL.getCode());
        resp.setMsg(message);

        return resp;
    }
}
