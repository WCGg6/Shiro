package com.zhiyou.service;

import com.zhiyou.model.User;

/**
 * @author QiuShiju
 * @date 2021/3/26
 * @desc
 */
public interface UserService {
    User findUserByUsernamePassword(String username, String password);

    User findUserByUsername(String username);
}
