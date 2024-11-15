package com.yiye.service;

import com.yiye.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    void testAddUser() {
        User user = new User();
        user.setUserName("yiye");
        user.setUserAccount("123456");
        user.setAvatarUrl("https://yiye.png");
        user.setGender(0);
        user.setUserPassword("123456");
        user.setPhone("17835110025");
        user.setEmail("2226548812@qq.com");
        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);  // 断言 result 为 true
    }

    @Test
    void userRegister() {
        System.out.println("注册功能测试");
        String userAccount = "";
        String userPassword = "";
        String checkPassword = "";
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);

        userAccount = "";
        userPassword = "12345678";
        checkPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);

        userAccount = "yiye";
        userPassword = "";
        checkPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);

        userAccount = "yiye";
        userPassword = "12345678";
        checkPassword = "";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);

        userAccount = "yiye";
        userPassword = "12345678";
        checkPassword = "123456789";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);

        userAccount = "yi ye";
        userPassword = "12345678";
        checkPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);

        userAccount = "yiye";
        userPassword = "12345678";
        checkPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertTrue(result > 0);
    }
}