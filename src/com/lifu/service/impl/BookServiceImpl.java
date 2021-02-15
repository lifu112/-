package com.lifu.service.impl;

import com.lifu.bean.Book;
import com.lifu.bean.Page;
import com.lifu.dao.BookDAO;
import com.lifu.dao.impl.BookDAOImpl;
import com.lifu.service.BookService;

import java.util.List;

/**
 * @Description
 * @Author lifu
 * @Date 2021/1/29 19:28
 */
public class BookServiceImpl implements BookService {
    private BookDAO bookDAO = new BookDAOImpl();
    @Override
    public void addBook(Book book) {
        bookDAO.addBook(book);
    }

    @Override
    public void deleteBook(Integer id) {
        bookDAO.deleteBook(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDAO.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDAO.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDAO.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<Book>();
        //总共多少本书
        Integer pageTotalCount = bookDAO.queryPageTotalCount();
        //总页数
        int pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize >0){
            pageTotal += 1;
        }
         //赋值
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setPageTotalCount(pageTotalCount);
        //当前的数据
        int begin = (pageNo - 1) * pageSize;
        List<Book> items = bookDAO.queryForPageItems(begin,pageSize);
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<Book>();
        //总共多少本书
        Integer pageTotalCount = bookDAO.queryPageTotalCountByPrice(min,max);
        //总页数
        int pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize >0){
            pageTotal += 1;
        }
        //赋值
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setPageTotalCount(pageTotalCount);
        //当前的数据
        int begin = (pageNo - 1) * pageSize;
        List<Book> items = bookDAO.queryForPageItemsByPrice(begin,pageSize,min,max);
        page.setItems(items);
        return page;
    }
}
