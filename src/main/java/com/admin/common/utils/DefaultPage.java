package com.admin.common.utils;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName DefaultPage
 * @Description TODO
 * @Author sky
 * @Date 2023/5/22 15:37
 * @Version 1.0
 **/
@Data
public class DefaultPage<T> extends Page {

    public DefaultPage(long current, long size) {
        super(current, size);
    }

    public static DefaultPage page() {
        ServletRequestAttributes servletRequestAttributes =  (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        Long pageNo = (Long) request.getAttribute("pageNo");
        Long pageSize = (Long) request.getAttribute("pageSize");
        if (ObjectUtil.isEmpty(pageNo) || ObjectUtil.isEmpty(pageSize)) {
            return new DefaultPage<>(1, 20);
        } else {
            return new DefaultPage<>(pageNo, pageSize);
        }
    }

}
