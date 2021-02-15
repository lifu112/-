package com.lifu.dao.impl;

import com.lifu.bean.OrderItem;
import com.lifu.dao.BaseDAO;
import com.lifu.dao.OrderItemDAO;

/**
 * @Description
 * @Author lifu
 * @Date 2021/2/1 20:25
 */
public class OrderItemDAOImpl extends BaseDAO implements OrderItemDAO {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }
}
