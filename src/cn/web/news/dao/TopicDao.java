package cn.web.news.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import cn.web.news.entity.News;
import cn.web.news.entity.Topic;

public class TopicDao extends BaseDao {

	public List<Topic> findTopicAndNewsList(int pageNo, int pageSize) {
		String sql = "SELECT * from topic t LEFT OUTER JOIN news n ON t.tid=n.ntid ORDER BY t.tid LIMIT ?,?";

		List<Topic> list = new ArrayList<Topic>();
		Connection con = super.getContion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, (pageNo - 1) * pageSize);
			ps.setInt(2, pageSize);

			rs = ps.executeQuery();
			// topic--> List<News>
			int tid = 0; // 中间比较数
			Topic topic = null;
			while (rs.next()) {

				if (rs.getInt("tid") != tid) { // 判断是否是同一个主题的ID，同一个ID，仅创建一个Topic对象
					// 创建topic对象
					topic = new Topic();
					topic.setTid(rs.getInt("tid"));
					topic.setTname(rs.getString("tname"));
					list.add(topic);
				}

				// 封装新闻数据?
				if (rs.getString("nid") != null) {
					News news = new News();
					news.setNid(rs.getInt("nid"));
					news.setNtid(rs.getInt("ntid"));
					news.setNtitle(rs.getString("ntitle"));
					news.setNauthor(rs.getString("nauthor"));
					news.setNcreatedate(rs.getString("ncreatedate"));
					news.setNcontent(rs.getString("ncontent"));
					news.setNsummary(rs.getString("nsummary"));
					topic.getNewsList().add(news);
				}
				tid = rs.getInt("tid"); // 更新中间比较数
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(con, ps, rs);
		}
		return list;
	}

	public List<Topic> findTopicLimit(int pageNo, int pageSize) {
		String sql = "SELECT * from topic  LIMIT ?,?";

		List<Topic> list = new ArrayList<Topic>();
		Connection con = super.getContion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, (pageNo - 1) * pageSize);
			ps.setInt(2, pageSize);

			rs = ps.executeQuery();
			
			while (rs.next()) {
				Topic topic = new Topic();
				topic.setTid(rs.getInt("tid"));
				topic.setTname(rs.getString("tname"));
				list.add(topic);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(con, ps, rs);
		}
		return list;
	}

	public int getTopicCount() {

		String sql = "SELECT count(*) from topic t LEFT OUTER JOIN news n ON t.tid=n.ntid";

		Connection con = super.getContion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(con, pstmt, rs);
			;
		}
		return count;
	}

	public List<Topic> getAllTopics() {
		String sql = "select * from topic";
		List<Topic> list = new ArrayList<Topic>();
		Connection con = super.getContion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Topic topic = new Topic();
				topic.setTid(rs.getInt("tid"));
				topic.setTname(rs.getString("tname"));
				list.add(topic);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(con, ps, rs);
		}
		return list;
	}
	
	public boolean getTopics(String tname) {
		String sql = "select * from topic where tname =?";
		List<Topic> list = new ArrayList<Topic>();
		Connection con = super.getContion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, tname);
			rs = ps.executeQuery();
			if (rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(con, ps, rs);
		}
		return flag;
	}

	public Topic getTopicById(int tid) {
		String sql = "select * from topic where tid=?";

		Connection con = super.getContion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Topic topic = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, tid);

			rs = ps.executeQuery();
			if (rs.next()) {
				topic = new Topic();
				topic.setTid(rs.getInt("tid"));
				topic.setTname(rs.getString("tname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(con, ps, rs);
		}
		return topic;
	}

	public List<Topic> getTopicsByPager(int pageNo, int pageSize) {
		String sql = "select * from topic limit ?,?";
		List<Topic> list = new ArrayList<Topic>();
		Connection con = super.getContion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, (pageNo - 1) * pageSize);
			ps.setInt(2, pageSize);

			rs = ps.executeQuery();

			while (rs.next()) {
				Topic topic = new Topic();
				topic.setTid(rs.getInt("tid"));
				topic.setTname(rs.getString("tname"));
				list.add(topic);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(con, ps, rs);
		}
		return list;
	}

	public int getAllTopicsCount() {
		String sql = "select count(*) from topic";

		Connection con = super.getContion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(con, ps, rs);
		}
		return count;
	}

	public int addTopic(Topic topic) {
		String sql = "insert into topic(tname) values(?)";

		Connection con = super.getContion();
		PreparedStatement pstmt = null;
		int row = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, topic.getTname());

			row = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(con, pstmt, null);
		}
		return row;
	}

	public int deleteTopic(int tid) {
		String sql = "delete from topic where tid=?";

		Connection con = super.getContion();
		PreparedStatement pstmt = null;
		int row = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, tid);

			row = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(con, pstmt, null);
		}
		return row;
	}

	public int updateTopic(Topic topic) {
		String sql = "update topic set tname=? where tid=?";
		Connection con = super.getContion();
		PreparedStatement pstmt = null;
		int row = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, topic.getTname());
			pstmt.setInt(2, topic.getTid());

			row = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(con, pstmt, null);
		}
		return row;
	}
}
