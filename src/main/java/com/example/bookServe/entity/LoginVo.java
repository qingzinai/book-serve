package com.example.bookServe.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class LoginVo extends  User{
    @TableField(exist = false)
    private String token;
}
