package com.zeng.servlet;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class TestServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("init execute ...");
        System.out.println("init end ...");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setContentType("UTF-8");
        response.getWriter().append("test servlet");
    }


}
