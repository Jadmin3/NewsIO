<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>新闻发布系统管理后台</title>
<link href="../CSS/admin.css" rel="stylesheet" type="text/css" />
<div id="header">
  <div id="welcome">欢迎使用新闻管理系统！</div>
  <div id="nav">
    <div id="logo"><img src="../Images/logo.jpg" alt="新闻中国" /></div>
    <div id="a_b01"><img src="../Images/a_b01.gif" alt="" /></div>
  </div>
</div>
<div id="admin_bar">
  <div id="status">管理员：${admin} 登录  &#160;&#160;&#160;&#160; <a href="${pageContext.request.contextPath}/UserServlet?action=logout">login out</a></div>
  <div id="channel"> </div>
</div>
</head>
<body>
