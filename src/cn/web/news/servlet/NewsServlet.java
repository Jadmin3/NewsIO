package cn.web.news.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.alibaba.fastjson.JSON;

import cn.web.news.dao.CommentDao;
import cn.web.news.dao.NewsDao;
import cn.web.news.entity.Comment;
import cn.web.news.entity.News;
import cn.web.news.entity.NewsDetial;
import cn.web.news.entity.Topic;

/**
 * Servlet implementation class NewsServlet
 */
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final int pageSize = 15;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		NewsDao newsdao=new NewsDao();
		List<News> news =newsdao.findCnNews(1);
		List<News> news2 =newsdao.findCnNews(2);
		List<News> news5 =newsdao.findCnNews(5);
		request.setAttribute("news", news);
		request.setAttribute("news2", news2);
		request.setAttribute("news5", news5);
		String action = request.getParameter("action");
		
		if (action.equals("newList")) {
			doNewList(request, response);
		} else if (action.equals("comment")) {
			doComment(request, response);
		} else if (action.equals("addCommon")) {
			doAddCommon(request, response);
		}else if (action.equals("findnewList")) {
				seleteNews(request,response);
		}else if (action.equals("addCommonajax")) {
			addCommonajax(request,response);
		}

	}

	private void addCommonajax(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int cnid = Integer.parseInt(request.getParameter("nid"));
		String cauthor = request.getParameter("cauthor");
		String cip = request.getParameter("cip");
		String ccontent = request.getParameter("ccontent");
		
		CommentDao comdao = new CommentDao();
		Comment comm = new Comment(cnid, ccontent, cip, cauthor);
		int temp = comdao.addComment(comm);
		List<Comment> comList = comdao.findComment(cnid);
		PrintWriter out = response.getWriter();
		String json = JSON.toJSONString(comList);
		System.out.println(json);
		out.write(json);
		out.flush();
		out.close();
	}

	private void seleteNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("tid"));
		NewsDao newsdao= new NewsDao();
		List<News> limit = newsdao.findCnNews(id);
		int pageNo=1;
		newsdao.indexFindByLimit(id, pageNo, pageSize);
		//id查询的新闻总数
		int count = newsdao.NewsCountByID(id);
		
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		// 尾页
		int last = (count % pageSize == 0) ? count / pageSize : count / pageSize + 1;
		// List<News> list = newsdao.findNews();
		// 分页数据
		int prev = pageNo;
		int next = pageNo;
		if (pageNo <= 1) {
			pageNo = 1;
			next++;
		} else if (pageNo >= last) {
			prev--;
			next = last;
		} else {
			prev--;
			next++;
		}
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);
		request.setAttribute("last", last);
		
		List<Topic> listTopic = newsdao.findTopic();
		request.setAttribute("listTopic", listTopic);
		request.setAttribute("limit", limit);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	private void doAddCommon(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int cnid = Integer.parseInt(request.getParameter("nid"));
		String cauthor = request.getParameter("cauthor");
		String cip = request.getParameter("cip");
		String ccontent = request.getParameter("ccontent");
		CommentDao comdao = new CommentDao();
		Comment comm = new Comment(cnid, ccontent, cip, cauthor);
		
		
		int temp = comdao.addComment(comm);
		if (temp > 0) {
			request.getRequestDispatcher("NewsServlet?action=comment&nid=" + cnid + "").forward(request, response);
		}
	}

	private void doComment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ip = request.getRemoteAddr();
		int nid = Integer.parseInt(request.getParameter("nid"));
		NewsDao newsdao = new NewsDao();
		CommentDao cmdao = new CommentDao();
		List<Comment> comList = cmdao.findComment(nid);
		NewsDetial newsDetial = newsdao.findContent(nid);
		request.setAttribute("ip", ip);
		request.setAttribute("newsDetial", newsDetial);
		request.setAttribute("comList", comList);
		request.getRequestDispatcher("news_ajax_reader.jsp").forward(request, response);
	}

	private void doNewList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NewsDao newsdao = new NewsDao();
		// 定义一个当前页
		int pageNo = 1;
		// 返回的总记录条数
		int allCount = newsdao.AllCount();
		// 首页
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		// 尾页
		int last = (allCount % pageSize == 0) ? allCount / pageSize : allCount / pageSize + 1;
		// List<News> list = newsdao.findNews();
		// 分页数据
		int prev = pageNo;
		int next = pageNo;
		if (pageNo <= 1) {
			pageNo = 1;
			next++;
		} else if (pageNo >= last) {
			prev--;
			next = last;
		} else {
			prev--;
			next++;
		}
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);
		request.setAttribute("last", last);

		List<News> limit = newsdao.AllByLimit(pageNo, pageSize);
		//查找标题
		List<Topic> listTopic = newsdao.findTopic();
		//查找国内新闻
		
		request.setAttribute("listTopic", listTopic);
		request.setAttribute("limit", limit);
		// request.setAttribute("newsList", list);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
}
