package com.yiye.constant;

public interface UserConstant {
    /**
     * 用户登录态
     */
    String USER_LOGIN_STATE = "userLoginState";

    /**
     * 盐值：用于混淆代码
     */
    String SALT = "yiye";

    /**
     * 默认权限
     */
    int DEFAULT_ROLE = 0;

    /**
     * 管理员权限
     */
    int ADMIN_ROLE = 1;
}