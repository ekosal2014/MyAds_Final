package myads.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.websocket.Session;

import myads.model.dto.Image;
import myads.model.dto.MainCategoryDto;
import myads.model.dto.MemberDto;
import myads.model.dto.PostingDto;
import myads.model.dto.PostingListDto;
import myads.model.dto.SubCategoryDto;
import myads.model.sqlConnection.SqlConnection;
import myads.model.util.EncryptionUtil;

public class PostingDao {
	DataSource ds;
	Connection con;
	static PreparedStatement ps;
	PreparedStatement psimg;
	static ResultSet rs;
	
	public PostingDao(){
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
	public boolean insertPosting(PostingDto dto) throws SQLException{
		
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		
		String sql = "insert into tbl_posting values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String sqlimage="insert into tbl_image values(?,?,?,?)";
		try{
			ds.getConnection().setAutoCommit(false);
			ps =ds.getConnection().prepareStatement(sql);
			psimg =SqlConnection.getConnection().prepareStatement(sqlimage);
			ps.setInt(1, dto.getPostingId());
			ps.setInt(2, dto.getMemId());
			ps.setString(3, dto.getTitle());
			ps.setString(4, dto.getKey());
			ps.setInt(5, dto.getSubCateId());
			ps.setInt(6, dto.getPrice());
			ps.setString(7, dto.getPhone());
			ps.setString(8, dto.getAdr());
			ps.setString(9, dto.getDsc());
			ps.setInt(10, dto.getActive());
			ps.setString(11,sdf.format(dt));
			ps.setString(12, dto.getDiscount());
			ps.setString(13, dto.getView());
			ps.executeUpdate();
			
			for(int i=0;i<dto.getPhoto().size();i++){
				psimg.setInt(1, dto.getPhoto().get(i).getImage_id());
				psimg.setInt(2, dto.getPhoto().get(i).getPost_id());
				psimg.setString(3, dto.getPhoto().get(i).getImage());
				psimg.setInt(4, dto.getPhoto().get(i).getOrder());
				psimg.executeUpdate();
			}
			
			SqlConnection.getConnection().commit();
			
			return true;
		}catch(SQLException e){
			System.err.println("SQL Error : insert"+ e);
			return false;
		}
	}
	
	///   list posting
	
	public List<PostingListDto> getPostingList(MemberDto memberDto){
		ResultSet rs=null;
		String sql="select p.PostingId,p.MemId,p.ProductName,p.Price,p.Phone,"+
		"p.Address,p.Description,p.Discount ,i.Image,sc.Name from tbl_posting p INNER JOIN tbl_image i "+
		"on p.PostingId=i.PostingId INNER JOIN tbl_sub_category sc "+
		"on p.SubCateId=sc.SubCateId where i.order=1 and p.MemId=? "		
		+ "GROUP BY p.PostingId ORDER BY p.PostDate DESC";
 
		List<PostingListDto> postinglist=new ArrayList<>();
		
		try {
			System.out.println(" Member Id : "+memberDto.getId());
			ps=ds.getConnection().prepareStatement(sql);
			ps.setInt(1, memberDto.getId());
			rs=ps.executeQuery();
		    while(rs.next()){
		    	
		    	PostingListDto posintdto=new PostingListDto();
		    	posintdto.setPostingId(rs.getInt("PostingId"));
		    	posintdto.setMemId(rs.getInt("MemId"));
		    	posintdto.setProductName(rs.getString("ProductName"));
		    	posintdto.setPrice(rs.getInt("Price"));
		    	posintdto.setPhone(rs.getString("Phone"));
		    	posintdto.setAdr(rs.getString("Address"));
		    	posintdto.setDsc(rs.getString("Description"));
		    	posintdto.setDiscount(rs.getString("Discount"));
		    	posintdto.setImg(rs.getString("Image"));
		    	posintdto.setSubCateName(rs.getString("Name"));
		    	postinglist.add(posintdto);
		    }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			postinglist=null;
		}
		return postinglist;
	}
	
	public int countPostingByUser(MemberDto memberDto,String search){
		ResultSet rs=null;
		String sql="select count(*) total from  (select p.PostingId from tbl_posting p INNER JOIN tbl_image i  "
					 +"on p.PostingId=i.PostingId INNER JOIN tbl_sub_category sc "
					 +"on p.SubCateId=sc.SubCateId where i.order=1 and p.MemId=?  "
					 + "and (p.ProductName like ? or p.Price like ? OR  "
					 + "p.phone like ? OR p.Address like ? OR p.Description like ?)	"
					 + " GROUP BY p.PostingId) as product "	;
		int count=0;
		//System.out.println("Search :"+search);
		try {
			ps=ds.getConnection().prepareStatement(sql);
			ps.setInt(1, memberDto.getId());
			ps.setString(2, "%"+search+"%");
			ps.setString(3, "%"+search+"%");
			ps.setString(4, "%"+search+"%");
			ps.setString(5, "%"+search+"%");
			ps.setString(6, "%"+search+"%");
			rs=ps.executeQuery();
		    while(rs.next()){
		    	count=rs.getInt("total");
		    }
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return 0;
	}
	
	public List<PostingDto> getSearchPostingList(MemberDto memberDto,String search,int start,int row){
		ResultSet rs=null;
		String sql="select p.PostingId,p.MemId,p.ProductName,p.Price,p.Phone,"+
		"p.Address,p.Description,p.Discount ,i.Image,sc.Name,sc.CateId,sc.SubCateId from tbl_posting p INNER JOIN tbl_image i "+
		"on p.PostingId=i.PostingId INNER JOIN tbl_sub_category sc "+
		"on p.SubCateId=sc.SubCateId where i.order=1 and p.MemId=? "
		+ "and (p.ProductName like ? or p.Price like ? OR "
		+ "p.phone like ? OR p.Address like ? OR p.Description like ?) "		
		+ "GROUP BY p.PostingId ORDER BY p.PostDate DESC LIMIT ?,?";
 
		List<PostingDto> postinglist=new ArrayList<>();
		
		try {
			System.out.println(" Start : "+start + "end : "+ memberDto.getId());
			ps=ds.getConnection().prepareStatement(sql);
			ps.setInt(1, memberDto.getId());
			ps.setString(2, "%"+search+"%");
			ps.setString(3, "%"+search+"%");
			ps.setString(4, "%"+search+"%");
			ps.setString(5, "%"+search+"%");
			ps.setString(6, "%"+search+"%");
			ps.setInt(7, start );
			ps.setInt(8, row);
			//System.out.println("PS : "+ps);
			rs=ps.executeQuery();
		    while(rs.next()){
		    	
		    	MainCategoryDto catedto=new MainCategoryDto();
		    	SubCategoryDto  subcatedto=new SubCategoryDto();
		    	Image image=new Image();
		    	
		    	PostingDto posintdto=new PostingDto();
		    	posintdto.setPostingId(rs.getInt("PostingId"));
		    	posintdto.setPostingId_security(EncryptionUtil.encode(rs.getString("PostingId")));
		    	posintdto.setMemId(rs.getInt("MemId"));
		    	posintdto.setTitle(rs.getString("ProductName"));
		    	posintdto.setPrice(rs.getInt("Price"));
		    	posintdto.setPhone(rs.getString("Phone"));
		    	posintdto.setAdr(rs.getString("Address"));
		    	posintdto.setDsc(rs.getString("Description"));
		    	posintdto.setDiscount(rs.getString("Discount"));
		    	
		    	catedto.setId_security(EncryptionUtil.encode(rs.getString("CateId")));
		    	
		    	subcatedto.setSubid_security(EncryptionUtil.encode(rs.getString("SubCateId")));
		    	subcatedto.setName(rs.getString("Name"));
		    	
		    	image.setImage(rs.getString("Image"));
		    	
		    	posintdto.setImage(image);
		    	posintdto.setSubCategory(subcatedto);
		    	posintdto.setMainCategory(catedto);
		    	
		    	postinglist.add(posintdto);
		    }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			postinglist=null;
		}
		return postinglist;
	}
	
	
	public int readCountPage(String category_id){
		int count=0;
		try{
			String sql="select count(*) as total from tbl_posting "
					+ "p INNER JOIN tbl_sub_category sc on p.SubCateId=sc.SubCateId "
					+ "INNER JOIN tbl_category c on c.CateId=sc.CateId INNER JOIN tbl_image "
					+ "i on p.PostingId=i.PostingId WHERE c.CateId=? and p.Active=1 and i.order=1";
		
			ps=ds.getConnection().prepareStatement(sql);
			ps.setInt(1,Integer.valueOf(category_id));
			rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt("total");
			}
			return count;
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public int readSearchCountPage(String txt_name,String location){
		int count=0;
		System.out.println("Search "+txt_name +  "    "+location);
		try{
			String sql="select count(*) as total from tbl_posting "
					+ "p INNER JOIN tbl_sub_category sc on p.SubCateId=sc.SubCateId "
					+ "INNER JOIN tbl_category c on c.CateId=sc.CateId INNER JOIN tbl_image "
					+ "i on p.PostingId=i.PostingId WHERE (p.ProductName like ? or p.Address LIKE ? ) and p.Active=1 and i.order=1";
		
			ps=ds.getConnection().prepareStatement(sql);
			ps.setString(1,"%"+txt_name+"%");
			ps.setString(2,"%"+ location+"%");
			rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt("total");
			}
			return count;
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public int readSearchCountPage(String txt_name,String location,int subCategoryId){
		int count=0;
		System.out.println("Search "+txt_name +  "    "+location);
		try{
			String sql="select count(*) as total from tbl_posting "
					+ "p INNER JOIN tbl_sub_category sc on p.SubCateId=sc.SubCateId "
					+ "INNER JOIN tbl_category c on c.CateId=sc.CateId INNER JOIN tbl_image "
					+ "i on p.PostingId=i.PostingId WHERE (p.ProductName like ? and p.Address LIKE ? )"
					+ "and sc.SubCateId=? and p.Active=1 and i.order=1";
		
			ps=ds.getConnection().prepareStatement(sql);
			ps.setString(1,"%"+txt_name+"%");
			ps.setString(2,"%"+ location+"%");
			ps.setInt(3, subCategoryId);
			rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt("total");
			}
			return count;
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public int readCountPage(String category_id,String subcategory){
		int count=0;
		try{
			String sql="select count(*) as total from tbl_posting "
					+ "p INNER JOIN tbl_sub_category sc on p.SubCateId=sc.SubCateId "
					+ "INNER JOIN tbl_category c on c.CateId=sc.CateId INNER JOIN tbl_image "
					+ "i on p.PostingId=i.PostingId WHERE c.CateId=? and sc.SubCateId=? and p.Active=1 and i.order=1";
		
			ps=ds.getConnection().prepareStatement(sql);
			ps.setInt(1,Integer.valueOf(category_id));
			ps.setInt(2,Integer.valueOf(subcategory));
			rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt("total");
			}
			return count;
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	public List<PostingDto> readProductByCategory(String category_id,int start,int end){
		List<PostingDto> productList=new ArrayList<>();
		System.out.println(start +  "    "+end);
		try{			
			String sql="select sc.SubCateId,c.CateId,i.Image,p.PostingId,p.ProductName,p.Price,p.Discount,p.KeyNotice from tbl_posting "
					+ "p INNER JOIN tbl_sub_category sc on p.SubCateId=sc.SubCateId "
					+ "INNER JOIN tbl_category c on c.CateId=sc.CateId INNER JOIN tbl_image "
					+ "i on p.PostingId=i.PostingId WHERE c.CateId=? and p.Active=1 and i.order=1 ORDER BY p.PostDate DESC  LIMIT ?,?";
			
			ps=ds.getConnection().prepareStatement(sql);
			ps.setInt(1,Integer.valueOf(category_id));
			ps.setInt(2, start);
			ps.setInt(3, end);
			rs=ps.executeQuery();
			while(rs.next()){
				PostingDto posting=new PostingDto();
				Image image=new Image();
				MainCategoryDto mainCategory=new MainCategoryDto();
				SubCategoryDto subcategory=new SubCategoryDto();
				
				image.setImage(rs.getString("Image"));	
				
				posting.setTitle(rs.getString("ProductName"));
				posting.setKey(rs.getString("KeyNotice"));
				posting.setPostingId(rs.getInt("PostingId"));
				posting.setPostingId_security(EncryptionUtil.encode(rs.getString("PostingId")));				
				posting.setPrice(rs.getInt("Price"));
				posting.setDiscount(rs.getString("Discount"));
				
				mainCategory.setId(rs.getInt("CateId"));
				mainCategory.setId_security(EncryptionUtil.encode(rs.getString("CateId")));
				
				subcategory.setSubid(rs.getInt("SubCateId"));
				subcategory.setSubid_security(EncryptionUtil.encode(rs.getString("SubCateId")));
				
				posting.setMainCategory(mainCategory);
				posting.setSubCategory(subcategory);
				posting.setImage(image);
				productList.add(posting);
			}
			System.out.println("Category ID "+productList);
			return productList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public List<PostingDto> readSearchProductByCategory(String txt_name,String location,int start,int end){
		List<PostingDto> productList=new ArrayList<>();
		System.out.println("Search "+txt_name +  "    "+location);
		try{			
			String sql="select sc.SubCateId,c.CateId,i.Image,p.PostingId,p.ProductName,p.Price,p.Discount,p.KeyNotice from tbl_posting "
					+ "p INNER JOIN tbl_sub_category sc on p.SubCateId=sc.SubCateId "
					+ "INNER JOIN tbl_category c on c.CateId=sc.CateId INNER JOIN tbl_image "
					+ "i on p.PostingId=i.PostingId WHERE (p.ProductName LIKE ? AND p.Address LIKE ? ) and p.Active=1 and i.order=1 ORDER BY p.PostDate DESC  LIMIT ?,?";
			
			ps=ds.getConnection().prepareStatement(sql);
			ps.setString(1,"%"+txt_name+"%");
			ps.setString(2,"%"+ location+"%");
			ps.setInt(3, start);
			ps.setInt(4, end);
			rs=ps.executeQuery();
			while(rs.next()){
				PostingDto posting=new PostingDto();
				Image image=new Image();
				MainCategoryDto mainCategory=new MainCategoryDto();
				SubCategoryDto subcategory=new SubCategoryDto();
				
				image.setImage(rs.getString("Image"));	
				
				posting.setTitle(rs.getString("ProductName"));
				posting.setKey(rs.getString("KeyNotice"));
				posting.setPostingId(rs.getInt("PostingId"));
				posting.setPostingId_security(EncryptionUtil.encode(rs.getString("PostingId")));				
				posting.setPrice(rs.getInt("Price"));
				posting.setDiscount(rs.getString("Discount"));
				
				mainCategory.setId(rs.getInt("CateId"));
				mainCategory.setId_security(EncryptionUtil.encode(rs.getString("CateId")));
				
				subcategory.setSubid(rs.getInt("SubCateId"));
				subcategory.setSubid_security(EncryptionUtil.encode(rs.getString("SubCateId")));
				
				posting.setMainCategory(mainCategory);
				posting.setSubCategory(subcategory);
				posting.setImage(image);
				productList.add(posting);
			}
			System.out.println("Category ID "+productList);
			return productList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public List<PostingDto> readSearchProductByCategory(String txt_name,String location,int subcategoryid,int start,int end){
		List<PostingDto> productList=new ArrayList<>();
		System.out.println("Search "+txt_name +  "    "+location);
		try{			
			String sql="select sc.SubCateId,c.CateId,i.Image,p.PostingId,p.ProductName,p.Price,p.Discount,p.KeyNotice from tbl_posting "
					+ "p INNER JOIN tbl_sub_category sc on p.SubCateId=sc.SubCateId "
					+ "INNER JOIN tbl_category c on c.CateId=sc.CateId INNER JOIN tbl_image "
					+ "i on p.PostingId=i.PostingId WHERE (p.ProductName LIKE ? AND p.Address LIKE ? AND sc.SubCateId = ?) and p.Active=1 and i.order=1 ORDER BY p.PostDate DESC  LIMIT ?,?";
			
			ps=ds.getConnection().prepareStatement(sql);
			ps.setString(1,"%"+txt_name+"%");
			ps.setString(2,"%"+ location+"%");
			ps.setInt(3, subcategoryid);
			ps.setInt(4, start);
			ps.setInt(5, end);
			rs=ps.executeQuery();
			while(rs.next()){
				PostingDto posting=new PostingDto();
				Image image=new Image();
				MainCategoryDto mainCategory=new MainCategoryDto();
				SubCategoryDto subcategory=new SubCategoryDto();
				
				image.setImage(rs.getString("Image"));	
				
				posting.setTitle(rs.getString("ProductName"));
				posting.setKey(rs.getString("KeyNotice"));
				posting.setPostingId(rs.getInt("PostingId"));
				posting.setPostingId_security(EncryptionUtil.encode(rs.getString("PostingId")));				
				posting.setPrice(rs.getInt("Price"));
				posting.setDiscount(rs.getString("Discount"));
				
				mainCategory.setId(rs.getInt("CateId"));
				mainCategory.setId_security(EncryptionUtil.encode(rs.getString("CateId")));
				
				subcategory.setSubid(rs.getInt("SubCateId"));
				subcategory.setSubid_security(EncryptionUtil.encode(rs.getString("SubCateId")));
				
				posting.setMainCategory(mainCategory);
				posting.setSubCategory(subcategory);
				posting.setImage(image);
				productList.add(posting);
			}
			System.out.println("Category ID "+productList);
			return productList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	// Relative Product 
	public List<PostingDto> readProductByCategory(String category_id){
		List<PostingDto> productList=new ArrayList<>();
		try{			
			String sql="select sc.SubCateId,c.CateId,i.Image,p.PostingId,p.ProductName,p.Price,p.Discount,p.KeyNotice from tbl_posting "
					+ "p INNER JOIN tbl_sub_category sc on p.SubCateId=sc.SubCateId "
					+ "INNER JOIN tbl_category c on c.CateId=sc.CateId INNER JOIN tbl_image "
					+ "i on p.PostingId=i.PostingId WHERE c.CateId=? and p.Active=1 and i.order=1 ORDER BY p.PostDate DESC";
			
			ps=ds.getConnection().prepareStatement(sql);
			ps.setInt(1,Integer.valueOf(category_id));
			rs=ps.executeQuery();
			while(rs.next()){
				PostingDto posting=new PostingDto();
				Image image=new Image();
				MainCategoryDto mainCategory=new MainCategoryDto();
				SubCategoryDto subcategory=new SubCategoryDto();
				
				image.setImage(rs.getString("Image"));	
				
				posting.setTitle(rs.getString("ProductName"));
				posting.setKey(rs.getString("KeyNotice"));
				posting.setPostingId(rs.getInt("PostingId"));
				posting.setPostingId_security(EncryptionUtil.encode(rs.getString("PostingId")));				
				posting.setPrice(rs.getInt("Price"));
				posting.setDiscount(rs.getString("Discount"));
				
				mainCategory.setId(rs.getInt("CateId"));
				mainCategory.setId_security(EncryptionUtil.encode(rs.getString("CateId")));
				
				subcategory.setSubid(rs.getInt("SubCateId"));
				subcategory.setSubid_security(EncryptionUtil.encode(rs.getString("SubCateId")));
				
				posting.setMainCategory(mainCategory);
				posting.setSubCategory(subcategory);
				posting.setImage(image);
				productList.add(posting);
			}
			System.out.println("Category ID "+productList);
			return productList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public List<PostingDto> readProductByCategoryAndSubCategory(String category_id,String subCategoryId,int start,int row){
		List<PostingDto> productList=new ArrayList<>();
		try{
			String sql="select sc.SubCateId,c.CateId,i.Image,p.PostingId,p.ProductName,p.Price,p.Discount,p.KeyNotice from tbl_posting "
					+ "p INNER JOIN tbl_sub_category sc on p.SubCateId=sc.SubCateId "
					+ "INNER JOIN tbl_category c on c.CateId=sc.CateId INNER JOIN tbl_image "
					+ "i on p.PostingId=i.PostingId WHERE c.CateId=? and p.Active=1 and i.order=1 and sc.SubCateId=? ORDER BY p.PostDate DESC LIMIT ?,?";
			ps=ds.getConnection().prepareStatement(sql);
			ps.setInt(1,Integer.valueOf(category_id));
			ps.setInt(2,Integer.valueOf(subCategoryId));
			ps.setInt(3,start);
			ps.setInt(4,row);
			rs=ps.executeQuery();
			while(rs.next()){
				PostingDto posting=new PostingDto();
				Image image=new Image();
				MainCategoryDto mainCategory=new MainCategoryDto();
				SubCategoryDto subcategory=new SubCategoryDto();
				
				image.setImage(rs.getString("Image"));
				
				posting.setTitle(rs.getString("ProductName"));
				posting.setPostingId(rs.getInt("PostingId"));
				posting.setPostingId_security(EncryptionUtil.encode(rs.getString("PostingId")));
				posting.setPrice(rs.getInt("Price"));
				posting.setKey(rs.getString("KeyNotice"));
				posting.setDiscount(rs.getString("Discount"));
				
				mainCategory.setId(rs.getInt("CateId"));
				mainCategory.setId_security(EncryptionUtil.encode(rs.getString("CateId")));
				
				subcategory.setId(rs.getInt("SubCateId"));
				subcategory.setSubid_security(EncryptionUtil.encode(rs.getString("SubCateId")));
				
				posting.setMainCategory(mainCategory);
				posting.setSubCategory(subcategory);
				posting.setImage(image);
				productList.add(posting);
			}
			return productList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/*Relative Product*/
	public List<PostingDto> readProductByCategoryAndSubCategory(String category_id,String subCategoryId){
		List<PostingDto> productList=new ArrayList<>();
		try{
			String sql="select sc.SubCateId,c.CateId,i.Image,p.PostingId,p.ProductName,p.Price,p.Discount,p.KeyNotice from tbl_posting "
					+ "p INNER JOIN tbl_sub_category sc on p.SubCateId=sc.SubCateId "
					+ "INNER JOIN tbl_category c on c.CateId=sc.CateId INNER JOIN tbl_image "
					+ "i on p.PostingId=i.PostingId WHERE c.CateId=? and p.Active=1 and i.order=1 and sc.SubCateId=?";
			ps=ds.getConnection().prepareStatement(sql);
			ps.setInt(1,Integer.valueOf(category_id));
			ps.setInt(2,Integer.valueOf(subCategoryId));
			rs=ps.executeQuery();
			while(rs.next()){
				PostingDto posting=new PostingDto();
				Image image=new Image();
				MainCategoryDto mainCategory=new MainCategoryDto();
				SubCategoryDto subcategory=new SubCategoryDto();
				image.setImage(rs.getString("Image"));
				posting.setTitle(rs.getString("ProductName"));
				posting.setPostingId(rs.getInt("PostingId"));
				posting.setPostingId_security(EncryptionUtil.encode(rs.getString("PostingId")));
				posting.setPrice(rs.getInt("Price"));
				posting.setKey(rs.getString("KeyNotice"));
				posting.setDiscount(rs.getString("Discount"));
				
				mainCategory.setId(rs.getInt("CateId"));
				mainCategory.setId_security(EncryptionUtil.encode(rs.getString("CateId")));
				
				subcategory.setId(rs.getInt("SubCateId"));
				subcategory.setSubid_security(EncryptionUtil.encode(rs.getString("SubCateId")));
				
				posting.setMainCategory(mainCategory);
				posting.setSubCategory(subcategory);
				posting.setImage(image);
				productList.add(posting);
			}
			return productList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public PostingDto readProductByCategoryAndSubCategory(String category_id,String subCategoryId,String productId){
		PostingDto posting=new PostingDto();	
		try{
			String sql="select p.PostingId,p.view,p.ProductName,p.Price,p.Discount,p.KeyNotice,p.Description,"
					+ "m.`Name`,m.Phone,m.Email,m.Address,c.CateId,c.`Name` as cateName,sc.SubCateId,sc.`Name` as subcateName from tbl_posting "
					+ "p INNER JOIN tbl_sub_category sc on p.SubCateId=sc.SubCateId "
					+ "INNER JOIN tbl_category c on c.CateId=sc.CateId INNER JOIN tbl_member m on p.MemId=m.MemId "
					+ " WHERE c.CateId=? and sc.SubCateId=? and p.PostingId=?";
			String sql1 = "select i.image from tbl_image i INNER JOIN tbl_posting p "
					+ "on i.PostingId=p.PostingId where "
					+ "p.PostingId=? ORDER BY i.`order`";
			
			
			
			ps=ds.getConnection().prepareStatement(sql);
			ps.setInt(1,Integer.valueOf(category_id));
			ps.setInt(2,Integer.valueOf(subCategoryId));
			ps.setInt(3, Integer.valueOf(productId));
			rs=ps.executeQuery();
			System.out.println(" Posting Dto "+category_id + "Sub "+subCategoryId+" pro "+productId);	
			while(rs.next()){
			    /*System.out.println(" Posting Dto 123");	*/
				/*MainCategoryDto mainCategory=new MainCategoryDto();
				SubCategoryDto subcategory=new SubCategoryDto();*/			
				
				PreparedStatement ps1=ds.getConnection().prepareStatement(sql1);
				ResultSet rs1=null;
				ps1.setInt(1, Integer.valueOf(productId));
				rs1=ps1.executeQuery();
				
				List<Image> imageList=new ArrayList<>();
				while(rs1.next()){
					Image image=new Image();
					image.setImage(rs1.getString("image"));
					imageList.add(image);
				}
				
				MainCategoryDto mainCategory=new MainCategoryDto();
				mainCategory.setId_security(rs.getString("CateId"));
				mainCategory.setId(rs.getInt("CateId"));
				mainCategory.setName(rs.getString("cateName"));
				
				SubCategoryDto subcategory=new SubCategoryDto();
				subcategory.setSubid(rs.getInt("SubCateId"));
				subcategory.setName(rs.getString("subcateName"));
				subcategory.setSubid_security("SubCateId");
								
				MemberDto memberDto=new MemberDto();
				memberDto.setName(rs.getString("Name"));
				memberDto.setEmail(rs.getString("Email"));
				memberDto.setPhone(rs.getString("Phone"));
				memberDto.setAddress(rs.getString("Address"));
				
				posting.setTitle(rs.getString("ProductName"));
				posting.setPostingId(rs.getInt("PostingId"));
				posting.setPostingId_security(rs.getString("PostingId"));
				posting.setPrice(rs.getInt("Price"));
				posting.setKey(rs.getString("KeyNotice"));
				posting.setDiscount(rs.getString("Discount"));
				posting.setDsc(rs.getString("Description"));
				posting.setSubCategory(subcategory);
				posting.setMainCategory(mainCategory);
				posting.setMemberDto(memberDto);
				posting.setImageList(imageList);
				
				String sql2 = "UPDATE tbl_posting SET `view`=? WHERE PostingId=?";
				PreparedStatement ps2=ds.getConnection().prepareStatement(sql2);
				ps2.setInt(1, (rs.getInt("view")+1));
				ps2.setInt(2,Integer.valueOf(productId));
				ps2.executeUpdate();
				
			}
			return posting;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/*delete Product*/
	public boolean deleteProduct(int id,int user_id){
		System.out.println(id + "   "+user_id);
		try{
			String sql="delete from tbl_posting  where PostingId=? and MemId=?";
			String sql1="delete from tbl_image where PostingId=?";
			ps=ds.getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, user_id);
			int i=ps.executeUpdate();
			System.out.println("I "+i);
			if (i>0){
				PreparedStatement ps1=ds.getConnection().prepareStatement(sql1);
				ps1.setInt(1, id);
				int j=ps1.executeUpdate();
				if (j>0)	return true;
			}
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
       
	}
	
	public PostingDto getPostingById(int id,int user_id){
		PostingDto postingDto=new PostingDto();
		MemberDto memberDto=new MemberDto();
		SubCategoryDto subcategoryDto=new SubCategoryDto();
		List<Image> listimage=new ArrayList<>();
		
		try{
			String sql="SELECT p.PostingId,p.MemId,p.ProductName,p.KeyNotice,p.SubCateId, "
					+ "p.Price,p.Phone,p.Address,p.Description,p.Discount from tbl_posting p  "
					+ "where p.PostingId=? and p.MemId=?";
			ps=ds.getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, user_id);
			rs=ps.executeQuery();
			while(rs.next()){
				postingDto.setPostingId(rs.getInt("PostingId"));
				postingDto.setTitle(rs.getString("ProductName"));
				postingDto.setKey(rs.getString("KeyNotice"));
				postingDto.setPrice(rs.getInt("Price"));
				postingDto.setPhone(rs.getString("Phone"));
				postingDto.setAdr(rs.getString("Address"));
				postingDto.setDsc(rs.getString("Description"));
				postingDto.setDiscount(rs.getString("Discount"));
				
				memberDto.setId(rs.getInt("MemId"));
				
				subcategoryDto.setSubid(rs.getInt("SubCateId"));
				
				postingDto.setMemberDto(memberDto);
				postingDto.setSubCategory(subcategoryDto);
				
				String sql1="SELECT i.ImgId,i.PostingId,i.Image,i.`order`  FROM tbl_image i INNER JOIN "
						+ " tbl_posting p on i.PostingId=p.PostingId where i.PostingId=? ORDER BY i.`order` ASC";
				PreparedStatement ps1=ds.getConnection().prepareStatement(sql1);
				ps1.setInt(1, id);
				ResultSet rs1=ps1.executeQuery();
				while(rs1.next()){
					Image image=new Image();
					image.setImage_id(rs1.getInt("ImgId"));
					image.setImage(rs1.getString("Image"));
					image.setOrder(rs1.getInt("order"));
					image.setPost_id(rs1.getInt("PostingId"));
					listimage.add(image);
				}
				System.out.println("List Size "+listimage.size());
				postingDto.setImageList(listimage);
			}
			return postingDto;
		}catch(Exception e){
			
		}
		return null;
	}
	
	
	public boolean getEditPosting(PostingDto dto){
		
		String sql="UPDATE tbl_posting "
				+ "SET ProductName=?,"
				+ "KeyNotice=?,"
				+ "SubCateId=?,"
				+ "Price=?,"
				+ "Phone=?,"
				+ "Address=?,"
				+ "Description=?,"
				+ "Discount=? "
				+ "WHERE PostingId=? AND MemId=?";
		try{
			System.out.println("Posting "+dto.getPostingId()+ " "+dto.getMemId());
			ps=ds.getConnection().prepareStatement(sql);
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getKey());
			ps.setInt(3, dto.getSubCateId());
			ps.setInt(4, dto.getPrice());
			ps.setString(5, dto.getPhone());
			ps.setString(6, dto.getAdr());
			ps.setString(7, dto.getDsc());
			ps.setString(8, dto.getDiscount());
			ps.setInt(9, dto.getPostingId());
			ps.setInt(10, dto.getMemId());
			int i=ps.executeUpdate();
			if (i>0){
				return true;
			}
		}catch(Exception e){
			
		}
		
		return false;
	}
	
	public boolean getUpdateImage(Image dto){
		String sql="UPDATE tbl_image SET Image=? WHERE ImgId=? AND PostingId=?";
		try{
			ps=ds.getConnection().prepareStatement(sql);
			ps.setString(1, dto.getImage());
			ps.setInt(2, dto.getImage_id());
			ps.setInt(3, dto.getPost_id());
			
			int i = ps.executeUpdate();
			if (i>0){
				return true;
			}
			
		}catch(Exception e){
			
		}
		
		return false;
	}
	
	public boolean getDeleteImage(int pro_id){
		String sql="DELETE FROM tbl_image WHERE PostingId=?";
		try{
			ps=ds.getConnection().prepareStatement(sql);
			ps.setInt(1, pro_id);
			
			int i = ps.executeUpdate();
			if (i>0){
				return true;
			}
			
		}catch(Exception e){
			
		}
		
		return false;
	}
	public boolean getDeleteImage(int pro_id,int image_id){
		String sql="DELETE FROM tbl_image WHERE PostingId=? AND ImgId=?";
		try{
			System.out.println("  "+pro_id+"  "+image_id);
			ps=ds.getConnection().prepareStatement(sql);
			ps.setInt(1, pro_id);
			ps.setInt(2,image_id);			
			ps.executeUpdate();		
			return true;			
		}catch(Exception e){
			return false;
		}
		
		
	}
	
	public List<Image> getListImage(int pro_id){
		List<Image> listImage=new ArrayList<>();
		
		String sql="SELECT i.ImgId,i.PostingId,i.Image,i.`order` FROM tbl_image i WHERE i.PostingId=?";
		try{
			
			 
			ps=ds.getConnection().prepareStatement(sql);
			ps.setInt(1, pro_id);
			rs= ps.executeQuery();
		
			while(rs.next()){
				System.out.println("Posting Id "+pro_id);
				Image img=new Image();
				img.setImage_id(rs.getInt("ImgId"));
				img.setPost_id(rs.getInt("PostingId"));
				img.setImage(rs.getString("Image"));
				img.setOrder(rs.getInt("order"));
				
				
				listImage.add(img);
			}
		}catch(Exception e){
			
		}
		
		return listImage;
	}
	
	public void getInsertImage(List<Image> listimage){
		System.out.println("Hello");
		String sql="insert into tbl_image values(?,?,?,?)";
		try{
			ps=ds.getConnection().prepareStatement(sql);			
			for(int i=0;i<listimage.size();i++){
				System.out.println("Hello");
				ps.setInt(1, i+1);
				ps.setInt(2, listimage.get(i).getPost_id());
				ps.setString(3, listimage.get(i).getImage());
				ps.setInt(4, i+1);
				ps.executeUpdate();
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public int getImageId(int pro_id){
		int max=0;
		String sql ="SELECT max(ImgId) as imgid FROM tbl_image WHERE PostingId=?";
		try{
			ps=ds.getConnection().prepareStatement(sql);
			ps.setInt(1, pro_id);
			rs=ps.executeQuery();
			while(rs.next()){
				max=rs.getInt("imgid");
			}
		}catch(Exception e){
			
		}
		return max;
	}
	
	public boolean saveImageMore(Image dto){
		String sql="insert into tbl_image values(?,?,?,?)";
		try{
			System.out.println("Hello  "+dto.getImage_id()+"  "+dto.getPost_id());
			ps=ds.getConnection().prepareStatement(sql);
			ps.setInt(1, dto.getImage_id());
			ps.setInt(2, dto.getPost_id());
			ps.setString(3, dto.getImage());
			ps.setInt(4, dto.getOrder());
			int i= ps.executeUpdate();
			if (i>0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
}


