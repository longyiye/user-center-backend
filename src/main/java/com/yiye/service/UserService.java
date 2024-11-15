package com.yiye.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yiye.entity.User;

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
}
