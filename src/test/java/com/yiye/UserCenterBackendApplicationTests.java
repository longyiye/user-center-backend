package com.yiye;

import com.yiye.entity.User;
import com.yiye.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

import java.util.List;

@SpringBootTest
class UserCenterBackendApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        System.out.println("测试查找全部数据");
        List<User> userList = userMapper.selectList(null);
        Assert.isTrue(5 == userList.size(), "");
        userList.forEach(System.out::println);
    }

    @Test
    void testDigest() {
        System.out.println("加密测试");
        final String SALT = "yiye";
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + "123456").getBytes());
        System.out.println(encryptPassword);
    }
}
