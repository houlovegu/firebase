package com.admin.firebase.user.controller;

import com.admin.common.response.Result;
import com.admin.firebase.user.entity.ResetRequest;
import com.admin.firebase.user.entity.TokenRequest;
import com.admin.firebase.user.entity.User;
import com.admin.firebase.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Api(tags = "用户模块")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @ApiOperation("登录接口")
    @PostMapping("/login")
    public Result login(@Validated(User.Add.class) @RequestBody User user) {
        return userService.login(user);
    }

    @ApiOperation("注册接口")
    @PostMapping("/register")
    public Result register(@Validated(User.Add.class) @RequestBody User user) {
        return userService.register(user);
    }

    @ApiOperation("密码重置")
    @PostMapping("/reset")
    public Result reset(@Validated @RequestBody ResetRequest reset) {
        return userService.reset(reset);
    }

    @ApiOperation("退出登录接口")
    @GetMapping("/logout")
    public Result logout(HttpServletRequest request) {
        return userService.logout(request);
    }

    @ApiOperation("退出登录接口")
    @PostMapping("/loginByToken")
    public Result loginByToken(@RequestBody TokenRequest tokenRequest) {
        return userService.loginByToken(tokenRequest);
    }
}
