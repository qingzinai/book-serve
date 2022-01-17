package com.example.bookServe.tool;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//返回数据的包装类
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Result<T> {
    private String status;
    private String message;
    private T data;
    private String time;

    public <T> Result success(String time, T data) {
        return this.success(time, data);
    }

    public <T> Result success(String msg, String time, T data) {

        return this.success("OK", time, msg, data);
    }

    public <T> Result success(String status, String msg, String time, T data) {
        Result<T> tResult = new Result<>();
        tResult.setMessage(msg);
        tResult.setStatus(status);
        tResult.setTime(time);
        tResult.setData(data);
        return tResult;
    }
}
