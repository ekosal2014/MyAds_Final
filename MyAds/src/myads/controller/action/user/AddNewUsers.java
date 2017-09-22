package myads.controller.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;

public class AddNewUsers implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		ActionForward forward=new ActionForward();

		try{
			
			forward.setRedirect(false);
			forward.setPath("/step2/myregister_admin.jsp");
			return forward;
		}catch(Exception e){
		}
		return null;
	}

}
