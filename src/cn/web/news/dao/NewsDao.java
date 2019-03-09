package cn.web.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.web.news.entity.Comment;
import cn.web.news.entity.News;
import cn.web.news.entity.NewsDetial;
import cn.web.news.entity.Topic;

/**
 * 
 * 
 * @author Administrator
 *
 */
public class NewsDao extends BaseDao {
	// 查新闻
	public List<News> findNews() {
		String sql = "select * from news";
		Connection conn = super.getContion();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<News> list = new ArrayList<>();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				News news = new News();
				news.setNid(rs.getInt("nid"));
				news.setNtid(rs.getInt("ntid"));
				news.setNtitle(rs.getString("ntitle"));
				news.setNauthor(rs.getString("nauthor"));
				news.setNcreatedate(rs.getString("ncreatedate"));
				news.setNpicpath(rs.getString("npicpath"));
				news.setNcontent(rs.getString("ncontent"));
				news.setNmodifydate(rs.getString("nmodifydate"));
				news.setNsummary(rs.getString("nsummary"));
				list.add(news);
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
	 * 查询新闻内容
	 * 
	 * @return  NewsDetial
	 */
	public NewsDetial findContent(int nid) {
		String sql = "select news.*,topic.tname from news,topic WHERE news.ntid = topic.tid and news.nid=?";
		Connection conn = super.getContion();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<NewsDetial> list = new ArrayList<>();
		NewsDetial news = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, nid);
			rs = stmt.executeQuery();
			while (rs.next()) {
				news = new NewsDetial();
				news.setNid(rs.getInt("nid"));
				news.setNtid(rs.getInt("ntid"));
				news.setNtitle(rs.getString("ntitle"));
				news.setNauthor(rs.getString("nauthor"));
				news.setNcreatedate(rs.getString("ncreatedate"));
				news.setNpicpath(rs.getString("npicpath"));
				news.setNcontent(rs.getString("ncontent"));
				news.setNmodifydate(rs.getString("nmodifydate"));
				news.setNsummary(rs.getString("nsummary"));
				news.setTname(rs.getString("tname"));
				list.add(news);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			super.closeDB(conn, stmt, rs);
		}

		return news;

	}

	/**
	 * 查询国内新闻
	 * @param nid
	 * 
	 */
	public List<News> findCnNews(int ntid) {
		String sql = "SELECT * FROM news WHERE ntid =?";
		Connection conn = super.getContion();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<News> list = new ArrayList<>();
		NewsDetial news = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ntid);
			rs = stmt.executeQuery();
			while (rs.next()) {
				news = new NewsDetial();
				news.setNid(rs.getInt("nid"));
				news.setNtid(rs.getInt("ntid"));
				news.setNtitle(rs.getString("ntitle"));
				news.setNauthor(rs.getString("nauthor"));
				news.setNcreatedate(rs.getString("ncreatedate"));
				news.setNpicpath(rs.getString("npicpath"));
				news.setNcontent(rs.getString("ncontent"));
				news.setNmodifydate(rs.getString("nmodifydate"));
				news.setNsummary(rs.getString("nsummary"));
				list.add(news);
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
	 * 查询评论内容
	 * 
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

	// 查询标题
	public List<Topic> findTopic() {
		String sql = "select * from topic";
		Connection conn = super.getContion();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Topic> list = new ArrayList<>();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Topic topic = new Topic();
				topic.setTid(rs.getInt("tid"));
				topic.setTname(rs.getString("tname"));
				list.add(topic);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			super.closeDB(conn, stmt, rs);
		}

		return list;

	}

