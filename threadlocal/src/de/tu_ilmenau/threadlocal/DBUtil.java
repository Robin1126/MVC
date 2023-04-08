package de.tu_ilmenau.threadlocal;

/**
 * Author : Binbin Luo
 * Date : 08.04.2023
 */
public class DBUtil {
    // 静态变量是类加载时执行，并且只执行一次
    // 所以一开始加载类就创建了全局的大Map
    private static MyThreadLocal<Connection> local = new MyThreadLocal<>();

    public static Connection getConnection() {
        // 从大Map当中取
        Connection connection = local.get();
        if (connection == null) {
            // 第一次取的时候是空的
            connection = new Connection();
            // 将new出来的connection绑定到大Map
            local.set(connection);
        }
        // 不是空就直接返回
        return connection;
    }
}
