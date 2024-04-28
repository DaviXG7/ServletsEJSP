<%@ page import="com.estudo.jsf.models.SessionModel" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>

<a href="logins/login.jsp">Faça login aqui!!</a>
<br>
<%
    SessionModel user = ((SessionModel)request.getSession().getAttribute("user"));
%>

<%= user != null ? ((SessionModel)request.getSession().getAttribute("user")).getNome() : "null"
%>
<br>

<% if (user != null) { %>
<%
    if (user.isAdmin()) {
%>
<p>Você é um ADMIN <%= user.getNome() %></p>
<%
    } else {
%>
<p>Você não é um ADMIN, <%= user.getNome() %>!</p>
<% }} else { %>
<p>Você não está logado.</p>
<% } %>


<a href="hello">hello world test</a>
</body>
</html>