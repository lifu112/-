package com.lifu.service.impl;

import com.lifu.bean.User;
import com.lifu.dao.UserDAO;
import com.lifu.dao.impl.UserDAOImpl;
import com.lifu.service.UserService;

/**
 * @Description
 * @Author lifu
 * @Date 2021/1/27 19:08
 */
public class UserServiceImpl implements UserService {
    private UserDAO userDAO = new UserDAOImpl();
    @Override
    public void register(User user) {
        userDAO.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDAO.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public Boolean existsUsername(String username) {
        if(userDAO.queryUserByUsername(username) == null){
            return false;
        }
        return true;
    }
}
