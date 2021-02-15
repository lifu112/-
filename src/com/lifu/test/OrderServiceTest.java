package com.lifu.test;

import com.lifu.bean.Cart;
import com.lifu.bean.CartItem;
import com.lifu.service.OrderService;
import com.lifu.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @Description
 * @Author lifu
 * @Date 2021/2/1 22:15
 */
public class OrderServiceTest {
    private OrderService orderService = new OrderServiceImpl();
    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"python",1,new BigDecimal(10),new BigDecimal(10)));
        System.out.println(orderService.createOrder(cart,1));
    }
}