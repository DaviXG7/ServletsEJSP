<%@ page import="com.estudo.jsf.models.SessionModel" %><%--
  Created by IntelliJ IDEA.
  User: davis
  Date: 27/04/2024
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Seja bem vindo <%= request.getSession().getAttribute("user") != null ? ((SessionModel) request.getSession().getAttribute("user")).getNome() : "null"
%></h1>
<br>
<form action="finalizar" method="get"><input type="submit" value="Finalizar sessão"></form>


</body>
</html>
