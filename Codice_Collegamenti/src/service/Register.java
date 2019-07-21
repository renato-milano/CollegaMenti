package service;
/** 
 * Register
 * 
 * Classe che gestisce l'iscrizione di un utente registrato al sistema
 * 
 * @author Renato Milano
 * @version 0.1
 * @since 10/06/19
 * 
 * 2019 Copyright by Collegamenti
 * 
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import collegamenti.model.Database;
import collegamenti.model.User;

public class Register implements Action {
	private String operation;
	private HttpSession session;
	
	@Override
	public HttpSession perform(HttpServletRequest request, HttpServletResponse response) {
		operation= (String)request.getParameter("operation");
		this.session= request.getSession();
		
		
		if(operation.equalsIgnoreCase("regStud")) {
			String Email=request.getParameter("emailStud");
			String Password=request.getParameter("passStud");
			String Name=request.getParameter("nomeStud");
			String Surname= request.getParameter("cognomeStud");
			int Tipo=0;
			RegisterStudent(Email, Password, Name, Surname, Tipo, null, null);
			
				return session;
			}
		
		
         if(operation.equalsIgnoreCase("regCoach")) {
			
        	 	String Email=request.getParameter("emailCoach");
				String Password=request.getParameter("passCoach");
				String Nome=request.getParameter("nomeCoach");
				String Cognome= request.getParameter("cognomeCoach");
				int Tipo=2;
				String Titolo=request.getParameter("TitoloDiStudio"); 
				String Category= getCategoria(request.getParameter("TitoloDiStudio"));
				RegisterCoach(Email,Password,Nome,Cognome,Tipo,Titolo,Category);
				return session;
				}
			
			if(operation.equalsIgnoreCase("RejectCoach")) {
        	 RejectCoach(request.getParameter("Email"));
        	 return session;
         }
			if(operation.equalsIgnoreCase("AcceptCoach")) {
				AcceptCoach(request.getParameter("Email"));
				return session;
			}
         
		return null;
	}
	
	
private void AcceptCoach(String Email) {
	Database.getIstance().ValidateCoach(Email);
}
	
 private boolean RegisterCoach(String Email,String Password,String Name,String Surname,int Tipo,String Study,String Category) {
	 Database data= Database.getIstance();
	 boolean insertCoach = data.newUser(Email,Password, Name,Surname, Tipo,Study, Category);
		if(insertCoach==true) {
			User utente= data.CheckAccess(Email, Password);
			session.setAttribute("User", utente);
			
			}
		return insertCoach;
 }
	
 private void RegisterStudent(String Email,String Password,String Name,String Surname,int Tipo,String Study,String Category) {
	 Database data= Database.getIstance();
	 boolean insertCoach = data.newUser(Email,Password, Name,Surname, Tipo,Study, Category);
		if(insertCoach==true) {
			User utente= data.CheckAccess(Email, Password);
			session.setAttribute("User", utente);
			
			}
 }
 
private String getCategoria(String TitoloDiStudio) {
	String ToCompare= TitoloDiStudio.toLowerCase();
	if(ToCompare.contains("matematica")||ToCompare.contains("fisica")
			||ToCompare.contains("ingegnere")) {
		return "Matematica e Fisica";
	}
	if(ToCompare.contains("lingue")) {
		return "Lingue";
	}
	if(ToCompare.contains("lettere")||ToCompare.contains("italiano")) {
		return "Italiano";
	}
	if(ToCompare.contains("informatica")) {
		ToCompare.contains("Informatica");
	}
		return "Altro";
	
}

private void RejectCoach(String Email) {
	Database.getIstance().RemoveUser(Email);
}

}
