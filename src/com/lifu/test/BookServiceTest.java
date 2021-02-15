package com.lifu.test;

import com.lifu.bean.Book;
import com.lifu.service.BookService;
import com.lifu.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @Description
 * @Author lifu
 * @Date 2021/1/29 19:32
 */
public class BookServiceTest {
    BookService bookService = new BookServiceImpl();
    @Test
    public void addBook() {
        Book book = new Book(null,"python入门到放弃","lifu",new BigDecimal(19),1,10,null);
        bookService.addBook(book);
    }

    @Test
    public void deleteBook() {
        bookService.deleteBook(22);
    }

    @Test
    public void updateBook() {
        Book book = new Book(22,"python入门到放弃","lifu",new BigDecimal(299),10,10,null);
        bookService.updateBook(book);
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(10));
    }

    @Test
    public void queryBooks() {
        for(Book querybook : bookService.queryBooks()){
            System.out.println(querybook);
        }
    }
    @Test
    public void page(){
        System.out.println(bookService.page(1,4));
    }
    @Test
    public void pageByPrice(){
        System.out.println(bookService.pageByPrice(1,4,20,50));
    }
}