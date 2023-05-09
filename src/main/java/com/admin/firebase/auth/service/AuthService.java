package com.admin.firebase.auth.service;

import cn.hutool.core.lang.Dict;
import com.admin.common.response.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {

    /**
     * @Author sky
     * @Description 获取token
     * @Date 2023/5/9 8:56
     * @Param [dict]
     * @return com.admin.common.response.Result
     **/
    Result getToken(Dict dict);

    /**
     * @Author sky
     * @Description 获取验证码
     * @Date 2023/5/9 8:56
     * @Param [request, response]
     * @return void
     **/
    void getCaptcha(HttpServletRequest request, HttpServletResponse response);
}
