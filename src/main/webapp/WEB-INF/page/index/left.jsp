<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 去掉超链接下划线 -->
<style> 
a{text-decoration:none} 
a:hover{text-decoration:none} 
</style> 
</head>
<body>

	<h1><a href="<%=request.getContextPath()%>/user/toShow" target="right">用户展示</a></h1>
	<h1><a href="<%=request.getContextPath()%>/truck/toList" target="right">车位管理</a></h1>
</body>
</html>
