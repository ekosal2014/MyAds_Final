package myads.controller.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.model.dao.PositionDao;
import myads.model.dto.PositionDto;

public class ExistPosition implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PositionDao dao=new PositionDao();
		PositionDto dto=new PositionDto();
		ActionForward forward=new ActionForward();

		 //get id request from browser requst
		   int num=Integer.parseInt(request.getParameter("id"));

		try{
			
			dto=dao.existPosition(num);// view category
			request.setAttribute("result", dto);
	
			forward.setRedirect(false);
			forward.setPath("/step2/mypostion_exist.jsp");
			return forward;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
