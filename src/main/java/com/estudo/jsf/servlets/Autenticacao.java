package com.estudo.jsf.servlets;

import com.estudo.jsf.db.DBManager;
import com.estudo.jsf.models.SessionModel;
import jakarta.mail.Session;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebFilter(urlPatterns = {"/home/*"})
public class Autenticacao implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        try {
            DBManager.init();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String name = servletRequest.getParameter("nome");
        String password = servletRequest.getParameter("senha");
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        SessionModel model = null;
        try {
            model = DBManager.getUser(name,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (model == null) {

            if (request.getServletPath().contains("home")) {
                ((HttpServletResponse)servletResponse).sendRedirect("/logins/login.jsp");
                return;
            }
        }


        request.getSession().setAttribute("user", model);

        filterChain.doFilter(servletRequest, servletResponse);





    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