	// 分页查询
	public List<News> AllByLimit(int pageNo, int pageSize) {
		String sql = "SELECT *FROM news LIMIT ?,?";
		Connection conn = super.getContion();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<News> list = new ArrayList<>();
		try {
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, (pageNo - 1) * pageSize);
			stmt.setInt(2, pageSize);
			rs = stmt.executeQuery();
			while (rs.next()) {
				News news = new News();
				news.setNid(rs.getInt("nid"));
				news.setNtid(rs.getInt("ntid"));
				news.setNtitle(rs.getString("ntitle"));
				news.setNauthor(rs.getString("nauthor"));
				news.setNcreatedate(rs.getString("ncreatedate"));
				news.setNpicpath(rs.getString("npicpath"));
				news.setNcontent(rs.getString("ncontent"));
				news.setNmodifydate(rs.getString("nmodifydate"));
				news.setNsummary(rs.getString("nsummary"));
				list.add(news);
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
	 * 
	 * @param 首页导航分页查询
	 * @param pageSize
	 * @return
	 */
	public List<News> indexFindByLimit(int ntid,int pageNo, int pageSize) {
		String sql = "SELECT * FROM news WHERE ntid=?  LIMIT ?,?";
		Connection conn = super.getContion();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<News> list = new ArrayList<>();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ntid);
			stmt.setInt(2, (pageNo - 1) * pageSize);
			stmt.setInt(3, pageSize);
			rs = stmt.executeQuery();
			while (rs.next()) {
				News news = new News();
				news.setNid(rs.getInt("nid"));
				news.setNtid(rs.getInt("ntid"));
				news.setNtitle(rs.getString("ntitle"));
				news.setNauthor(rs.getString("nauthor"));
				news.setNcreatedate(rs.getString("ncreatedate"));
				news.setNpicpath(rs.getString("npicpath"));
				news.setNcontent(rs.getString("ncontent"));
				news.setNmodifydate(rs.getString("nmodifydate"));
				news.setNsummary(rs.getString("nsummary"));
				list.add(news);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			super.closeDB(conn, stmt, rs);
		}

		return list;

	}
	

	// 查总数
	public int AllCount() {
		String sql = "SELECT count(*) FROM news";
		Connection conn = super.getContion();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int i = 0;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			rs.next();
			i = rs.getInt(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			super.closeDB(conn, stmt, rs);
		}

		return i;

	}
	
	/**
	 * 国内新闻总数
	 * @return
	 */
	public int NewsCountByID(int tid) {
		String sql = "SELECT COUNT(ntid) FROM news WHERE ntid =?";
		Connection conn = super.getContion();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int i = 0;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, tid);
			rs = stmt.executeQuery();
			rs.next();
			i = rs.getInt(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			super.closeDB(conn, stmt, rs);
		}

		return i;

	}
	/**
	 * 添加新闻
	 * @param news
	 * @return
	 */
	public int addNews(News news) {
		String sql = "INSERT INTO news (ntid,ntitle,nauthor,ncreatedate,npicpath,ncontent,nsummary)VALUES(?,?,?,NOW(),?,?,?)";
		Connection connection = super.getContion();
		PreparedStatement statement = null;
		int row = 0;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, news.getNtid());
			statement.setString(2, news.getNtitle());
			statement.setString(3,news.getNauthor());
			statement.setString(4, news.getNpicpath());
			statement.setString(5, news.getNcontent());
			statement.setString(6, news.getNsummary());
			row = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
		
	}
	
	
	/**
	 * 添加主题
	 * @param news
	 * @return int类型受影响的行
	 */
	public int addTopic(String topic) {
		String sql = "INSERT INTO topic (tname) VALUES(?)";
		Connection connection = super.getContion();
		PreparedStatement statement = null;
		int row = 0;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, topic);
			row = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
		
	}
	
	/**
	 * 修改新闻
	 * @param news
	 * @return
	 */
	public int updateNews(News news) {
		String sql ="UPDATE news SET ntid = ?,ntitle=?,nauthor=?,nmodifydate=NOW(),npicpath=?,ncontent=?,nsummary=? WHERE nid = ?";
		Connection connection = super.getContion();
		PreparedStatement statement = null;
		int row = 0;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, news.getNtid());
			statement.setString(2, news.getNtitle());
			statement.setString(3,news.getNauthor());
			statement.setString(4, news.getNpicpath());
			statement.setString(5, news.getNcontent());
			statement.setString(6, news.getNsummary());
			statement.setInt(7, news.getNid());
			row = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
		
	}
	
	/**
	 * 有误的方法
	 * @param nid
	 * @return
	 */
	public List<NewsDetial> findNewsInfo(int nid){
		String sql="SELECT * from news, comments WHERE nid ="+nid+" and news.nid = comments.cnid";
		Connection connection = super.getContion();
		PreparedStatement statement =null;
		ResultSet rs = null;
		List<NewsDetial> list = new ArrayList<>();
		try {
			statement = connection.prepareStatement(sql);
			//statement.setInt(1, nid);
			rs = statement.executeQuery();
			while (rs.next()) {
				NewsDetial detial = new NewsDetial();
				detial.setNid(rs.getInt("nid"));
				detial.setNtid(rs.getInt("ntid"));
				detial.setNtitle(rs.getString("ntitle"));
				detial.setNauthor(rs.getString("nauthor"));
				detial.setNcreatedate(rs.getString("ncreatedate"));
				detial.setNcontent(rs.getString("ncontent"));
				detial.setNsummary(rs.getString("nsummary"));
				detial.setCid(rs.getInt("cid"));
				detial.setCnid(rs.getInt("cnid"));
				detial.setCcontent(rs.getString("ccontent"));
				detial.setCdate(rs.getString("cdate"));
				detial.setCip(rs.getString("cip"));
				detial.setCauthor(rs.getString("cauthor"));
				list.add(detial);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	/**
	 * 删除新闻
	 * @param nid
	 * @return  int类型的影响行数
	 */
	public int delNews(int nid) {
		String sql = "DELETE FROM news WHERE nid =?";
		Connection connection = super.getContion();
		PreparedStatement statement = null;
		int row = 0;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, nid);
			row = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return row;
		
	}
	
	/**
	 * 删除评论 
	 * @param nid
	 * @return  int类型的影响行数
	 */
	public int delComments(int cid) {
		String sql = "DELETE FROM comments WHERE cid = ?";
		Connection connection = super.getContion();
		PreparedStatement statement = null;
		int row = 0;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, cid);
			row = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return row;
		
	}

}

