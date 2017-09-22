package myads.controller.action.posting;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.model.dao.AdminPostingDao;

public class AllPostingAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward=new ActionForward();
		AdminPostingDao dao=new AdminPostingDao();

		try{
			
			request.getSession().setAttribute("listAllPosting", dao.listAllPosting());		
			//System.out.println("Hello");
			forward.setRedirect(false);
			forward.setPath("mynewposts.jsp");
			return forward;
			
		}catch(Exception e){
		}
		return null;
	}

}
