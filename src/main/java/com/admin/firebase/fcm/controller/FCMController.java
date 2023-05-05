package com.admin.firebase.fcm.controller;

import com.admin.common.response.Result;
import com.admin.firebase.fcm.entity.MsgMultiEntity;
import com.admin.firebase.fcm.entity.MsgSingleEntity;
import com.admin.firebase.fcm.service.FCMService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "FCM消息模块")
@RestController
@RequestMapping("/fcm")
public class FCMController {

    @Resource
    FCMService fcmService;

    @ApiOperation("发送消息")
    @PostMapping("/sendMsg")
    public Result sendMsg(@RequestBody MsgSingleEntity message) {
        return fcmService.sendMsg(message);
    }

    @ApiOperation("批量发送消息(同一消息,多设备发送，类似广播)")
    @PostMapping("/batch/token/sendMsg")
    public Result batchSendMsg(@RequestBody MsgMultiEntity message) {
        return fcmService.batchSendMsg(message);
    }

    @ApiOperation("批量发送消息(多条消息,同一设备发送)")
    @PostMapping("/batch/msg/sendMsg")
    public Result batchSendMsgList(@RequestBody List<MsgSingleEntity> messages) {
        return fcmService.batchSendMsgList(messages);
    }
}
