package de.tu_ilmenau.bank.web;

import de.tu_ilmenau.bank.exceptions.AppException;
import de.tu_ilmenau.bank.exceptions.NotEnoughMoneyException;
import de.tu_ilmenau.bank.service.AccountService;

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
 * 这个java程序是司令官，负责调度和展示页面
 *
 * MVC架构有 表示层/web层/表现层
 * 中间 业务逻辑层
 * 底层 持久化层
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
            // 执行到这里，说明成功了
            // 展示页面view
            response.sendRedirect(request.getContextPath() + "/success.jsp");

        } catch (AppException e) {
            // 转账失败

            response.sendRedirect(request.getContextPath() + "/fail.jsp");
        } catch (NotEnoughMoneyException e) {
            response.sendRedirect(request.getContextPath() + "/notEnoughMoney.jsp");
        }


    }
}
