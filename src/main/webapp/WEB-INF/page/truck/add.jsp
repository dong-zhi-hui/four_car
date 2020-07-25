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
	<script type="text/javascript" src = "<%=request.getContextPath()%>/static/jquery-validation-1.14.0/dist/jquery.validate.js"></script>
<script type="text/javascript">

	 $(function () {
		 $("#fm").validate({
			 rules:{
				 carNumber:{
					 required:true,
					 remote: {
						 url: "<%=request.getContextPath()%>/truck/findCarNumber",
						 type: "post",
						 data: {
							 carNumber: function() {
								 return $("#carNumber").val();
							 },

						 },
						 dataType: "json",
						 dataFilter: function(data, type) {
							 if(data == 'true'){
								 return true;
							 }else{
								 return false;
							 }
						 }
					 },
				 },
			 },
			 messages:{
				 carNumber:{
					 required:"车位必填",
					 remote : "车位已存在",
				 }
			 }
		 })
	 })

	 $.validator.setDefaults({
		 submitHandler: function() {
			 var index = layer.load(1,{shade:0.5});
			 $.post("<%=request.getContextPath() %>/truck/add",
					 $("#fm").serialize(),
					 function(data){
						 layer.close(index);
						 if(data.code != 200){
							 layer.msg(data.msg,{icon:2});
							 return;
						 }
						 if(data.code == 200){
							 layer.msg(data.msg,{icon:1},function(){
								 parent.window.location.href = "<%=request.getContextPath()%>/truck/toList"
							 });
						 }
					 })
		 }
	 });



</script>
</head>
<style>
	.error{
		color:red;
	}
</style>
<body>
<form id="fm" >
	车位编号:<input type="text" name="carNumber" id="carNumber"/><br />
	车位价格:<input type="text" name="price" /><br />
	车位状态<input type="radio" name="carStatus" value="0" checked>空置<br />

	车位等级<input type="radio" name="carLevel" value="0" checked>普通车位
	<input type="radio" name="carLevel" value="1" >会员车位<br />
	<input type="submit" value="增加"/>
</form>
</body>
</html>