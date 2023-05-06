package com.admin.firebase.device.service;

import com.admin.common.response.Result;
import com.admin.firebase.device.entity.DeviceEntity;
import com.admin.firebase.device.entity.DeviceListRequest;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author sky
* @description 针对表【device(设备表)】的数据库操作Service
* @createDate 2023-04-28 18:16:34
*/
public interface DeviceEntityService extends IService<DeviceEntity> {

    Result report(DeviceEntity device);

    Result deviceList(DeviceListRequest request);

    Result deviceUidList();
}
