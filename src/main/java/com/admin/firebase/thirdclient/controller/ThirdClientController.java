package com.admin.firebase.thirdclient.controller;

import com.admin.common.response.Result;
import com.admin.firebase.thirdclient.entity.AddClientRequest;
import com.admin.firebase.thirdclient.entity.ClientListRequest;
import com.admin.firebase.thirdclient.service.SysRegisteredClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName ThirdClientController
 * @Description TODO
 * @Author sky
 * @Date 2023/5/22 14:37
 * @Version 1.0
 **/
@Api(tags = "第三方认证模块")
@RestController
@RequestMapping("/third/oauth")
public class ThirdClientController {

    @Resource
    private SysRegisteredClientService sysRegisteredClientService;

    @ApiOperation("客户端列表")
    @PostMapping("/list")
    public Result list(@RequestBody ClientListRequest clientListRequest) {
        return sysRegisteredClientService.list(clientListRequest);
    }

    @ApiOperation("添加客户端")
    @PostMapping("/add")
    public Result addClient(@RequestBody AddClientRequest addClientRequest) {
        return sysRegisteredClientService.add(addClientRequest);
    }
}
