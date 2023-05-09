package com.admin.firebase.user.service;


import com.admin.common.response.Result;
import com.admin.firebase.user.entity.ResetRequest;
import com.admin.firebase.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author sky
 * @description 针对表【user(用户表)】的数据库操作Service
 * @createDate 2023-05-04 14:33:19
 */
public interface UserService extends IService<User> {

    /**
     * @Author sky
     * @Description 登录
     * @Date 2023/5/9 9:00
     * @Param [user]
     * @return com.admin.common.response.Result
     **/
    Result login(User user);

    /**
     * @Author sky
     * @Description 注册
     * @Date 2023/5/9 9:01
     * @Param [user]
     * @return com.admin.common.response.Result
     **/
    Result register(User user);

    /**
     * @Author sky
     * @Description 重置密码
     * @Date 2023/5/9 9:01
     * @Param [reset, session]
     * @return com.admin.common.response.Result
     **/
    Result reset(ResetRequest reset, HttpSession session);

    /**
     * @Author sky
     * @Description 退出登录
     * @Date 2023/5/9 9:01
     * @Param [request]
     * @return com.admin.common.response.Result
     **/
    Result logout(HttpServletRequest request);
}
