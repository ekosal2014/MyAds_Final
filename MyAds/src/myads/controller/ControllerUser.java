package myads.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.controller.action.category.AddCompany;
import myads.controller.action.category.CompanyAction;
import myads.controller.action.category.ExistFrontMainCategory;
import myads.controller.action.member.*;
import myads.controller.action.posting.AddImageMore;
import myads.controller.action.posting.AddPosting;
import myads.controller.action.posting.AddPostingAction;
import myads.controller.action.posting.DeleteProducts;
import myads.controller.action.posting.EditPhotoAction;
import myads.controller.action.posting.EditPosting;
import myads.controller.action.posting.GetIdProductUpdate;
import myads.controller.action.posting.MyAdsAction;
import myads.controller.action.posting.RemovePhoto;
import myads.controller.action.posting.SaveImageMore;
import myads.controller.action.posting.SearchAdsAction;
import myads.controller.action.posting.SearchPostingAction;
import myads.controller.action.posting.details.ViewPostingDetails;
import myads.controller.action.profile.ProfileAction;
import myads.controller.action.user.LoginUser;
import myads.controller.action.user.ViewUsers;
import myads.model.util.Pagination;

/**
 * Servlet implementation class ControllerAdmin
 */
@WebServlet("*.ads")

@MultipartConfig(fileSizeThreshold=1024 * 1024 * 10,
maxFileSize=1024 * 1024 * 10,
maxRequestSize=1024 * 1024 * 100
)

