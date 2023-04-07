package de.tu_ilmenau.javaweb.mvc.jdbc;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * Author : Binbin Luo
 * Date : 07.04.2023
 */
public class DButils {
    // 获取bundle
    private static ResourceBundle bundle = ResourceBundle.getBundle("resources.jdbc");
    // 获取url
    private static String url = bundle.getString("url");
    // 获取DB用户名
    private static String username = bundle.getString("username");
    // 获取DB用户密码
    private static String password = bundle.getString("password");
    // 获取DB驱动
    private static String driver = bundle.getString("driver");

    static {
        // 注册驱动
        try {
            // 这里直接写变量名
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




    /**
     * 获取连接Connection
     * @return
     * @throws SQLException
     */
    public static Connection getConnetcion() throws SQLException {
        Connection conn = DriverManager.getConnection(url,username,password);
        return conn;
    }

    /**
     * 关闭通道
     * @param conn
     * @param ps
     * @param rs
     */
    public static void clear(Connection conn, PreparedStatement ps, ResultSet rs) {
        if (rs != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
