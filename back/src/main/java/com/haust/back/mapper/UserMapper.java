package com.haust.back.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haust.back.entity.User;


@Mapper
public interface UserMapper extends BaseMapper<User> {
}
