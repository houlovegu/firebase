package com.admin.common.handler;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * mybatis-plus 字段自动填充
 */
@Component
public class FiledMetaObjectHandler implements MetaObjectHandler  {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.fillStrategy(metaObject, "createTime", DateTime.now());
        this.fillStrategy(metaObject, "updateTime", DateTime.now());
        this.fillStrategy(metaObject, "createUser", 1l);
        this.fillStrategy(metaObject, "updateUser", 1l);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.fillStrategy(metaObject, "updateTime", DateTime.now());
        this.fillStrategy(metaObject, "updateUser", 1l);
    }
}
