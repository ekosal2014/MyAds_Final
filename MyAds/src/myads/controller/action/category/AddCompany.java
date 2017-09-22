package myads.controller.action.category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.model.dao.CompanyDao;
import myads.model.dto.CompanyDto;
import myads.model.sqlConnection.SqlConnection;

public class AddCompany implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		CompanyDao dao=new CompanyDao();
		CompanyDto dto=new CompanyDto();
		ActionForward forward=new ActionForward();

		int id=0;
		
		try{
			PreparedStatement ps=SqlConnection.getConnection().prepareStatement("SELECT Max(ComId) FROM tbl_company");
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				id =rs.getInt(1)+1;
				System.out.println("CompanyId:"+id);
			}else{
				id=1;
			}
			
		}catch(Exception ex){
			System.out.println("insert Error! : "+ex);
		
		}
		
		try {
			
			dto.setComid(id);
			dto.setComname(request.getParameter("txt_com_name"));
			dto.setType(request.getParameter("txt_type"));
			dto.setFax(request.getParameter("txt_fax"));
			dto.setEmail(request.getParameter("txt_email"));
			dto.setAdr(request.getParameter("txt_address"));
			dto.setActive(1);
			
			if (dao.insertCompany(dto)){
				request.setAttribute("message", "successfull");
				System.out.println("Insert successfully");
			}else{ 
				request.setAttribute("message", "fail");
				System.out.println("Insert fail");
			}

			forward.setRedirect(false);
			forward.setPath("lay_reg.ads");
			return forward;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
