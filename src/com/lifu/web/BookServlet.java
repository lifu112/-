package com.lifu.web;

import com.lifu.bean.Book;
import com.lifu.bean.Page;
import com.lifu.service.BookService;
import com.lifu.service.impl.BookServiceImpl;
import com.lifu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description
 * @Author lifu
 * @Date 2021/1/29 19:49
 */
public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * 处理分页的业务
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数pageNo,pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //获取page对象
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("manager/bookServlet?action=page");
        //保存至request域
        req.setAttribute("page",page);
        //请求转发
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo += 1;
        //将请求参数封装成javabean对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        //保存到数据库
        bookService.addBook(book);
        //返回到图书列表页面,请求重定向，不能请求转发，不然刷新就会有重复提交的bug
        //重定向的/是到端口号
        resp.sendRedirect(req.getContextPath() +
                "/manager/bookServlet?action=page&pageNo="+pageNo);
    }
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int i = Integer.parseInt(id);
        Book book = bookService.queryBookById(i);
        req.setAttribute("book",book);
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        bookService.updateBook(book);
        resp.sendRedirect(req.getContextPath() +
                "/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int i = Integer.parseInt(id);
        bookService.deleteBook(i);
        resp.sendRedirect(req.getContextPath() +
                "/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取全部图书
        List<Book> books = bookService.queryBooks();
        //将结果保存到请求域
        req.setAttribute("books",books);
        //请求转发
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
