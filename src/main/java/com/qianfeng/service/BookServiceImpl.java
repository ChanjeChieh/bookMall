package com.qianfeng.service;

import com.qianfeng.dao.BookMapper;
import com.qianfeng.pojo.Book;
import com.qianfeng.util.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public ResultVo updateBookById(Book book) {
        if(bookMapper.updateByPrimaryKey(book) == 1){
            return ResultVo.success("修改成功");
        }else{
            return ResultVo.error("修改失败");
        }
    }

    @Override
    public List<Book> getListByTypeId(Integer tid) {
        List<Book> bookList = bookMapper.getListByTypeId(tid);
        return bookList;
    }

    @Override
    public ResultVo deleteBookById(Integer id) {
        if(bookMapper.deleteByPrimaryKey(id) == 1){
            return ResultVo.success("删除成功");
        }else{
            return ResultVo.error("删除失败");
        }
    }

    @Override
    public List<Book> selectAll() {
        List<Book> bookList = bookMapper.selectAll();
        return bookList;
    }

    @Override
    public Book getBookById(Integer bid) {
        return bookMapper.selectByPrimaryKey(bid);
    }

    @Override
    public ResultVo searchBookBySql(String searchBook) {
        List<Book> bookList = bookMapper.searchBookBySql(searchBook);
        return ResultVo.success("success", bookList);
    }
}
