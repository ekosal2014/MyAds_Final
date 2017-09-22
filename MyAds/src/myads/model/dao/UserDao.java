package myads.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import myads.model.dto.UserDto;
import myads.model.sqlConnection.SqlConnection;

public class UserDao {
static PreparedStatement ps;
static ResultSet rs;

	public ResultSet login(String uname, String password){
		
		String sql="select Id,Name,Sex,Photo,Dob,Email,Phone,Address from tbl_user where Name=? AND Password=?";
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

	
	public boolean insertUser(UserDto dto){

		String sql = "insert into tbl_user values(?,?,?,?,?,?,?,?,?,?,?)";

		try{
			ps =SqlConnection.getConnection().prepareStatement(sql);  
			ps.setInt(1, dto.getId());
			ps.setInt(2, dto.getPostId());
			ps.setString(3, dto.getName());
			ps.setString(4, dto.getSex());
			ps.setString(5, dto.getPhoto());
			ps.setString(6, dto.getDob());
			ps.setString(7, dto.getPassword());
			ps.setString(8, dto.getEmail());
			ps.setString(9, dto.getPhone());
			ps.setString(10, dto.getAddress());
			ps.setInt(11, dto.getActive());

			ps.executeUpdate();
			ps.close();

			return true;
		}catch(SQLException e){
			System.err.println("SQL Error : insert"+ e);
			return false;
		}
	}
	
	// exist user 
	public UserDto existUser(int id) {
		
		String sql="select u.Id,u.PositId,p.PositAs,u.Name,u.Sex,u.Photo,u.Dob,u.Password,u.Email,u.Phone,u.Address,u.Active  from tbl_position p inner join tbl_user u on p.PositId= u.PositId where u.Id ="+id;
		UserDto dto=new UserDto();
		System.out.println("Id get from browser= "+id);
		
		try {
			
			ps=SqlConnection.getConnection().prepareStatement(sql);
			rs=ps.executeQuery();
			
			if(rs!=null && rs.next()){
				dto.setId(rs.getInt(1));
				dto.setPostId(rs.getInt(2));
				dto.setPositionAs(rs.getString(3));
				dto.setName(rs.getString(4));
				dto.setSex(rs.getString(5));
				dto.setPhoto(rs.getString(6));
				dto.setDob(rs.getString(7));
				dto.setPassword(rs.getString(8));
				dto.setEmail(rs.getString(9));
				dto.setPhone(rs.getString(10));
				dto.setAddress(rs.getString(11));
				dto.setActive(rs.getInt(12));
			}
			
			return dto;
			
		} catch (Exception e) {
			System.out.println("error:" + e);
		}
		
		return null;
	}
	
}
