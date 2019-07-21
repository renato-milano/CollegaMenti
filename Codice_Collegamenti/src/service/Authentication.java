package service;

/** 
 * User
 * 
 * Classe che gestisce l'accesso di un utente registrato al sistema
 * 
 * @author Renato Milano
 * @version 0.1
 * @since 10/06/19
 * 
 * 2019 Copyright by Collegamenti
 * 
 */

import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import collegamenti.model.Database;
import collegamenti.model.User;

public class Authentication implements Action{
	
	private HttpServletRequest req;
	
	private String operation;
	
	@Override
	public HttpSession perform(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = (HttpSession) request.getSession();
		req=request;
		this.operation= (String) request.getParameter("operation");
		
		if(operation.equalsIgnoreCase("login")) {
			
			User utente= login();
			session.setAttribute("User", utente);
			
		}
		
		if(operation.equalsIgnoreCase("logout")) {
			logout();
		}
		return session;
	}
	
	
	
	private User login() {
		
		String email= (String) req.getParameter("email");
		String pass= (String) req.getParameter("pass");
		Database data= Database.getIstance();
		User utente =data.CheckAccess(email, pass);
		
		return utente;
	}
	
	private void logout() {
		req.getSession().removeAttribute("User");
	}

	
}
