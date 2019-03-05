package cn.web.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.web.news.entity.User;

public class UserDao extends BaseDao{
		
		public User findUser(String username,String password) {
			String sql="SELECT *FROM news_users WHERE uname = ? and upwd =?;";
			Connection conn=super.getContion();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			User user =null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, username);
				stmt.setString(2, password);
				rs  = stmt.executeQuery();
				while(rs.next()) {
					user= new User();
					user.setUname(rs.getString("uname"));
					user.setUpwd(rs.getString("upwd"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return user;
			
		}
	
}
