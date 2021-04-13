package com.zhiyou.servlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author QiuShiju
 * @date 2021/3/25
 * @desc
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IniSecurityManagerFactory securityManagerFactory =
//                new IniSecurityManagerFactory("classpath:shiro-author-realm.ini");
//
//        SecurityManager securityManager = securityManagerFactory.getInstance( );
//
//        SecurityUtils.setSecurityManager(securityManager);

        // 从SecurityUtils中获得主体
        Subject subject = SecurityUtils.getSubject( );

        // 模拟登录,输入用户名密码
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);

        // 登录
        try {
            subject.login(token);
            // 登录成功查询全部
            resp.sendRedirect("/select");
            System.out.println("登录成功" );
        } catch (UnknownAccountException e) {
            System.out.println("用户名不存在" );
        } catch (IncorrectCredentialsException e){
            System.out.println("密码错误" );
        }
    }
}
