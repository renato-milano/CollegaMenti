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
<h1>Richiedi un Coach!</h1>
<form action="ServerHandler" method="get" name="ServerHandler">
<input type="hidden" class="op" name="operation" value="NewCoachRequest">
<span>
<p>Nome: </p>
<input type="text" id="name" name="name">
</span>
<br><br>
<span>
<p>Cognome: </p>
<input type="text" id="surname" name="surname"> 
</span>
<br><br>
<span>
<p>Categoria: </p>
<select  name="Category">
<option value="Matematica e Fisica">Matematica e Fisica
<option value="Italiano">Italiano
<option value="Informatica">Informatica
<option value="Lingue">Lingue
<option value="Altro">Altro
</select>
</span>
<br><br>
<span>
<p>Argomento: </p>
<input type="text" name="Description">
</span>
<br><br>
<input type="submit" onclick="NewCoachReq()" class="button" value="Richiedi Coach"> 
</form>
</body>
<script type="text/javascript">

function NewCoachReq(){
	$("#op").val("NewCoachRequest");
}

</script>
</html>