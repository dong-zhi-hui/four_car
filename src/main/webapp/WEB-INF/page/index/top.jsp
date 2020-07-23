<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style> 
a{text-decoration:none} 
a:hover{text-decoration:none} 
</style> 
</head>
<body style="color: pink">
	<center>
	<h1>欢迎${user.userName}登录</h1>
	</center>
	<a href= "<%=request.getContextPath()%>/index/toEsc">退出登录</a>
	<div id="datetime" align="right" style="color:red">
	<script>
 		setInterval("document.getElementById('datetime').innerHTML=new Date().toLocaleString();", 1000);
	</script>
	</div>
</body>
</html>