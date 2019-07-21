<%@ page language="java" import="java.util.ArrayList" import="collegamenti.model.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:include page="header.jsp" flush="true"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Collegamenti 2019</title>
</head>
<body>
<%
ArrayList<User> associated= new ArrayList<User>();
User local= (User) session.getAttribute("User");
String localEmail= local.getEmail();
associated = Database.getIstance().getStudents(localEmail); %>
<h1>Ecco i tuoi Studenti: </h1>
<div id="Listed"></div>
<%for(int i=0;i<associated.size();i++){
%>
<a href="Message.jsp?Email=<%=associated.get(i).getEmail() %>">
<div class="MessageBox" style="width:90%;text-align:center">
<p style="color:white;text-align:center "><%=associated.get(i).getNome()+" "+ associated.get(i).getCognome()%></p>
</div>
</a>
<%}%>
</body>
</html>
