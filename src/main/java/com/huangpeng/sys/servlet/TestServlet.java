package com.huangpeng.sys.servlet;

/**
 * <pre>
 * 任务：
 * 描述：
 * 作者：@author huangpeng
 * 时间：@create 2017-12-15 上午11:15
 * 类名: TestSevlet
 * </pre>
 **/

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("content===" + req.getParameter("content"));
        req.getSession().setAttribute("content", req.getParameter("content"));
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}