package com.estudo.jsf.servlets;

import java.io.*;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;

import com.estudo.jsf.db.DBManager;
import com.estudo.jsf.models.SessionModel;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
@WebServlet(name = "cadastro", value = "/logins/cadastre")
public class CadastrarServlet extends HttpServlet {

    public void init() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String nome = request.getParameter("nome");
        String senha = request.getParameter("senha");
        String confirmaSenha = request.getParameter("confirmarSenha");

        if (nome == null || senha == null || confirmaSenha == null ||
        nome.isEmpty() || senha.isEmpty() || confirmaSenha.isEmpty()) {

            String message = "Os campos devem ser preenchidos!";
            RequestDispatcher rd = request.getRequestDispatcher("/logins/cadastrar.jsp");
            request.setAttribute("message", message);
            rd.forward(request,response);

            return;

        }

        if (!senha.equals(confirmaSenha)) {
            String message = "As senhas devem ser iguais!!";
            RequestDispatcher rd = request.getRequestDispatcher("/logins/cadastrar.jsp");
            request.setAttribute("message", message);
            rd.forward(request,response);

            return;
        }

        if (senha.length() < 6 || senha.length() > 30) {
            String message = "A senha devem ser maior que 6 caracteres e menor que 30 caracteres!";
            RequestDispatcher rd = request.getRequestDispatcher("/logins/cadastrar.jsp");
            request.setAttribute("message", message);
            rd.forward(request,response);

            return;
        }

        AtomicBoolean exists = new AtomicBoolean(false);

        try {
            DBManager.allUsers().stream().filter(model -> model.getNome().equals(nome)).forEach(model -> exists.set(true));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (exists.get()) {
            String message = "Esse usuário já existe!!";
            RequestDispatcher rd = request.getRequestDispatcher("/logins/cadastrar.jsp");
            request.setAttribute("message", message);
            rd.forward(request,response);
            return;
        }

        SessionModel model = new SessionModel(senha,nome);

        try {
            DBManager.addUser(model);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.getSession().setAttribute("user", model);

        RequestDispatcher rd = request.getRequestDispatcher("/home/principal.jsp");

        rd.forward(request,response);


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    public void destroy() {
    }
}