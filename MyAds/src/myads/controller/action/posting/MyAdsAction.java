package myads.controller.action.posting;

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

public class MyAdsAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		ActionForward forward=new ActionForward();
		String current=request.getParameter("cp");
		String search=request.getParameter("txtSearch");
		try{
			MemberDto memberDto=new MemberDto();
			memberDto=(MemberDto)request.getSession().getAttribute("user");
		    PostingDao postingDao=new PostingDao();	
		    Pagination.startpage=0;
			if (current=="" || current==null){
				Pagination.currentpage=1;
			}else{
				Pagination.currentpage=Integer.valueOf(current);
			}
			if (search==null) search="";
			
			List<PostingDto> postinglist=postingDao.getSearchPostingList(memberDto, search, Pagination.startpage, Pagination.rowperpage);			
			Pagination.countPage(postingDao.countPostingByUser(memberDto,search));	
			request.getSession().setAttribute("postingList", postinglist);
			
			forward.setRedirect(false);
			forward.setPath("ads_myadspages.jsp");
			return forward;
		}catch(Exception e){
		}
		return null;
	}

}
