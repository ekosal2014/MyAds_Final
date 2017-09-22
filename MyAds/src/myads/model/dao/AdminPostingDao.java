package myads.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import myads.model.dto.Image;
import myads.model.dto.MainCategoryDto;
import myads.model.dto.MemberDto;
import myads.model.dto.PostingDto;
import myads.model.dto.SubCategoryDto;

public class AdminPostingDao {
	DataSource ds;
	Connection con;
	static PreparedStatement ps;
	PreparedStatement psimg;
	static ResultSet rs;
	
	public AdminPostingDao(){
		try{
			Context init = new InitialContext();
	  	    ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception ex){
			System.out.println("DB Connection Failture! : " + ex);
			return;
		}	
	}
	
	public List<PostingDto> listAllPosting(){
		List<PostingDto> postingList=new ArrayList<>();
		try{
			String sql="select p.PostingId,p.view,p.ProductName,p.Price,p.Phone,p.Address,p.Discount,p.KeyNotice,p.Description,"
					+ "m.`Name`,m.MemId,m.Email,m.Address,c.CateId,c.`Name` as cateName,sc.SubCateId,sc.`Name` as subcateName from tbl_posting "
					+ "p INNER JOIN tbl_sub_category sc on p.SubCateId=sc.SubCateId "
					+ "INNER JOIN tbl_category c on c.CateId=sc.CateId INNER JOIN tbl_member m on p.MemId=m.MemId "
					+ " WHERE p.Active=0";
			String sql1 = "select i.image from tbl_image i INNER JOIN tbl_posting p "
					+ "on i.PostingId=p.PostingId where "
					+ "p.PostingId=? ORDER BY i.`order`";
			
			
			
			ps=ds.getConnection().prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
		
				PostingDto posting=new PostingDto();
				
				PreparedStatement ps1=ds.getConnection().prepareStatement(sql1);
				ResultSet rs1=null;
				ps1.setInt(1, Integer.valueOf(rs.getInt("PostingId")));
				rs1=ps1.executeQuery();
				
				List<Image> imageList=new ArrayList<>();
				while(rs1.next()){
					Image image=new Image();
					image.setImage(rs1.getString("image"));
					imageList.add(image);
				}
				
				MainCategoryDto mainCategory=new MainCategoryDto();
				mainCategory.setId(rs.getInt("CateId"));
				mainCategory.setName(rs.getString("cateName"));
				
				SubCategoryDto subcategory=new SubCategoryDto();
				subcategory.setSubid(rs.getInt("SubCateId"));
				subcategory.setName(rs.getString("subcateName"));
								
				MemberDto memberDto=new MemberDto();
				memberDto.setName(rs.getString("Name"));
				memberDto.setId(rs.getInt("MemId"));

				
				posting.setTitle(rs.getString("ProductName"));
				posting.setPostingId(rs.getInt("PostingId"));
				posting.setPrice(rs.getInt("Price"));
				posting.setKey(rs.getString("KeyNotice"));
				posting.setDiscount(rs.getString("Discount"));
				posting.setDsc(rs.getString("Description"));
				posting.setAdr(rs.getString("Address"));
				posting.setPhone(rs.getString("Phone"));
				posting.setSubCategory(subcategory);
				posting.setMainCategory(mainCategory);
				posting.setMemberDto(memberDto);
				posting.setImageList(imageList);
				
				postingList.add(posting);
				
			}
			return postingList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	public boolean getApprovePosting(int posting_id,int mem_id){
		String sql="UPDATE tbl_posting SET Active='1' WHERE PostingId=? and MemId=?";
		try{
			
			ps=ds.getConnection().prepareStatement(sql);
			ps.setInt(1, posting_id);
			ps.setInt(2,mem_id);
			if (ps.executeUpdate()>0){
				return true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	public List<PostingDto> getNotice(){
		
		List<PostingDto> listdto=new ArrayList<>();
		
		String sql="SELECT m.Photo,m.`Name`,p.PostDate,p.ProductName "
				+ "from tbl_posting p  "
				+ "INNER JOIN tbl_member m on p.MemId=m.MemId "
				+ "WHERE p.Active=0";
		try{
			
			ps=ds.getConnection().prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				
				MemberDto memberDto=new MemberDto();
				memberDto.setPhoto("");
				memberDto.setName(rs.getString("Name"));
				
				PostingDto dto=new PostingDto();
				dto.setPostdate(rs.getString("PostDate"));
				dto.setTitle(rs.getString("ProductName"));				
				dto.setMemberDto(memberDto);
				
				listdto.add(dto);
				
			}
			
			return listdto;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
}
