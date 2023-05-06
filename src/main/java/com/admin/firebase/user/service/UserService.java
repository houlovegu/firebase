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

    Result login(User user);

    Result register(User user);

    Result reset(ResetRequest reset, HttpSession session);

    Result logout(HttpServletRequest request);
}
