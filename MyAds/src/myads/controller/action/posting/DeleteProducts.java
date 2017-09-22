package myads.controller.action.posting;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.model.dao.PostingDao;
import myads.model.dto.MemberDto;
import myads.model.dto.PostingDto;
import myads.model.dto.PostingListDto;
import myads.model.util.Pagination;

public class DeleteProducts implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward=new ActionForward();
		List<PostingDto> postinList=new ArrayList<PostingDto>();
		MemberDto memberDto=new MemberDto();
		PostingDao postingDao=new PostingDao();
		String search=request.getParameter("txtSearch");
		String cp=request.getParameter("cp");
		String id=request.getParameter("id");

		
		try{
		    
			memberDto=(MemberDto) request.getSession().getAttribute("user");
			Pagination.startpage=0;
			Pagination.currentpage=1;
			Pagination.rowperpage=5;
			
			if (cp!=null || cp!= ""){
				Pagination.startpage=(Pagination.rowperpage*Integer.valueOf(cp))-Pagination.rowperpage;
				Pagination.currentpage=Integer.valueOf(cp);
			}
			
			if (search==null) search="";
			
			if (Pagination.startpage==0) Pagination.startpage=1;
			
			if (id!=null && id!= ""){
				if (memberDto!=null){
					if (postingDao.deleteProduct(Integer.valueOf(id),memberDto.getId())== true){					
						postinList= postingDao.getSearchPostingList(memberDto, search, Pagination.startpage, Pagination.rowperpage);
						Pagination.countPage(postingDao.countPostingByUser(memberDto,search));
						request.getSession().setAttribute("postingList", postinList);
					}
					else{
						request.getSession().setAttribute("postingList", false);
					}
				}
			}
			
			forward.setRedirect(false);
			forward.setPath("lay_myadspages.ads");
			return forward;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
