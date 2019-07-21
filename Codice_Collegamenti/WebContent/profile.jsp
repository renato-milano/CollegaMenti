<%@ page language="java" import="collegamenti.model.User" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
            <jsp:include page="header.jsp" flush="true"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Collegamenti 2019</title>
</head>
<body>
<%User logged= (User) session.getAttribute("User"); 

if(logged!= null){%>
<h1>Dettagli Utente:</h1>
<%if(logged.getTipo()==0||logged.getTipo()==1){ %>
<a style="float:right" id="ShowRequest" style="margin:0.5%;" href="ShowStudentRequest.jsp" class="button" >Visualizza Richieste
</a>
<a id="ShowLinked" style="margin:0.5%;" href="ShowStudentLinked.jsp" class="button" >Visualizza Studenti Assegnati
</a>
<%}if(logged.isAbbonato()){ %>
<a id="ShowLinked" style="margin:0.5%;" href="ShowCoachLinked.jsp" class="button" >Visualizza Coach Assegnati
</a>
<%} %>
<div id="ProfileInformation">
<p>Nome: <%=logged.getNome() %></p><br>
<p>Cognome: <%=logged.getCognome() %></p><br>
<p>Email: <%=logged.getEmail() %></p><br>
<p>Password: <%=logged.getPassword() %></p><br>
</div>

<%if(!logged.isAbbonato()){ %>
<a href="Subscription.jsp" id="SubButton" class="button" >Abbonati 
</a>
<%}else{%>
<a href="RequestCoach.jsp"class="button"> Richiesta Coach
</a>
<% }%>
<a id="ShowLinked" style="margin:0.5%;" href="ChangePassword.jsp" class="button">Modifica Password 
</a>
<a id="ShowLinked" style="margin:0.5%;" href="DeleteAccount.jsp"class="button">Elimina Account
</a>
<%} else{%>
<h1>Effettua il Login per visualizzare questa pagina!</h1>
<%} %>
</body>
</html>