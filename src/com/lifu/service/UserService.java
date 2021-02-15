package com.lifu.service;

import com.lifu.bean.User;

/**
 * @Description
 * @Author lifu
 * @Date 2021/1/27 18:30
 */
public interface UserService {
    /**
     * 用户注册
     * @param user
     */
    public void register(User user);

    /**
     * 用户登录
     * @param user
     * @return 返回user对象
     */
    public User login(User user);

    /**
     * 检查用户名是否已存在
     * @param username
     * @return 返回true就表示已存在了
     */
    public Boolean existsUsername(String username);
}
