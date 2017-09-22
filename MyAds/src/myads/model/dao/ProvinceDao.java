package myads.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import myads.model.dto.ProvinceDto;
import myads.model.sqlConnection.SqlConnection;

public class ProvinceDao{
	static PreparedStatement ps;
	static ResultSet rs;
	
	// insert main category
	public boolean insertProvince(ProvinceDto dto){
		
		String sql = "insert into tbl_province values(?,?,?,?)";
		
		try{
			ps =SqlConnection.getConnection().prepareStatement(sql);  
			ps.setInt(1, dto.getId());
			ps.setString(2, dto.getName());
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

	
	// exist province
		public ProvinceDto exisprovince(int id) {
			
			String sql="select * from tbl_province where ProvId="+id;
			ProvinceDto dto=new ProvinceDto();
			System.out.println("Id get from browser= "+id);
			
			try {
				
				ps=SqlConnection.getConnection().prepareStatement(sql);
				rs=ps.executeQuery();
				
				if(rs!=null && rs.next()){
					dto.setId(rs.getInt(1));
					dto.setName(rs.getString(2));
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
		public boolean updateProvince(ProvinceDto dto){
			
			String sql="update tbl_province set ProvName=?,Description=?,Active=? where ProvId="+dto.getId();
			
			try {
				ps=SqlConnection.getConnection().prepareStatement(sql);
				ps.setString(1, dto.getName());
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
