package com.admin.firebase.user.service;

import com.admin.firebase.user.entity.Socialuser;
import com.baomidou.mybatisplus.extension.service.IService;
import me.zhyd.oauth.model.AuthUser;

/**
* @author sky
* @description 针对表【socialuser】的数据库操作Service
* @createDate 2023-05-23 13:41:16
*/
public interface SocialuserService extends IService<Socialuser> {

    /**
     * @Author sky
     * @Description //创建第三方用户
     * @Date 2023/5/23 14:02
     * @Param [socialuser, authUser]
     * @return void
     **/
    void createSocialUserByAuthUser(Socialuser socialuser, AuthUser authUser);
}
