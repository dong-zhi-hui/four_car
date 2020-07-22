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
		 $.post("<%=request.getContextPath()%>/truck/update",
				 $("#fm").serialize(),
				 function (data) {
					 layer.close(index);
					 if(data.code != 200){
						 layer.msg(data.msg,{icon:2});
						 return;
					 }
					 if(data.code == 200){
						 layer.msg(data.msg,{icon:1},function(){
							 parent.window.location.href="<%=request.getContextPath()%>/truck/toList"
						 });
					 }
				 })
	 }

</script>
</head>
<body>
<form id="fm" >
         <input type="hidden" name="id" value="${truckSpace.id}"/>
	车位编号:<input type="text" name="userName" value="${truckSpace.carNumber}"/><br />
	车位价格:<input type="text" name="userPwd" value="${truckSpace.price}"/><br />
	车位状态<input type="radio" name="carStatus" value="0" <c:if test="${truckSpace.carStatus == 0}">checked</c:if>>空置
	<input type="radio" name="carStatus" value="1" <c:if test="${truckSpace.carStatus == 1}">checked</c:if>>已预约<br />
	车位等级<input type="radio" name="carLevel" value="0" <c:if test="${truckSpace.carLevel == 0}">checked</c:if>>普通车位
	<input type="radio" name="carLevel" value="1" <c:if test="${truckSpace.carLevel == 1}">checked</c:if>>会员车位<br />
	<input type="button" value="修改" onclick="upd()"/>
</form>
</body>
</html>