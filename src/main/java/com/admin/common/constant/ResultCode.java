package com.admin.common.constant;

import lombok.Getter;

@Getter
public enum ResultCode {

    SUCCESS(200, "操作成功"),

    OPERATE_FAIL(1001, "响应失败"),

    VALIDATE_FAILED(1002, "参数校验失败"),

    INTERNAL_FAIL(5000, "系统异常"),

    NO_FOUND_TOKEN(4000, "缺少token信息"),

    VALIDATE_FAIL_TOKEN(4001, "token验证失败"),

    EXPIRE_TOKEN(4002, "token已过期"),

    VALIDATE_TOKEN(4003, "无效的token"),

    NOT_FOUND_USER(4004, "用户不存在"),

    PASSWORD_VERIFY_USER(4005, "密码错误"),

    FAIL_TO_REGISTER_USER(4006, "用户注册失败"),

    VALIDATE_FAIL_CODE(4007, "验证码失效"),

    CHECK_FAIL_CODE(4008, "验证码错误"),

    DEVICE_TOKEN_FOUND_FAIL(4008, "未找到设备绑定的token"),
    ;


    private final Integer code;
    private final String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
