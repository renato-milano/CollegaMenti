package service;
/** 
 * AccountManager
 * 
 * Classe che gestisce l'utente registrato al sistema
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

public class AccountManager implements Action {

	private HttpSession session;
	@Override
	public HttpSession perform(HttpServletRequest request, HttpServletResponse response) {
		
		this.session= request.getSession();
		String operation=request.getParameter("operation");
		User local= (User) session.getAttribute("User");
		String Email = local.getEmail();
		switch(operation) {
		case "NewSub":
			NewSubscription(Email);
			local.setAbbonato(true);
			session.removeAttribute("User");
			session.setAttribute("User", local);
			break;
		case "EditPass":
			User newUser= ChangePassword(Email, request.getParameter("NewPassword"));
			session.removeAttribute("User");
			session.setAttribute("User", newUser);
			break;
		case "NewCoachRequest":
			String Category= request.getParameter("Category");
			String Description = request.getParameter("Description");
			NewCoachRequest(Email, Category, Description);
			break;
			
		case "AddStudent":
			String Student= request.getParameter("Email");
			String Coach= local.getEmail();
			AcceptStudent(Coach, Student);
			Database.getIstance().RemoveStudentRequest(Integer.parseInt(request.getParameter("IDRichiesta")));
				break;
		case "DelAccount":		
			DeleteAccount();
			
			
		}
		return session;
		
		
	}
	
	private void DeleteAccount() {
		User local= (User) session.getAttribute("User");
		Database.getIstance().RemoveUser(local.getEmail());
		session.removeAttribute("User");
	}
	
	private void NewCoachRequest(String Email,String Category,String Description) {
		Database.getIstance().AddRequest(Email, Category, Description);

	}
	
	private void AcceptStudent(String Coach,String Student) {
		Database.getIstance().AddStudents(Coach, Student);
			
	}
	
	private void NewSubscription(String Email) {
		Database.getIstance().AddSub(Email);
		
	}
	private User ChangePassword(String Email,String Pass) {
		Database.getIstance().ChangePassword(Email, Pass);
	return Database.getIstance().CheckAccess(Email, Pass);
	}
}
