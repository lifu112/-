package com.lifu.service;

import com.lifu.bean.Cart;

/**
 * @Description
 * @Author lifu
 * @Date 2021/2/1 21:06
 */
public interface OrderService {
    public String createOrder(Cart cart,Integer userid);
}
