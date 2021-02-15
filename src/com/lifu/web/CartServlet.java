package com.lifu.web;

import com.google.gson.Gson;
import com.lifu.bean.Book;
import com.lifu.bean.Cart;
import com.lifu.bean.CartItem;
import com.lifu.service.BookService;
import com.lifu.service.impl.BookServiceImpl;
import com.lifu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author lifu
 * @Date 2021/1/31 21:45
 */
public class CartServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();

    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //根据id获取图书信息
        Book book = bookService.queryBookById(id);
        //将图书信息转成CartItem
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //添加商品
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        //最后添加的商品的名称
        req.getSession().setAttribute("lastName",cartItem.getName());
        //请求重定向,返回原来的地址
        resp.sendRedirect(req.getHeader("Referer"));
    }
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            cart.deleteItem(id);
        }
        //重定向购物车页面
        resp.sendRedirect(req.getHeader("Referer"));
    }
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            cart.clear();
        }
        //重定向购物车页面
        resp.sendRedirect(req.getHeader("Referer"));
    }
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        int count = WebUtils.parseInt(req.getParameter("count"),0);
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            cart.updateCount(id,count);
        }
        //重定向购物车页面
        resp.sendRedirect(req.getHeader("Referer"));
    }
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //根据id获取图书信息
        Book book = bookService.queryBookById(id);
        //将图书信息转成CartItem
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //添加商品
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        //最后添加的商品的名称
        req.getSession().setAttribute("lastName",cartItem.getName());

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }
}
