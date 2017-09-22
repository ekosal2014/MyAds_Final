package myads.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import myads.model.dto.PositionDto;
import myads.model.sqlConnection.SqlConnection;

public class PositionDao {
	static PreparedStatement ps;
	static ResultSet rs;
	
	
	
	// insert main category
	public boolean insertPosition(PositionDto dto){
		
		String sql = "insert into tbl_position values(?,?,?,?,?)";
		
		try{
			ps =SqlConnection.getConnection().prepareStatement(sql);  
			ps.setInt(1,dto.getPostId());
			ps.setInt(2, dto.getRoleId());
			ps.setString(3, dto.getPostAS());
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
	public PositionDto existPosition(int id) {
		
		String sql="select p.PositId, r.RoleId, r.Role,p.PositAs,p.Description, p.Active from tbl_role r inner join tbl_position p on r.RoleId= p.RoleId where p.RoleId ="+id;
		PositionDto dto=new PositionDto();
		System.out.println("Id get from browser= "+id);
		
		try {
			
			ps=SqlConnection.getConnection().prepareStatement(sql);
			rs=ps.executeQuery();
			
			if(rs!=null && rs.next()){
				dto.setPostId(rs.getInt(1));
				dto.setRoleId(rs.getInt(2));
				dto.setRoleName(rs.getString(3));
				dto.setPostAS(rs.getString(4));
				dto.setDsc(rs.getString(5));
				dto.setActive(rs.getInt(6));
			}
			
			return dto;
			
		} catch (Exception e) {
			System.out.println("error:" + e);
		}
		
		return null;
	}

}
