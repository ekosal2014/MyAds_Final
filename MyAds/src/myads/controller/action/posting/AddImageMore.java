package myads.controller.action.posting;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.model.dao.PostingDao;
import myads.model.dto.Image;

public class AddImageMore implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Image dto=new Image();
		PostingDao dao=new PostingDao();
		ActionForward forward=new ActionForward();
		try{
			
			int pro_id=Integer.valueOf(request.getParameter("pro_id"));
			request.getSession().setAttribute("max", (dao.getImageId(pro_id)+1)+"");
			
		}catch(Exception e){
			
		}
		forward.setRedirect(false);
		forward.setPath("lay_myadspages.ads");
		return forward;
	}

}
