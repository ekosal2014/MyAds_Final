package myads.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import myads.model.dto.RoleDto;
import myads.model.sqlConnection.SqlConnection;

public class RoleDao {
	static PreparedStatement ps;
	static ResultSet rs;
	
	// insert main category
	public boolean insertRole(RoleDto dto){
		
		String sql = "insert into tbl_role values(?,?,?,?)";
		
		try{
			ps =SqlConnection.getConnection().prepareStatement(sql);  
			ps.setInt(1,dto.getRoleId());
			ps.setString(2, dto.getRole());
			ps.setString(3, dto.getDsc());
			ps.setInt(4, dto.getActive());
			
			ps.executeUpdate();
			ps.close();
			
			return true;
		}catch(SQLException e){
			System.err.println("SQL Error : insert"+ e);
			return false;
		}
	}

	// exist role
	public RoleDto existrole(int id) {
		
		String sql="select * from tbl_role where RoleId="+id;
		RoleDto dto=new RoleDto();
		System.out.println("Id get from browser= "+id);
		
		try {
			
			ps=SqlConnection.getConnection().prepareStatement(sql);
			rs=ps.executeQuery();
			
			if(rs!=null && rs.next()){
				dto.setRoleId(rs.getInt(1));
				dto.setRole(rs.getString(2));
				dto.setDsc(rs.getString(3));
				dto.setActive(rs.getInt(4));
			}
			
			return dto;
			
		} catch (Exception e) {
			System.out.println("error:" + e);
		}
		
		return null;
	}
	
	// update province 
	public boolean updateRole(RoleDto dto){
		
		String sql="update tbl_role set Role=?,Description=?,Active=? where RoleId="+dto.getRoleId();
		
		try {
			ps=SqlConnection.getConnection().prepareStatement(sql);
			ps.setString(1, dto.getRole());
			ps.setString(2, dto.getDsc());
			ps.setInt(3, dto.getActive());
			
			ps.executeUpdate();
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
}
