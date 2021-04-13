package com.zhiyou.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.Date;

/**
 * @author QiuShiju
 * @date 2021/3/26
 * @desc
 * 1 注册时,用户输入的正常的密码
 * 2 插入数据库时,在业务层将密码加密,如果使用盐值加密,
 *   盐值也需要存入数据库/或者使用一个所有人公认的密码
 *   如果使用多次加密,加密次数也需要存储/或者是使用一个默认的次数
 * 3 以后再登录,输入正常密码. 将正常密码再利用同一种加密算法,即盐值得到密码
 *    然后再与数据库中的加密过的密码比对
 */
public class TestCredentials {


    public static void main(String[] args) {
        String password = "123456";

        long time = new Date( ).getTime( );
        System.out.println(time );
        Md5Hash md5Hash = new Md5Hash(password,"1616741144605",1024);
        System.out.println(md5Hash );
    }
}
