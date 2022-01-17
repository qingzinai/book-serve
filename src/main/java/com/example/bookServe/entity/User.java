package com.example.bookServe.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//用户的实体类

//创建构造方法和getter和setter的注解
@Data
//创建所有参数的构造方法
@AllArgsConstructor
//创建无参构造方法
@NoArgsConstructor
//重写ToString方法
@ToString

//指定表名如不指定默认为类名
@TableName("user")
public class User {
    private Integer id;
    @TableField("user_name")
    private String userName;
    @TableField("pass_word")
    private String passWord;
}
