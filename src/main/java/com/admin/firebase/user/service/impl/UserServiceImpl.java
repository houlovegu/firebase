package com.admin.firebase.user.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.jwt.JWT;
import com.admin.common.constant.ResultCode;
import com.admin.common.exception.CustomException;
import com.admin.common.response.Result;
import com.admin.constants.CacheConstants;
import com.admin.firebase.oauth.constant.TokenStore;
import com.admin.firebase.user.entity.ResetRequest;
import com.admin.firebase.user.entity.Socialuser;
import com.admin.firebase.user.entity.TokenRequest;
import com.admin.firebase.user.entity.User;
import com.admin.firebase.user.mapper.UserMapper;
import com.admin.firebase.user.service.SocialuserService;
import com.admin.firebase.user.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author sky
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2023-05-04 14:33:19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService{

    @Value("${security.secret}")
    private String secret;

    @Value("${jwt.key}")
    private String JWT_KEY;

    @Resource
    private TokenStore tokenStore;

    @Resource
    private SocialuserService socialuserService;

    @Override
    public Result login(User user) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(User::getEmail, user.getEmail());
        User loginUser = this.getOne(wrapper);
        if (ObjectUtil.isEmpty(loginUser)) {
            return Result.fail(ResultCode.NOT_FOUND_USER.getCode(),ResultCode.NOT_FOUND_USER.getMsg());
        }
//        String pwd = getPwd(loginUser.getPassword());
        if (!StrUtil.equals(user.getPassword(), loginUser.getPassword())) {
            return Result.fail(ResultCode.PASSWORD_VERIFY_USER.getCode(),ResultCode.PASSWORD_VERIFY_USER.getMsg());
        }
        // 登录成功后生成token
        String userId = loginUser.getId().toString();
        DateTime date = DateUtil.date();
        String token = JWT.create().setPayload("uid", userId)
                .setKey(JWT_KEY.getBytes())
                .setIssuedAt(date)
                .setExpiresAt(DateUtil.offsetHour(date,1))
                .sign();
        // 默认1天有效
//        redisUtils.set(userId, token, 3600);
        return Result.ok(token);
    }

    @Override
    public Result register(User user) {
        String pwd = getPwd(user.getPassword());
//        user.setPassword(pwd);
        boolean saved = this.save(user);
        if (saved) {
            return Result.ok();
        } else {
            return Result.fail(ResultCode.FAIL_TO_REGISTER_USER.getCode(),ResultCode.FAIL_TO_REGISTER_USER.getMsg());
        }

    }

    @Override
    public Result reset(ResetRequest reset) {
//        String verifyCode = (String) session.getAttribute("verifyCode");
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + reset.getUuid();
//        String captcha = (String) redisUtils.get(verifyKey);
//        redisUtils.del(verifyKey);
//        if (!reset.getCode().equalsIgnoreCase(captcha)) {
//            return Result.fail(ResultCode.VALIDATE_FAIL_CODE.getCode(), ResultCode.VALIDATE_FAIL_CODE.getMsg());
//        }
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(User::getEmail, reset.getEmail());
        User user = this.getOne(wrapper);
        if(ObjectUtil.isEmpty(user)) {
            return Result.fail(ResultCode.NOT_FOUND_USER.getCode(), ResultCode.NOT_FOUND_USER.getMsg());
        }
//        String pwd = getPwd(reset.getPassword());
        user.setPassword(reset.getPassword());
        this.updateById(user);
        return Result.ok();
    }

    @Override
    public Result logout(HttpServletRequest request) {
//        String uid = request.getAttribute("uid").toString();
//        boolean b = redisUtils.hasKey(uid);
//        if (b) {
//            redisUtils.del(uid);
//        }
        return Result.ok();
    }

    @Override
    public Result loginByToken(TokenRequest tokenRequest) {
        String tokenStoreByToken = tokenStore.getTokenStoreByToken(tokenRequest.getAccessToken());
        if (StrUtil.isBlank(tokenStoreByToken)) {
            throw new CustomException(ResultCode.VALIDATE_TOKEN);
        }
        Socialuser socialuser = socialuserService.getOne(new LambdaQueryWrapper<Socialuser>().eq(Socialuser::getAccesstoken, tokenRequest.getAccessToken()));
        if (ObjectUtil.isEmpty(socialuser)) {
            throw new CustomException(ResultCode.NOT_FOUND_USER);
        }
        User one = this.getOne(new LambdaQueryWrapper<User>().eq(User::getOpenid, socialuser.getUuid()));
        if (ObjectUtil.isEmpty(one)) {
            throw new CustomException(ResultCode.NOT_FOUND_USER);
        }

        return Result.ok(one);
    }

    private String getPwd(String password) {
        MD5 md5 = SecureUtil.md5();
        md5.setSalt(secret.getBytes());
        String pwd = md5.digestHex16(password);
        return pwd;
    }
}





