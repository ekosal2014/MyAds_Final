package myads.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.controller.action.category.AddCategory;
import myads.controller.action.category.AddNewMainCategory;
import myads.controller.action.category.AddNewProvince;
import myads.controller.action.category.AddNewSubCategory;
import myads.controller.action.category.AddProvince;
import myads.controller.action.category.AddSubCategory;
import myads.controller.action.category.DeleteMainCategoryAction;
import myads.controller.action.category.ExistMainCategory;
import myads.controller.action.category.ExistProvince;
import myads.controller.action.category.ExistSubCategory;
import myads.controller.action.category.SaveMainCategory;
import myads.controller.action.category.SaveProvince;
import myads.controller.action.category.SaveSubCategory;
import myads.controller.action.category.ViewMainCategory;
import myads.controller.action.category.ViewProvince;
import myads.controller.action.category.ViewSubCategory;
import myads.controller.action.posting.AllPostingAction;
import myads.controller.action.user.AddNewPosition;
import myads.controller.action.user.AddNewRole;
import myads.controller.action.user.AddNewUsers;
import myads.controller.action.user.AddPosition;
import myads.controller.action.user.AddRole;
import myads.controller.action.user.AddUser;
import myads.controller.action.user.ExistPosition;
import myads.controller.action.user.ExistRole;
import myads.controller.action.user.ExistUser;
import myads.controller.action.user.SaveRole;
import myads.controller.action.user.ViewPosition;
import myads.controller.action.user.ViewUsers;
import myads.controller.admin.posting.AprovePositng;
import myads.controller.admin.posting.CountNewPosting;
import myads.model.util.Pagination;
import myads.controller.action.user.ViewRole;

/**
 * Servlet implementation class ControllerAdmin
 */
@WebServlet("*.adm")
public class ControllerAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ControllerAdmin() {
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
		 
