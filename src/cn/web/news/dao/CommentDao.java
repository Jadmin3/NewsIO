package cn.web.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.web.news.entity.Comment;

public class CommentDao extends BaseDao {
	/**
	 * 查询评论内容
	 * 在把评论内容放入作用域当中
	 * @return
	 */
	public List<Comment> findComment(int nid) {
		String sql = "SELECT * FROM comments WHERE cnid =?";
		Connection conn = super.getContion();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Comment> list = new ArrayList<>();

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, nid);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Comment com = new Comment();
				com.setCauthor(rs.getString("cauthor"));
				com.setCcontent(rs.getString("ccontent"));
				com.setCdate(rs.getString("cdate"));
				com.setCid(rs.getInt("cid"));
				com.setCip(rs.getString("cip"));
				com.setCnid(rs.getInt("cnid"));
				list.add(com);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			super.closeDB(conn, stmt, rs);
		}

		return list;

	}

	/**
	 * 添加评论
	 */

	public int addComment(Comment comm) {
		String sql = "INSERT INTO comments(cnid,ccontent,cdate,cip,cauthor) VALUES (?,?,NOW(),?,?);";
		Connection conn = super.getContion();
		PreparedStatement stmt = null;
		int temp = 0;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, comm.getCnid());
			stmt.setString(2, comm.getCcontent());
			stmt.setString(3, comm.getCip());
			stmt.setString(4, comm.getCauthor());
			temp = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;

	}

}
