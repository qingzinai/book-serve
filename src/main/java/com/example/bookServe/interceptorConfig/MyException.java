package com.example.bookServe.interceptorConfig;

import lombok.Data;
import org.apache.ibatis.jdbc.Null;

@Data
public class MyException extends RuntimeException {
    public MyException(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private String status;
    private String msg;
}
