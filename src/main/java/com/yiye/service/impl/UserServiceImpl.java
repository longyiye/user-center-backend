package com.yiye.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiye.entity.User;
import com.yiye.mapper.UserMapper;
import com.yiye.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author longyiye
 * @description 针对表【t_user(用户表)】的数据库操作Service实现
 * @createDate 2024-11-15 11:01:47
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

}




