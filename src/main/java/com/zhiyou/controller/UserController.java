package com.zhiyou.controller;

import com.zhiyou.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author QiuShiju
 * @date 2021/3/26
 * @desc
 */
@Controller
public class UserController {

    @RequestMapping("/login")
    public String login(Model model,String username, String password) {
        System.out.println("name == " + username);

        // 创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        // 从容器中获得主体
        Subject subject = SecurityUtils.getSubject( );

        // 登录
        try {
            subject.login(token);
            // 从SimpleAuthenticationInfo返回值获得的对象
            User user = (User) subject.getPrincipal( );
            model.addAttribute("user",user);
            return "select";
        } catch (AuthenticationException e) {
            e.printStackTrace( );
        }
        model.addAttribute("msg","用户名或密码错误");
        return "index";

    }
    @RequiresPermissions("/add")
    @RequestMapping("/user/add")
    public String add() {
        return "add";
    }

    @RequiresPermissions("/update")
    @RequestMapping("/user/update")
    public String update() {
        return "update";
    }

    @RequiresRoles("admin")
    @RequestMapping("/user/delete")
    public String delete() {
        return "select";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "index";
    }
}
