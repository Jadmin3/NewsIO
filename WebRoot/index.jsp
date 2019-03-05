<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="index-elements/index_top.jsp"></jsp:include>
<div id="container">
	<jsp:include page="index-elements/index_sidebar.jsp" />
	<div class="main">
		<div class="class_type">
			<img src="Images/class_type.gif" alt="新闻中心" />
		</div>
		<div class="content">
			<ul class="class_date">
				<!--遍历 显示topic list -->
				<c:forEach var="listTopic" items="${listTopic }">
				<li id='class_month' style="float:left"><a href="NewsServlet?action=findnewList&tid=${listTopic.tid }"><b>${listTopic.tname}</b> </a> <!--遍历 显示topic list -->
				</c:forEach>
			</ul>
			
			<ul class="classlist">
			<hr>
				<!-- 遍历显示 news list  -->
				<c:forEach var="limit" items="${limit}">
				<li><a href="NewsServlet?action=comment&nid=${limit.nid }">${limit.ntitle } </a> <span>${limit.ncreatedate }</span></li>
				<!-- 遍历显示 news list  -->
				</c:forEach>
				<!-- 分页显示 -->
				<p align="right">
					当前页数:[   ${pageNo}/${last}  ]&nbsp;&nbsp; <a
						href="NewsServlet?action=newList&pageNo=1">首页</a><a
						href="NewsServlet?action=newList&pageNo=${prev}">&nbsp;&nbsp;上一页</a><a
						href="NewsServlet?action=newList&pageNo=${next}">&nbsp;&nbsp;下一页</a> <a
						href="NewsServlet?action=newList&pageNo=${last}">&nbsp;&nbsp;末页</a>
				</p>
			</ul>
		</div>
		<jsp:include page="index-elements/index_rightbar.html" />
	</div>
</div>

<jsp:include page="index-elements/index_bottom.html" />
