package myads.controller.action.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.model.dao.RoleDao;
import myads.model.dto.RoleDto;
import myads.model.sqlConnection.SqlConnection;

public class AddRole implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RoleDao dao=new RoleDao();
		RoleDto dto=new RoleDto();
		ActionForward forward=new ActionForward();

		int id =0;
		
		try{
			PreparedStatement ps=SqlConnection.getConnection().prepareStatement("SELECT Max(RoleId) FROM tbl_role");
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				id =rs.getInt(1)+1;
				System.out.println("RoleId:"+id);
			}else{
				id=1;
			}
			
		}catch(Exception ex){
			System.out.println("insert Error! : "+ex);
		
		}
		
		try {
			dto.setRoleId(id);
			dto.setRole(request.getParameter("txt_role"));
			dto.setDsc(request.getParameter("txt_dsc"));
			dto.setActive(1);
			
			if (dao.insertRole(dto)){
				request.setAttribute("message", "successfull");
				System.out.println("Insert successfully");
			}else{ 
				request.setAttribute("message", "fail");
				System.out.println("Insert fail");
			}

			forward.setRedirect(false);
			forward.setPath("layad_role.adm");
			return forward;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
