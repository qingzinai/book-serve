package com.example.bookServe.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.bookServe.entity.User;
import com.example.bookServe.mapper.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;


//用户的控制类用户处理数据返回接口


//标识为控制器
@RestController
//设置请求前半段地址
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserMapper userMapper;

    @PostMapping("/login")
    public User testSelectList(@RequestBody User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        System.out.println(user);
        queryWrapper.eq("user_name", user.getUserName())
                .eq("pass_word", user.getPassWord());
        User users =  userMapper.selectOne(queryWrapper);
        return users;
    }
}