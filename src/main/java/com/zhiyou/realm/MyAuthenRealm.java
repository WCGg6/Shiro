package com.zhiyou.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author QiuShiju
 * @date 2021/3/25
 * @desc 演示从数据库查询数据,进行[认证]操作
 *       从数据库查询信息,进行比对用户
 *       确定是登录成功
 */
public class MyAuthenRealm extends AuthenticatingRealm{

    /**
     * 认证
     * @param token 当subject.login(token) --> login方法的token赋值给
     *              doGetAuthenticationInfo(token)
     * @return 如果登录成功,返回认证信息
     * @throws AuthenticationException 认证异常,如果登录信息有误,自行抛出异常
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        // 从token获得用户名和密码
        String principal = (String) token.getPrincipal( ); // 获得身份-用户名
        char[] credentials = (char[]) token.getCredentials( );
        String password = String.valueOf(credentials);

        // 凭证会自动变成:字符数组
        System.out.println("凭证 - "+credentials );

        // 通过用户名密码查数据库
//        User user = service.findUserByName(principal);
//        if(user == null) {
//            // 用户名不存在
//        }else{
//            if (!user.getPassword().equals(credentials)) {
//                // 密码错误
//            } else{
//                // 成功
//            }
//        }

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
         * 3 当前realmName
         */
        System.out.println(getName() );
        return new SimpleAuthenticationInfo(principal,credentials,getName());
    }
}
