package com.lifu.test;

import com.lifu.bean.User;
import com.lifu.dao.impl.UserDAOImpl;
import org.junit.Test;

/**
 * @Description
 * @Author lifu
 * @Date 2021/1/27 16:47
 */
public class UserDAOTest {
    UserDAOImpl userDAO = new UserDAOImpl();
    @Test
    public void queryUserByUsername() {
        System.out.println(userDAO.queryUserByUsername("admin"));
    }

    @Test
    public void saveUser() {
        System.out.println(userDAO.saveUser(new User(null,"lifu","112011","lifu@qq.com")));
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        System.out.println(userDAO.queryUserByUsernameAndPassword("admin","admi"));
    }
}