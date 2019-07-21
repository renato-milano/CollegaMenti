<%@ page language="java" import="collegamenti.model.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="header.jsp" flush="true"/>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Collegamenti 2019</title>
</head>
<body>
<h1>Sottoscrivi Abbonamento</h1>
<form action="ServerHandler" method="get" name="ServerHandler">
<input type="hidden" class="op" name="operation" value="NewSub">
<p>Nome: </p>
<input type="text" id="name" name="name">
</span>
<br><br>
<span>
<p>Cognome:</p>
<input type="text" id="surname" name="surname"> 
</span>
<br><br>
<span>
<p>Intestatario Carta:</p>
<input type="text" name="nameCard">
</span>
<br><br>
<span>
<p>Numero Carta:</p>
<input type="text" name="NumCard">
</span>
<br><br>
<input type="submit" onclick="NewSub()" class="button" value="Abbonati"> 
</form>
</body>
<script>
function NewSub(){
	$("#op").val("NewSub");
}
</script>

</html>