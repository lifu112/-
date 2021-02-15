package com.lifu.dao.impl;

import com.lifu.bean.User;
import com.lifu.dao.BaseDAO;
import com.lifu.dao.UserDAO;

/**
 * @Description
 * @Author lifu
 * @Date 2021/1/27 16:31
 */
public class UserDAOImpl extends BaseDAO implements UserDAO {

    @Override
    public User queryUserByUsername(String username) {
        String sql = "select `id`,`username`,`password`,`email` from `user` where username=?";
        return queryForOne(User.class,sql,username);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into `user`(username,password,email) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select `id`,`username`,`password`,`email` from `user` where username=? and password=?";
        return queryForOne(User.class,sql,username,password);
    }
}
