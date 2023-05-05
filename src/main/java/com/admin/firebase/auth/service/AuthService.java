package com.admin.firebase.auth.service;

import cn.hutool.core.lang.Dict;
import com.admin.common.response.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {

    Result getToken(Dict dict);

    void getCaptcha(HttpServletRequest request, HttpServletResponse response);
}
