package com.yiye.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户注册请求体
 *
 * @author longyiye
 * @link <a href="https://github.com/longyiye/user-center-backend></a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterReq implements Serializable {
    private static final long serialVersionUID = -958949990351762138L;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 校验密码
     */
    private String checkPassword;
}
