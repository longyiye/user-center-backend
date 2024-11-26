package com.yiye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yiye.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户 Mapper
 *
 * @author longyiye
 * @link <a href="https://github.com/longyiye/user-center-backend></a>
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
