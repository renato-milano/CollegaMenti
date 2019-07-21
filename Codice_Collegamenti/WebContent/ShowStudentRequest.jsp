<%@ page language="java" import="java.util.ArrayList" import="collegamenti.model.*" contentType="text/html; charset=ISO-8859-1"
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
User local=(User) session.getAttribute("User");
String Category= local.getCompetenza();
ArrayList<StudentRequest> Students= Database.getIstance().getStudentRequest(Category); %>
<h1>Richieste Studenti: </h1>
<div id="RequestTable">
<%for(int i =0;i<Students.size();i++){%>
<div class="RequestBox" id="RequestCell<%=i%>"> 
<% 
String Email= Students.get(i).getEmail();
int idRichiesta= Students.get(i).getID();
%>

<p style="margin-left:1%;">Richiesta inviata da: <%=Email%></p>
<p style="margin-left:1%;">Argomento: <%= Students.get(i).getDescription()%></p>
<button style="margin:1%;float:right;" class="button" onclick="StudentRejected(<%=i%>)" value="X">X</button>
<button style="margin:1%;float:right;" class="button" onclick="StudentAccept('<%=Email%>',<%=i%>,<%=idRichiesta%>)" value="Accetta">Accetta</button>
</div>

<%} %>
</div>
</body>
<script charset="utf-8" type="text/javascript">
function StudentAccept(Email,i,id){
	console.log("Email Studente: "+Email);
	var xhttp =new XMLHttpRequest();
	xhttp.open("POST","ServerHandler?Email="+Email+"&IDRichiesta="+id+"&operation=AddStudent", true);
	xhttp.send();
	$("#RequestCell"+i).hide("slow");
}
function StudentRejected(i){
	$("#RequestCell"+i).hide("slow");
	var xhttp =new XMLHttpRequest();
	xhttp.open("POST","AddStudents?Email="+Email, true);
	xhttp.send();
}
</script>
</html>