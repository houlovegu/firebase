package com.admin.firebase.fcm.service;

import com.admin.common.response.Result;
import com.admin.firebase.fcm.entity.MsgMultiEntity;
import com.admin.firebase.fcm.entity.MsgSingleEntity;

import java.util.List;

public interface FCMService {

    /**
     * @Author sky
     * @Description fcm发送消息
     * @Date 2023/5/9 8:58
     * @Param [message]
     * @return com.admin.common.response.Result
     **/
    Result sendMsg(MsgSingleEntity message);

    /**
     * @Author sky
     * @Description 批量发送消息[多设备发送同一个消息]
     * @Date 2023/5/9 8:58
     * @Param [message]
     * @return com.admin.common.response.Result
     **/
    Result batchSendMsg(MsgMultiEntity message);

    /**
     * @Author sky
     * @Description 批量发送消息[多条消息,同一设备发送]
     * @Date 2023/5/9 8:59
     * @Param [messages]
     * @return com.admin.common.response.Result
     **/
    Result batchSendMsgList(List<MsgSingleEntity> messages);
}
