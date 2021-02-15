package com.lifu.service.impl;

import com.lifu.bean.*;
import com.lifu.dao.BookDAO;
import com.lifu.dao.OrderDAO;
import com.lifu.dao.OrderItemDAO;
import com.lifu.dao.impl.BookDAOImpl;
import com.lifu.dao.impl.OrderDAOImpl;
import com.lifu.dao.impl.OrderItemDAOImpl;
import com.lifu.service.OrderService;

import java.util.Date;
import java.util.Map;

/**
 * @Description
 * @Author lifu
 * @Date 2021/2/1 21:09
 */
public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO = new OrderDAOImpl();
    private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    private BookDAO bookDAO = new BookDAOImpl();
;
    @Override
    public String createOrder(Cart cart, Integer userid) {
        String orderId = System.currentTimeMillis()+""+userid;
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userid);
        orderDAO.saveOrder(order);

        //遍历购物车中的商品项转成订单中的订单项
        for(Map.Entry<Integer, CartItem> entry: cart.getItems().entrySet()){
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            orderItemDAO.saveOrderItem(orderItem);
            //修改图书的销量和库存
            Book book = bookDAO.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookDAO.updateBook(book);
        };
        //清空购物车
        cart.clear();
        return orderId;
    }
}
