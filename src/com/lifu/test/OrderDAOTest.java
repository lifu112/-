package com.lifu.test;

import com.lifu.bean.Order;
import com.lifu.dao.OrderDAO;
import com.lifu.dao.impl.OrderDAOImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description
 * @Author lifu
 * @Date 2021/2/1 20:37
 */
public class OrderDAOTest {

    @Test
    public void saveOrder() {
        OrderDAO orderDAO = new OrderDAOImpl();
        orderDAO.saveOrder(new Order("123456",new Date(),new BigDecimal(100),0,1));
    }
}