package com.lifu.web;

import com.lifu.bean.Cart;
import com.lifu.bean.User;
import com.lifu.service.OrderService;
import com.lifu.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description
 * @Author lifu
 * @Date 2021/2/1 22:23
 */
public class OrderServlet extends BaseServlet{
    private OrderService orderService = new OrderServiceImpl();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User user = (User) req.getSession().getAttribute("user");
        //需要先判断用户是否已经登录
        if(user == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer userId = user.getId();
        String orderId = null;
        orderId = orderService.createOrder(cart, userId);

        req.getSession().setAttribute("orderId",orderId);
        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req,resp);
    }
}
