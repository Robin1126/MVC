package de.tu_ilmenau.mvc.bank.utils;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * Author : Binbin Luo
 * Date : 08.04.2023
 * JDBC工具类
 *
 */
public class DBUtils {
    // 私有化构造方法
    private DBUtils() {}
    // 获取Bundle
    private static ResourceBundle bundle = ResourceBundle.getBundle("resources.info");
    // 导入driver
    private static String  driver = bundle.getString("driver");
    // 导入url
    private static String url = bundle.getString("url");
    // 导入username
    private static String username = bundle.getString("username");
    // 导入password
    private static String password = bundle.getString("password");
    private static ThreadLocal<Connection> local = new ThreadLocal<>();

    // static 代码块 注册驱动
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    // 构造Connection方法
    public static Connection getConnection() throws SQLException {
        Connection conn = local.get();
        if (conn == null) {
            conn = DriverManager.getConnection(url, username, password);
            local.set(conn);
        }
        return conn;
    }

    // 构造close方法
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
                // 移除map中的项
                // 因为Tomcat中含有线程池，如果没有移除的话，又来一个t1线程，会拿到关闭的Connection
                local.remove();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
