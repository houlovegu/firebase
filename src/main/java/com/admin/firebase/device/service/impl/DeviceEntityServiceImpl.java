package com.admin.firebase.device.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.admin.common.response.Result;
import com.admin.firebase.device.entity.DeviceEntity;
import com.admin.firebase.device.entity.DeviceListRequest;
import com.admin.firebase.device.mapper.DeviceEntityMapper;
import com.admin.firebase.device.service.DeviceEntityService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author sky
* @description 针对表【device(设备表)】的数据库操作Service实现
* @createDate 2023-04-28 18:16:34
*/
@Service
public class DeviceEntityServiceImpl extends ServiceImpl<DeviceEntityMapper, DeviceEntity>
    implements DeviceEntityService {

    @Override
    public Result report(DeviceEntity device) {
        LambdaQueryWrapper<DeviceEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(DeviceEntity::getUid, device.getUid());
        DeviceEntity deviceEntity = this.getOne(wrapper);
        if (ObjectUtil.isEmpty(deviceEntity)) {
            // 不存在则插入
            this.save(device);
        } else {
            // 存在则更新
            deviceEntity.setRegistryToken(device.getRegistryToken());
            this.updateById(deviceEntity);
        }
        return Result.ok();
    }

    @Override
    public Result deviceList(DeviceListRequest request) {
        Long pageNo = request.getPageNo();
        Long pageSize = request.getPageSize();
        pageNo = ObjectUtil.isEmpty(pageNo)? 1l:pageNo;
        pageSize = ObjectUtil.isEmpty(pageSize)? 15l:pageSize;
        Page<DeviceEntity> page = new Page(pageNo, pageSize);
        Page<DeviceEntity> result = this.page(page);
        return Result.ok(result);
    }
}




