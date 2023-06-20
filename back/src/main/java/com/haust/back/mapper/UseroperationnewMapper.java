package com.haust.back.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haust.back.entity.Useroperationnew;
@Mapper
public interface UseroperationnewMapper extends BaseMapper<Useroperationnew>{
    //查询并计算返回不同用户的历史行为数据对每条新闻的value值
    @Select("SELECT user_id,new_id,SUM( CASE operation_type WHEN 0 THEN 3 WHEN 1 THEN 3 WHEN 2 THEN 5 ELSE 0 END ) AS value FROM useroperationnew GROUP BY user_id,new_id ORDER BY user_id")
    public List<Useroperationnew> getAllUserPreference();
    //查询并计算返回value值排名前20的新闻
    @Select("SELECT user_id,new_id,SUM( CASE operation_type WHEN 0 THEN 3 WHEN 1 THEN 3 WHEN 2 THEN 5 ELSE 0 END ) AS value FROM useroperationnew GROUP BY user_id,new_id ORDER BY value DESC LIMIT 0,20")
    public List<Useroperationnew> getHotNews();
}
