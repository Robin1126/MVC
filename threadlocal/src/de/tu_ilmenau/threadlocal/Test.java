package de.tu_ilmenau.threadlocal;

/**
 * Author : Binbin Luo
 * Date : 08.04.2023
 */

// 张三发送一个请求，对应t1线程
// 李四发送一个请求，对应t2线程
public class Test {
    public static void main(String[] args) {
        // 这三个程序的thread毫无疑问都是同一个



        // 调用service
        UserService userService = new UserService();
        userService.save();
    }
}
