<%@page import="collegamenti.model.ThreadMessage"%>
<%@page import="collegamenti.model.Database"%>
<%@ page language="java" import="java.util.ArrayList" import="collegamenti.model.User" import="collegamenti.*" session="true" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <jsp:include page="header.jsp" flush="true"/> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Collegamenti 2019</title>
</head>
<body>
<% 
Database data = Database.getIstance();
ArrayList<ThreadMessage> Answers= new ArrayList<ThreadMessage>();
int id = Integer.parseInt(request.getParameter("id"));
collegamenti.model.Thread local= data.getThreadByid(id);
Answers= data.getThreadAnswers(id);
User user= (User)session.getAttribute("User");
if(user.getEmail().equals(local.getCreatore())){%>
<form action="ServerHandler" method="post">
<input type="submit"  class="button" value="Chiudi Thread">
<input type="hidden" value="DeleteThread" name="operation">
<input type="hidden" value="<%=local.getID()%>" name=idThread>
</form>
<%}%>
<div class="MessageBox" >
<p id="CreatorMessageBox">Inviato da: <%=local.getCreatore() %></p><br>
<p id="TitleThreadBox"><br><%=local.getTitolo() %></p><br>
<p id="ObjectThreadBox">Oggetto: <br><%=local.getOggetto()%></p><br>

</div><br>
<%for(int i =0;i<Answers.size();i++) {%>
<div class="MessageBox">
<%int votes=Answers.get(i).getVotes();  %>
<button class="button" id="VoteMessage" onclick="Addvote(<%=i+1%>,<%=id%>,
<%=votes%>)" value="+">+</button>
<p id="NumVotes<%=i %>"><%=votes%> Utenti hanno apprezzato la risposta! <p><br>
<p id="CreatorMessageBox">Inviato da: <%=Answers.get(i).getAutore() %></p><br><br>
<p id="ObjectThreadBox">Oggetto: <br><%=Answers.get(i).getMessaggio()%></p><br>
</div><br>
<%} %>
<div>
<form action="ServerHandler" method="post" name="ServerHandler">
<p>Rispondi:</p>
    <textarea class="Message_Box" rows = "5" cols = "500" name = "Answer">
    </textarea>
     <input class="button" onclick="NewAnswer()" type = "submit" value = "Invia" />
     <input type="hidden" id="op" name="operation" value="NewThreadAnswer">
     <input type="hidden" id="op" name="idThread" value="<%=id %>">
     </form>
     </div>
</body>
<script>
function NewAnswer(){
	$("#op").val("NewThreadAnswer");
return;
}
function Addvote(i,idThread,numVoti){
	console.log("idT:"+idThread+" Ordine:"+i+" NumVoti:"+numVoti);
	numVoti++;
	var xhttp =new XMLHttpRequest();
	xhttp.open("POST","ServerHandler?idThread="+idThread+"&Ordine="+i+"&votes="+numVoti+"&operation=AddVotes", true);
	xhttp.send();
	i= i-1;
	$("#NumVotes"+i).html(numVoti+" Utenti hanno apprezzato la risposta!");
}
</script>
</html>