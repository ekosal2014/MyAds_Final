package myads.controller.action.category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.model.dao.ProvinceDao;
import myads.model.dto.ProvinceDto;

public class ExistProvince implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ProvinceDao dao=new ProvinceDao();
		ProvinceDto dto=new ProvinceDto();
		ActionForward forward=new ActionForward();

		 //get id request from browser requst
		   int num=Integer.parseInt(request.getParameter("id"));

		try{
			
			dto=dao.exisprovince(num);// view category
			request.setAttribute("result", dto);
	
			forward.setRedirect(false);
			forward.setPath("/step2/myprovince_exist.jsp");
			return forward;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
