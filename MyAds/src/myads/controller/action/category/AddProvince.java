package myads.controller.action.category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.model.dao.ProvinceDao;
import myads.model.dto.ProvinceDto;
import myads.model.sqlConnection.SqlConnection;

public class AddProvince implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ProvinceDao dao=new ProvinceDao();
		ProvinceDto dto=new ProvinceDto();
		ActionForward forward=new ActionForward();

		int id=0;
		
		try{
			PreparedStatement ps=SqlConnection.getConnection().prepareStatement("SELECT Max(ProvId) FROM tbl_province");
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				id =rs.getInt(1)+1;
				System.out.println("ProvinceId:"+id);
			}else{
				id=1;
			}
			
		}catch(Exception ex){
			System.out.println("insert Error! : "+ex);
		
		}
		
		try {
			
			dto.setId(id);
			dto.setName(request.getParameter("txt_province_name"));
			dto.setDsc(request.getParameter("txt_dsc"));
			dto.setActive(1);
			
			if (dao.insertProvince(dto)){
				request.setAttribute("message", "successfull");
				System.out.println("Insert successfully");
			}else{ 
				request.setAttribute("message", "fail");
				System.out.println("Insert fail");
			}

			forward.setRedirect(false);
			forward.setPath("/step2/myprovince.jsp");
			return forward;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
