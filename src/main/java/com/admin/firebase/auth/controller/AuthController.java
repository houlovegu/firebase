package com.admin.firebase.auth.controller;

import cn.hutool.core.lang.Dict;
import com.admin.firebase.auth.service.AuthService;
import com.admin.common.response.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags = "权限认证")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private AuthService authService;

    @ApiOperation(value = "获取token", notes = "dict是一个map集合增强类,此接口需要传入uid:设备唯一标识-mac地址,json格式:{\"uid\":\"\"}")
    @PostMapping("/token")
    public Result getToken(@RequestBody Dict dict) {
        return authService.getToken(dict);
    }

    @ApiOperation("获取验证码")
    @GetMapping("/getCaptcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        authService.getCaptcha(request, response);
    }
}
