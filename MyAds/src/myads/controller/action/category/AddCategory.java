package myads.controller.action.category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.model.dao.CategoryDao;
import myads.model.dto.MainCategoryDto;
import myads.model.sqlConnection.SqlConnection;

public class AddCategory implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		CategoryDao dao=new CategoryDao();
		MainCategoryDto dto=new MainCategoryDto();
		ActionForward forward=new ActionForward();

		int id=0;
		
		try{
			PreparedStatement ps=SqlConnection.getConnection().prepareStatement("SELECT Max(CateId) FROM tbl_category");
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				id =rs.getInt(1)+1;
				System.out.println("Categoryid:"+id);
			}else{
				id=1;
			}
			
		}catch(Exception ex){
			System.out.println("insert Error! : "+ex);
		
		}
		
		try {
			
			dto.setId(id);
			dto.setIco_cls_name(request.getParameter("txt_ico_class"));
			dto.setName(request.getParameter("txt_cate_name"));
			dto.setDsc(request.getParameter("txt_dsc"));
			dto.setActive(1);
			
			if (dao.insertMainCategory(dto)){
				request.setAttribute("message", "successfull");
				System.out.println("Insert successfully");
			}else{ 
				request.setAttribute("message", "fail");
				System.out.println("Insert fail");
			}

			forward.setRedirect(false);
			forward.setPath("/step2/mymain_category.jsp");
			return forward;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
