package myads.model.sqlConnection;

import java.sql.*;
import java.sql.Connection;

public class SqlConnection implements SqlProvider{
	
	static Connection con=null;
	
	public static Connection getConnection(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,usr,pwd);
			System.out.println("Connection is OK");
		} catch (Exception e) {
			System.out.println("No Connection" + e);
		}
		
		return con;
	}
		
}
