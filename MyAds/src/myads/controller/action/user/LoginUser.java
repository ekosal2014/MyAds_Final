package myads.controller.action.user;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.model.dao.UserDao;
import myads.model.dto.UserDto;

public class LoginUser implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

ActionForward forward=new ActionForward();
		
		UserDao dao=new UserDao();
		ResultSet rs=null;
		HttpSession session=request.getSession(true);
		
		try {
			 System.out.println("txt_username: "+ request.getParameter("txt_username"));
			 System.out.println("txt_password: "+request.getParameter("txt_password"));
			 
			 MessageDigest md=MessageDigest.getInstance("MD5");

			md.update(request.getParameter("txt_password").getBytes(), 0, request.getParameter("txt_password").length());
			String encpass=new BigInteger(1,md.digest()).toString(16);
			 
			 rs=dao.login(request.getParameter("txt_username"), encpass);
			 
			 if(rs.next()){
				 
				 
				 UserDto user=new UserDto();
				 user.setId(rs.getInt("Id"));
				 user.setName(rs.getString("Name"));
				 user.setSex(rs.getString("Sex"));
				 user.setPhoto(rs.getString("Photo"));
				 user.setPhone(rs.getString("Phone"));
				 user.setEmail(rs.getString("Email"));
				 user.setDob(rs.getString("Dob"));
				 user.setAddress(rs.getString("Address"));
				 
				 session.setAttribute("userinfo", user);
				 session.setMaxInactiveInterval(24*60*60);
				// session.setAttribute("id", rs.getInt(1));
				 
				 forward.setRedirect(false);
				 forward.setPath("/step2/myadm.jsp");
				 
			 }else{
				 
				 System.out.println("Login fail");
				 session.removeAttribute("user");
				// session.removeAttribute("id");
				 session.setAttribute("fail", "fail");
				 
				 forward.setRedirect(true);
				 forward.setPath("");
				 
			 }
			 return forward;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}

}
