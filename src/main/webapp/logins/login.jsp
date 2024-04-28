<%--
  Created by IntelliJ IDEA.
  User: davis
  Date: 27/04/2024
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="login" method="post">

    <label>
        Nome:
        <input type="text" name="nome" placeholder="Digite o nome" required>
    </label>
    <br>
    <label>
        Senha:
        <input type="password" name="senha" placeholder="Digite a senha" required>
    </label>
    <br>
    <a href="/logins/cadastrar.jsp">Não tem uma conta? Clique para cadastrar</a>
    <br>
    <input type="submit">

    <h1>${message}</h1>

</form>

</body>
</html>
