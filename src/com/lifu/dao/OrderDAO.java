package com.lifu.dao;

import com.lifu.bean.Order;

/**
 * @Description
 * @Author lifu
 * @Date 2021/2/1 20:20
 */
public interface OrderDAO {
    public int saveOrder(Order order);
}
