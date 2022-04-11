package com.broadtech.databus.soar.pojo;

import lombok.Data;

import java.util.List;

/**
 * @Author: leo.j
 * @desc:   分页包装
 * @Date: 2022/3/29 11:39 上午
 */
@Data
public class PageChunk<T> {
    /**
     * 内容
     */
    private List<T> content;
    /**
     * 总页数
     */
    private int totalPages;
    /**
     * 总记录数
     */
    private long totalElements;
    /**
     * 当前页码
     */
    private int pageNumber;
}
