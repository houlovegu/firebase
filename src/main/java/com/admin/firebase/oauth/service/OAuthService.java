package com.admin.firebase.oauth.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName OAuthService
 * @Description TODO
 * @Author sky
 * @Date 2023/5/23 16:21
 * @Version 1.0
 **/
public interface OAuthService {


    /**
     * @Author sky
     * @Description 认证
     * @Date 2023/5/23 16:23
     * @Param [clientID, state, redirectUri, scope, response]
     * @return void
     **/
    void auth(String clientID, String state, String redirectUri, String scope, HttpServletResponse response) throws IOException;
}
