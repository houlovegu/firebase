package com.admin.firebase.oauth.service;

import cn.hutool.core.util.ObjectUtil;
import com.admin.common.constant.ResultCode;
import com.admin.common.exception.CustomException;
import com.admin.firebase.oauth.constant.OauthGenerator;
import com.admin.firebase.oauth.constant.TokenStore;
import com.admin.firebase.thirdclient.entity.SysRegisteredClient;
import com.admin.firebase.thirdclient.service.SysRegisteredClientService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName OAuthServiceImpl
 * @Description TODO
 * @Author sky
 * @Date 2023/5/23 16:21
 * @Version 1.0
 **/
@Service
public class OAuthServiceImpl implements OAuthService {

    @Resource
    private TokenStore tokenStore;

    @Resource
    private SysRegisteredClientService sysRegisteredClientService;

    @Override
    public void auth(String clientID, String state, String redirectUri, String scope, HttpServletResponse response) throws IOException {
        String oauthState = tokenStore.getOauthState(state);
        if (state == null || ObjectUtil.isNotEmpty(oauthState)) {
            throw new CustomException(ResultCode.OAUTH_STATE_ERROR);
        }
        SysRegisteredClient serviceOne = sysRegisteredClientService.getOne(new LambdaQueryWrapper<SysRegisteredClient>().eq(SysRegisteredClient::getClientId, clientID));
        if (ObjectUtil.isEmpty(serviceOne)) {
            throw new CustomException(ResultCode.OAUTH_CLIENT_ERROR);
        }
        String scopes = serviceOne.getScopes();
        List<String> result = Arrays.asList(scopes.split(","));
        if (!result.contains(scope)) {
            throw new CustomException(ResultCode.OAUTH_SCOPE_ERROR);
        }
        // 生成access_token
        String accessToken = OauthGenerator.generateAccessToken();
        String refreshToken = OauthGenerator.generateRefreshToken();
        long accessTokenExpire = serviceOne.getAccessTokenTimeToLive();
        long refreshTokenExpire = serviceOne.getRefreshTokenTimeToLive();
        tokenStore.delOauthState(state);
        response.sendRedirect(redirectUri+"?access_token="+accessToken+"&expire_in="
                +refreshToken+"&refresh_token="+accessTokenExpire+"&refresh_token_expire_in="+refreshTokenExpire);
    }
}
