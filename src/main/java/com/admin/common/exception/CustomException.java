package com.admin.common.exception;

import com.admin.common.constant.ResultCode;
import lombok.Data;

@Data
public class CustomException extends RuntimeException{

    private ResultCode code;

    public CustomException(ResultCode code) {
        this.code = code;
    }
}
