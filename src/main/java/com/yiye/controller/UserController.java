package com.yiye.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yiye.dto.UserLoginReq;
import com.yiye.dto.UserRegisterReq;
import com.yiye.entity.User;
import com.yiye.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static com.yiye.constant.UserConstant.ADMIN_ROLE;
import static com.yiye.constant.UserConstant.USER_LOGIN_STATE;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Long userRegister(@RequestBody UserRegisterReq userRegisterReq) {
        if (userRegisterReq == null) return null;

        String userAccount = userRegisterReq.getUserAccount();
        String userPassword = userRegisterReq.getUserPassword();
        String checkPassword = userRegisterReq.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return null;
        }
        return userService.userRegister(userAccount, userPassword, checkPassword);
    }

    @PostMapping("/login")
    public User userLogin(@RequestBody UserLoginReq userLoginReq, HttpServletRequest request) {
        if (userLoginReq == null) return null;

        String userAccount = userLoginReq.getUserAccount();
        String userPassword = userLoginReq.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        return userService.userLogin(userAccount, userPassword, request);
    }

    @GetMapping("/search")
    public List<User> searchUsers(String userName, HttpServletRequest request) {
        if (!isAdmin(request)) return null;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(userName)) {
            queryWrapper.like("user_name", userName);
        }
        List<User> userList = userService.list(queryWrapper);
        return userList.stream()
                .map(user -> userService.getSafetyUser(user))
                .collect(Collectors.toList());
    }

    @PostMapping("/delete")
    public boolean deleteUser(@RequestBody long id, HttpServletRequest request) {
        if (!isAdmin(request)) return false;

        if (id <= 0) return false;
        return userService.removeById(id);
    }

    private boolean isAdmin(HttpServletRequest request) {
        // 仅管理员可查询
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        return user != null && user.getUserRole() == ADMIN_ROLE;
    }
}
