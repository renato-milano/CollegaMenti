package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import collegamenti.model.Database;
import collegamenti.model.User;

public class ThreadManager implements Action {
	private HttpSession session;

	@Override
	public HttpSession perform(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String operation = (String) request.getParameter("operation");
		User Local= (User) request.getSession().getAttribute("User");
		Database data= Database.getIstance();

		switch(operation) {

		case "NewThread":
			String Title= request.getParameter("Titolo");
			String Description= request.getParameter("Descrizione");
			String Category= request.getParameter("CategSelection");
			String Creator= Local.getEmail();
			NewThread(Title, Description, Category, Creator);
			break;
		case "NewThreadAnswer":
			String Answer= request.getParameter("Answer");
			String user = Local.getEmail();
			int ID= Integer.parseInt(request.getParameter("idThread"));
			NewThreadAnswer(Answer, user, ID);
			break;
		
		case "AddVotes":
			data = Database.getIstance();
			int idThread= Integer.parseInt(request.getParameter("idThread"));
			int Ordine= Integer.parseInt(request.getParameter("Ordine"));
			int i= Integer.parseInt(request.getParameter("votes"));
			AddVotes(idThread,Ordine,i);
			break;
		case "DeleteThread":
			
			int IDThread= Integer.parseInt(request.getParameter("idThread"));
			DeleteThread(IDThread);
			break;
		}
		
		return session;
	}

	private void NewThread(String Title, String Description,String Category,String Creator) {
		Database.getIstance().addThread(Title,Description,Category,Creator);
	}
	
	private void NewThreadAnswer(String Answer,String User,int ID) {
		Database.getIstance().addThreadAnswer(Answer, User, ID); 
	}
	private void AddVotes(int IDThread, int Order, int votes) {
		Database.getIstance().addVote(votes, IDThread, Order);
	}
	private void DeleteThread(int ID) {
		Database.getIstance().DeleteThread(ID);
	}
	
}
