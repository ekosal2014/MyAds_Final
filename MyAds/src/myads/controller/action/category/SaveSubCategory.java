package myads.controller.action.category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.model.dao.CategoryDao;
import myads.model.dto.SubCategoryDto;

public class SaveSubCategory implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SubCategoryDto dto=new SubCategoryDto();
		CategoryDao dao=new CategoryDao();
		ActionForward forward=new ActionForward();
		
		//set data from request data to DTO
		dto.setId(Integer.parseInt(request.getParameter("id")));
		dto.setSubid(Integer.valueOf(request.getParameter("txt_cateid")));
		dto.setClass_name(request.getParameter("txt_cls_name"));
		dto.setName(request.getParameter("txt_subcate_name"));
		dto.setDsc(request.getParameter("txt_dsc"));
		dto.setActive(1);
		
		try{
	
			if(dao.updateSubcategory(dto)){
				System.out.println("save is ok");
				forward.setRedirect(false);
				forward.setPath("/step2/mysub_category_tbl.jsp");
				return forward;
			}else{
				System.out.println("save is error");
			}

		}catch(Exception e){
		}
		return null;
	}

}
