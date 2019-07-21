package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface Action {

	public HttpSession perform(HttpServletRequest request , HttpServletResponse response);
	
}
