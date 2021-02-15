package com.lifu.test;

import com.lifu.bean.Book;
import com.lifu.bean.Page;
import com.lifu.dao.BookDAO;
import com.lifu.dao.impl.BookDAOImpl;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @Description
 * @Author lifu
 * @Date 2021/1/29 18:37
 */
public class BookDAOTest {
    private BookDAO bookDAO = new BookDAOImpl();
    @Test
    public void addBook() {
        Book book = new Book(null,"Java入门","lifu",new BigDecimal(99),10,1000,null);
        bookDAO.addBook(book);
    };

    @Test
    public void deleteBook() {
        System.out.println(bookDAO.deleteBook(20));
    }

    @Test
    public void updateBook() {
        System.out.println(bookDAO.updateBook(new Book(21,"Java入门","lifu",new BigDecimal(999),10,1000,null)));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDAO.queryBookById(1));
    }

    @Test
    public void queryBooks() {
        for(Book querybook : bookDAO.queryBooks()){
            System.out.println(querybook);
        }
    }
    @Test
    public void queryPageTotalCount() {
        System.out.println(bookDAO.queryPageTotalCount());
    }

    @Test
    public void queryForPageItems() {
        for(Book querybook : bookDAO.queryForPageItems(8, Page.PAGE_SIZE)){
            System.out.println(querybook);
        }
    }
    @Test
    public void queryPageTotalCountByPrice() {
        System.out.println(bookDAO.queryPageTotalCountByPrice(20,50));
    }
    @Test
    public void queryForPageItemsByPrice() {
        for(Book querybook : bookDAO.queryForPageItemsByPrice(4, Page.PAGE_SIZE,10,100)){
            System.out.println(querybook);
        }
    }
}