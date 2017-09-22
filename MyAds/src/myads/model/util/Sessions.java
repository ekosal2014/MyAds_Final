package myads.model.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Sessions {
	public static HttpSession session(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		return session;
	}
	
}
