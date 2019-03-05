<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="sidebar">
    <h1> <img src="Images/title_1.gif" alt="国内新闻" /> </h1>
    <div class="side_list">
      <ul>
        <!-- 国内新闻 -->
        <c:forEach var="news" items="${news}">
     		<li> <a href='NewsServlet?action=comment&nid=${news.nid }'><b> ${fn:substring(news.ntitle,1,12)}... </b></a> </li>
     		</c:forEach>
      	<!-- 国内新闻 -->
      </ul>
    </div>
    <h1> <img src="Images/title_2.gif" alt="国际新闻" /> </h1>
    <div class="side_list">

        <!-- 国际新闻 -->
        <c:forEach var="news2" items="${news2 }">
     		<li> <a href='NewsServlet?action=comment&nid=${news2.nid }'><b>${fn:substring(news2.ntitle,1,12)}...</b></a> </li>
		<!-- 国际新闻 -->
		</c:forEach>
      </ul>
    </div>
    <h1> <img src="Images/title_3.gif" alt="娱乐新闻" /> </h1>
    <div class="side_list">
      <ul>
      <c:forEach var="news5" items="${news5 }">
         <!-- 娱乐新闻 -->
     		<li> <a href='NewsServlet?action=comment&nid=${news5.nid }'><b>${fn:substring(news5.ntitle,1,12)}...</b></a> </li>
      	 <!-- 娱乐新闻 -->
      	 </c:forEach>
      </ul>
    </div>
  </div>

