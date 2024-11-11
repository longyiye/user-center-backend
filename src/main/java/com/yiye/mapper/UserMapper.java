package com.yiye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yiye.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
