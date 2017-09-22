package myads.controller.action.posting;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.model.dao.PostingDao;
import myads.model.dto.Image;

public class RemovePhoto  implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		PostingDao dao=new PostingDao();
		ActionForward forward=new ActionForward();
		
		try{
			
			System.out.println("Hello");
			int pro_id  =Integer.valueOf(request.getParameter("txt_pro_id"));
			int image_id=Integer.valueOf(request.getParameter("txt_id"));
			
		    System.out.println(pro_id+"  "+image_id);
			
			if (dao.getDeleteImage(pro_id, image_id)){
				request.getSession().setAttribute("result", "true");
			}else{
				request.getSession().setAttribute("result", "false");
			}
			
			
			List<Image> listImage= dao.getListImage(pro_id);
			System.out.println("Size "+listImage);
			dao.getDeleteImage(pro_id);
			dao.getInsertImage(listImage);
			
		}catch(Exception e){
			
		}
		
		forward.setRedirect(false);
		forward.setPath("lay_myadspages.ads");
		return forward;
	}

}