		if (request.getSession().getAttribute("userinfo")==null){
			 request.getSession().removeAttribute("userinfo");				 
			 forward.setRedirect(true);
			 forward.setPath("../step2/index.jsp");
		 }else if (command.equals("/step2/logout_admin.adm")){
			 request.getSession().removeAttribute("user");		
			 response.setContentType("application/json");
			 response.setCharacterEncoding("UTF-8");
			 response.getWriter().write(new Gson().toJson("true"));
			 return ;
		 }else if (command.equals("/step2/myadm_acc.adm")){
			 action=new ViewUsers();
			 try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			 
			 System.out.println(forward.getPath());
		 }else if (command.equals("/step2/layad_adm_acc.adm")){
			 action=new AddNewUsers();
			 try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			 
			 System.out.println(forward.getPath());
		 }else if (command.equals("/step2/register.adm")){
			 action=new AddUser();
			 try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			 
			 System.out.println(forward.getPath());
		 }else if (command.equals("/step2/layexist_acc.adm")){
			 action=new ExistUser();
			 try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			 
			 System.out.println(forward.getPath());
		 }else if (command.equals("/step2/myrole.adm")){
			 action=new ViewRole();
			 try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			 
			 System.out.println(forward.getPath());
		 }else if (command.equals("/step2/layad_role.adm")){
			 action=new AddNewRole();
			 try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			 
			 System.out.println(forward.getPath());
		 }else if (command.equals("/step2/addrole.adm")){
			 action=new AddRole();
			 try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			 
			 System.out.println(forward.getPath());
		 }else if (command.equals("/step2/layexist_role.adm")){
			 action=new ExistRole();
			 try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			 
			 System.out.println(forward.getPath());
		 }else if (command.equals("/step2/save_role.adm")){
			 action=new SaveRole();
			 try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			 
			 System.out.println(forward.getPath());
		 }else if (command.equals("/step2/mypostion.adm")){
			 action=new ViewPosition();
			 try{
					forward=action.execute(request, response);
				}catch(Exception e){
					e.printStackTrace();
				}
			 
			 System.out.println(forward.getPath());
		 }else if (command.equals("/step2/layad_postion.adm")){
			 
				 action=new AddNewPosition();
				 try{
						forward=action.execute(request, response);
					}catch(Exception e){
						e.printStackTrace();
					}
				 
				 System.out.println(forward.getPath());
			 
			 
		 }else if (command.equals("/step2/addposition.adm")){
			 
				 action=new AddPosition();
				 try{
						forward=action.execute(request, response);
					}catch(Exception e){
						e.printStackTrace();
					}
				 
			 System.out.println(forward.getPath());
			 
			 
		 }else if (command.equals("/step2/layexist_position.adm")){

				 action=new ExistPosition();
				 try{
						forward=action.execute(request, response);
					}catch(Exception e){
						e.printStackTrace();
					}
				 
				 System.out.println(forward.getPath());
			
			
		 }else if (command.equals("/step2/maincate.adm")){
			
				 action=new ViewMainCategory();
				 try{
						forward=action.execute(request, response);
					}catch(Exception e){
						e.printStackTrace();
					}
				 
				 //System.out.println(forward.getPath());
			 
			
		 }else if (command.equals("/step2/layad_maincate.adm")){
			 
				 action=new AddNewMainCategory();
				 try{
						forward=action.execute(request, response);
					}catch(Exception e){
						e.printStackTrace();
					}
				 
				 //System.out.println(forward.getPath());
			
			 
			 
		 }else if (command.equals("/step2/layexit_maincate.adm")){

				 action=new ExistMainCategory();
				 try{
						forward=action.execute(request, response);
					}catch(Exception e){
						e.printStackTrace();
					}
				 
				 //System.out.println(forward.getPath());
			
			 
		 }else if (command.equals("/step2/save_maincate.adm")){
			
				 action=new SaveMainCategory();
				 try{
						forward=action.execute(request, response);
					}catch(Exception e){
						e.printStackTrace();
					}
				 
				 //System.out.println(forward.getPath());
			
			 
		 }else if (command.equals("/step2/maincate_delete.adm")){
			
				 action=new DeleteMainCategoryAction();
				 try{
						forward=action.execute(request, response);
					}catch(Exception e){
						e.printStackTrace();
					}
				 
				// System.out.println(forward.getPath());
			 
			 
		 }else if (command.equals("/step2/subcate.adm")){
			 
				 action=new ViewSubCategory();
				 try{
						forward=action.execute(request, response);
					}catch(Exception e){
						e.printStackTrace();
					}
				 
				 //System.out.println(forward.getPath());
			 
			
		 }else if (command.equals("/step2/layad_subcate.adm")){
			 
				 action=new AddNewSubCategory();
				 try{
						forward=action.execute(request, response);
					}catch(Exception e){
						e.printStackTrace();
					}
				 
				// System.out.println(forward.getPath());
			 
			
		 }else if (command.equals("/step2/addcate.adm")){
			  
					 action=new AddCategory();
					 try{
							forward=action.execute(request, response);
						}catch(Exception e){
							e.printStackTrace();
						}
					 
					// System.out.println(forward.getPath());
				 
			 
		 }else if (command.equals("/step2/addsubcate.adm")){
			  
				 action=new AddSubCategory();
				 try{
						forward=action.execute(request, response);
					}catch(Exception e){
						e.printStackTrace();
					}
				 
				 //System.out.println(forward.getPath());
			 
			
		 }else if (command.equals("/step2/layexit_subcate.adm")){
			 
				 action=new ExistSubCategory();
				 try{
						forward=action.execute(request, response);
					}catch(Exception e){
						e.printStackTrace();
					}
				 
				// System.out.println(forward.getPath());
			 
			
		 }else if (command.equals("/step2/myprovince.adm")){
			
				 action=new ViewProvince();
				 try{
						forward=action.execute(request, response);
					}catch(Exception e){
						e.printStackTrace();
					}
				 
				 //System.out.println(forward.getPath());
			 
			 
		 }else if (command.equals("/step2/layad_province.adm")){
			
				 action=new AddNewProvince();
				 try{
						forward=action.execute(request, response);
					}catch(Exception e){
						e.printStackTrace();
					}
				 
				 System.out.println(forward.getPath());
			 
			
		 }else if (command.equals("/step2/save_subcate.adm")){
			 
				 action=new SaveSubCategory();
				 try{
						forward=action.execute(request, response);
					}catch(Exception e){
						e.printStackTrace();
					}
				 
				 //System.out.println(forward.getPath());
			 
			
		 }else if (command.equals("/step2/addprovince.adm")){
			
				 action=new AddProvince();
				 try{
						forward=action.execute(request, response);
					}catch(Exception e){
						e.printStackTrace();
					}
				 
				// System.out.println(forward.getPath());
			 
			
		 }else if (command.equals("/step2/layexist_province.adm")){
			 
				 action=new ExistProvince();
				 try{
						forward=action.execute(request, response);
					}catch(Exception e){
						e.printStackTrace();
					}
				 
				 //System.out.println(forward.getPath());
			 
			 
		 }else if (command.equals("/step2/save_province.adm")){
			
				 	action=new SaveProvince();
				 	try{
						forward=action.execute(request, response);
					}catch(Exception e){
						e.printStackTrace();
					}
				 
				// System.out.println(forward.getPath());
			 
			 
		 }else if (command.equals("/step2/new_posting.adm")){
			 
				 action=new AllPostingAction();
				 try{
						forward=action.execute(request, response);
					}catch(Exception e){
						e.printStackTrace();
					}
			 
			 //System.out.println(forward.getPath());
			 
		 }else if (command.equals("/step2/count_newPosting.adm")){
			 
			 action=new CountNewPosting();
			 try{
					 forward=action.execute(request, response);
					 response.setContentType("application/json");
					 response.setCharacterEncoding("UTF-8");
					 response.getWriter().write(new Gson().toJson(request.getSession().getAttribute("result")));
					 return;
				}catch(Exception e){
					e.printStackTrace();
				}
		 
		 //System.out.println(forward.getPath());
		 
	  }else if (command.equals("/step2/approve_posting.adm")){
			 		 
				 action=new AprovePositng();
				 try{
						forward=action.execute(request, response);
							 response.getWriter().write(new Gson().toJson(request.getSession().getAttribute("result")));
							 return ;

					}catch(Exception e){
						e.printStackTrace();
					}
			 
			 //System.out.println(forward.getPath());
			 
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
