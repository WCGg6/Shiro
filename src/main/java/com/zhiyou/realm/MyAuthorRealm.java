package com.zhiyou.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * @author QiuShiju
 * @date 2021/3/25
 * @desc
 */
public class MyAuthorRealm extends AuthorizingRealm{
    /**
     * 授权
     * @param principals 身份信息集合
     * @return AuthorizationInfo 返回关于当前用户的角色及权限信息
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获得身份
        String principal = (String)principals.getPrimaryPrincipal( );

        // 根据身份先查角色
        // Set<String> roles  = service.findRolesByUsername(principal);
        Set<String> roles = new HashSet<>();
        roles.add("role1");
        roles.add("role2");

        // 再根据角色查权限
        // Set<String> permissions = new HashSet<>();
        // for (String role : roles) {
        //     Set per = service.findPermissionsByRole(role);
        //     permissions.addAll(per);

        // }
        Set<String> permissions = new HashSet<>();
        permissions.add("/select");
        permissions.add("/add");
        permissions.add("/delete");
        permissions.add("/update");

        // 将角色,权限封装进info对象
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo( );
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        return info;
    }
    /**
     * 认证
     * @param token 当subject.login(token) --> login方法的token赋值给
     *              doGetAuthenticationInfo(token)
     * @return 如果登录成功,返回认证信息
     * @throws AuthenticationException 认证异常,如果登录信息有误,自行抛出异常
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 从token获得用户名和密码
        String principal = (String) token.getPrincipal( ); // 获得身份-用户名
        char[] credentials = (char[]) token.getCredentials( );
        String password = String.valueOf(credentials);

        // 凭证会自动变成:字符数组
        System.out.println("凭证 - "+credentials );

        // 通过查询数据库得到的数据与登录时输入的比对
        if (!"admin".equals(principal)) {
            // 如果不成功,抛出AuthenticationException
            throw new UnknownAccountException();
        } else {
            if (!"123456".equals(password)) {
                // 如果不成功,抛出AuthenticationException
                throw new IncorrectCredentialsException();
            }
        }

        // 如果成功,就封装数据到AuthenticationInfo 返回
        /**
         * info中封装的数据
         * 1 身份
         * 2 凭证
         * 3 当前realmName, 当前类的名字--->内部用来当做map的key,身份信息时value
         */
        System.out.println(getName() );
        return new SimpleAuthenticationInfo(principal,credentials,getName());
    }
}
