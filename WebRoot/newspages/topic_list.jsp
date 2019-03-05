<%@ page language="java" import="java.util.*,java.sql.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<jsp:include page="console_element/top.jsp" />

<script language="javascript">
	function clickdel() {
		return confirm("删除后，主题下的文章也会删除，确认删除吗？");
	}
</script>


<div id="main">
	<jsp:include page="console_element/left.html" />
	<div id="opt_area">
		<ul class="classlist">
			<!-- 遍历  topic list -->
			<li>&#160;&#160;&#160;&#160; tname&#160;&#160;&#160;&#160; <a
				href='topic_modify.jsp?tid=1&tname=tname'>修改</a>
				&#160;&#160;&#160;&#160; <a
				href='../util/do_delete_topics.jsp?tid=1'
				onclick='return clickdel()'>删除</a>
			</li>
			<!-- 遍历  topic list -->
			
			<!-- 显示分页 -->
			<p align="right">
				当前页数:[${page_no}/${total_page }]&nbsp;&nbsp; <a
					href="topic_list.jsp?page_no=1">首页</a><a
					href="topic_list.jsp?page_no=${page_no-1}">&nbsp;&nbsp;上一页</a><a
					href="topic_list.jsp?page_no=${page_no+1}">&nbsp;&nbsp;下一页</a> <a
					href="topic_list.jsp?page_no=${total_page }">&nbsp;&nbsp;末页</a>
			</p>
		</ul>
	</div>
</div>


<jsp:include page="console_element/bottom.html" />
