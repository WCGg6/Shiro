package com.zhiyou.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * @author QiuShiju
 * @date 2021/3/25
 * @desc
 */
public class HelloShiro {

    public static void main(String[] args) {
        IniSecurityManagerFactory securityManagerFactory =
                new IniSecurityManagerFactory("classpath:shiro.ini");

        SecurityManager securityManager = securityManagerFactory.getInstance( );

        SecurityUtils.setSecurityManager(securityManager);

        // 从SecurityUtils中获得主体
        Subject subject = SecurityUtils.getSubject( );

        // 模拟登录,输入用户名密码
        String username = "zhangsan";
        String password = "123456";

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        /**
         * 没有报错就是成功
         * 如果用户名或密码错误等问题导致登录失败,全都以抛出的形式展现
         */
        try {
            subject.login(token);
            System.out.println("登录成功" );
        } catch (UnknownAccountException e) {
            System.out.println("用户名不存在" );
        } catch (IncorrectCredentialsException e){
            System.out.println("密码错误" );
        }


    }

}
