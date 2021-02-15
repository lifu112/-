package com.lifu.service;

import com.lifu.bean.Book;
import com.lifu.bean.Page;

import java.util.List;

/**
 * @Description
 * @Author lifu
 * @Date 2021/1/29 19:26
 */
public interface BookService {
    public void addBook(Book book);
    public void deleteBook(Integer id);
    public void updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();

    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
