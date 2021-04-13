package com.zhiyou.mapper;

import com.zhiyou.model.Role;

import java.util.List;

/**
 * @author QiuShiju
 * @date 2021/3/26
 * @desc
 */
public interface RoleMapper {
    List<Role> findRolesByUsername(String username);
}
