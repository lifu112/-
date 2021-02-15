package com.lifu.test;

import com.lifu.bean.Cart;
import com.lifu.bean.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @Description
 * @Author lifu
 * @Date 2021/1/31 21:13
 */
public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"python",1,new BigDecimal(10),new BigDecimal(10)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"python",1,new BigDecimal(10),new BigDecimal(10)));
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"python",1,new BigDecimal(10),new BigDecimal(10)));
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"python",1,new BigDecimal(10),new BigDecimal(10)));
        cart.updateCount(1,3);
        System.out.println(cart);
    }
}