package myads.controller.action.category;

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
import myads.model.util.EncryptionUtil;
import myads.model.util.Pagination;

public class ExistFrontMainCategory implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		ActionForward forward=new ActionForward();
		String categoryId= request.getParameter("id");
		String subCateryId=  request.getParameter("subid");
		String cp=request.getParameter("cp");
		//System.out.println("Encript : "+subCateryId);
		try{
			
			if (subCateryId == null || subCateryId=="") subCateryId="";
			System.out.println("Sub Category "+EncryptionUtil.decode(subCateryId));
			Pagination.category=categoryId;
			Pagination.subcategory=subCateryId;
			
			CategoryDao category=new CategoryDao();
			List<MainCategoryDto> listCategory=new ArrayList<>();
			listCategory=category.readSubCategoryByCategory(EncryptionUtil.decode(categoryId));
			request.getSession().setAttribute("CategoryList", listCategory);
			
			PostingDao positing=new PostingDao();
			List<PostingDto> productList=new ArrayList<>();
			List<PostingDto> productRelativeList=new ArrayList<>();
			
			Pagination.startpage=0;
			Pagination.currentpage=1;
			Pagination.rowperpage=5;

			if (cp != null && cp != ""){
				Pagination.startpage=(Pagination.rowperpage*Integer.valueOf(cp))-Pagination.rowperpage;
				Pagination.currentpage=Integer.valueOf(cp);
			}
			
			productRelativeList=positing.readProductByCategory(EncryptionUtil.decode(categoryId));			
			
			if (subCateryId==null || subCateryId==""){				
				Pagination.countPage(positing.readCountPage(EncryptionUtil.decode(categoryId)));
				productList=positing.readProductByCategory(EncryptionUtil.decode(categoryId),Pagination.startpage,Pagination.rowperpage);
			}else{
				Pagination.countPage(positing.readCountPage(EncryptionUtil.decode(categoryId),EncryptionUtil.decode(subCateryId)));
				productList=positing.readProductByCategoryAndSubCategory(EncryptionUtil.decode(categoryId), EncryptionUtil.decode(subCateryId),Pagination.startpage,Pagination.rowperpage);
			}
			
			request.getSession().setAttribute("productByCategory", productList);
			request.getSession().setAttribute("listRelative", productRelativeList);
				
			forward.setRedirect(false);
			forward.setPath("ads_product.jsp");
			return forward;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
