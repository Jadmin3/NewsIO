<%@ page language="java" import="java.util.*,java.sql.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<jsp:include page="console_element/top.jsp" />
<script language="javascript">
	function clickdel() {
		return confirm("删除请点击确认");
	}
</script>


<div id="main">
	<jsp:include page="console_element/left.html" />
	<div id="opt_area">
		<ul class="classlist">

			<!-- 遍历   news list -->
			<li class='space'><strong>${news.tname}</strong></li>
			<li>title<span> 作者：nauthor&#160;&#160;&#160;&#160; <a
					href='news_modify.jsp?nid=1'>修改</a> &#160;&#160;&#160;&#160; <a
					href='../util/do_delete_news.jsp?nid=1'
					onclick='return clickdel()'>删除</a>
			</span>
			</li>
			<!-- 遍历   news list -->

			<!-- 分页处理 -->
			<p align="right">
				当前页数:[ / ]&nbsp;&nbsp; <a href="admin.jsp?page_no=1">首页</a><a
					href="admin.jsp?page_no=1">&nbsp;&nbsp;上一页</a><a
					href="admin.jsp?page_no=1">&nbsp;&nbsp;下一页</a> <a
					href="admin.jsp?page_no=1">&nbsp;&nbsp;末页</a>
			</p>
		</ul>
	</div>
</div>

<jsp:include page="console_element/bottom.html" />
