package myads.controller.action.category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.model.dao.CategoryDao;

public class DeleteMainCategoryAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CategoryDao dao=new CategoryDao();
		ActionForward forward=new ActionForward();

		 //get id request from browser requst
		   int id=Integer.parseInt(request.getParameter("id"));
		
		try{
			
			dao.deleteMaincate(id);
	
			forward.setRedirect(false);
			forward.setPath("maincate.adm");
			return forward;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
