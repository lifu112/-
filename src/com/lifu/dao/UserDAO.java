package com.lifu.dao;

import com.lifu.bean.User;

/**
 * @Description
 * @Author lifu
 * @Date 2021/1/27 16:21
 */
public interface UserDAO {
    /**
    * @Description 根据用户名查询用户信息（用于注册的时候验证用户存在吗）
    * @Author lifu
    * @Date 2021/1/27 16:24
    * @Param [username]
    * @Return com.lifu.bean.User 返回null说表示没有这个用户
    */
    public User queryUserByUsername(String username);

    /**
    * @Description 保存用户信息，用于注册
    * @Author lifu
    * @Date 2021/1/27 16:26
    * @Param [user]
    * @Return int
    */
    public int saveUser(User user);

    /**
    * @Description 根据用户名和密码查询用户，用于登录
    * @Author lifu
    * @Date 2021/1/27 16:29
    * @Param [username, password]
    * @Return com.lifu.bean.User
    */
    public User queryUserByUsernameAndPassword(String username,String password);

}
