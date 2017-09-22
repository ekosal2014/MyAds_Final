package myads.controller.action.category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.model.dao.CategoryDao;
import myads.model.dto.SubCategoryDto;

public class ExistSubCategory implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CategoryDao dao=new CategoryDao();
		SubCategoryDto dto=null;
		
		ActionForward forward=new ActionForward();

		 //get id request from browser requst
		   int num=Integer.parseInt(request.getParameter("id"));
		   System.out.println("my sub cate:" + num);

		try{
			
			dto=dao.existsubCategory(num); // view category
			request.setAttribute("result", dto);
	
			forward.setRedirect(false);
			forward.setPath("/step2/mysub_category_exist.jsp");
			return forward;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
