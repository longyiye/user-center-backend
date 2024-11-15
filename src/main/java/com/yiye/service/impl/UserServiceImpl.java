package com.yiye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiye.entity.User;
import com.yiye.mapper.UserMapper;
import com.yiye.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author longyiye
 * @description 针对表【t_user(用户表)】的数据库操作Service实现
 * @createDate 2024-11-15 11:01:47
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        /* 1. 校验 */
        // 账户密码非空
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) return -1;
        // 账户不小于 4 位
        if (userAccount.length() < 4) return -1;
        // 密码不小于 8 位
        if (userPassword.length() < 8 || checkPassword.length() < 8) return -1;
        // 账户不能包含特殊字符
        String validateRegExp = "[^a-zA-Z0-9]";
        Matcher matcher = Pattern.compile(validateRegExp).matcher(userAccount);
        if (matcher.find()) return -1;
        // 密码和校验密码需要相同
        if (!userPassword.equals(checkPassword)) return -1;
        // 账户不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount);
        Long count = userMapper.selectCount(queryWrapper);
        if (count > 0) return -1;

        /* 2. 加密 */
        final String SALT = "yiye";
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        /* 3. 插入数据 */
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        boolean saveResult = this.save(user);
        if (!saveResult) return -1;
        return user.getId();
    }
}




