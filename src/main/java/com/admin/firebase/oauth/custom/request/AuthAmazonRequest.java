package com.admin.firebase.oauth.custom.request;

import com.admin.firebase.oauth.custom.AuthCustomSource;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.enums.AuthUserGender;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthDefaultRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.UrlBuilder;

/**
 * @ClassName AuthAmazonRequest
 * @Description TODO
 * @Author sky
 * @Date 2023/5/23 8:27
 * @Version 1.0
 **/
public class AuthAmazonRequest extends AuthDefaultRequest {

    public AuthAmazonRequest(AuthConfig config) {
        super(config, AuthCustomSource.AMAZON);
    }
    public AuthAmazonRequest(AuthConfig config, AuthStateCache authStateCache) {
        super(config, AuthCustomSource.AMAZON, authStateCache);
    }
    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        String result = doPostAuthorizationCode(authCallback.getCode());
        JSONObject object = JSONObject.parseObject(result);

        this.checkResponse(object);

        return AuthToken.builder()
                .accessToken(object.getString("access_token"))
                .refreshToken(object.getString("refresh_token"))
                .idToken(object.getString("id_token"))
                .tokenType(object.getString("token_type"))
                .scope(object.getString("scope"))
                .build();
    }

    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        String result = doGetUserInfo(authToken);
        JSONObject object = JSONObject.parseObject(result);

        this.checkResponse(object);

        return AuthUser.builder()
                .uuid(object.getString("id"))
                .username(object.getString("username"))
                .nickname(object.getString("name"))
                .avatar(object.getString("avatar_url"))
                .blog(object.getString("web_url"))
                .company(object.getString("organization"))
                .location(object.getString("location"))
                .email(object.getString("email"))
                .remark(object.getString("bio"))
                .gender(AuthUserGender.UNKNOWN)
                .token(authToken)
                .source(source.toString())
                .build();
    }

    /**
     * 返回带{@code state}参数的授权url，授权回调时会带上这个{@code state}
     *
     * @param state state 验证授权流程的参数，可以防止csrf
     * @return 返回授权地址
     * @since 1.11.0
     */
    @Override
    public String authorize(String state) {
        return UrlBuilder.fromBaseUrl(super.authorize(state))
                .queryParam("scope", "read_user+openid")
                .build();
    }

    private void checkResponse(JSONObject object) {
        // oauth/token 验证异常
        if (object.containsKey("error")) {
            throw new AuthException(object.getString("error_description"));
        }
        // user 验证异常
        if (object.containsKey("message")) {
            throw new AuthException(object.getString("message"));
        }
    }

    public static void main(String[] args) {
        AuthRequest authRequest = new me.zhyd.oauth.request.AuthAmazonRequest(AuthConfig.builder()
                .clientId("63398e403231d4aa7e856cf5413620d536a876cb94e8d10ced0d3191b5d1d246")
                .clientSecret("65b0eba68fff019e682e6755882a24dfdbf0a61be55de119cb8970320186c8eb")
                .redirectUri("http://127.0.0.1:8443/oauth/callback/mygitlab")
                .build());
    }
}
