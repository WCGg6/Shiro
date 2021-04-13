package com.zhiyou.service;

import com.zhiyou.mapper.RoleMapper;
import com.zhiyou.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author QiuShiju
 * @date 2021/3/26
 * @desc
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findRolesByUsername(String username) {
        return roleMapper.findRolesByUsername(username);
    }
}
