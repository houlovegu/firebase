package com.admin.firebase.fcm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.admin.common.constant.ResultCode;
import com.admin.common.response.Result;
import com.admin.config.FireBaseConfig;
import com.admin.firebase.device.entity.DeviceEntity;
import com.admin.firebase.device.service.DeviceEntityService;
import com.admin.firebase.fcm.entity.MsgMultiEntity;
import com.admin.firebase.fcm.entity.MsgSingleEntity;
import com.admin.firebase.fcm.service.FCMService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.firebase.messaging.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FCMServiceImpl implements FCMService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private DeviceEntityService deviceEntityService;
    private final FirebaseMessaging firebaseMessaging = FireBaseConfig.FIREBASEMESSAGING;

    @Override
    public Result sendMsg(MsgSingleEntity message) {
        String token = getToken(message.getUid().get(0));
        if (ObjectUtil.isEmpty(token)) {
            return Result.fail(ResultCode.DEVICE_TOKEN_FOUND_FAIL.getCode(),ResultCode.DEVICE_TOKEN_FOUND_FAIL.getMsg());
        }
        message.setToken(token);
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
        BatchResponse batchResponse = null;
        List<String> failedTokens = new ArrayList<>();
        List<String> uidTokens = getTokens(message.getUid());
        if (CollUtil.isEmpty(uidTokens)) {
            return Result.fail(ResultCode.DEVICE_TOKEN_FOUND_FAIL.getCode(),ResultCode.DEVICE_TOKEN_FOUND_FAIL.getMsg());
        }
        message.setTokens(uidTokens);
        MulticastMessage multicastMessage = getMultiMessage(message);
        try {
            batchResponse = firebaseMessaging.sendMulticast(multicastMessage);
            if (batchResponse.getFailureCount() > 0) {
                // 有发送失败的消息
                List<SendResponse> responses = batchResponse.getResponses();
                for (int i = 0; i < responses.size(); i++) {
                    if (!responses.get(i).isSuccessful()) {
                        failedTokens.add(uidTokens.get(i));
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

    private String getToken(String uid) {
        LambdaQueryWrapper<DeviceEntity> wrapper = Wrappers.lambdaQuery();
        DeviceEntity device = deviceEntityService.getOne(wrapper.eq(DeviceEntity::getUid, uid));
        if (ObjectUtil.isEmpty(device)) {
            return null;
        }
        return device.getRegistryToken();
    }

    private List<String> getTokens(List<String> uid) {
        LambdaQueryWrapper<DeviceEntity> wrapper = Wrappers.lambdaQuery();
        List<DeviceEntity> list = deviceEntityService.list(wrapper.in(DeviceEntity::getUid, uid));
        List<String> tokens = Collections.synchronizedList(new ArrayList<>());
        list.parallelStream().forEach(deviceEntity -> {
            tokens.add(deviceEntity.getRegistryToken());
        });
        return tokens;
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
