<%@ page language="java" import="collegamenti.model.User" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:include page="header.jsp" flush="true"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Collegamenti 2019</title>
</head>
<body>
<h1>Modifica Password:</h1>
<form action="ServerHandler" method="post">
<%User local = (User) session.getAttribute("User"); %>
<input type="hidden" value="<%=local.getPassword()%>" id="OldPass">
<span>
<p>Vecchia Password:</p>
<input type="text" id="pass" onChange="controllaVecchiaPass()" > 
<p style="visibility:hidden" class="warning" id="messaggio_pass2">Inserire Password Corretta<p>
</span><br><br>
<span>
<p>Nuova Password:</p>
<input type="text" id="newpass" onChange="controllaPass()" name="NewPassword"> 
<p style="visibility:hidden" class="warning" id="messaggio_pass">Minimo 8 caratteri massimo 16, almeno un Carattere maiuscolo, almeno un numero<p>
</span><br><br>
<span>
<p>Conferma Password:</p>
<input type="text" id="PassToControl" onChange="UguaglianzaPass()" > 
<p style="visibility:hidden" class="warning" id="messaggio_pass3">Corrispondenza non trovata.<p>
</span><br><br>
<input type="hidden" name="operation" value="EditPass">
<input id="VerificaCheck" class="button" value="Verifica" onclick="CheckAll()"><br>
<input style="visibility:hidden" id="BeforeCheck" class="button" value="Modifica Password" type="submit">
</form>
</body>
<script>
function controllaVecchiaPass(){
	var old= $("#OldPass").val();
	var nuova = $("#pass").val();
	var result= old.localeCompare(nuova);
	console.log("Old:" +old ,"Nuova:"+ nuova , result);
	if(result!=0){
		$("#messaggio_pass2").css("visibility","visible");
		$("#messaggio_pass2").css("color","red");
	    document.getElementById("messaggio_pass2").innerHTML = "Corrispondenza non trovata!";
		$("#messaggio_pass2").show("slow");
		return false;
		
	}else{
		$("#messaggio_pass2").css("visibility","visible");
		$("#messaggio_pass2").css("color","green");
	    document.getElementById("messaggio_pass2").innerHTML = "Ok!";
	    $("#messaggio_pass2").show("slow");
	    return true;
		}
	
}
function controllaPass(){
	var pass= document.getElementById("newpass").value;
	if(pass==""){
		$("#messaggio_pass").css("visibility","visible");
		$("#messaggio_pass").css("color","red");
		$("#messaggio_pass").hide("slow");
		document.getElementById("messaggio_pass").innerHTML = "Minimo 8 caratteri massimo 16, almeno un Carattere maiuscolo, almeno un numero";
		$("#messaggio_pass").show("slow");

		return false;

	}else{
	var pass_exp= /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9_.,\-+*!#@?]{8,16})$/;
	if(!pass_exp.test(pass)){
		$("#messaggio_pass").css("visibility","visible");
		$("#messaggio_pass").css("color","red");
		$("#messaggio_pass").hide();
		document.getElementById("messaggio_pass").innerHTML = "Minimo 8 caratteri massimo 16, almeno un Carattere maiuscolo, almeno un numero";
		$("#messaggio_pass").show("slow");

		return false;
	}
	else{
		$("#messaggio_pass").css("visibility","visible");
		$("#messaggio_pass").css("color","green");
		$("#messaggio_pass").hide();
		document.getElementById("messaggio_pass").innerHTML = "Ok!";
		$("#messaggio_pass").show("slow");
		return true;
		}
}
	}
	
	function UguaglianzaPass(){
		var pass1= $("#newpass").val();
		var pass2= $("#PassToControl").val();
		var result= pass1.localeCompare(pass2);
		if (result!=0){
			$("#messaggio_pass3").css("visibility","visible");
			$("#messaggio_pass3").css("color","red");
			document.getElementById("messaggio_pass3").innerHTML = "Corrispondenza non trovata!";
			$("#messaggio_pass3").show("slow");
		
			return false;
		}else{
			$("#messaggio_pass3").css("visibility","visible");
			$("#messaggio_pass3").css("color","green");
			document.getElementById("messaggio_pass3").innerHTML = "Ok!";
			$("#messaggio_pass3").show("slow");
		    return true;
		    

		}
	}
	
	function CheckAll(){
		var test1= controllaVecchiaPass();
		var test2= controllaPass();
		var test3= UguaglianzaPass();
		console.log(test1,test2,test3);
		if(test1&&test3&&test2){
			$("#BeforeCheck").css("visibility","visible");
			$("#VerificaCheck").css("visibility","hidden");
		}
	}
</script>
</html>