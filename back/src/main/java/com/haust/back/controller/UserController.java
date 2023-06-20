package com.haust.back.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haust.back.entity.User;
import com.haust.back.mapper.UserMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @ApiOperation("登录接口，需传入用户账号和密码")
    @PostMapping("/user/login")
    public User login(@RequestBody User user) {
        return userMapper
            .selectOne(new QueryWrapper<User>()
                    .eq("user_name", user.getUserName())
                    .eq("user_pwd", user.getUserPwd()));
    }

    @ApiOperation("对所有的用户信息进行分页查询，默认每次查询5条数据")
    @GetMapping("/user/{id}/{size}")
    public IPage getUserPage(@PathVariable int id, @PathVariable int size) {
        Page<User> page = new Page<>(id, size);
        IPage iPage = userMapper.selectPage(page, null);
        return iPage;
    }

    @ApiOperation("查询指定用户的详细内容，需传入用户id")
    @GetMapping("/user/{id}")
    public User getUserDetailById(@PathVariable Integer id) {
        return userMapper.selectById(id);
    }

    @ApiOperation("添加一条用户，需传入用户的详细内容")
    @PostMapping("/user/insert")
    public User insertUser(User user) {
        User nuser = userMapper.selectOne(new QueryWrapper<User>().eq("user_name", user.getUserName()));
        if (nuser != null)
            return null;
        else {
            userMapper.insert(user);
            return user;
        }
    }

    @ApiOperation("更新一条用户信息，根据传入的用户id进行匹配，需传入更新用户的详细内容")
    @PutMapping("/user/update")
    public User updateUser(User user) {
        int num = userMapper.update(user, new QueryWrapper<User>().eq("user_id", user.getUserId()));
        return num == 1 ? user : null;
    }

    @ApiOperation("删除一条用户信息，需传入用户id进行匹配")
    @DeleteMapping("/user/{id}")
    public int deleteById(@PathVariable Integer id) {
        return userMapper.deleteById(id);
    }

}
