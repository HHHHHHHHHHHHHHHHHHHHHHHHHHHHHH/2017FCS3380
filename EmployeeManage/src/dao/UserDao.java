package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBUtil;

public class UserDao {
	public boolean checkLogin(String username, String password){
		Connection connection=DBUtil.getConnection();
		PreparedStatement statement=null;
		try {
			statement=connection.prepareStatement("select * from user where username=? and password=?");
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet rs=statement.executeQuery();
			while(rs.next()){
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
