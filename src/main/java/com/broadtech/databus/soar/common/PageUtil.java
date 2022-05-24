package com.broadtech.databus.soar.common;

import java.util.List;

/**
 * @program sdc-databus-soar
 * @description: 分页工具类
 * @author: 蒋青松
 * @create: 2022/05/24 10:28
 */
public class PageUtil {

    public<T> List<T> startPage(List<T> list, Integer pageNum, Integer pageSize) {

        if(list == null){
            return null;
        }
        if(list.size() == 0){
            return null;
        }

        Integer count = list.size(); //记录总数
        Integer pageCount = 0; //页数
        if (count % pageSize == 0) {
            pageCount = count / pageSize;
        } else {
            pageCount = count / pageSize + 1;
        }

        int fromIndex = 0; //开始索引
        int toIndex = 0; //结束索引

        if(pageNum > pageCount){
            pageNum = pageCount;
        }
        if (!pageNum.equals(pageCount)) {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = fromIndex + pageSize;
        } else {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = count;
        }

        List<T> pageList = list.subList(fromIndex, toIndex);

        return pageList;
    }

}
