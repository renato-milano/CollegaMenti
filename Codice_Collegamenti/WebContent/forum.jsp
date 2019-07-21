<%@ page language="java" contentType="text/html; charset=ISO-8859-1" session="true" 
import="collegamenti.model.Thread" 
import="collegamenti.model.*" 
import="java.util.ArrayList"
pageEncoding="ISO-8859-1"%>
<jsp:include page="header.jsp" flush="true"/>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Collegamenti 2019</title>
</head>
<body>
<h1 class="Title">Forum</h1>
<form id="CategoriesForum" action="ServerHandler" method="get" name="ServerHandler">
<select  name="CategSelection">
<option value="Matematica e Fisica">Matematica e Fisica
<option value="Italiano">Italiano
<option value="Informatica">Informatica
<option value="Lingue">Lingue
<option value="Altro">Altro

</select>
<input class="button" onclick="applyFilter()" type="submit" value="Applica">
<input type="hidden" id="op" name="operation" value="Filter">
</form>
<%if(session.getAttribute("User")!=null) { %>
<a href="NewThread.jsp">
<input class="button" id="newThreadButton" onclick="newThread()" type="submit" value="+ Apri Nuovo Thread">
</a>
<%}
%>
<%
ArrayList<Thread> Threads= new ArrayList<Thread>();
if(session.getAttribute("Categ")==null){
Database data = Database.getIstance();
Threads = data.getRandomThreads();
}
else{
Database data = Database.getIstance();
Threads = data.getCategorizedThreads((String)session.getAttribute("Categ"));
}
for(int i=0;i<Threads.size();i++){%>

<div class="MessageBox" ><a href="Thread.jsp?id=<%=Threads.get(i).getID() %>">
<p id="TitoloThreadList">Titolo: <%=Threads.get(i).getTitolo() %></p><br>
<p id="CreatorThreadList">Creato da: <%=Threads.get(i).getCreatore()%></p><br>
</a>
</div><br>


<%} %>

</body>
<script>
function applyFilter(){
	$("#op").val("Filter");
	return;
}

</script>
</html>