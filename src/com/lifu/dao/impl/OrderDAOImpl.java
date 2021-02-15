package com.lifu.dao.impl;

import com.lifu.bean.Order;
import com.lifu.dao.BaseDAO;
import com.lifu.dao.OrderDAO;

/**
 * @Description
 * @Author lifu
 * @Date 2021/2/1 20:24
 */
public class OrderDAOImpl extends BaseDAO implements OrderDAO {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}
