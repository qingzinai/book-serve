package com.example.bookServe.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pager<T> implements Serializable{

    /**
     * 当前页码
     */
    private long pageIndex;
    /**
     * 每页数量
     */
    private long pageSize;
    /**
     * 总页数
     */
    private long totalPage;
    /**
     * 总数据数量
     */
    private long totalCount;
    /**
     * 当前页数据
     */
    private List<T> dataList;
}