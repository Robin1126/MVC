package de.tu_ilmenau.javaweb.mvc.servlet;

import de.tu_ilmenau.javaweb.mvc.utils.DBUtils;

import java.awt.event.WindowFocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author : Binbin Luo
 * Date : 08.04.2023
 * AccountDao是负责数据的增删改查的
 * 1. 什么是Dao？
 * Data Access Object 数据访问对象
 * 2. DAO是JavaEE的设计模式，不是23种设置模式
 * 3. DAO只负责数据库的CRUD，没有业务逻辑在里面
 * 4. 这里是负责处理t_mvc这张表的
 */
public class AccountDao {
    /**
     * @param act 账户信息
     * @return 1表示插入成功
     */
    public int insert(Account act) {
        // 拆入数据库的基本操作

        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = DBUtils.getConnection();
            String sql = "insert into t_mvc(actno, balance) values(?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, act.getActno());
            ps.setDouble(2, act.getBalance());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, ps, null);
        }
        return count;
    }

    /**
     * @param actno 账户名称
     * @return 1表示删除成功
     */
    public int deleteByActno(String actno) {
        // 数据库操作
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = DBUtils.getConnection();
            String sql = "delete from t_mvc where actno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, actno);
            count = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, ps, null);
        }
        return count;
    }

    /**
     * 根据ID删除账户信息
     *
     * @param id 主键
     * @return 1表示删除成功
     */

    public int deleteById(Long id) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            String sql = "delete from t_mvc where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, ps, null);
        }
        return count;
    }

    /**
     * 更新账户信息
     *
     * @param act 账户
     * @return 1表示更新成功
     */

    public int update(Account act) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            String sql = "update t_mvc set balance = ? ,  actno = ? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, act.getBalance());
            ps.setString(2, act.getActno());
            ps.setLong(3, act.getId());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, ps, null);
        }
        return count;
    }

    /**
     * 查找账户信息
     *
     * @param actno 账户名
     * @return 账户信息
     */
    public Account selectByActno(String actno) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Account account = null;
        int count = 0;

        try {
            conn = DBUtils.getConnection();
            String sql = "select id, balance from t_mvc where actno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, actno);
            rs = ps.executeQuery();
            if (rs.next()) {
                Long id = rs.getLong("id");
                Double balance = rs.getDouble("balance");
                // 将结果集封装成java对象
                account = new Account(id, actno, balance);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, ps, rs);
        }
        return account;
    }

    /**
     * 查询所有账户信息
     *
     * @return 账户信息list
     */
    public List<Account> selectAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Account account = null;
        ArrayList<Account> list = new ArrayList<>();
        int count = 0;

        try {
            conn = DBUtils.getConnection();
            String sql = "select id, actno, balance from t_mvc";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                Double balance = rs.getDouble("balance");
                String actno = rs.getString("actno");
                // 将结果集封装成java对象
                account = new Account(id, actno, balance);
                list.add(account);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, ps, rs);
        }

        return list;
    }
}
