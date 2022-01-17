package com.example.bookServe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bookServe.entity.User;
import org.springframework.stereotype.Repository;

//用户的映射接口
@Repository
public interface UserMapper extends BaseMapper<User> {

}
