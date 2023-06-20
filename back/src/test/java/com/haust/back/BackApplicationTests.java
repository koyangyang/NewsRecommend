package com.haust.back;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haust.back.entity.Comment;
import com.haust.back.entity.Newstatus;
import com.haust.back.entity.User;
import com.haust.back.entity.Useroperationnew;
import com.haust.back.mapper.*;

@SpringBootTest
class BackApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NewMapper newMapper;
    @Autowired
    private UseroperationnewMapper useroperationnewMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private NewstatusMapper newstatusMapper;
    @Test
    public void test() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }
    @Test
    public void GetHotNewsTest() {
        List<Useroperationnew> hotsnews = useroperationnewMapper.getHotNews();
        hotsnews.forEach(System.out::println);
    }
    @Test
    public void GetCommentTest() {
        List<Comment> comments = commentMapper.selectList(null);
        System.out.println(comments);
    }
    @Test
    public void InsertCommentTest() {
        Comment comment = new Comment();
        comment.setUserId(1);
        comment.setNewId(1);
        comment.setCCnt("测试评论");
        java.util.Date date = new Date();//获得当前时间
        Timestamp t = new Timestamp(date.getTime());//将时间转换成Timestamp类型，这样便可以存入到Mysql数据库中
        comment.setCDate(t);
        commentMapper.insert(comment);
        System.out.println(comment);
    }
    @Test
    public void CommentTest()
    {
        List<Comment> comment=commentMapper.selectList(null);
        System.out.println(comment);
    }
    @Test
    public void GetStatusTest()
    {
        System.out.println(newstatusMapper.selectOne(new QueryWrapper<Newstatus>().eq("new_id", 1)));
    }
}
