package myads.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import myads.model.sqlConnection.SqlConnection;

public class MyComobox extends SqlConnection{

	private static PreparedStatement ps=null;
	
	public static ResultSet getRole(){
		ResultSet rs=null;
		String sql="select * from tbl_role";
		try {
			
			ps=SqlConnection.getConnection().prepareStatement(sql);
			rs=ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public static ResultSet getlistRole(){
		ResultSet rs=null;
		String sql="select * from tbl_role where Active=1 order by RoleId desc";
		try {
			
			ps=SqlConnection.getConnection().prepareStatement(sql);
			rs=ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public static ResultSet getPosition(){
		ResultSet rs=null;
		String sql="select * from tbl_position order by PositId";
		try {
			
			ps=SqlConnection.getConnection().prepareStatement(sql);
			rs=ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public static ResultSet getlistPosition(){
		ResultSet rs=null;
		String sql="select pos.PositId, rol.Role, pos.PositAs, pos.Description, pos.Active from tbl_role rol, tbl_position pos where rol.RoleId=pos.RoleId  order by PositId desc";
		try {
			
			ps=SqlConnection.getConnection().prepareStatement(sql);
			rs=ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public static ResultSet getCompany(){
		ResultSet rs=null;
		String sql="select * from tbl_company";
		try {
			
			ps=SqlConnection.getConnection().prepareStatement(sql);
			rs=ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public static ResultSet getCategory(){
		ResultSet rs=null;
		String sql="select * from tbl_category where Active=1 order by CateId";
		try {
			
			ps=SqlConnection.getConnection().prepareStatement(sql);
			rs=ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public static ResultSet getlistCategory(){
		ResultSet rs=null;
		String sql="select * from tbl_category where Active=1 order by CateId desc";
		try {
			
			ps=SqlConnection.getConnection().prepareStatement(sql);
			rs=ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public static ResultSet getsubCategory(){
		ResultSet rs=null;
		String sql="select scate.SubCateId, mcate.Name, scate.Name, scate.Description, scate.Active from tbl_category mcate, tbl_sub_category scate where mcate.CateId=scate.CateId order by scate.SubCateId desc";
		try {
			
			ps=SqlConnection.getConnection().prepareStatement(sql);
			rs=ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	
	public static ResultSet getmymainCategory(){
		ResultSet rs=null;
		String sql="select * from tbl_category order by CateId";
		try {
			
			ps=SqlConnection.getConnection().prepareStatement(sql);
			rs=ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public static ResultSet getmysubCategory(){
		ResultSet rs=null;
		String sql="select c.CateId,c.Name,s.Name, c.Description,c.Active from tbl_category c join tbl_sub_category s on c.CateId=s.CateId where c.CateId order by s.CateId";
		try {
			
			ps=SqlConnection.getConnection().prepareStatement(sql);
			rs=ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	
	public static ResultSet getProvince(){
		ResultSet rs=null;
		String sql="select * from tbl_province";
		try {
			
			ps=SqlConnection.getConnection().prepareStatement(sql);
			rs=ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public static ResultSet getlistProvince(){
		ResultSet rs=null;
		String sql="select * from tbl_province where Active=1 order by ProvId desc";
		try {
			
			ps=SqlConnection.getConnection().prepareStatement(sql);
			rs=ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public static ResultSet getlistUser(){
		ResultSet rs=null;
		String sql="select usr.Id,pos.PositAs, usr.Name,usr.Sex,usr.Photo,usr.Dob,usr.Password,usr.Email,usr.Phone,usr.Address,usr.Active from tbl_user usr, tbl_position pos where usr.PositId=pos.PositId order by usr.Id desc";
		try {
			
			ps=SqlConnection.getConnection().prepareStatement(sql);
			rs=ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
}
