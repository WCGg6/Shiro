package com.zhiyou.service;

import com.zhiyou.mapper.PermissionMapper;
import com.zhiyou.model.Permission;
import com.zhiyou.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author QiuShiju
 * @date 2021/3/26
 * @desc
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findPermissionByRoles(List<Role> roles) {
        ArrayList<Integer> ids = new ArrayList<>( );
        for (Role role : roles) {
            ids.add(role.getId());
        }
        return permissionMapper.findPermissionByRoles(ids);
    }
}
