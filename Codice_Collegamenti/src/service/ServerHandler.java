package service;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import collegamenti.model.Database;
import collegamenti.model.User;






@WebServlet("/ServerHandler")
public class ServerHandler extends HttpServlet {
	private  Map<String,Action> actionMap;
	//TIPI DI ACCOUNT TIPO=0 STUDENTE | TIPO=1 COACH | TIPO=2 COACH DA CONFERMARE | TIPO=3 ADMIN
	private String operation;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServerHandler() {
		actionMap = new HashMap<String,Action>();
		 actionMap.put("Authentication", new Authentication());
		 actionMap.put("Register", new Register());
		 actionMap.put("Thread", new ThreadManager());
		 actionMap.put("Message", new MessageManager());
		 actionMap.put("Account", new AccountManager());
		
}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		operation = (String) request.getParameter("operation");
		HttpSession session= request.getSession();
		Action action=null;
		
		if (operation.equalsIgnoreCase("login") || operation.equalsIgnoreCase("logout")) {
			 action = actionMap.get("Authentication");
			 session = action.perform(request, response);
			 RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/home.jsp");
			 if(session.getAttribute("User")==null) {
				 response.setStatus(404);
			 }
				dispatcher.forward(request, response);
			
		}
			
 
		if (operation.equalsIgnoreCase("regStud") || operation.equalsIgnoreCase("regCoach")) {
			 action = actionMap.get("Register");
			 session = action.perform(request, response);
			 RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/home.jsp");
			 if(session.getAttribute("User")==null) {
				 response.setStatus(404);
			 }
				dispatcher.forward(request, response);
			 
			}
		
		if(operation.equalsIgnoreCase("Filter")) {
			String Selection= (String) request.getParameter("CategSelection");
			session.setAttribute("Categ", Selection);
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/forum.jsp");
			dispatcher.forward(request, response);
		}
		if (operation.equalsIgnoreCase("NewThread")) {
			if(session.getAttribute("User")!=null) {
				action= actionMap.get("Thread");
				session= action.perform(request, response);
				RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/forum.jsp");
				dispatcher.forward(request, response);
			}
		}
		if (operation.equalsIgnoreCase("NewThreadAnswer")) {
			if(session.getAttribute("User")!=null) {
				action= actionMap.get("Thread");
				session= action.perform(request, response);
				RequestDispatcher dispatcher= getServletContext().getRequestDispatcher
						("/Thread.jsp?id="+request.getParameter("idThread"));
				dispatcher.forward(request, response);
			}
	}
		if(operation.equalsIgnoreCase("AddVotes")) {
		if(session.getAttribute("User")!=null) {
			action=actionMap.get("Thread");
			session= action.perform(request, response);
		}
		}
		
		
		if(operation.equalsIgnoreCase("NewSub")) {
		action= actionMap.get("Account");
		session= action.perform(request, response);
		RequestDispatcher dispatcher= getServletContext().getRequestDispatcher
				("/profile.jsp");
		dispatcher.forward(request, response);
		}
		
		if(operation.equalsIgnoreCase("NewCoachRequest")) {
			action= actionMap.get("Account");
			session= action.perform(request, response);
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher
					("/profile.jsp");
			dispatcher.forward(request, response);
		}
		if(operation.equalsIgnoreCase("AddStudent")) {
		action=actionMap.get("Account");	
		session= action.perform(request, response);
		
		}
		
		if(operation.equalsIgnoreCase("NewMessage")) {
		action= actionMap.get("Message");
		session= action.perform(request, response);
	}
		if(operation.equalsIgnoreCase("DelAccount")) {
			//User local= (User) session.getAttribute("User");
			//Database.getIstance().RemoveUser(local.getEmail());
			//session.removeAttribute("User");
			action=actionMap.get("Account");
			action.perform(request, response);
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher
					("/profile.jsp");
			dispatcher.forward(request, response);
		}
		if(operation.equalsIgnoreCase("EditPass")) {
			action=actionMap.get("Account");
			session= action.perform(request, response);
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher
					("/profile.jsp");
			dispatcher.forward(request, response);
		}
		if(operation.equalsIgnoreCase("RejectCoach")) {
			action= actionMap.get("Register");
			action.perform(request, response);
		}
		if(operation.equalsIgnoreCase("AcceptCoach")) {
			action= actionMap.get("Register");
			action.perform(request, response);
		}
		if(operation.equalsIgnoreCase("DeleteThread")) {
			action= actionMap.get("Thread");
			session= action.perform(request, response);
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher
					("/forum.jsp");
			dispatcher.forward(request, response);
		}
}
	
}
