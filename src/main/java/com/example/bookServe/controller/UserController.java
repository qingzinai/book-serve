package com.example.bookServe.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bookServe.entity.Pager;
import com.example.bookServe.entity.User;
import com.example.bookServe.mapper.UserMapper;
import com.example.bookServe.tool.JwtTool;
import com.example.bookServe.tool.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.bookServe.entity.LoginVo;

//用户的控制类用户处理数据返回接口


//标识为控制器
@RestController
//设置请求前半段地址
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserMapper userMapper;

    //登录
    @PostMapping("/login")
    public Result selectList(@RequestBody User user, HttpServletRequest request) {
        Long startTime = (Long) request.getAttribute("startTime");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", user.getUserName())
                .eq("pass_word", user.getPassWord());
        User users = userMapper.selectOne(queryWrapper);
        String code = users == null ? "-1" : "1";
        String msg = users == null ? "失败" : "成功";
        LoginVo loginVo = new LoginVo();
        BeanUtils.copyProperties(users, loginVo);
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("user", loginVo.getUserName());
        String token = new JwtTool().createToken(claims);
        loginVo.setToken(token);
        String time = (System.currentTimeMillis() - startTime) + "ms";
        Result<LoginVo> result = new Result<LoginVo>().success(code, msg, time, loginVo);
        return result;
    }

    //分页查询用户列表
    @GetMapping("/userList")
    public Result getUserList(@RequestBody Pager pager, HttpServletRequest request) {
        Long startTime = (Long) request.getAttribute("startTime");
        String key = request.getParameter("key");
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.select("id", "user_name", "pass_word", "tel")
                .like("user_name", key);
        IPage<User> page = new Page<>(pager.getPageIndex(), pager.getPageSize());
        IPage<User> resultPage = userMapper.selectPage(page, qw);
        List<User> records = resultPage.getRecords();
        Pager<User> userPage = new Pager<>(resultPage.getCurrent(), resultPage.getSize(), resultPage.getPages(), resultPage.getTotal(), records);
        String time = (System.currentTimeMillis() - startTime) + "ms";
        Result<Pager> result = new Result<Pager>().success("1", "成功", time, userPage);
        return result;
    }


    @PostMapping("test")
    public String test(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        return new String("测试数据".getBytes(), StandardCharsets.UTF_8);
    }
}