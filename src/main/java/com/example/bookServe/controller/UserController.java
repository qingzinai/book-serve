package com.example.bookServe.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.bookServe.entity.User;
import com.example.bookServe.mapper.UserMapper;
import com.example.bookServe.tool.JwtTool;
import com.example.bookServe.tool.Result;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.util.MapUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
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

    @PostMapping("/login")
    public Result testSelectList(@RequestBody User user, HttpServletRequest request) {
        Long startTime = (Long)request.getAttribute("startTime");
        System.out.println(startTime);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", user.getUserName())
                .eq("pass_word", user.getPassWord());
        User users = userMapper.selectOne(queryWrapper);
        String code = users==null ? "-1" : "1";
        String msg = users==null ? "失败" : "成功";
        LoginVo loginVo = new LoginVo();
        BeanUtils.copyProperties(users,loginVo);
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("mobile", "1333333333");
        claims.put("id", "2");
        String token = new JwtTool().createToken(claims);
        loginVo.setToken(token);
        String time= (System.currentTimeMillis()-startTime)+"ms";
        Result<LoginVo> result =  new Result<LoginVo>().success(code,msg,time,loginVo);
        return result;
    }
}