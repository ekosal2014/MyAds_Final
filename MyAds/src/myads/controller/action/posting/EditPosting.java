package myads.controller.action.posting;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.model.dao.PostingDao;
import myads.model.dto.PostingDto;

public class EditPosting  implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		PostingDto dto=new PostingDto();
		PostingDao dao=new PostingDao();
		ActionForward forward=new ActionForward();
		

		try{
			
			dto.setPostingId(Integer.valueOf(request.getParameter("txt_pro_id")));
			dto.setMemId(Integer.valueOf(request.getParameter("txt_memid")));
			dto.setTitle(request.getParameter("txt_pro_tit"));
			dto.setKey(request.getParameter("txt_keynotice"));
			dto.setSubCateId(Integer.valueOf(request.getParameter("txt_subcatid")));
			dto.setPrice(Integer.valueOf(request.getParameter("txt_price")));
			dto.setPhone(request.getParameter("txt_phone"));
			dto.setAdr(request.getParameter("txt_address"));
			dto.setDsc(request.getParameter("txt_dsc"));
			dto.setDiscount(request.getParameter("txt_discount"));
			
			if (dao.getEditPosting(dto)){
				request.getSession().setAttribute("result", "true");
			}else{
				request.getSession().setAttribute("result", "false");
			}
			
			forward.setRedirect(false);
			forward.setPath("lay_myadspages.ads");
			return forward;
		}catch(Exception e){
			
		}
		
		return null;
	}

}
