package com.yiye.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yiye.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author longyiye
 * @description 针对表【t_user(用户表)】的数据库操作Service
 * @createDate 2024-11-15 11:01:47
 */
public interface UserService extends IService<User> {

    /**
     * userRegister
     *
     * @param userAccount   String
     * @param userPassword  String
     * @param checkPassword String
     * @return long
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * userLogin
     *
     * @param userAccount  String
     * @param userPassword String
     * @param request      HttpServletRequest
     * @return User
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * getSafetyUser
     *
     * @param OriginUser User
     * @return User
     */
    User getSafetyUser(User OriginUser);

    /**
     * userLogout
     *
     * @param request HttpServletRequest
     * @return void
     */
    int userLogout(HttpServletRequest request);
}
