<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:include page="header.jsp" flush="true"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Collegamenti 2019</title>
</head>
<body>
<br><br>
<div class="register_form_stud">
<h1 class="Title">Compila il modulo per Registrarti come Studente!</h1><br>
<form action="ServerHandler" method="get"><span>
<input type="hidden" class="op" name="operation" value="">
<p>Email: </p>
<input type="text" onChange="controllaEmail()" id="idemail" name="emailStud">
<p class="warning" id="messaggio_email"><p><br>
</span>
<br><br>
<span>
<p>Password:</p>
<input type="password" id="idpass" onChange="controllaPass()" name="passStud"> 
<p class="warning" id="messaggio_pass">Minimo 8 caratteri massimo 16, almeno un Carattere maiuscolo, almeno un numero<p>
</span><br><br>
<span>
<p>Nome:</p>
<input type="text" name="nomeStud">
</span>
<br><br>
<span>
<p>Cognome:</p>
<input type="text" name="cognomeStud">
</span>
<br>
<br>
<input type="submit" id="reg_button" onclick="registraStud()" class="button" value="Registrati come Studente"> 
</form>
</div>

<!-- Sezione per la registrazione del Coach -->

<div class="register_form_coach">
<h1 class="Title">Compila il modulo per Registrarti come Coach!</h1><br>
<form action="ServerHandler" method="get"><span>
<input type="hidden" class="op" name="operation" value="regCoach">
<p>Email: </p>
<input type="text" onChange="controllaEmailCoach()" id="idemailCoach" name="emailCoach">
<p class="warning" id="messaggio_email_Coach"><p>
</span><br><br>
<span>
<p>Password:</p>
<input type="password" id="idpassCoach" onChange="controllaPassCoach()" name="passCoach"> 
<p class="warning" id="messaggio_pass_Coach">Minimo 8 caratteri massimo 16, almeno un Carattere maiuscolo, almeno un numero<p>
</span><br><br>
<span>
<p>Nome:</p>
<input type="text" name="nomeCoach">
</span><br><br>
<span>
<p>Cognome:</p>
<input type="text" name="cognomeCoach">
</span>
<span><br><br>
<p>Titolo Di Studio:</p>
<input type="text" name="TitoloDiStudio">
</span><br>
<br>
<input type="submit" id="reg_button_Coach" onclick="registraCoach()" class="button" value="Registrati come Coach"> 
</form>
</div>
<script>

function controllaEmail(){
	var email= document.getElementById("idemail").value;
	var email_exp= /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if(!email_exp.test(email)){
		$("#messaggio_email").css("color","red");
		$("#messaggio_email").hide();
		document.getElementById("messaggio_email").innerHTML = "Inserire un Email Valida!";
		$("#messaggio_email").show("slow");
		$("#reg_button").hide("slow");
		return false;
	}
	else{
		$("#messaggio_email").css("color","green");
		$("#messaggio_email").hide();
		document.getElementById("messaggio_email").innerHTML = "Ok!";
		$("#messaggio_email").show("slow");
		$("#reg_button").show("slow");
		return true;
	}
}
function controllaEmailCoach(){
	var email= document.getElementById("idemailCoach").value;
	var email_exp= /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if(!email_exp.test(email)){
		$("#messaggio_email_Coach").css("color","red");
		$("#messaggio_email_Coach").hide();
		document.getElementById("messaggio_email_Coach").innerHTML = "Inserire un Email Valida!";
		$("#messaggio_email_Coach").show("slow");
		$("#reg_button_Coach").hide("slow");
		return false;
	}
	else{
		$("#messaggio_email_Coach").css("color","green");
		$("#messaggio_email_Coach").hide();
		document.getElementById("messaggio_email_Coach").innerHTML = "Ok!";
		$("#messaggio_email_Coach").show("slow");
		$("#reg_button_Coach").show("slow");
		return true;
	}
}

function controllaPass(){
	var pass= document.getElementById("idpass").value;
	if(pass==""){
		$("#messaggio_pass").css("color","red");
		$("#messaggio_pass").hide();
		document.getElementById("messaggio_pass").innerHTML = "Minimo 8 caratteri massimo 16, almeno un Carattere maiuscolo, almeno un numero";
		$("#messaggio_pass").show("slow");
	}else{
	var pass_exp= /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9_.,\-+*!#@?]{8,16})$/;
	if(!pass_exp.test(pass)){
		$("#messaggio_pass").css("color","red");
		$("#messaggio_pass").hide();
		document.getElementById("messaggio_pass").innerHTML = "Inserire una Password Valida!";
		$("#messaggio_pass").show("slow");
		$("#reg_button").hide("slow");
		return false;
	}
	else{
		$("#messaggio_pass").css("color","green");
		$("#messaggio_pass").hide();
		document.getElementById("messaggio_pass").innerHTML = "Ok!";
		$("#messaggio_pass").show("slow");
		$("#reg_button").show("slow");
		return true;
		}
}
	}
	
function controllaPassCoach(){
	var pass= document.getElementById("idpassCoach").value;
	if(pass==""){
		$("#messaggio_pass_Coach").css("color","red");
		$("#messaggio_pass_Coach").hide();
		document.getElementById("messaggio_pass_Coach").innerHTML = "Minimo 8 caratteri massimo 16, almeno un Carattere maiuscolo, almeno un numero";
		$("#messaggio_pass_Coach").show("slow");
	}else{
	var pass_exp= /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9_.,\-+*!#@?]{8,16})$/;
	if(!pass_exp.test(pass)){
		$("#messaggio_pass_Coach").css("color","red");
		$("#messaggio_pass_Coach").hide();
		document.getElementById("messaggio_pass_Coach").innerHTML = "Inserire una Password Valida!";
		$("#messaggio_pass_Coach").show("slow");
		$("#reg_button").hide("slow");
		return false;
	}
	else{
		$("#messaggio_pass_Coach").css("color","green");
		$("#messaggio_pass_Coach").hide();
		document.getElementById("messaggio_pass_Coach").innerHTML = "Ok!";
		$("#messaggio_pass_Coach").show("slow");
		$("#reg_button_Coach").show("slow");
		return true;
		}
}
	}
	
	function registraCoach(){
		$(".op").val("regCoach");
		return;
	}
	
	function registraStud(){
		$(".op").val("regStud");
		return;
	}
</script>
</body>
</html>