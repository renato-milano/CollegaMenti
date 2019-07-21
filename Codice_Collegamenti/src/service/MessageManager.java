package service;
/** 
 * MessageManager
 * 
 * Classe che gestisce i messaggi registrati al sistema
 * 
 * @author Ciro Valerio Cerchia
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
import collegamenti.model.Message;
import collegamenti.model.User;

public class MessageManager implements Action {

	@Override
	public HttpSession perform(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session= request.getSession();
		User local= (User) session.getAttribute("User");
		Database data= Database.getIstance();
		String Sender= local.getEmail();
		String Reciever= request.getParameter("EmailToSend");
		String Object= request.getParameter("Object");
		Message toSend= new Message(Object, Reciever, Sender);
		data.SendMessage(toSend);
		return session;
	}

}
