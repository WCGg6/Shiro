package com.zhiyou.service;

import com.zhiyou.model.Permission;
import com.zhiyou.model.Role;

import java.util.List; /**
 * @author QiuShiju
 * @date 2021/3/26
 * @desc
 */
public interface PermissionService {
    List<Permission> findPermissionByRoles(List<Role> roles);
}
