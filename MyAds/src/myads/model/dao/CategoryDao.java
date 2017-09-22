package myads.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import myads.model.dto.Image;
import myads.model.dto.MainCategoryDto;
import myads.model.dto.PostingDto;
import myads.model.dto.SubCategoryDto;
import myads.model.sqlConnection.SqlConnection;
import myads.model.util.EncryptionUtil;

public class CategoryDao {
	DataSource ds;
	Connection con;
	private static PreparedStatement ps;
	static ResultSet rs;
	
	
	public CategoryDao(){
		try{
			Context init = new InitialContext();
	  	    ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
	  		System.out.println("Connection New!!!!!!!!!!1");
		}catch(Exception ex){
			System.out.println("DB Connection Failture! : " + ex);
			return;
		}	
	}
	// insert main category
	public boolean insertMainCategory(MainCategoryDto dto){
		
		String sql = "insert into tbl_category values(?,?,?,?,?)";
		
		try{
			ps =ds.getConnection().prepareStatement(sql);  
			ps.setInt(1, dto.getId());
			ps.setString(2, dto.getIco_cls_name());
			ps.setString(3, dto.getName());
			ps.setString(4, dto.getDsc());
			ps.setInt(5, dto.getActive());
			
			ps.executeUpdate();
			ps.close();
			
			return true;
		}catch(SQLException e){
			System.err.println("SQL Error : insert"+ e);
			return false;
		}
	}
	
