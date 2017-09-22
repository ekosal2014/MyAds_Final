package myads.controller.admin.posting;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.model.dao.AdminPostingDao;

public class CountNewPosting implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward=new ActionForward();
		AdminPostingDao dao=new AdminPostingDao();

		try{
			
			request.getSession().setAttribute("result", dao.listAllPosting().size()+"");				
			
			forward.setRedirect(false);
			forward.setPath("mynewposts.jsp");
			return forward;
		}catch(Exception e){
		}
		return null;
	}

}
