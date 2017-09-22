package myads.controller.action.member;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import myads.controller.action.Action;
import myads.controller.action.ActionForward;
import myads.model.dao.MemberDao;
import myads.model.dto.MemberDto;

public class EditImageMember implements Action{

	private static final String UPLOAD_DIR="profile";
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		MemberDao dao=new MemberDao();
		MemberDto dto=new MemberDto();
		ActionForward forward=new ActionForward();
		

		String applicationPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
          
        System.out.println(uploadFilePath);
        // creates the save directory if it does not exists
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        
		try {
			
			int id=(Integer.valueOf(request.getParameter("txt_id")));
			String fileName="";
			Part part=request.getPart("txt_photo");
			fileName = getFileName(part);
			System.out.println("Image Name : "+fileName);
			if (!fileName.equals("")){
				part.write(uploadFilePath + File.separator + fileName);
				
				dto.setId(id);
				dto.setPhoto(fileName);
				if (dao.saveChangeImage(id, fileName)){
					request.getSession().setAttribute("user", dao.getMemberEdit(dto.getId()));
					request.getSession().setAttribute("result", "true");
				}else{
					request.getSession().setAttribute("result", "false");
				}
			}
			
			
			
			
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
