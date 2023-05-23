package com.admin.firebase.oauth.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.admin.common.response.Result;
import com.admin.firebase.oauth.constant.TokenStore;
import com.admin.firebase.user.entity.Socialuser;
import com.admin.firebase.user.entity.User;
import com.admin.firebase.user.service.SocialuserService;
import com.admin.firebase.user.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xkcoding.justauth.AuthRequestFactory;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName AuthController
 * @Description TODO
 * @Author sky
 * @Date 2023/5/23 8:51
 * @Version 1.0
 **/
@RestController
@RequestMapping("/oauth")
public class OAuthController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${web.redirect-url}")
    private String redirectUrl;

    @Resource
    private AuthRequestFactory factory;

    @Resource
    private SocialuserService socialuserService;

    @Resource
    private UserService userService;

    @Resource
    private TokenStore tokenStore;

    @GetMapping
    public List<String> list() {
        return factory.oauthList();
    }

    @RequestMapping("/login/{type}")
    public Result login(@PathVariable String type, HttpServletRequest request, HttpServletResponse response) throws IOException {
        AuthRequest authRequest = factory.get(type);
        return Result.ok(authRequest.authorize(AuthStateUtils.createState()));
    }

    @GetMapping("/{type}/callback")
    public void login(@PathVariable String type, AuthCallback callback, HttpServletResponse resp) throws IOException {
        AuthRequest authRequest = factory.get(type);
        AuthResponse<AuthUser> response = authRequest.login(callback);
        if (response.ok()) {
            AuthUser authUser = response.getData();
            String openId = authUser.getUuid();
            String source = authUser.getSource();

            // 创建User
            User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getOpenid, openId));
            if (ObjectUtil.isEmpty(user)) {
                user = new User();
            }
            user.setOpenid(openId);
            user.setUsername(authUser.getUsername());
            user.setRealname(authUser.getUsername());
            user.setLastlogintime(DateUtil.now());
            user.setEmail(authUser.getEmail());
            user.setImageurl(authUser.getAvatar());
            user.setSex(authUser.getGender().getCode());
            userService.saveOrUpdate(user);
            // 存储用户信息
            Socialuser socialuser = socialuserService.getOne(new LambdaQueryWrapper<Socialuser>().eq(Socialuser::getUuid, openId).eq(Socialuser::getSource, source));
            socialuserService.createSocialUserByAuthUser(socialuser, authUser);

            AuthToken token = authUser.getToken();
            tokenStore.storeAccessToken(token);

            resp.sendRedirect(redirectUrl+"?access_token="+token.getAccessToken()+"&expire_in="
                    +token.getExpireIn()+"&refresh_token="+token.getRefreshToken()+"&refresh_token_expire_in="+token.getRefreshTokenExpireIn());
        } else {
            log.info("【{}】login is fail...", type);
        }
    }

}
