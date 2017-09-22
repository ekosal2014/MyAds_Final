package myads.controller.action.member;

import java.math.BigInteger;
import java.security.MessageDigest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.model.dao.MemberDao;
import myads.model.dto.MemberDto;

public class EditMember implements Action {

	@SuppressWarnings("unused")
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		MemberDao dao=new MemberDao();
		MemberDto dto=new MemberDto();
		ActionForward forward=new ActionForward();
		
		try{
			
			int id=(Integer.valueOf(request.getParameter("txt_id")));
			String name=request.getParameter("txt_name");
			String realname=request.getParameter("txt_realname");
			int companyid=(Integer.valueOf(request.getParameter("txt_companyid")));
			String sex=request.getParameter("txt_sex");
			String email=request.getParameter("txt_email");
			String phone=request.getParameter("txt_phone");
			String address=request.getParameter("txt_address");
			String oldpass=request.getParameter("txt_pass");
			String newpass=request.getParameter("txt_nex_pass");		
			
			
            MessageDigest md=MessageDigest.getInstance("MD5");			
			md.update(oldpass.getBytes(), 0, oldpass.length());			
			String encpass=new BigInteger(1,md.digest()).toString(16);
			
			
			MessageDigest newmd=MessageDigest.getInstance("MD5");			
			newmd.update(newpass.getBytes(), 0, newpass.length());
			String encnewpass=new BigInteger(1,newmd.digest()).toString(16);
			
			dto.setId(id);
			dto.setName(name);
			dto.setRealName(realname);
			dto.setComid(companyid);
			dto.setSex(sex);
			dto.setEmail(email);
			dto.setPhone(phone);
			dto.setAddress(address);
			dto.setPassword(encnewpass);
			
			if (newpass!=null || newpass!=""){
				if (dao.getMemberCheckPassword(id, encpass)>0){
					if (dao.editMemberWithPassword(dto)){
						request.getSession().setAttribute("user", dao.getMemberEdit(dto.getId()));
						request.getSession().setAttribute("result", "true");
					}					
				}
			}else if (dao.editMember(dto)){
				request.getSession().setAttribute("user", dao.getMemberEdit(dto.getId()));
				request.getSession().setAttribute("result", "true");
				
			}else{
				request.getSession().setAttribute("result", "false");
			}
					
			forward.setRedirect(false);
			forward.setPath("lay_account_setting.ads");
			return forward;
			
		}catch(Exception e){
			
		}
		return null;
	}

}
