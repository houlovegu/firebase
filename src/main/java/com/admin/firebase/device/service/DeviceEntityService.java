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

    /**
     * @Author sky
     * @Description 设备上报
     * @Date 2023/5/9 8:57
     * @Param [device]
     * @return com.admin.common.response.Result
     **/
    Result report(DeviceEntity device);

    /**
     * @Author sky
     * @Description 获取设备列表
     * @Date 2023/5/9 8:57
     * @Param [request]
     * @return com.admin.common.response.Result
     **/
    Result deviceList(DeviceListRequest request);

    /**
     * @Author sky
     * @Description 获取设备uid列表
     * @Date 2023/5/9 8:58
     * @Param []
     * @return com.admin.common.response.Result
     **/
    Result deviceUidList();
}
