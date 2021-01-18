package com.qianfeng.dao;

import com.qianfeng.pojo.Book;
import java.util.List;

public interface BookMapper {
    int deleteByPrimaryKey(Integer bookId);

    int insert(Book record);

    Book selectByPrimaryKey(Integer bookId);

    List<Book> selectAll();

    int updateByPrimaryKey(Book record);

    List<Book> searchBookBySql(String name);

    /**
     * 根据类型id查询商品
     * @param tid
     * @return
     */
    List<Book> getListByTypeId(Integer tid);
}