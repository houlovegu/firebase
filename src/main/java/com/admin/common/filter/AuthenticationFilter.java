package com.admin.common.filter;

import cn.hutool.core.util.StrUtil;
import com.admin.common.constant.ConstantValue;
import com.admin.common.utils.RedisUtils;
import com.admin.common.utils.RespUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用于校验请求是否包含token
 */
@Slf4j
@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Value("${jwt.key}")
    private String JWT_KEY;

    @Resource
    RedisUtils redisUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filter(request,response,filterChain);
    }

    private void filter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            String requestURI = request.getRequestURI();
            // 放行swagger
            if (StrUtil.containsAny(requestURI, "/oauth/authorize","/callback","/oauth/login","/favicon.ico","/user/reset","/user/register","/user/login","/auth/getCaptcha","/auth/token","/doc.html","webjars","swagger-resources","/v2/api-docs")) {
                filterChain.doFilter(request, response);
                return ;
            }
            String token = request.getHeader(ConstantValue.TOKEN_HEADER);
            if (StrUtil.isEmpty(token) || StrUtil.equals("null", token)) {
                RespUtil.writeResult(request, response);
                return ;
            }
            // 验证token
//            validateToken(token, request, response);
//            JWT jwt = JWT.of(token);
//            boolean verify = jwt.setKey(JWT_KEY.getBytes()).verify();
//            if (!verify) {
//                RespUtil.writeResultByToken(request, response, ResultCode.VALIDATE_FAIL_TOKEN);
//                return;
//            }
//            String uid = (String) jwt.getPayload("uid");
//            Object cacheToken = redisUtils.get(uid);
//            if (ObjectUtil.isEmpty(cacheToken)) {
//                RespUtil.writeResultByToken(request, response, ResultCode.EXPIRE_TOKEN);
//                return;
//            }
//            if (!StrUtil.equals((String) cacheToken, token)) {
//                RespUtil.writeResultByToken(request, response, ResultCode.VALIDATE_TOKEN);
//                return;
//            }
//            // 如果认证通过，刷新redis缓存时间
//            redisUtils.expire(uid, 3600, TimeUnit.SECONDS);
//            // 将uid放入request用于logout接口
//            request.setAttribute("uid", uid);
            filterChain.doFilter(request, response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    private void validateToken(String token, HttpServletRequest request, HttpServletResponse response) {

    }


}
