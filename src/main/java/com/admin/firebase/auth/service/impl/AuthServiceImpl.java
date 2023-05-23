package com.admin.firebase.auth.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.UUID;
import cn.hutool.jwt.JWT;
import com.admin.common.response.Result;
import com.admin.common.utils.RedisUtils;
import com.admin.constants.CacheConstants;
import com.admin.constants.Constants;
import com.admin.firebase.auth.service.AuthService;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Value("${jwt.key}")
    private String JWT_KEY;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

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
    public Result getCaptcha() {
        //简化的UUID，去掉了横线
        String uuid = UUID.randomUUID().toString(true);
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;
        String capText = captchaProducerMath.createText();
        String capStr = capText.substring(0, capText.lastIndexOf("@"));
        String code = capText.substring(capText.lastIndexOf("@") + 1);
        BufferedImage image = captchaProducerMath.createImage(capStr);
        redisUtils.set(verifyKey, code, Constants.CAPTCHA_EXPIRATION);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try
        {
            ImageIO.write(image, "jpg", os);
        }
        catch (IOException e)
        {
            return Result.operateFail(e.getMessage());
        }
        Map<String, Object> data = new HashMap<>();
        data.put("uuid", uuid);
        data.put("img", Base64.encode(os.toByteArray()));
        return Result.ok(data);
//        try {
//            //定义图形验证码的长、宽、验证码字符数、干扰元素个数
//            CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(100, 50, 4, 10);
//            String code = captcha.getCode();
//            captcha.write(response.getOutputStream());
//            request.getSession().setAttribute("verifyCode", code);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
