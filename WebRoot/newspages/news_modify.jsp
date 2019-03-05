<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<jsp:include page="console_element/top.jsp" />

<script type="text/javascript">
	function check() {
		var ntitle = document.getElementById("ntitle");
		var nauthor = document.getElementById("nauthor");
		var nsummary = document.getElementById("nsummary");
		var ncontent = document.getElementById("ncontent");

		if (ntitle.value == "") {
			alert("标题不能为空！！");
			ntitle.focus();
			return false;
		} else if (nauthor.value == "") {
			alert("作者不能为空！！");
			nauthor.focus();
			return false;
		} else if (nsummary.value == "") {
			alert("摘要不能为空！！");
			nsummary.focus();
			return false;
		} else if (ncontent.value == "") {
			alert("内容不能为空！！");
			ncontent.focus();
			return false;
		}
		return true;
	}
</script>


<div id="main">
	<jsp:include page="console_element/left.html" />
	<div id="opt_area">
		<h1 id="opt_type">添加新闻：</h1>
		<form action="../util/do_update_news.jsp" method="post"
			enctype="multipart/form-data" onsubmit="return check()">
			<p>
				<label> 主题 </label> 
				<select name="ntid">
					 <!-- 迭代输出主题 -->
						<!-- 当前项判断 -->
							<option value='tid' selected="selected">tname</option>
						<!-- 当前项判断 -->
						<!-- 其余option -->
						<option value='tid'>tname</option>
						<!-- 其余option -->
					 <!-- 迭代输出主题 -->
				</select> 
				<input type="hidden" name="nid" value="nid" />
			</p>
			<p>
				<label> 标题 </label> <input name="ntitle" type="text"
					class="opt_input" value="ntitle" />
			</p>
			<p>
				<label> 作者 </label> <input name="nauthor" type="text"
					class="opt_input" value="nauthor" />
			</p>
			<p>
				<label> 摘要 </label>
				<textarea name="nsummary" cols="40" rows="3">nsummary</textarea>
			</p>
			<p>
				<label> 内容 </label>
				<textarea name="ncontent" cols="70" rows="10">ncontent</textarea>
			</p>
			<p>
				<label> 上传图片 </label> <input name="file" type="file"
					class="opt_input" />
			</p>
			<input type="submit" value="提交" class="opt_sub" /> <input
				type="reset" value="重置" class="opt_sub" />
		</form>
		<h1 id="opt_type">修改新闻评论：</h1>
		<table width="80%" align="left">
			
			<!-- 判断： 无评论 -->
				<td colspan="6">暂无评论！</td>
				<tr>
					<td colspan="6"><hr /></td>
				</tr>
			<!-- 判断： 无评论 -->
			
			<!-- 判断： 有评论 -->
				<!-- 循环输出评论 -->
					<tr>
						<td>留言人：</td>
						<td>cauthor</td>
						<td>IP：</td>
						<td>cip</td>
						<td>留言时间：</td>
						<td>cdate</td>
						<td><a
							href="../util/do_delete_comments.jsp?cid=1&cnid=1">删除</a></td>
					</tr>
					<tr>
						<td colspan="6">ccontent</td>
					</tr>
					<tr>
						<td colspan="6"><hr /></td>
					</tr>
				<!-- 循环输出评论 -->
		   <!-- 判断： 有评论 -->
		</table>
	</div>
</div>

<jsp:include page="console_element/bottom.html" />
