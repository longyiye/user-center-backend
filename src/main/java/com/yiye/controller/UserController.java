package com.yiye.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yiye.common.BaseResponse;
import com.yiye.common.ErrorCode;
import com.yiye.common.ResultUtils;
import com.yiye.dto.UserLoginReq;
import com.yiye.dto.UserRegisterReq;
import com.yiye.entity.User;
import com.yiye.exception.BusinessException;
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

import static com.yiye.constant.UserConstant.USER_LOGIN_STATE;
import static com.yiye.constant.UserConstant.ADMIN_ROLE;

/**
 * 用户接口
 *
 * @author longyiye
 * @link <a href="https://github.com/longyiye/user-center-backend></a>
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     *
     * @param userRegisterReq UserRegisterReq
     * @return BaseResponse<Long>
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterReq userRegisterReq) {
        if (userRegisterReq == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterReq.getUserAccount();
        String userPassword = userRegisterReq.getUserPassword();
        String checkPassword = userRegisterReq.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return null;
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(result);
    }

    /**
     * 用户登录
     *
     * @param userLoginReq UserLoginReq
     * @param request      HttpServletRequest
     * @return BaseResponse<User>
     */
    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginReq userLoginReq, HttpServletRequest request) {
        if (userLoginReq == null) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginReq.getUserAccount();
        String userPassword = userLoginReq.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(user);
    }

    /**
     * 查询（仅管理员）
     *
     * @param userName String
     * @param request  HttpServletRequest
     * @return BaseResponse<List<User>>
     */
    @GetMapping("/search")
    public BaseResponse<List<User>> searchUsers(String userName, HttpServletRequest request) {
        if (!isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH, "缺少管理员权限");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(userName)) {
            queryWrapper.like("user_name", userName);
        }
        List<User> userList = userService.list(queryWrapper);
        List<User> list = userList.stream()
                .map(user -> userService.getSafetyUser(user))
                .collect(Collectors.toList());
        return ResultUtils.success(list);
    }

    /**
     * 删除（仅管理员）
     *
     * @param id      long
     * @param request HttpServletRequest
     * @return boolean
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody long id, HttpServletRequest request) {
        if (!isAdmin(request)) throw new BusinessException(ErrorCode.NO_AUTH);
        if (id <= 0) throw new BusinessException(ErrorCode.PARAMS_ERROR);
        boolean result = userService.removeById(id);
        return ResultUtils.success(result);
    }

    /**
     * 是否为管理员
     *
     * @param request HttpServletRequest
     * @return boolean
     */
    private boolean isAdmin(HttpServletRequest request) {
        // 仅管理员可查询
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        return user != null && user.getUserRole() == ADMIN_ROLE;
    }

    /**
     * 获取当前用户
     *
     * @param request HttpServletRequest
     * @return BaseResponse<User>
     */
    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        long userId = currentUser.getId();
        // todo 校验用户是否合法
        User user = userService.getById(userId);
        User safetyUser = userService.getSafetyUser(user);
        return ResultUtils.success(safetyUser);
    }

    /**
     * 用户注销
     *
     * @param request HttpServletRequest
     * @return BaseResponse<Integer>
     */
    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        int result = userService.userLogout(request);
        return ResultUtils.success(result);
    }
}
