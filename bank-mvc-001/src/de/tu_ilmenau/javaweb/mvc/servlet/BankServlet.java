package de.tu_ilmenau.javaweb.mvc.servlet;

import de.tu_ilmenau.javaweb.mvc.Exceptions.NotEnoughMoneyException;
import de.tu_ilmenau.javaweb.mvc.Exceptions.TransferFailException;
import de.tu_ilmenau.javaweb.mvc.jdbc.DButils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author : Binbin Luo
 * Date : 07.04.2023
 */
@WebServlet("/transfer")
public class BankServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String fromAct = request.getParameter("act1");
        String toAct = request.getParameter("act2");
        double money = Double.parseDouble(request.getParameter("money"));

//        out.print("BankServlet执行了！");
//        Class.forName("com.mysql.jdbc.Driver");

        // 连接数据库 获取账户信息
        Connection conn = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        int count = 0;

        try {
            conn = DButils.getConnetcion();
            String sql = "select balance from t_mvc where actno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,fromAct);
            rs = ps.executeQuery();
            if (rs.next()) {
                double balance = rs.getDouble("balance");
                if (balance < money) {
                    // 余额不足，抛出异常
                    throw new NotEnoughMoneyException("对不起，余额不足");
                }
            }
            // 如果程序能执行到这里，证明余额充足
            // 执行转账操作
            // act1减去10000，act2增加10000
            String sql1 = "update t_mvc set balance = balance - ? where actno = ? ";
            ps1 = conn.prepareStatement(sql1);
            ps1.setDouble(1,money);
            ps1.setString(2,fromAct);
            count = ps1.executeUpdate();

            String sql2 = "update t_mvc set balance = balance + ? where actno = ? ";
            ps2 = conn.prepareStatement(sql2);
            ps2.setDouble(1,money);
            ps2.setString(2,toAct);
            count += ps2.executeUpdate();

            if (count != 2) {
                throw new TransferFailException("转账失败！");
            }else {
                out.print("转账成功！");
            }

        } catch (Exception e) {
            // 在浏览器直接显示异常信息
            out.print(e.getMessage());
        } finally {
            DButils.clear(conn,ps,rs);
            if (ps1 != null) {
                try {
                    ps1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps2 != null) {
                try {
                    ps2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
