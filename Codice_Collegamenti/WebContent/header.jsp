<%@ page language="java" import="collegamenti.model.*" import= "service.*" session="true" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="layout.css">
<meta charset="ISO-8859-1">
<script src="./Script/jquery.js" type="text/javascript"></script>
<title>Collegamenti 2019</title>
</head>
<body>

<body>
    <div class="banner"><a href="home.jsp">
      <img id="logo" src="img/logo.png" alt=""></a>
      <%
      	User utente = ((User)session.getAttribute("User")); 
            if (utente==null){
      %>
        <form id="AccessBox" action="ServerHandler" method="post" name="ServerHandler">
		<input type="text" id="box_email" name="email" placeholder="Email">
		<input type="password" id="box_pass" name="pass" placeholder="Password">
		<input type="hidden" id="op" name="operation" value="">
	
		<input class="button" onclick="accedi()" type="submit" value="Login">
		<a href="register.jsp" id="register_box">Non sei ancora registrato?</a>
		</form>
		<%} else{%>
		<div class="NotifyHeader">
		<a href="forum.jsp">
		<img src="img/icons8-gruppo-utente-uomo-donna-80.png">
		<button class="button">Forum</button>
		</a>
		<a href="profile.jsp">
		<img src="img/icons8-amministratore-uomo-80.png">
		<button class="button">Area Utente</button>
		</a>
		<a href="Mailbox.jsp">
		<img src="img/icons8-chat-80.png">
		<button class="button">Messaggi</button>
		</a>
		<form action="ServerHandler" method="get" name="ServerHandler">
		<input type="hidden" id="op" name="operation" value="">
		<img src="img/icons8-denied-80.png">
		<input class="button" type="submit" onclick="logout()" value="Logout">
		</form>
		</div>
		<%}%>
		
	           
</div>
</body>

<script >
    function accedi(){
    	$("#op").val("login");
    	return;
    }
    
    function logout(){
    	$("#op").val("logout");
    }
    </script>
</html>