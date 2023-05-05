package com.admin.common.annotation;

import java.lang.annotation.*;

/**
 * ResponseResult的注解类
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface ResponseResult {
}
