package myads.controller.action.posting;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.model.dao.PostingDao;
import myads.model.dto.Image;
import myads.model.dto.PostingDto;
import myads.model.sqlConnection.SqlConnection;

public class AddPosting implements Action{
    
	
	private static final String UPLOAD_DIR="uploads";
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		PostingDto dto=new PostingDto();
		PostingDao dao=new PostingDao();
		ActionForward forward=new ActionForward();
		
		int id =0;
		
		try{
			PreparedStatement ps=SqlConnection.getConnection().prepareStatement("SELECT Max(PostingId) FROM tbl_posting");
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				id =rs.getInt(1)+1;
				System.out.println("Posting Id:"+id);
			}else{
				id=1;
			}
			
		}catch(Exception ex){
			System.out.println("insert Error! : "+ex);
		
		}
		
		String applicationPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
          
        // creates the save directory if it does not exists
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        List<Image> imagelist=new ArrayList<>();
        System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());
		 try{				
			 
			String fileName="";
			int i=1;
			for(Part part:request.getParts()){					
				fileName = getFileName(part);
				 Image image=new Image();
				if (!fileName.equals("")){
					image.setImage_id(i);
					image.setImage(fileName);
					image.setPost_id(id);
					image.setOrder(i);
					part.write(uploadFilePath + File.separator + fileName);
					i++;
					imagelist.add(image);
				}
				
			}
			
		 }catch(Exception e){
			 
		 }
		
		
		int memid=(Integer.valueOf(request.getParameter("txt_memid")));
		String name=request.getParameter("txt_pro_tit");
		String key=request.getParameter("txt_keynotice");
		int subcateid=(Integer.valueOf(request.getParameter("txt_subcatid")));
		int price=(Integer.valueOf(request.getParameter("txt_price")));
		String phone=request.getParameter("txt_phone");
		String adr=request.getParameter("txt_address");
		String dsc=request.getParameter("txt_dsc");
		String dis=request.getParameter("txt_discount");
		

		System.out.println(" image Name  " + imagelist);
		
		try{
			
			
			dto.setPostingId(id);
			dto.setMemId(memid);
			dto.setTitle(name);
			dto.setKey(key);
			dto.setPrice(price);
			dto.setPhone(phone);
			dto.setSubCateId(subcateid);
			dto.setAdr(adr);
			dto.setDsc(dsc);
			/*dto.setPhoto(photo);*/
			dto.setActive(0);
			dto.setPhoto(imagelist);
			dto.setDiscount(dis);
			dto.setView("0");
			
			if (dao.insertPosting(dto)){
				request.setAttribute("message", "true");
				System.out.println("Insert successfully");
			}else{ 
				request.setAttribute("message", "fase");
				System.out.println("Insert fail");
			}
			
			forward.setRedirect(false);
			forward.setPath("lay_myadspages.ads");
			return forward;
		}catch(Exception e){
		}
		return null;
	}
	
	private String getFileName(Part part){
		String contentDisp = part.getHeader("content-disposition");
        //System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {            	
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }

}
