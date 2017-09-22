package myads.controller.action.posting;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.model.dao.CategoryDao;
import myads.model.dao.PostingDao;
import myads.model.dto.MainCategoryDto;
import myads.model.dto.PostingDto;
import myads.model.dto.SubCategoryDto;
import myads.model.util.EncryptionUtil;
import myads.model.util.Pagination;

public class SearchPostingAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward=new ActionForward();
		CategoryDao category=new CategoryDao();
		List<MainCategoryDto> listCategory=new ArrayList<>();
		String categoryId= request.getParameter("id");
		String subCateryId=  request.getParameter("subid");
		String cp=request.getParameter("cp");
		String location=request.getParameter("location");
        String product=request.getParameter("product");
		try{
			
			if (subCateryId == null || subCateryId=="") subCateryId="";
			
			if (categoryId==null || categoryId=="") {				
				categoryId=EncryptionUtil.encode("1");
			}
			
			if (cp==null || cp==""){
				cp="1";
			}					
			
			listCategory=category.readSubCategoryByCategory(EncryptionUtil.decode(categoryId));
			request.getSession().setAttribute("CategoryList", listCategory);
			
			PostingDao positing=new PostingDao();
			List<PostingDto> productList=new ArrayList<>();
			
			//productRelativeList=positing.readProductByCategory(EncryptionUtil.decode(categoryId));
			
			Pagination.startpage=0;
			Pagination.currentpage=1;
			Pagination.rowperpage=5;
			
			if (cp != null && cp != ""){
				Pagination.startpage=(Pagination.rowperpage*Integer.valueOf(cp))-Pagination.rowperpage;
				Pagination.currentpage=Integer.valueOf(cp);
			}
			
			if (subCateryId==null || subCateryId==""){				
				Pagination.countPage(positing.readSearchCountPage(product, location));
				productList=positing.readSearchProductByCategory(product,location,Pagination.startpage,Pagination.rowperpage);
				
			}else{
				Pagination.countPage(positing.readSearchCountPage(product, location,Integer.valueOf(EncryptionUtil.decode(subCateryId)) ));
				productList=positing.readSearchProductByCategory(product,location,Integer.valueOf(EncryptionUtil.decode(subCateryId)),Pagination.startpage,Pagination.rowperpage);
			}
			
			System.out.println("List Product : "+productList);
			
			request.getSession().setAttribute("listSearchProduct", productList);
			request.getSession().setAttribute("txt_name", product);
			request.getSession().setAttribute("categoryId",subCateryId);
			request.getSession().setAttribute("location", location);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		forward.setRedirect(false);
		forward.setPath("ads_searchproduct.jsp");
		return forward;
	}

}
