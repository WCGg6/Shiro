package com.zhiyou.mapper;

import com.zhiyou.model.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * @author QiuShiju
 * @date 2021/3/26
 * @desc
 */
public interface PermissionMapper {
    List<Permission> findPermissionByRoles(ArrayList<Integer> ids);
}
