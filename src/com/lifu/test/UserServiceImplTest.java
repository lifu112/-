package com.lifu.test;

import com.lifu.bean.User;
import com.lifu.service.UserService;
import com.lifu.service.impl.UserServiceImpl;
import org.junit.Test;

/**
 * @Description
 * @Author lifu
 * @Date 2021/1/27 19:17
 */
public class UserServiceImplTest {
    UserService userService = new UserServiceImpl();
    @Test
    public void register() {
        userService.register(new User(null,"text","123456","text@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"text","12345","text@qq.com")));
    }

    @Test
    public void existsUsername() {
        System.out.println(userService.existsUsername("admin"));
    }
}