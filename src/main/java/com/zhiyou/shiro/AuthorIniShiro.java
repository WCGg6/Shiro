package com.zhiyou.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

import java.util.ArrayList;

/**
 * @author QiuShiju
 * @date 2021/3/25
 * @desc 使用ini配置文件完成授权
 */
public class AuthorIniShiro {

    public static void main(String[] args) {
        IniSecurityManagerFactory securityManagerFactory =
                new IniSecurityManagerFactory("classpath:shiro-author.ini");

        SecurityManager securityManager = securityManagerFactory.getInstance( );

        SecurityUtils.setSecurityManager(securityManager);

        // 从SecurityUtils中获得主体
        Subject subject = SecurityUtils.getSubject( );

        // 模拟登录,输入用户名密码
        String username = "zhangsan";
        String password = "123456";

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        // 登录
        try {
            subject.login(token);
            System.out.println("登录成功" );
        } catch (UnknownAccountException e) {
            System.out.println("用户名不存在" );
        } catch (IncorrectCredentialsException e){
            System.out.println("密码错误" );
        }

        // 判断角色和权限
        // 1 是否拥有某个角色
        boolean role1 = subject.hasRole("role2");
        System.out.println("role1 = " +role1 );

        ArrayList list = new ArrayList();
        list.add("role1");
        list.add("role2");
//        list.add("role3");
        boolean[] booleans = subject.hasRoles(list);

        boolean allBoolean = subject.hasAllRoles(list);



        // 2 是否拥有某个权限
        boolean permitted = subject.isPermitted("/select");

        boolean[] permitted1 = subject.isPermitted("/add", "/delete");

        boolean permittedAll = subject.isPermittedAll("/select", "/add");

        System.out.println( );

    }

}
