package com.admin.firebase.device.controller;

import com.admin.common.response.Result;
import com.admin.firebase.device.entity.DeviceEntity;
import com.admin.firebase.device.entity.DeviceListRequest;
import com.admin.firebase.device.service.DeviceEntityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "设备模块")
@RestController
@RequestMapping("/device")
public class DeviceController {

    @Resource
    DeviceEntityService deviceEntityService;

    @ApiOperation("设备上报")
    @PostMapping("/report")
    public Result deviceReport(@Validated(DeviceEntity.Report.class) @RequestBody DeviceEntity device) {
        return deviceEntityService.report(device);
    }

    @ApiOperation("设备列表")
    @PostMapping("/list")
    public Result deviceList(@RequestBody DeviceListRequest request) {
        return deviceEntityService.deviceList(request);
    }
}
