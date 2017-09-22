package myads.controller.action.user;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.model.dao.UserDao;
import myads.model.dto.UserDto;
import myads.model.sqlConnection.SqlConnection;

public class AddUser implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserDao dao=new UserDao();
		UserDto dto=new UserDto();
		ActionForward forward=new ActionForward();

		int id =0;
		
		try{
			PreparedStatement ps=SqlConnection.getConnection().prepareStatement("SELECT Max(Id) FROM tbl_user");
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				id =rs.getInt(1)+1;
				System.out.println("UserId:"+id);
			}else{
				id=1;
			}
			
		}catch(Exception ex){
			System.out.println("insert Error! : "+ex);
		
		}

		int positid=(Integer.valueOf(request.getParameter("txt_cateid")));
		String name=request.getParameter("txt_name");
		String sex=request.getParameter("txt_sex");
		String photo=request.getParameter("txt_photo");
		String dob=request.getParameter("txt_dob");
		String password=request.getParameter("txt_pass");
		String email=request.getParameter("txt_email");
		String phone=request.getParameter("txt_phone");
		String address=request.getParameter("txt_address");
		
		try {
			
			MessageDigest md=MessageDigest.getInstance("MD5");
			
			md.update(password.getBytes(), 0, password.length());
			String encpass=new BigInteger(1,md.digest()).toString(16);
			//System.out.println(encpass);
			
			dto.setId(id);
			dto.setPostId(positid);
			dto.setName(name);
			dto.setSex(sex);
			dto.setPhoto(photo);
			dto.setDob(dob);
			dto.setPassword(encpass);
			dto.setEmail(email);
			dto.setPhone(phone);
			dto.setAddress(address);
			dto.setActive(1);
			
			if (dao.insertUser(dto)){
				request.setAttribute("message", "successfull");
				System.out.println("Insert successfully");
			}else{ 
				request.setAttribute("message", "fail");
				System.out.println("Insert fail");
			}

			forward.setRedirect(false);
			forward.setPath("layad_adm_acc.adm");
			return forward;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
