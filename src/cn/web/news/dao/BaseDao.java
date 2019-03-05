package cn.web.news.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	// 定义账号 密码 驱动 链接
	public static final String User = "root";
	public static final String PSSSWORD = "";
	public static final String Driver = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/newsdb?useUnicode=true&characterEncoding=UTF-8";

	public Connection getContion() {
		Connection conn = null;
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(URL, User, PSSSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void closeDB(Connection con, PreparedStatement stmt, ResultSet rs) {

		try {
			if (rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			if(con!=null) {
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
