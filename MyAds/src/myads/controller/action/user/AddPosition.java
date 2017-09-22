package myads.controller.action.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.model.dao.PositionDao;
import myads.model.dto.PositionDto;
import myads.model.sqlConnection.SqlConnection;

public class AddPosition implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PositionDao dao=new PositionDao();
		PositionDto dto=new PositionDto();
		ActionForward forward=new ActionForward();

		int id =0;
		
		try{
			PreparedStatement ps=SqlConnection.getConnection().prepareStatement("SELECT Max(PositId) FROM tbl_position");
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				id =rs.getInt(1)+1;
				System.out.println("PositionId:"+id);
			}else{
				id=1;
			}
			
		}catch(Exception ex){
			System.out.println("insert Error! : "+ex);
		
		}
		
		try {
			dto.setPostId(id);
			dto.setRoleId(Integer.valueOf(request.getParameter("txt_roleid")));
			dto.setPostAS(request.getParameter("txt_postionAs"));
			dto.setDsc(request.getParameter("txt_dsc"));
			dto.setActive(1);
			
			if (dao.insertPosition(dto)){
				request.setAttribute("message", "successfull");
				System.out.println("Insert successfully");
			}else{ 
				request.setAttribute("message", "fail");
				System.out.println("Insert fail");
			}

			forward.setRedirect(false);
			forward.setPath("layad_postion.adm");
			return forward;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
