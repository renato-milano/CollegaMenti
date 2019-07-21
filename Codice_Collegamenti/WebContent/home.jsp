<%@ page language="java" import="java.util.ArrayList" import="collegamenti.model.User" import="collegamenti.model.Database" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <jsp:include page="header.jsp" flush="true"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Collegamenti 2019</title>
</head>
<body>

<%User utente = ((User)session.getAttribute("User")); 
if (utente!=null&&utente.getTipo()!=3){ %>

<div style="display:inline;" id="Home_Selection">
<div class="Home_peek">

<a href="forum.jsp">
<img class="Home_img" src="img/icons8-gruppo-utente-uomo-donna-80.png">
<p class="Home_text">Entra nel Forum
</a>
<br><br>
<a href="profile.jsp">
<img class="Home_img" src="img/icons8-amministratore-uomo-80.png">
<p class="Home_text">Area Utente
</a>
</div>
</div>
<%}else if(utente!=null&&utente.getTipo()==3){ 
ArrayList<User> users= Database.getIstance().getRegisterRequest();%>
<h1>Richieste Coach: </h1>
<div id="RequestTable">
<%for(int i =0;i<users.size();i++){%>
<div class="RequestBox" id="RequestCell<%=i%>"> 
<% 
String Email= users.get(i).getEmail();
%>
<p style="margin-left:1%;">Richiesta inviata da: <%=users.get(i).getNome()+" "+users.get(i).getCognome()%></p><br>
<p style="margin-left:1%;">Titolo Di Studio: <%= users.get(i).getTitolodiStudio()%></p><br>
<button style="margin:1%;float:right;" class="button" onclick="CoachRejected('<%=Email%>',<%=i%>)" value="X">X</button>
<button style="margin:1%;float:right;" class="button" onclick="CoachAccepted('<%=Email%>',<%=i%>)" value="Accetta">Accetta</button>
</div>
<%}%>
<%} else{ %>
<div class="Presentation">
<h1> Registrati ad una piattaforma libera, composta da una community affiatata e sempre disposta ad aiutarti!</h1>
<h2> Se invece sei un professionista del settore registrati per aiutare studenti in difficoltà, migliora con i nostri ragazzi la tua esperienza da Tutor!</h2>
</div>
<img id="PeopleIMG" src="img/people1.jpg">
<%} %>
</body>
<script>
function CoachRejected(Email,i){
	var xhttp =new XMLHttpRequest();
	xhttp.open("POST","ServerHandler?Email="+Email+"&operation=RejectCoach", true);
	xhttp.send();
	$("#RequestCell"+i).hide("slow");
}

function CoachAccepted(Email,i){
	var xhttp =new XMLHttpRequest();
	xhttp.open("POST","ServerHandler?Email="+Email+"&operation=AcceptCoach", true);
	xhttp.send();
	$("#RequestCell"+i).hide("slow");
}

</script>
</html>