package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static Connection connection=null;
	
	private static String jdbc_driver = "com.mysql.jdbc.Driver";
	private static String jdbc_url = "jdbc:mysql://127.0.0.1:3306/db?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false";
	private static String jdbc_username = "root";
	private static String jdbc_password = "19960307";
	
	public static Connection getConnection(){
		if (connection==null) {
			try {
				Connection connection=null;
				Class.forName(jdbc_driver);
				connection=DriverManager.getConnection(jdbc_url,jdbc_username,jdbc_password);
				return connection;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
			}
		}else{
			return connection;
		}
	}
	
	public static void closeConnection(){
		if (connection==null) {
			return;
		}
		try {
			if (!connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
