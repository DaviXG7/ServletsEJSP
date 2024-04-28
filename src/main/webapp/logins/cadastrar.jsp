<%--
  Created by IntelliJ IDEA.
  User: davis
  Date: 27/04/2024
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="cadastre" method="post">
    <label>
        Nome:
        <input type="text" name="nome" placeholder="Digite o nome" required>
    </label>
    <br>
    <label>
        Senha:
        <input type="password" name="senha" placeholder="Digite a senha" >
        <br>
        <input type="password" name="confirmarSenha" placeholder="Repita a senha" >
    </label>
    <br>
    <a href="/logins/login.jsp">Já tem uma conta? Clique para logar!</a>
    <br>
    <input type="submit">
</form>

<h1>${message}</h1>

</body>
</html>
