package myads.controller.action.posting;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.model.dao.PostingDao;
import myads.model.dto.Image;

public class SaveImageMore implements Action{
	private static final String UPLOAD_DIR="uploads";
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Image dto=new Image();
		PostingDao dao=new PostingDao();
		ActionForward forward=new ActionForward();
		try{
			
			String applicationPath = request.getServletContext().getRealPath("");
	        // constructs path of the directory to save uploaded file
	        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
	          
	        // creates the save directory if it does not exists
	        File fileSaveDir = new File(uploadFilePath);
	        if (!fileSaveDir.exists()) {
	            fileSaveDir.mkdirs();
	        }
			
			int pro_id=Integer.valueOf(request.getParameter("txt_pro_id"));
			int image_id=Integer.valueOf(request.getParameter("txt_id"));
			
			
			String fileName="";
	        Part part=request.getPart("txt_photo");
			fileName = getFileName(part);
			
	        if (!fileName.equals("")){
				part.write(uploadFilePath + File.separator + fileName);				
			    dto.setImage(fileName);
			    dto.setImage_id(image_id);
				dto.setPost_id(pro_id);
				dto.setOrder(image_id);
			    if(dao.saveImageMore(dto)){
			    	request.getSession().setAttribute("result", "true");
			    	request.getSession().setAttribute("imagelist", dao.getListImage(pro_id));
			    }else{
			    	request.getSession().setAttribute("result", "false");
			    }
			    
			    
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		forward.setRedirect(false);
		forward.setPath("lay_myadspages.ads");
		return forward;
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
