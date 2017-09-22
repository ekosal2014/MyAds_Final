package myads.model.sqlConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection implements SqlProvider {

	private static Connection cn;
	
	public static void OpenConnection(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn=DriverManager.getConnection(url,usr,pwd);
			System.out.println("Connection is OK");
		} catch (Exception e) {
			System.out.println("No Connection" + e);
		}	
	}

	public Connection getConnection(){
		return cn;
	}
	
}
