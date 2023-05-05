package com.admin.firebase.user.controller;

import com.admin.common.response.Result;
import com.admin.firebase.user.entity.ResetRequest;
import com.admin.firebase.user.entity.User;
import com.admin.firebase.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
    public Result reset(@Validated @RequestBody ResetRequest reset, HttpSession session) {
        return userService.reset(reset, session);
    }
}
