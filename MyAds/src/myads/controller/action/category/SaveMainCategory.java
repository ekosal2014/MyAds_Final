package myads.controller.action.category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.model.dao.CategoryDao;
import myads.model.dto.MainCategoryDto;

public class SaveMainCategory implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MainCategoryDto dto=new MainCategoryDto();
		CategoryDao dao=new CategoryDao();
		ActionForward forward=new ActionForward();
		
		//set data from request data to DTO
		dto.setId(Integer.parseInt(request.getParameter("id")));
		dto.setIco_cls_name(request.getParameter("txt_ico_class"));
		dto.setName(request.getParameter("txt_cate_name"));
		dto.setDsc(request.getParameter("txt_dsc"));
		dto.setActive(Integer.parseInt(request.getParameter("txt_active")));
		
		try{
	
			if(dao.updateMaincategory(dto)){
				System.out.println("save is ok");
				forward.setRedirect(false);
				forward.setPath("/step2/mymain_category_tbl.jsp");
				return forward;
			}else{
				System.out.println("save is error");
			}

		}catch(Exception e){
		}
		return null;
	}

}