public class ControllerUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ControllerUser() {
        super();
    }
 
    private void doProccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   	 	 String RequestURI=request.getRequestURI();//-------/myFriendProject/FriendWrite.friend
		 String contextPath=request.getContextPath();//-----/myFriendProject
		 String command=RequestURI.substring(contextPath.length());//----/FriendWrite.friend
		 
		// System.out.println(RequestURI);
		// System.out.println(contextPath);
		 System.out.println(command);
		 
		 ActionForward forward=new ActionForward();
		 Action action=null;
		 
		 
		 
		 if (command.equals("/home.ads") || command.equals("/")){
			 try{
					forward.setPath("index.jsp");
				}catch(Exception e){
					e.printStackTrace();
				}
			 
			 System.out.println(forward.getPath());
			
		 }else if (command.equals("/lay_log.ads")){

			 action=new LoginAction();
			 try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			 
			 System.out.println(forward.getPath());
			
		 }else if (command.equals("/lay_reg.ads")){
			 action=new RegisterAction();
			 try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			 
			 System.out.println(forward.getPath());
		 }else if (command.equals("/lay_myadspages.ads")){
			 HttpSession session=request.getSession(true);
			 if (session.getAttribute("user")==null){
				 forward.setRedirect(true);
				 forward.setPath("lay_log.ads");
				 System.out.println(" Session Null");
			 }else{
				 action=new MyAdsAction();
				 try{
						forward=action.execute(request, response);
					}catch(Exception e){
						e.printStackTrace();
					}
			 }
			 System.out.println(forward.getPath());
		 }else if (command.equals("/lay_search_myadspages.ads")){
			 if (request.getSession().getAttribute("user")==null){
				 forward.setRedirect(false);
				 forward.setPath("lay_log.ads");
				 System.out.println(" Session Null");
			 }else{
				 action=new SearchAdsAction();			
				 try{
					forward=action.execute(request, response);
					 if (request.getSession().getAttribute("postingList")!=null){
						 List<Object> data=new ArrayList<Object>();
						 data.add(String.valueOf(Pagination.totalpage));
						 data.add(request.getSession().getAttribute("postingList"));
						 response.setContentType("application/json");
						 response.setCharacterEncoding("UTF-8");
						 response.getWriter().write(new Gson().toJson(data));
						 return ;
				    }
				}catch(Exception e){
					e.printStackTrace();
				}
			 }
			// System.out.println(forward.getPath());
		 }else if (command.equals("/delete_product.ads")){
			 if (request.getSession().getAttribute("user")==null){
				 forward.setRedirect(false);
				 forward.setPath("lay_log.ads");
				 System.out.println(" Session Null");
			 }else{
				 action=new DeleteProducts();	
				 try{
					forward=action.execute(request, response);
					if (request.getSession().getAttribute("postingList")!=null){
						 List<Object> data=new ArrayList<Object>();
						 data.add(String.valueOf(Pagination.totalpage));
						 data.add(request.getSession().getAttribute("postingList"));
						 response.setContentType("application/json");
						 response.setCharacterEncoding("UTF-8");
						 response.getWriter().write(new Gson().toJson(data));
						 return ;
				    }
				}catch(Exception e){
					e.printStackTrace();
				}
			 }
			 //System.out.println(forward.getPath());
		 }else if (command.equals("/edit_product.ads")){
			 if (request.getSession().getAttribute("user")==null){
				 forward.setRedirect(false);
				 forward.setPath("lay_log.ads");
				 System.out.println(" Session Null");
			 }else{
				 action=new GetIdProductUpdate();
				 try{
						forward=action.execute(request, response);
				 }catch(Exception e){
					e.printStackTrace();
				 }
			 }
		 }	 
		 else if (command.equals("/lay_newposting.ads")){
			 HttpSession session=request.getSession(true);
			 if (session.getAttribute("user")==null){
				 forward.setRedirect(true);
				 forward.setPath("lay_log.ads");
				// System.out.println(" Session Null");
			 }else{
				 action=new AddPostingAction();
				 try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			 }
			 //System.out.println(forward.getPath());
		 }else if (command.equals("/lay_editposting.ads")){
			 HttpSession session=request.getSession(true);
			 if (session.getAttribute("user")==null){
				 forward.setRedirect(true);
				 forward.setPath("lay_log.ads");
				// System.out.println(" Session Null");
			 }else{
				 action=new EditPosting();
				 try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			 }
			 //System.out.println(forward.getPath());
		 }else if (command.equals("/lay_editpostingphoto.ads")){
			 HttpSession session=request.getSession(true);
			 if (session.getAttribute("user")==null){
				 forward.setRedirect(true);
				 forward.setPath("lay_log.ads");
				 //System.out.println(" Session Null");
			 }else{
				 action=new EditPhotoAction();
				 try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			 }
			 //System.out.println(forward.getPath());
		 }else if (command.equals("/lay_addMorepostingphoto.ads")){
			 HttpSession session=request.getSession(true);
			 if (session.getAttribute("user")==null){
				 forward.setRedirect(false);
				 forward.setPath("lay_log.ads");
				 //System.out.println(" Session Null");
			 }else{
				 action=new SaveImageMore();
				 try{
					forward=action.execute(request, response);
					 String result=(String) request.getSession().getAttribute("result");
					 List<Object> data=new ArrayList<Object>();
					 data.add(result);
					 data.add(request.getSession().getAttribute("imagelist"));
					 response.setContentType("application/json");
					 response.setCharacterEncoding("UTF-8");
					 response.getWriter().write(new Gson().toJson(data));
					 return ;
				}catch(Exception e){
					e.printStackTrace();
				}
			 }
			 //System.out.println(forward.getPath());
		 }else if (command.equals("/lay_editpostingphoto.ads")){
			 HttpSession session=request.getSession(true);
			 if (session.getAttribute("user")==null){
				 forward.setRedirect(true);
				 forward.setPath("lay_log.ads");
				 //System.out.println(" Session Null");
			 }
			 action=new EditPhotoAction();
			 try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			 
			 //System.out.println(forward.getPath());
		 }else if (command.equals("/lay_removepostingphoto.ads")){
			 HttpSession session=request.getSession(true);
			 if (session.getAttribute("user")==null){
				 forward.setRedirect(true);
				 forward.setPath("lay_log.ads");
				// System.out.println(" Session Null");
			 }else{
				 action=new RemovePhoto();
				 try{
						forward=action.execute(request, response);
						 String result=(String) request.getSession().getAttribute("result");
						 response.setContentType("application/json");
						 response.setCharacterEncoding("UTF-8");
						 response.getWriter().write(new Gson().toJson(result));
						 return ;
						
					}catch(Exception e){
						e.printStackTrace();
					}
			 }
			
		 }else if (command.equals("/lay_addMoreImage.ads")){
			 HttpSession session=request.getSession(true);
			 if (session.getAttribute("user")==null){
				 forward.setRedirect(true);
				 forward.setPath("lay_log.ads");
				// System.out.println(" Session Null");
			 }else{
				 action=new AddImageMore();
				 try{
						forward=action.execute(request, response);
						 String result=(String) request.getSession().getAttribute("max");
						 response.setContentType("application/json");
						 response.setCharacterEncoding("UTF-8");
						 response.getWriter().write(new Gson().toJson(result));
						 return ;
						
					}catch(Exception e){
						e.printStackTrace();
					}
			 }
			
		 }else if (command.equals("/add_newposting.ads")){
			 HttpSession session=request.getSession(true);
			 if (session.getAttribute("user")==null){
				 forward.setRedirect(true);
				 forward.setPath("lay_log.ads");
				 //System.out.println(" Session Null");
			 }else{
				 action=new AddPosting();
				 try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			 }
			 //System.out.println(forward.getPath());
		 }else if (command.equals("/lay_myadsprofile.ads")){
			 HttpSession session=request.getSession(true);
			 if (session.getAttribute("user")==null){
				 forward.setRedirect(true);
				 forward.setPath("lay_log.ads");
				 //System.out.println(" Session Null");
			 }else{
				 action=new ProfileAction();
				 try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			 }
			 //System.out.println(forward.getPath());
		 }else if (command.equals("/lay_account_setting.ads")){
			 /*HttpSession session=request.getSession(true);*/
			 if (request.getSession().getAttribute("user")==null){
				 forward.setRedirect(true);
				 forward.setPath("lay_log.ads");
				 //System.out.println(" Session Null");
			 }else{
				 action=new ProfileAction();
				 try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			 }
			 System.out.println(forward.getPath());
		 }else if (command.equals("/lay_editaccount_setting.ads")){
			 if (request.getSession().getAttribute("user")==null){
				 forward.setRedirect(true);
				 forward.setPath("lay_log.ads");
				// System.out.println(" Session Null");
			 }else{
				 action=new EditMember();
				 try{
					forward=action.execute(request, response);
				     String result=(String) request.getSession().getAttribute("result");
					 response.setContentType("application/json");
					 response.setCharacterEncoding("UTF-8");
					 response.getWriter().write(new Gson().toJson(result));
					 return ;

				}catch(Exception e){
					e.printStackTrace();
				}
			 }
		 }else if (command.equals("/edit_image_account_setting.ads")){
			 if (request.getSession().getAttribute("user")==null){
				 forward.setRedirect(true);
				 forward.setPath("lay_log.ads");
				 //System.out.println(" Session Null");
			 }else{
				 action=new EditImageMember();
				 try{
					forward=action.execute(request, response);
				     String result=(String) request.getSession().getAttribute("result");
					 response.setContentType("application/json");
					 response.setCharacterEncoding("UTF-8");
					 response.getWriter().write(new Gson().toJson(result));
					 return ;

				}catch(Exception e){
					e.printStackTrace();
				}
			 }
		 }
		 else if (command.equals("/register.ads")){
			 action=new Register();
			 try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			 
			 System.out.println(forward.getPath());
		 }else if (command.equals("/login.ads")){
			 action=new Login();
			 try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			 
			 System.out.println(forward.getPath());
		 }else if (command.equals("/logout.ads")){
			 try{
				HttpSession session=request.getSession(true);
				session.removeAttribute("user");				 
				forward.setRedirect(true);
				forward.setPath("lay_log.ads");
					
				}catch(Exception e){
					e.printStackTrace();
				}
			 
			 System.out.println(forward.getPath());
		 }else if (command.equals("/lay_com.ads")){
			 action=new CompanyAction();
			 try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			 
			 System.out.println(forward.getPath());
		 }else if (command.equals("/addnew_com.ads")){
			 action=new AddCompany();
			 try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			 
			 System.out.println(forward.getPath());
		 }else if (command.equals("/search_myproducts.ads")){
			 action=new SearchPostingAction();
			 try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			 
			 System.out.println(forward.getPath());
		 }else if (command.equals("/mycategory.ads")){
			 action=new ExistFrontMainCategory();
			 try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			 
			 System.out.println(forward.getPath());
		 }else if (command.equals("/paging_mycategory.ads")){
			 action=new ExistFrontMainCategory();
			 try{
					forward=action.execute(request, response);
					if (request.getSession().getAttribute("productByCategory")!=null){
						 response.setContentType("application/json");
						 response.setCharacterEncoding("UTF-8");
						 response.getWriter().write(new Gson().toJson(request.getSession().getAttribute("productByCategory")));
						 return ;
				    }
					
				}catch(Exception e){
					e.printStackTrace();
				}
			 
			 System.out.println(forward.getPath());
		 }else if (command.equals("/step2/myhome.ads")){
			 action=new LoginUser();
			 try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			 
			 System.out.println(forward.getPath());
		 }else if (command.equals("/step2/myregister.ads")){
			 if (request.getSession().getAttribute("membername")==null){
				 forward.setRedirect(true);
				 forward.setPath("/step2/index.jsp");
			 }else{
				 action=new ViewUsers();
				 try{
						forward=action.execute(request, response);
					}catch(Exception e){
						e.printStackTrace();
					}
				 System.out.println(forward.getPath());
			 }
			
		 }else if (command.equals("/product_details.ads")){
			 action=new ViewPostingDetails();
			 try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			 System.out.println(forward.getPath());
		 }
		 
		 
		 if(forward != null){			 	
				if(forward.isRedirect()){ // true
					response.sendRedirect(forward.getPath());
				}else{                    // false
					RequestDispatcher dispatcher =	request.getRequestDispatcher(forward.getPath());
					dispatcher.forward(request, response);
				}
		}
    }
    
	/**.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProccess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProccess(request, response);
	}
    
}
