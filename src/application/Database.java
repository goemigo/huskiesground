package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	private Connection connect;
	private PreparedStatement prepare;
	private ResultSet result;
	
	public static Connection connectDB() {
		
		String url = "jdbc:mysql://hg.cafpnlsr5tw5.us-east-2.rds.amazonaws.com:3306/huskiesground";
		String user = "admin";
		String password = "adminpass";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url,user,password);
			if (conn!=null) {
				System.out.println("connection to database is successful");
			}else {
				System.out.println("connection failed");
			}
			return conn;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultSet getData() throws SQLException {
		//connect to aws mysql database
		connect = connectDB();
//		
		//testing fetch data from the database
		String sql = "select * from users";
		
		prepare = connect.prepareStatement(sql);
		result = prepare.executeQuery();
		
		boolean isEmpty = true;

	    while (result.next()) {
	        isEmpty = false;
	        int userId = result.getInt("userid");
	        String username = result.getString("username");
	        String password = result.getString("password");

	        System.out.println("UserID: " + userId + ", Username: " + username + ", Password: " + password);
	    }

	    if (isEmpty) {
	        System.out.println("empty table users");
	    }
		return null;
	}
}
