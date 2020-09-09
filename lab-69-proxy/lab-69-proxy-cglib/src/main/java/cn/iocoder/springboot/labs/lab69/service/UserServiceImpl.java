package cn.iocoder.springboot.labs.lab69.service;

public class UserServiceImpl {

    public void create(String username, String password) {
        System.out.println(String.format("登陆的用户名(%s) 密码(%s)", username, password));
    }

}