	// exist main category 
	public MainCategoryDto existmainCategory(int id) {
		
		String sql="select * from tbl_category where CateId="+id;
		MainCategoryDto dto=new MainCategoryDto();
		System.out.println("Id get from browser= "+id);
		
		try {
			
			ps=ds.getConnection().prepareStatement(sql);
			rs=ps.executeQuery();
			
			if(rs!=null && rs.next()){
				dto.setId(rs.getInt(1));
				dto.setIco_cls_name(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setDsc(rs.getString(4));
				dto.setActive(rs.getInt(5));
			}
			
			return dto;
			
		} catch (Exception e) {
			System.out.println("error:" + e);
		}
		
		return null;
	}
	
	// update main category 
	public boolean updateMaincategory(MainCategoryDto dto){
		
		String sql="update tbl_category set Ico_class_name=?,Name=?,Description=?,Active=? where CateId="+dto.getId();
		
		try {
			ps=ds.getConnection().prepareStatement(sql);
			ps.setString(1, dto.getIco_cls_name());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getDsc());
			ps.setInt(4, dto.getActive());
			
			ps.executeUpdate();
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	// delete main category
	public boolean deleteMaincate(int id){
		
		String sql = "delete from tbl_category where CateId="+id;
		
		try {
			
			ps=ds.getConnection().prepareStatement(sql);
			ps.executeQuery();
			
			return true;
			
		} catch (Exception e) {
			return false;
		}
		
	}
	
	// insert sub category
	public boolean insertSubCategory(SubCategoryDto dto){
			
		String sql = "insert into tbl_sub_category values(?,?,?,?,?,?)";

		try{
			ps =ds.getConnection().prepareStatement(sql);  
			ps.setInt(1, dto.getId());
			ps.setInt(2, dto.getSubid());
			ps.setString(3, dto.getClass_name());
			ps.setString(4, dto.getName());
			ps.setString(5, dto.getDsc());
			ps.setInt(6, dto.getActive());
			
			ps.executeUpdate();
			ps.close();
			
			return true;
		}catch(SQLException e){
			System.err.println("SQL Error : insert"+ e);
			return false;
		}
	}
	
	// exist sub category 
	public SubCategoryDto existsubCategory(int id) {
		
		String sql="select s.SubCateId, s.CateId, c.Name,  s.Cls_Icon, s.Name, s.Description, s.Active from tbl_category c inner join tbl_sub_category s on c.CateId = s.CateId where s.SubCateId="+id;
		SubCategoryDto dto=new SubCategoryDto();
		System.out.println("Sub CateId get from browser= "+id);
		
		try {
			
			ps=ds.getConnection().prepareStatement(sql);
			rs=ps.executeQuery();
			
			if(rs!=null && rs.next()){
				dto.setId(rs.getInt(1));
				dto.setSubid(rs.getInt(2));
				dto.setCatename(rs.getString(3));
				dto.setClass_name(rs.getString(4));
				dto.setName(rs.getString(5));
				dto.setDsc(rs.getString(6));
				dto.setActive(rs.getInt(7));
			}
			
			return dto;
			
		} catch (Exception e) {
			System.out.println("error:" + e);
		}
		
		return null;
	}
	
	// update sub category 
		public boolean updateSubcategory(SubCategoryDto dto){
			
			String sql="update tbl_sub_category set CateId=?,Cls_Icon=?,Name=?,Description=?,Active=? where SubCateId="+dto.getId();
			
			try {
				ps=ds.getConnection().prepareStatement(sql);
				
				ps.setInt(1, dto.getSubid());
				ps.setString(2, dto.getClass_name());
				ps.setString(3, dto.getName());
				ps.setString(4, dto.getDsc());
				ps.setInt(5, dto.getActive());
				
				ps.executeUpdate();
				
				return true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return false;
			
		}
		
		
		// all category 
		
		public List<MainCategoryDto> getCategory(){
			ResultSet rs=null;
			String sql="select * from tbl_category where Active=1 order by CateId";
			List<MainCategoryDto> mainCategory=new ArrayList<>();
			
			try {
				
				ps=ds.getConnection().prepareStatement(sql);
				rs=ps.executeQuery();
			    while(rs.next()){
			    	MainCategoryDto maincategory=new MainCategoryDto();
			    	maincategory.setId(rs.getInt("CateId"));
			    	maincategory.setId_security(EncryptionUtil.encode(rs.getString("CateId")));
			    	maincategory.setName(rs.getString("Name"));
			    	maincategory.setIco_cls_name(rs.getString("Ico_class_name"));
			    	mainCategory.add(maincategory);
			    }
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mainCategory=null;
			}
			return mainCategory;
		}
		
		
		public List<SubCategoryDto> getSubCategory(){
			ResultSet rs=null;
			String sql="select * from tbl_sub_category where Active=1 order by SubCateId";
			List<SubCategoryDto> subCategory=new ArrayList<>();
			
			try {
				
				ps=ds.getConnection().prepareStatement(sql);
				rs=ps.executeQuery();
			    while(rs.next()){
			    	SubCategoryDto category=new SubCategoryDto();
			    	category.setId(rs.getInt("CateId"));
			    	category.setSubid(rs.getInt("SubCateId"));
			    	category.setSubid_security(EncryptionUtil.encode(rs.getString("SubCateId")));
			    	category.setClass_name(rs.getString("Cls_Icon"));
			    	category.setName(rs.getString("Name"));
			    	subCategory.add(category);
			    }
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				subCategory=null;
			}
			return subCategory;
		}
		
		public List<PostingDto> getContentBody(){
			
			List<PostingDto> ContentBody=new ArrayList<>();
			try{
			    String sql="SELECT sc.SubCateId,c.CateId,p.PostingId,p.ProductName,p.KeyNotice,i.Image FROM tbl_category c "
					+ "INNER JOIN tbl_sub_category sc on c.CateId=sc.CateId INNER JOIN "
					+ "tbl_posting p on p.SubCateId=sc.SubCateId INNER JOIN tbl_image i "
					+ "on p.PostingId=i.PostingId "
					+ "where c.CateId=? And i.order=1 And p.Active='1'";
			   
				for(int i=0;i<getCategory().size();i++){	
					 	PreparedStatement psBody=ds.getConnection().prepareStatement(sql);
						ResultSet rsBody=null;
						psBody.setInt(1, getCategory().get(i).getId());
						rsBody=psBody.executeQuery();
						int index=1;
						while(rsBody.next()){
							if (index<=6){
								PostingDto postingDto=new PostingDto();
								
								Image image=new Image();
								image.setImage(rsBody.getString("Image"));
								 
								MainCategoryDto mainCategory=new MainCategoryDto();
								mainCategory.setId(rsBody.getInt("CateId"));
								mainCategory.setId_security(EncryptionUtil.encode(rsBody.getString("CateId")));
								
								SubCategoryDto subcategoryDto=new SubCategoryDto();
								subcategoryDto.setSubid(rsBody.getInt("SubCateId"));
								subcategoryDto.setSubid_security(EncryptionUtil.encode(rsBody.getString("SubCateId")));
									
								postingDto.setPostingId(rsBody.getInt("PostingId"));
								postingDto.setPostingId_security(EncryptionUtil.encode(rsBody.getString("PostingId")));
								postingDto.setTitle(rsBody.getString("ProductName"));
								postingDto.setKey(rsBody.getString("KeyNotice"));
							   						  
								postingDto.setImage(image);
								postingDto.setMainCategory(mainCategory);
								postingDto.setSubCategory(subcategoryDto);;
								ContentBody.add(postingDto);
								index+=1;
							}
						}				
				}
			}catch(Exception e){
				
			}finally{
				if (SqlConnection.getConnection()!=null) try{ SqlConnection.getConnection().close();}catch(SQLException e){}
			}
			//System.out.println("Body : "+ContentBody.size());
			return ContentBody;
		}
		
		public List<MainCategoryDto> readSubCategoryByCategory(String CategoryId){
			List<MainCategoryDto> listSubCategory=new ArrayList<>();
			try{
				String sql="SELECT c.CateId,c.`Name`,c.Ico_class_name,sc.Cls_Icon,sc.`Name` as subName,"
						+ "sc.SubCateId from tbl_category c INNER JOIN tbl_sub_category "
						+ "sc on c.CateId=sc.CateId WHERE c.CateId=?";
				ps=ds.getConnection().prepareStatement(sql);
				ps.setInt(1,Integer.valueOf(CategoryId));
				rs=ps.executeQuery();
				while(rs.next()){
					MainCategoryDto mainCategory=new MainCategoryDto();
					SubCategoryDto subcategory=new SubCategoryDto();
					mainCategory.setId(rs.getInt("CateId"));
					mainCategory.setId_security(EncryptionUtil.encode(rs.getString("CateId")));
					mainCategory.setName(rs.getString("Name"));
					mainCategory.setIco_cls_name(rs.getString("Ico_class_name"));
					subcategory.setId(rs.getInt("SubCateId"));
					subcategory.setSubid_security(EncryptionUtil.encode(rs.getString("SubCateId")));
					subcategory.setName(rs.getString("subName"));
					subcategory.setClass_name(rs.getString("Cls_Icon"));
					mainCategory.setSubcategory(subcategory);
					listSubCategory.add(mainCategory);
				}
				return listSubCategory;
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return null;
		}
		
		public List<PostingDto> getLastPosting(){
			List<PostingDto> ContentBody=new ArrayList<>();
			try{
			    String sql="SELECT sc.SubCateId,c.CateId,p.PostingId,p.ProductName,p.KeyNotice,i.Image FROM tbl_category c "
					+ "INNER JOIN tbl_sub_category sc on c.CateId=sc.CateId INNER JOIN "
					+ "tbl_posting p on p.SubCateId=sc.SubCateId INNER JOIN tbl_image i "
					+ "on p.PostingId=i.PostingId "
					+ "where  i.order=1 And p.Active='1' ORDER BY p.PostDate DESC limit 0,10" ;
					 	PreparedStatement psBody=ds.getConnection().prepareStatement(sql);
						ResultSet rsBody=null;
						rsBody=psBody.executeQuery();
						while(rsBody.next()){
								PostingDto postingDto=new PostingDto();
								
								Image image=new Image();
								image.setImage(rsBody.getString("Image"));
								 
								MainCategoryDto mainCategory=new MainCategoryDto();
								mainCategory.setId(rsBody.getInt("CateId"));
								mainCategory.setId_security(EncryptionUtil.encode(rsBody.getString("CateId")));
								
								SubCategoryDto subcategoryDto=new SubCategoryDto();
								subcategoryDto.setSubid(rsBody.getInt("SubCateId"));
								subcategoryDto.setSubid_security(EncryptionUtil.encode(rsBody.getString("SubCateId")));
									
								postingDto.setPostingId(rsBody.getInt("PostingId"));
								postingDto.setPostingId_security(EncryptionUtil.encode(rsBody.getString("PostingId")));
								postingDto.setTitle(rsBody.getString("ProductName"));
								postingDto.setKey(rsBody.getString("KeyNotice"));
							   						  
								postingDto.setImage(image);
								postingDto.setMainCategory(mainCategory);
								postingDto.setSubCategory(subcategoryDto);;
								ContentBody.add(postingDto);
								
					
										
				}
			}catch(Exception e){
				
			}finally{
				if (SqlConnection.getConnection()!=null) try{ SqlConnection.getConnection().close();}catch(SQLException e){}
			}
			//System.out.println("Body : "+ContentBody.size());
			return ContentBody;
		}
		
		
		public List<PostingDto> getPopularPosting(){
			List<PostingDto> ContentBody=new ArrayList<>();
			try{
			    String sql="SELECT sc.SubCateId,c.CateId,p.PostingId,p.ProductName,p.KeyNotice,i.Image FROM tbl_category c "
					+ "INNER JOIN tbl_sub_category sc on c.CateId=sc.CateId INNER JOIN "
					+ "tbl_posting p on p.SubCateId=sc.SubCateId INNER JOIN tbl_image i "
					+ "on p.PostingId=i.PostingId "
					+ "where  i.order=1 And p.Active='1' ORDER BY p.view DESC limit 0,10" ;
					 	PreparedStatement psBody=ds.getConnection().prepareStatement(sql);
						ResultSet rsBody=null;
						rsBody=psBody.executeQuery();
						while(rsBody.next()){
								PostingDto postingDto=new PostingDto();
								
								Image image=new Image();
								image.setImage(rsBody.getString("Image"));
								 
								MainCategoryDto mainCategory=new MainCategoryDto();
								mainCategory.setId(rsBody.getInt("CateId"));
								mainCategory.setId_security(EncryptionUtil.encode(rsBody.getString("CateId")));
								
								SubCategoryDto subcategoryDto=new SubCategoryDto();
								subcategoryDto.setSubid(rsBody.getInt("SubCateId"));
								subcategoryDto.setSubid_security(EncryptionUtil.encode(rsBody.getString("SubCateId")));
									
								postingDto.setPostingId(rsBody.getInt("PostingId"));
								postingDto.setPostingId_security(EncryptionUtil.encode(rsBody.getString("PostingId")));
								postingDto.setTitle(rsBody.getString("ProductName"));
								postingDto.setKey(rsBody.getString("KeyNotice"));
							   						  
								postingDto.setImage(image);
								postingDto.setMainCategory(mainCategory);
								postingDto.setSubCategory(subcategoryDto);;
								ContentBody.add(postingDto);
								
					
										
				}
			}catch(Exception e){
				
			}finally{
				if (SqlConnection.getConnection()!=null) try{ SqlConnection.getConnection().close();}catch(SQLException e){}
			}
			//System.out.println("Body : "+ContentBody.size());
			return ContentBody;
		}
		
	
}
