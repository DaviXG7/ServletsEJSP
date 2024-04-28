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

@WebServlet(name = "login", value = "/logins/login")
public class LoginServlet extends HttpServlet {

    public void init() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String senha = request.getParameter("senha");
        if (nome == null || senha == null ||
                nome.isBlank() || senha.isEmpty()) {

            String message = "Os campos devem ser preenchidos!";
            RequestDispatcher rd = request.getRequestDispatcher("/logins/login.jsp");
            request.setAttribute("message", message);
            rd.forward(request,response);

            return;

        }
        SessionModel model = null;

        try {
            model = DBManager.getUser(nome,senha);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (model == null) {
            String message = "Esta conta não existe! Tente digitar maus uma vez a senha ou o nome!";
            RequestDispatcher rd = request.getRequestDispatcher("/logins/login.jsp");
            request.setAttribute("message", message);
            rd.forward(request,response);
            return;
        }
        RequestDispatcher rd = request.getRequestDispatcher("/home/principal.jsp");
        request.getSession().setAttribute("user", model);
        rd.forward(request,response);



    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    public void destroy() {
    }
}
