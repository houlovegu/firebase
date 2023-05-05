package com.admin.common.interceptor;

import com.admin.common.annotation.ResponseResult;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * ResponseResult拦截器
 */
public class RestResponseInterceptor implements HandlerInterceptor {

    // 属性名称，用于记录是否标注了ResponseResult注解
    public static final String RESPONSE_RESULT_ATTR= "RESPONSE_RESULT_ATTR";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();

            // 判断是否在类对象上添加了注解
            if (clazz.isAnnotationPresent(ResponseResult.class)) {
                // 设置属性，值为该注解
                request.setAttribute(RESPONSE_RESULT_ATTR, clazz.getAnnotation(ResponseResult.class));
            } else if (method.isAnnotationPresent(ResponseResult.class)){ // 是否在方法上添加了注解
                request.setAttribute(RESPONSE_RESULT_ATTR, method.getAnnotation(ResponseResult.class));
            }
        }
        return true;
    }
}
