package com.admin.firebase.fcm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.admin.common.response.Result;
import com.admin.config.FireBaseConfig;
import com.admin.firebase.fcm.entity.MsgMultiEntity;
import com.admin.firebase.fcm.entity.MsgSingleEntity;
import com.admin.firebase.fcm.service.FCMService;
import com.google.firebase.messaging.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class FCMServiceImpl implements FCMService {

    private final FirebaseMessaging firebaseMessaging = FireBaseConfig.FIREBASEMESSAGING;

    @Override
    public Result sendMsg(MsgSingleEntity message) {
        Message build = getMessage(message);
        String send;
        try {
            send = firebaseMessaging.send(build);
        } catch (FirebaseMessagingException e) {
            log.info("firebaseMessaging send failed : ", e);
            throw new RuntimeException(e);
        }
        return Result.ok(send);
    }

    @Override
    public Result batchSendMsg(MsgMultiEntity message) {
        List<String> tokens = message.getTokens();
        BatchResponse batchResponse = null;
        List<String> failedTokens = new ArrayList<>();
        MulticastMessage multicastMessage = getMultiMessage(message);
        try {
            batchResponse = firebaseMessaging.sendMulticast(multicastMessage);
            if (batchResponse.getFailureCount() > 0) {
                // 有发送失败的消息
                List<SendResponse> responses = batchResponse.getResponses();
                for (int i = 0; i < responses.size(); i++) {
                    if (!responses.get(i).isSuccessful()) {
                        failedTokens.add(tokens.get(i));
                    }
                }
                log.info("List of tokens that caused failures:", failedTokens);
            }
        } catch (FirebaseMessagingException e) {
            log.info("firebaseMessaging send failed : ", e);
            throw new RuntimeException(e);
        }
        return Result.ok(failedTokens);
    }

    @Override
    public Result batchSendMsgList(List<MsgSingleEntity> messages) {
        List<Message> fireMessageList = new ArrayList<>();
        for (MsgSingleEntity msg:messages) {
            Message message = getMessage(msg);
            fireMessageList.add(message);
        }
        try {
            BatchResponse batchResponse = firebaseMessaging.sendAll(fireMessageList);
            log.info("firebaseMessaging send success msg count: {}, send failed msg count : {}", batchResponse.getSuccessCount(), batchResponse.getFailureCount());
        } catch (FirebaseMessagingException e) {
            log.info("firebaseMessaging send failed : ", e);
            throw new RuntimeException(e);
        }
        return Result.ok();
    }

    private static MulticastMessage getMultiMessage(MsgMultiEntity message) {
        MulticastMessage.Builder builder = MulticastMessage.builder();
        if (CollectionUtil.isNotEmpty(message.getData())) {
            builder.putAllData(message.getData());
        }
        if (ObjectUtil.isNotEmpty(message.getNotification())) {
            Notification.Builder notBd = Notification.builder();
            BeanUtil.copyProperties(message.getNotification(), notBd,true);
            builder.setNotification(notBd.build());
        }
        if (ObjectUtil.isNotEmpty(message.getAndroidConfig())) {
            AndroidConfig.Builder andBd = AndroidConfig.builder();
            BeanUtil.copyProperties(message.getAndroidConfig(), andBd,true);
            builder.setAndroidConfig(andBd.build());
        }
        if (ObjectUtil.isNotEmpty(message.getApnsConfig())) {
            ApnsConfig.Builder apBd = ApnsConfig.builder();
            BeanUtil.copyProperties(message.getApnsConfig(), apBd, true);
            builder.setApnsConfig(apBd.build());
        }
        if (ObjectUtil.isNotEmpty(message.getWebpushConfig())) {
            WebpushConfig.Builder weBd = WebpushConfig.builder();
            BeanUtil.copyProperties(message.getWebpushConfig(), weBd, true);
            builder.setWebpushConfig(weBd.build());
        }
        if (ObjectUtil.isNotEmpty(message.getFcmOptions())) {
            FcmOptions.Builder fcmBd = FcmOptions.builder();
            BeanUtil.copyProperties(message.getFcmOptions(), fcmBd, true);
            builder.setFcmOptions(fcmBd.build());
        }
        if (CollectionUtil.isNotEmpty(message.getTokens())) {
            builder.addAllTokens(message.getTokens());
        }
        MulticastMessage build = builder.build();
        return build;
    }

    private static Message getMessage(MsgSingleEntity message) {
        Message.Builder builder = Message.builder();
        if (CollectionUtil.isNotEmpty(message.getData())) {
            builder.putAllData(message.getData());
        }
        if (ObjectUtil.isNotEmpty(message.getNotification())) {
            Notification.Builder notBd = Notification.builder();
            BeanUtil.copyProperties(message.getNotification(), notBd,true);
            builder.setNotification(notBd.build());
        }
        if (ObjectUtil.isNotEmpty(message.getAndroidConfig())) {
            AndroidConfig.Builder andBd = AndroidConfig.builder();
            BeanUtil.copyProperties(message.getAndroidConfig(), andBd,true);
            builder.setAndroidConfig(andBd.build());
        }
        if (ObjectUtil.isNotEmpty(message.getApnsConfig())) {
            ApnsConfig.Builder apBd = ApnsConfig.builder();
            BeanUtil.copyProperties(message.getApnsConfig(), apBd, true);
            builder.setApnsConfig(apBd.build());
        }
        if (ObjectUtil.isNotEmpty(message.getWebpushConfig())) {
            WebpushConfig.Builder weBd = WebpushConfig.builder();
            BeanUtil.copyProperties(message.getWebpushConfig(), weBd, true);
            builder.setWebpushConfig(weBd.build());
        }
        if (ObjectUtil.isNotEmpty(message.getFcmOptions())) {
            FcmOptions.Builder fcmBd = FcmOptions.builder();
            BeanUtil.copyProperties(message.getFcmOptions(), fcmBd, true);
            builder.setFcmOptions(fcmBd.build());
        }
        if (StrUtil.isNotBlank(message.getToken())) {
            builder.setToken(message.getToken());
        }
        if (StrUtil.isNotBlank(message.getCondition())) {
            builder.setCondition(message.getCondition());
        }
        if (ObjectUtil.isNotEmpty(message.getTopic())) {
            builder.setTopic(message.getTopic());
        }
        Message build = builder.build();
        return build;
    }
}
