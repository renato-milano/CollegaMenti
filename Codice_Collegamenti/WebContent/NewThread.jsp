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
<h1 class="Title">Esponi il tuo problema!</h1>
<form action="ServerHandler" method="get" name="ServerHandler">
	<p>Titolo:</p><br>
    <textarea rows = "1" cols = "50" name = "Titolo">
    </textarea><br>
    <p>Descrizione:
    <textarea class="Message_Box" rows = "5" cols = "500" name = "Descrizione">
    </textarea>
    <p>Cateogria:
    <br>
    <select  name="CategSelection">
	<option value="Matematica e Fisica">Matematica e Fisica
	<option value="Italiano">Italiano
	<option value="Informatica">Informatica
	<option value="Lingue">Lingue
	<option value="Altro">Altro
</select>
<br>
<br>
    <input class="button" onclick="NewThread()" type = "submit" value = "+ Apri Thread" />
    <input type="hidden" id="op" name="operation" value="NewThread">
    </form>
</body>
<script>

function NewThread(){
	$("#op").val("NewThread");
	return;
}

</script>
</html>