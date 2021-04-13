package com.zhiyou.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * @author QiuShiju
 * @date 2021/3/25
 * @desc 使用自定义Realm完成认证
 * 1 项目启动
    2 输入用户密码, 制作token
    3 登录,调用subject.login(token)
    4 第三步的login方法就会调用自定义Realm类中的doGetAuthenticationInfo(token),并且将token传递,
    5 从token获得用户名密码,查数据库
    6 比对,如果成功,返回info信息
    7 如果不成功,抛出异常,抛给login方法
    8 login方法捕获异常,根据异常信息提示不同的错误信息
 */
public class AuthenShiro {

    public static void main(String[] args) {
        IniSecurityManagerFactory securityManagerFactory =
                new IniSecurityManagerFactory("classpath:shiro-authen.ini");

        SecurityManager securityManager = securityManagerFactory.getInstance( );

        SecurityUtils.setSecurityManager(securityManager);

        // 从SecurityUtils中获得主体
        Subject subject = SecurityUtils.getSubject( );

        // 模拟登录,输入用户名密码
        String username = "admin";
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
