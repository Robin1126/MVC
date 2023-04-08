package de.tu_ilmenau.javaweb.mvc.servlet;

import de.tu_ilmenau.javaweb.mvc.exceptions.AppException;
import de.tu_ilmenau.javaweb.mvc.exceptions.NotEnoughMoneyException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author : Binbin Luo
 * Date : 08.04.2023
 * @version 2.0
 * @since 2.0
 */
@WebServlet("/transfer")
public class AccountServlet extends HttpServlet {
    // Controller 负责调度
    AccountService accountService = new AccountService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        // 接收数据
        String fromActno = request.getParameter("fromActno");
        String toActno = request.getParameter("toActno");
        double money = Double.parseDouble(request.getParameter("money"));
        // 调用业务方法处理数据
        try {
            accountService.transfer(fromActno,toActno,money);
            response.sendRedirect(request.getContextPath() + "/success.jsp");
        } catch (Exception e) {
            // 转账失败

            response.sendRedirect(request.getContextPath() + "/fail.jsp");
        }

        // 展示处理结果 view
    }
}
