<%@ page language="java" import="collegamenti.model.User" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:include page="header.jsp" flush="true"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Collegamenti 2019</title>
</head>
<body>
<%User local= (User) session.getAttribute("User"); %>
<h1>SEI SICURO DI VOLER ELIMINARE IL TUO ACCOUNT DALLA NOSTRA PIATTAFORMA?</h1>
<a href="profile.jsp"><button class="button">No,Torna Indietro</button></a>
<form action="ServerHandler" method="post">
<input type="hidden" name="operation" value="DelAccount">
<input class="button" type="submit" value="Si">
</form>
</body>
</html>