<%@ page language="java" import="collegamenti.model.*" import="java.util.ArrayList" contentType="text/html; charset=ISO-8859-1"
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
Database data= Database.getIstance();
User toChat= data.getUserbyEmail(request.getParameter("Email"));
User local= (User) session.getAttribute("User");
ArrayList<Message> messagges= data.GetMessages(toChat.getEmail(), local.getEmail());
%>
<h1><%=toChat.getNome()+" "+toChat.getCognome() %></h1>
<%for(int i=0;i<messagges.size();i++){
Message msg= messagges.get(i);
if(msg.getReciever().equalsIgnoreCase(local.getEmail())){%>
<div style="background-color:#018BFE;float:left;width:50%" class="MessageBox">
<p><%=toChat.getNome() %>:</p><br>
<p><%=msg.getObject()%></p><br>
</div>
<%}else{ %>
<div style="float:right;width:50%" class="MessageBox">
<p>Tu:</p><br>
<p><%=msg.getObject()%></p><br>
</div>
<%}
} %>
<div id="MessageArea">
    <textarea id="Message" class="Message_Box" rows = "5" cols = "500" name = "Message">
    </textarea>
    <button style="float:right;"class="button" onclick="newMessage()">Invia</button>
	<input id="EmailToSend" type="hidden" value="<%=toChat.getEmail()%>">
	</div>
</body>

<script type="text/javascript">
function newMessage(){
	var Text= $.trim($("#Message").val());
	var Email= $.trim($("#EmailToSend").val());
	var $div=("<div style='float:right;width:50%' class='MessageBox'><p>Tu:</p><br><p>"+Text+"</p><br></div>");
	console.log(Text);
	$("#MessageArea").before($div);
	$("#Message").val("");
	var xhttp =new XMLHttpRequest();
	xhttp.open("POST","ServerHandler?EmailToSend="+Email+"&Object="+Text+"&operation=NewMessage", true);
	xhttp.send();
	
}
</script>
</html>