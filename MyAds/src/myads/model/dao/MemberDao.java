package myads.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import myads.model.dto.CompanyDto;
import myads.model.dto.MemberDto;
import myads.model.sqlConnection.SqlConnection;

public class MemberDao {
	DataSource ds;
	Connection con;
static PreparedStatement ps;
static ResultSet rs;
   public MemberDao(){
	   try{
			Context init = new InitialContext();
	  	    ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
	  		System.out.println("Connection New!!!!!!!!!!1");
		}catch(Exception ex){
			System.out.println("DB Connection Failture! : " + ex);
			return;
		}	
   }

	public ResultSet login(String uname, String password){
		
		String sql="select m.MemId,m.Name,m.RealName,m.Sex,m.Date,m.Password,m.Email,m.Phone,m.Address,m.Photo,m.Active,c.ComId,c.ComName from tbl_member m INNER JOIN tbl_company c where Name = ? AND Password = ?";
		try {
			
			ps=SqlConnection.getConnection().prepareStatement(sql);
			ps.setString(1, uname);
			ps.setString(2, password);
			rs=ps.executeQuery();
			
			return rs;
		} catch (Exception e) {
			// TODO: handle exception
			return rs;
		}
		
		
	}

	
	public boolean insertMember(MemberDto dto){
		
		String sql = "insert into tbl_member values(?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			ps =SqlConnection.getConnection().prepareStatement(sql);  
			ps.setInt(1, dto.getId());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getRealName());
			ps.setInt(4, dto.getComid());
			ps.setString(5, dto.getSex());
			ps.setString(6, dto.getDate());
			ps.setString(7, dto.getPassword());
			ps.setString(8, dto.getEmail());
			ps.setString(9, dto.getPhone());
			ps.setString(10, dto.getAddress());
			ps.setInt(11, dto.getActive());
			ps.setString(12, dto.getPhoto());
			
			ps.executeUpdate();
			ps.close();
			
			return true;
		}catch(SQLException e){
			System.err.println("SQL Error : insert"+ e);
			return false;
		}
	}
	
	public MemberDto getMemberEdit(int Mem_id){
		MemberDto member=new MemberDto();
		String sql="select m.MemId,m.Name,m.RealName,m.Sex,m.Date,m.Password,m.Email,m.Phone,m.Address,m.Photo,m.Active,c.ComId,c.ComName from tbl_member m INNER JOIN tbl_company c where m.MemId=? GROUP BY m.MemId";
		try {
			ps=ds.getConnection().prepareStatement(sql);
			ps.setInt(1, Mem_id);
			rs=ps.executeQuery();
			while(rs.next()){
			
				 member.setId(rs.getInt("MemId"));
				 member.setName(rs.getString("Name"));
				 member.setRealName(rs.getString("RealName"));
				 member.setSex(rs.getString("Sex"));
				 member.setDate(rs.getString("Date"));
				 member.setEmail(rs.getString("Email"));
				 member.setPhone(rs.getString("Phone"));
				 member.setAddress(rs.getString("Address"));
				 member.setPhoto(rs.getString("Photo"));
				 
				 CompanyDto company=new CompanyDto();
				 company.setComid(rs.getInt("ComId"));
				 company.setComname(rs.getString("ComName"));
				 
				 member.setCompanyDto(company);
				 System.out.println("Mem id = " +  member);
				 return member;
			}
			
		}catch(Exception e){
			
		}
		
		return null;
	}
	
	public boolean editMember(MemberDto memberDto){
		System.out.println(memberDto.getId());
		try{
			String sql="UPDATE tbl_member m SET m.`Name`=?,m.RealName=?,m.Sex=?,m.ComId=?,m.Phone=?,m.Address=?,m.Email=? WHERE m.MemId=?";
			ps=ds.getConnection().prepareStatement(sql);
			ps.setString(1, memberDto.getName());
			ps.setString(2, memberDto.getRealName());
			ps.setString(3, memberDto.getSex());
			ps.setInt(4, memberDto.getComid());
			ps.setString(5, memberDto.getPhone());
			ps.setString(6, memberDto.getAddress());
			ps.setString(7, memberDto.getEmail());
			ps.setInt(8, memberDto.getId());
			int i=ps.executeUpdate();
			if (i>0) return true;

			
		}catch(Exception e){
			
		}
		return false;
	}
	
	public boolean editMemberWithPassword(MemberDto memberDto){
		try{
			String sql="UPDATE tbl_member m SET m.`Name`=?,m.RealName=?,m.Sex=?,m.ComId=?,m.Phone=?,m.Address=?,m.Email=?,m.Password=? WHERE m.MemId=?";
			ps=ds.getConnection().prepareStatement(sql);
			ps.setString(1, memberDto.getName());
			ps.setString(2, memberDto.getRealName());
			ps.setString(3, memberDto.getSex());
			ps.setInt(4, memberDto.getComid());
			ps.setString(5, memberDto.getPhone());
			ps.setString(6, memberDto.getAddress());
			ps.setString(7, memberDto.getEmail());
			ps.setString(8, memberDto.getPassword());
			ps.setInt(9, memberDto.getId());
			int i=ps.executeUpdate();
			if (i>0) return true;

			
		}catch(Exception e){
			
		}
		return false;
	}
	
	public int getMemberCheckPassword(int mem_id,String password){
		int count=0;
		try{
			String sql="SELECT COUNT(*) Total FROM tbl_member WHERE MemId=? and `Password`=?";
			ps=ds.getConnection().prepareStatement(sql);
			ps.setInt(1, mem_id);
			ps.setString(2, password);
			rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt("Total");
			}
			return count;
		}catch(Exception e){
			
		}
		return count;
	}
	
	public boolean saveChangeImage(int id,String image){
		String sql="UPDATE tbl_member m SET m.Photo=? WHERE m.MemId=?";
		try{
			ps=ds.getConnection().prepareStatement(sql);
			ps.setString(1, image);
			ps.setInt(2, id);
			int i=ps.executeUpdate();
			if (i>0){
				return true;
			}
		}catch(Exception e){
			
		}
		return false;
	}
}
