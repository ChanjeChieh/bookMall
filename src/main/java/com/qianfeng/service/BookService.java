package com.qianfeng.service;

import com.qianfeng.pojo.Book;
import com.qianfeng.util.ResultVo;

import java.util.List;

public interface BookService {

    ResultVo updateBookById(Book book);

    List<Book> getListByTypeId(Integer tid);

    ResultVo deleteBookById(Integer id);

    List<Book> selectAll();

    /**
     * 根据图书id查询图书详情
     * @param bid
     * @return
     */
    Book getBookById(Integer bid);

    ResultVo searchBookBySql(String searchBook);
}
