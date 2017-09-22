package myads.controller.action.category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.model.dao.CategoryDao;
import myads.model.dto.MainCategoryDto;

public class ExistMainCategory implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CategoryDao dao=new CategoryDao();
		MainCategoryDto dto=null;
		
		ActionForward forward=new ActionForward();

		 //get id request from browser requst
		   int num=Integer.parseInt(request.getParameter("id"));

		try{
			
			dto=dao.existmainCategory(num);// view category
			request.setAttribute("result", dto);
	
			forward.setRedirect(false);
			forward.setPath("/step2/mymain_category_exist.jsp");
			return forward;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
