package com.admin.firebase.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.admin.firebase.user.entity.Socialuser;
import com.admin.firebase.user.service.SocialuserService;
import com.admin.firebase.user.mapper.SocialuserMapper;
import me.zhyd.oauth.model.AuthUser;
import org.springframework.stereotype.Service;

/**
* @author sky
* @description 针对表【socialuser】的数据库操作Service实现
* @createDate 2023-05-23 13:41:16
*/
@Service
public class SocialuserServiceImpl extends ServiceImpl<SocialuserMapper, Socialuser>
    implements SocialuserService{

    @Override
    public void createSocialUserByAuthUser(Socialuser socialuser, AuthUser authUser) {
        if (ObjectUtil.isEmpty(socialuser)) {
            socialuser = new Socialuser();
        }
        BeanUtil.copyProperties(authUser, socialuser, true);
        BeanUtil.copyProperties(authUser.getToken(), socialuser, true);
        this.saveOrUpdate(socialuser);
    }
}




