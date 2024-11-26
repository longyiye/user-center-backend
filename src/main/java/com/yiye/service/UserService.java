package com.yiye.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yiye.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户服务
 *
 * @author longyiye
 * @link <a href="https://github.com/longyiye/user-center-backend></a>
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userAccount   String
     * @param userPassword  String
     * @param checkPassword String
     * @return long
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录
     *
     * @param userAccount  String
     * @param userPassword String
     * @param request      HttpServletRequest
     * @return User
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     *
     * @param originUser User
     * @return User
     */
    User getSafetyUser(User originUser);

    /**
     * 用户注销
     *
     * @param request HttpServletRequest
     * @return int
     */
    int userLogout(HttpServletRequest request);
}
