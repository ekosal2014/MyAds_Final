package myads.controller.action.category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.model.dao.ProvinceDao;
import myads.model.dto.ProvinceDto;

public class SaveProvince implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ProvinceDto dto=new ProvinceDto();
		ProvinceDao dao=new ProvinceDao();
		ActionForward forward=new ActionForward();
		
		//set data from request data to DTO
		dto.setId(Integer.parseInt(request.getParameter("id")));
		dto.setName(request.getParameter("txt_province_name"));
		dto.setDsc(request.getParameter("txt_dsc"));
		dto.setActive(1);
		
		try{
	
			if(dao.updateProvince(dto)){
				System.out.println("save is ok");
				forward.setRedirect(false);
				forward.setPath("myprovince.adm");
				return forward;
			}else{
				System.out.println("save is error");
			}

		}catch(Exception e){
		}
		return null;
	}

}
