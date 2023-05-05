package com.admin.common.handler;

import com.admin.common.annotation.ResponseResult;
import com.admin.common.response.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 全局响应数据预处理器
 * 使用RestControllerAdvice和ResponseBodyAdvice
 * 拦截Controller方法默认返回参数，统一处理响应体
 */
@RestControllerAdvice
public class RestResponseHandler implements ResponseBodyAdvice<Object> {

    // 属性名称，用于记录是否标注了ResponseResult注解
    public static final String RESPONSE_RESULT_ATTR= "RESPONSE_RESULT_ATTR";

    @Resource
    private ObjectMapper objectMapper;

    /**
     * 判断是否需要执行beforeBodyWrite方法，true为执行；false为不执行
     * @param returnType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        // 判断请求是否有注解标记
        ResponseResult responseResultAnn = (ResponseResult) request.getAttribute(RESPONSE_RESULT_ATTR);

        return responseResultAnn != null;
    }

    /**
     * 对返回值包装处理
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 已经返回的是Result类型对象了，直接返回，比如全局异常处理之后直接返回了
        if (body instanceof Result) {
            return (Result) body;
        } else if (body instanceof  String) {
            // 如果Controller直接返回String时，需要转换为Json，因为强化的是RestController
            return objectMapper.writeValueAsString(Result.ok(body));
        }

        return Result.ok(body);
    }
}
