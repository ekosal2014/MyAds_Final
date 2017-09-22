package myads.controller.admin.posting;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.model.dao.AdminPostingDao;

public class AprovePositng implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		AdminPostingDao dao=new AdminPostingDao();

		try{
			
			int posting_id=Integer.valueOf(request.getParameter("postingId"));
			int member_id =Integer.valueOf(request.getParameter("memberid"));
			
			System.out.println("Posint = "+posting_id+"   "+member_id);
			
			request.getSession().setAttribute("result", dao.getApprovePosting(posting_id, member_id));			
			
			forward.setRedirect(false);
			forward.setPath("mynewposts.jsp");
			return forward;
		}catch(Exception e){
		}
		return null;
	}

	
	

}
