package com.haust.back.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haust.back.entity.Comment;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
//    @Select("SELECT comment.c_id,comment.user_id,comment.new_id,comment.c_cnt,c_date,user.user_name FROM comment,user where new_id = #{nid} AND comment.user_id= user.user_id")
//    public List<Comment> getCommentById(int nid);
}
