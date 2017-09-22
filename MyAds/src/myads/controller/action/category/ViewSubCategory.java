package myads.controller.action.category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;

public class ViewSubCategory implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward=new ActionForward();

		try{
			
			forward.setRedirect(false);
			forward.setPath("/step2/mysub_category_tbl.jsp");
			return forward;
		}catch(Exception e){
		}
		return null;
	}

}
