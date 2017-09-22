package myads.controller.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.model.dao.RoleDao;
import myads.model.dto.RoleDto;

public class SaveRole implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		RoleDto dto=new RoleDto();
		RoleDao dao=new RoleDao();
		ActionForward forward=new ActionForward();
		
		//set data from request data to DTO
		dto.setRoleId(Integer.parseInt(request.getParameter("id")));
		dto.setRole(request.getParameter("txt_role"));
		dto.setDsc(request.getParameter("txt_dsc"));
		dto.setActive(1);
		
		try{
	
			if(dao.updateRole(dto)){
				System.out.println("save is ok");
				forward.setRedirect(false);
				forward.setPath("/step2/myrole_tbl.jsp");
				return forward;
			}else{
				System.out.println("save is error");
			}

		}catch(Exception e){
		}
		return null;
	}

}
