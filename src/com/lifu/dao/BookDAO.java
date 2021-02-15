package com.lifu.dao;

import com.lifu.bean.Book;

import java.util.List;

/**
 * @Description
 * @Author lifu
 * @Date 2021/1/29 17:17
 */
public interface BookDAO {
    public int addBook(Book book);
    public int deleteBook(Integer id);
    public int updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();

    Integer queryPageTotalCount();

    List<Book> queryForPageItems(int begin, int pageSize);

    Integer queryPageTotalCountByPrice(int min, int max);

    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
