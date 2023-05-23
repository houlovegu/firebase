package com.admin.firebase.oauth.constant;

import java.util.UUID;

public class OauthGenerator {

    static final String ACCESS_TOKEN_PREFIX = "ACC|";
    static final String REFRESH_TOKEN_PREFIX = "REF|";


    public static String generateAccessToken() {
        return ACCESS_TOKEN_PREFIX + UUID.randomUUID().toString();
    }

    public static String generateRefreshToken() {
        return REFRESH_TOKEN_PREFIX + UUID.randomUUID().toString();
    }
}
