package myads.controller.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.model.dao.UserDao;
import myads.model.dto.UserDto;

public class ExistUser implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UserDao dao=new UserDao();
		UserDto dto=new UserDto();
		ActionForward forward=new ActionForward();

		 //get id request from browser requst
		   int num=Integer.parseInt(request.getParameter("id"));

		try{

			dto=dao.existUser(num);// view category
			request.setAttribute("result", dto);
	
			forward.setRedirect(false);
			forward.setPath("/step2/myregister_admin_exist.jsp");
			return forward;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
