package com.admin.firebase.oauth.constant;

import com.admin.common.utils.RedisUtils;
import me.zhyd.oauth.model.AuthToken;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName TokenStore
 * @Description TODO
 * @Author sky
 * @Date 2023/5/23 14:22
 * @Version 1.0
 **/
@Component
public class TokenStore {

    @Resource
    private RedisUtils redisUtils;

    private final String OAUTH_ACCESS_TOKEN = "access_token:";
    private final String OAUTH_REFRESH_TOKEN = "refresh_token:";

    public void storeAccessToken(AuthToken authToken) {
        String accessToken = authToken.getAccessToken();
        int expireIn = authToken.getExpireIn();
        String refreshToken = authToken.getRefreshToken();
        int refreshTokenExpireIn = authToken.getRefreshTokenExpireIn();
        redisUtils.set(OAUTH_ACCESS_TOKEN+accessToken, accessToken, expireIn);
        if (refreshTokenExpireIn == 0) {
            redisUtils.set(OAUTH_REFRESH_TOKEN+refreshToken, refreshToken);
        } else {
            redisUtils.set(OAUTH_REFRESH_TOKEN+refreshToken, refreshToken, refreshTokenExpireIn);
        }
    }

    public String getTokenStoreByToken(String token) {
        String accessToken = get(OAUTH_ACCESS_TOKEN + token, String.class);
        if (accessToken == null) {
            return null;
        }
        return accessToken;
    }

    public <T> T get(String key, Class<T> type) {
        return (T) redisUtils.get(key);
    }
}
