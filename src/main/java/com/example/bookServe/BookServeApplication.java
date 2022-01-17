package com.example.bookServe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//设置启动服务类的注解
@SpringBootApplication
//设置扫描映射文件的注解
@MapperScan("com.example.bookServe.mapper")
public class BookServeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookServeApplication.class, args);
    }

}
