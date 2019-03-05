package cn.web.news.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.web.news.dao.NewsDao;
import cn.web.news.dao.UserDao;
import cn.web.news.entity.News;
import cn.web.news.entity.Topic;
import cn.web.news.entity.User;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if (action.equals("user")) {
			doLogin(request, response);
		}else if (action.equals("logout")) {
			request.getSession().invalidate();
			response.sendRedirect("NewsServlet?action=newList");
		}
		
		
	}

	public void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NewsDao newsdao=new NewsDao();
		String username= request.getParameter("uname");
		String password = request.getParameter("upwd");
		UserDao userdao = new UserDao();
		User user = userdao.findUser(username,password);
		if(user!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("admin", user.getUname());
			response.sendRedirect(request.getContextPath()+"/NewsServlet?action=newList");
		}else {
			response.sendRedirect(request.getContextPath()+"/NewsServlet?action=newList");
		}
	}

}
