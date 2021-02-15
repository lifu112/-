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

/**
 * @Description
 * @Author lifu
 * @Date 2021/1/30 18:52
 */
public class ClientBookServlet extends BaseServlet{
    BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数pageNo,pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //获取page对象
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("client/clientBookServlet?action=page");
        //保存至request域
        req.setAttribute("page",page);
        //请求转发
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    /**
     * 价格区间查询分页
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数pageNo,pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"),0);
        int max = WebUtils.parseInt(req.getParameter("max"),Integer.MAX_VALUE);
        //获取page对象
        Page<Book> page = bookService.pageByPrice(pageNo,pageSize,min,max);
        //需要判断一下min max是不是空的，不然url和区间回显会是默认值
        StringBuilder sb = new StringBuilder("client/clientBookServlet?action=pageByPrice");
        if(req.getParameter("min") != null){
            sb.append("&min=").append(req.getParameter("min"));
        }
        if(req.getParameter("max") != null){
            sb.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(sb.toString() );
        //保存至request域
        req.setAttribute("page",page);
        //请求转发
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}
