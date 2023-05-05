package com.admin.firebase.fcm.service;

import com.admin.common.response.Result;
import com.admin.firebase.fcm.entity.MsgMultiEntity;
import com.admin.firebase.fcm.entity.MsgSingleEntity;

import java.util.List;

public interface FCMService {
    Result sendMsg(MsgSingleEntity message);

    Result batchSendMsg(MsgMultiEntity message);

    Result batchSendMsgList(List<MsgSingleEntity> messages);
}
