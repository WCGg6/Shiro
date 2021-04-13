package com.zhiyou.mapper;

import com.zhiyou.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author QiuShiju
 * @date 2021/3/26
 * @desc
 */
public interface UserMapper {

    User findUserByUsernamePassword(@Param("username") String username, @Param("password") String password);

    User findUserByUsername(String username);
}
