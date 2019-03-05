<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="index-elements/index_top.jsp" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<link href="CSS/read.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	function checkComment() {
		var cauthor = document.getElementById("cauthor");
		var content = document.getElementById("ccontent");
		if (cauthor.value == "") {
			alert("用户名不能为空！！");
			return false;
		} else if (content.value == "") {
			alert("评论内容不能为空！！");
			return false;
		}
		return true;
	}  
</script>


<div id="container">
	<jsp:include page="index-elements/index_sidebar.jsp"></jsp:include>
	<div class="main">
		<div class="class_type">
			<img src="Images/class_type.gif" alt="新闻中心" />
		</div>
		<div class="content">
			<ul class="classlist">
				<table width="80%" align="center">
					<tr width="100%">
						<td colspan="2" align="center">
							<!-- news title -->
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<hr />
						</td>
					</tr>
					<tr>
						<td align="center">作者:${newsDetial.nauthor }
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							类型：${newsDetial.tname } <a href="index.jsp?tid=1"> <!-- tname -->
						</a>
						</td>
						<td align="left">发布时间: ${newsDetial.ncreatedate}<!-- ncreatedate -->
						</td>
					</tr>
					<tr>
						<td align="right"><strong>摘要：${newsDetial.nsummary }<!-- nsummary --></strong>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center"></td>
					</tr>
					<tr>
						<td colspan="2">${newsDetial.ncontent }<!-- ncontent -->
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<hr />
						</td>
					</tr>
				</table>
			</ul>
			<ul class="classlist">

				<table width="80%" align="center" id="commontable">
					<c:if test="${fn:length(comList)==0}">
						<!--  暂无评论！ -->
						<td colspan="6">暂无评论！</td>
						<tr>
							<td colspan="6">
								<hr />
							</td>
						</tr>
					</c:if>
					<c:forEach var="comList" items="${comList}">

						<!--  -->
						<!-- 有评论 -->
						<!-- 遍历评论列表 -->
						<tr>
							<td>留言人：</td>
							<td>${comList.cauthor}</td>
							<td>IP：</td>
							<td>${comList.cip}</td>
							<td>留言时间：</td>
							<td>${comList.cdate}</td>
						</tr>
						<tr>
							<td colspan="6">${comList.ccontent}</td>
						</tr>
						<tr>
							<td colspan="6">
								<hr />
							</td>
						</tr>

					</c:forEach>
					<!-- 遍历评论列表 -->
					<!-- 有评论 -->
				</table>
			</ul>
			<ul class="classlist">
				<form>
					<table width="80%" align="center">
						<tr>
							<td>评 论</td>
						</tr>
						<tr>
							<td>用户名：</td>
							<td><input type="hidden" name="nid" id="nid"
								value="${newsDetial.nid }"> <input id="cauthor"
								name="cauthor" value="这家伙很懒什么也没留下" /> IP： <input name="cip"
								value="${ip}" readonly="readonly" id="cip" /></td>
						</tr>
						<tr>
							<td colspan="2"><textarea name="ccontent" cols="70"
									id="ccontent" rows="10"></textarea></td>
						</tr>
						<td><input id="button" value="发  表" type="button" onclick="ajaxDemo()" /></td>
					</table>
				</form>
			</ul>
		</div>
	</div>
</div>
<script type="text/javascript">
function ajaxDemo(){
	//alert("123123")
	
	$.ajax({
		url:'NewsServlet?action=addCommonajax',
		type:'post',
		data:{
			nid:$('#nid').val(),
			cauthor:$('#cauthor').val(),
			cip:$('#cip').val(),
			ccontent:$('#ccontent').val(),
		},
		dataType:'json',
		success:function(result){
			//alert(2222222)
			$("#commontable").empty();
			$.each(result,function(i,comments){
				var row=$("<tr><td>留言人：</td><td>"+comments.cauthor+"</td><td>IP：</td><td>"+comments.cip+"</td><td>留言时间：</td><td>"+comments.cdate+"</td></tr><tr><td colspan='6'>"+comments.ccontent+"</td></tr><tr><td colspan='6'><hr/></td></tr>");
				$("#commontable").append($(row));
			});
		}
	});
}
</script>

<%
	request.removeAttribute("news_view");
	request.removeAttribute("comments_view");
%>
<jsp:include page="index-elements/index_bottom.html" />
