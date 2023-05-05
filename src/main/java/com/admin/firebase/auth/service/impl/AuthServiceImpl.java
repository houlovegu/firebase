package com.admin.firebase.auth.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.jwt.JWT;
import com.admin.firebase.auth.service.AuthService;
import com.admin.common.response.Result;
import com.admin.common.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class AuthServiceImpl implements AuthService {

    @Value("${jwt.key}")
    private String JWT_KEY;

    @Resource
    RedisUtils redisUtils;
    @Override
    public Result getToken(Dict dict) {
        String uid = dict.getStr("uid");
        DateTime date = DateUtil.date();
        String token = JWT.create().setPayload("uid", uid)
                .setKey(JWT_KEY.getBytes())
                .setIssuedAt(date)
                .setExpiresAt(DateUtil.offsetHour(date,1))
                .sign();

        redisUtils.set(uid, token, 3600);
        return Result.ok(token);
    }

    @Override
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        try {
            //定义图形验证码的长、宽、验证码字符数、干扰元素个数
            CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(100, 50, 4, 10);
            String code = captcha.getCode();
            captcha.write(response.getOutputStream());
            request.getSession().setAttribute("verifyCode", code);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
