<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui-v2.5.6/layui/css/layui.css"  media="all">
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src = "<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
<script type="text/javascript">

	 function upd() {
		 var index = layer.load(1,{shade:0.5});
		 $.post("<%=request.getContextPath()%>/message/update",
				 $("#fm").serialize(),
				 function (data) {
					 layer.close(index);
					 if(data.code != 200){
						 layer.msg(data.msg,{icon:2});
						 return;
					 }
					 if(data.code == 200){
						 layer.msg(data.msg,{icon:1},function(){
							 parent.window.location.href="<%=request.getContextPath()%>/message/toShow"
						 });
					 }
				 })
	 }

</script>
</head>
<body>
<form id="fm" >
         <input type="hidden" name="id" value="${message.id}"/>
	留言内容:${message.messageContents} <br />
	留言时间:${message.createTime} <br />
	留言人:${message.userName} <br />
	回复内容:<input type="text" name="response" value="${message.response}"/> <br />
	<input type="button" value="回复" onclick="upd()" class="layui-btn"/>
</form>
</body>
</html>