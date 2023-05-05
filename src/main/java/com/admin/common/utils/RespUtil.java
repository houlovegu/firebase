package com.admin.common.utils;

import cn.hutool.json.JSONUtil;
import com.admin.common.constant.ResultCode;
import com.admin.common.exception.CustomException;
import com.admin.common.response.Result;
import org.springframework.http.MediaType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RespUtil {

    public static void writeResult(HttpServletRequest request, HttpServletResponse response) {

        try {
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_OK);
            CustomException exception = new CustomException(ResultCode.NO_FOUND_TOKEN);
            response.getWriter().write(JSONUtil.toJsonStr(Result.fail(exception.getCode().getCode(), exception.getCode().getMsg())));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void writeResultByToken(HttpServletRequest request, HttpServletResponse response, ResultCode code) {
        try {
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_OK);
            CustomException exception = new CustomException(code);
            response.getWriter().write(JSONUtil.toJsonStr(Result.fail(exception.getCode().getCode(), exception.getCode().getMsg())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
