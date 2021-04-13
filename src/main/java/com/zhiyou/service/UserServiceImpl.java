package com.zhiyou.service;

import com.zhiyou.mapper.UserMapper;
import com.zhiyou.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author QiuShiju
 * @date 2021/3/26
 * @desc
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User findUserByUsernamePassword(String username, String password) {
        return userMapper.findUserByUsernamePassword(username,password);
    }

    @Override
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }
}
