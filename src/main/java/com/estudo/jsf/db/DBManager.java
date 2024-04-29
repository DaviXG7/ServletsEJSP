package com.estudo.jsf.db;

import com.estudo.jsf.models.SessionModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBManager {
    private static final String[] whiteList = new String[]{"DaviXG7"};

    private static Connection connection;

    public static void init() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test","root",""
        );
        connection.setAutoCommit(true);
    }

    public static Connection getConnection() {
        return connection;
    }

    public static SessionModel getUser(String usuario, String senha) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM contas WHERE senha = ? AND nome = ?");
        preparedStatement.setString(1, senha);
        preparedStatement.setString(2, usuario);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) {
            return null;
        }
        return new SessionModel(resultSet.getString("senha"), resultSet.getString("nome"));
    }
    public static void addUser(SessionModel session) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO contas (nome, senha) VALUES (?,?)");
        preparedStatement.setString(1, session.getNome());
        preparedStatement.setString(2, session.getSenha());
        preparedStatement.executeUpdate();
    }
    public static List<SessionModel> allUsers() throws SQLException {
        List<SessionModel> users = new ArrayList<>();
        ResultSet set = connection.prepareStatement("SELECT * FROM contas").executeQuery();
        while (set.next()) users.add(new SessionModel(set.getString("nome"), set.getString("senha")));

        return users;

    }
    public static boolean isInWhiteList(SessionModel session) {
        return Arrays.asList(whiteList).contains(session.getNome());
    }



}
