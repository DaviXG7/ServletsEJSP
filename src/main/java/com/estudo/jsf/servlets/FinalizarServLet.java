package com.estudo.jsf.servlets;

import com.estudo.jsf.db.DBManager;
import com.estudo.jsf.models.SessionModel;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "finalizar", value = "/logins/finalizar")
public class FinalizarServLet extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().setAttribute("user", null);
        response.sendRedirect("/index.jsp");
    }

    public void destroy() {
    }

}
