package myads.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import myads.model.dto.CompanyDto;
import myads.model.sqlConnection.SqlConnection;

public class CompanyDao {
	static PreparedStatement ps;
	static ResultSet rs;
	
	// insert main category
	public boolean insertCompany(CompanyDto dto){
		
		String sql = "insert into tbl_company values(?,?,?,?,?,?,?)";
		
		try{
			ps =SqlConnection.getConnection().prepareStatement(sql);  
			ps.setInt(1, dto.getComid());
			ps.setString(2, dto.getComname());
			ps.setString(3, dto.getType());
			ps.setString(4, dto.getFax());
			ps.setString(5, dto.getEmail());
			ps.setString(6, dto.getAdr());
			ps.setInt(7, dto.getActive());
			
			ps.executeUpdate();
			ps.close();
			
			return true;
		}catch(SQLException e){
			System.err.println("SQL Error : insert"+ e);
			return false;
		}
	}

}
