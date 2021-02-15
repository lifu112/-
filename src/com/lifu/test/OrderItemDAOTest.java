package com.lifu.test;

import com.lifu.bean.OrderItem;
import com.lifu.dao.OrderItemDAO;
import com.lifu.dao.impl.OrderItemDAOImpl;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @Description
 * @Author lifu
 * @Date 2021/2/1 20:37
 */
public class OrderItemDAOTest {
    OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    @Test
    public void saveOrderItem() {
        orderItemDAO.saveOrderItem(new OrderItem(null,"java入门",1,new BigDecimal(10),new BigDecimal(10),"123456"));
        orderItemDAO.saveOrderItem(new OrderItem(null,"python入门",2,new BigDecimal(10),new BigDecimal(20),"123456"));
        orderItemDAO.saveOrderItem(new OrderItem(null,"java放弃",1,new BigDecimal(100),new BigDecimal(100),"123456"));
    }
}