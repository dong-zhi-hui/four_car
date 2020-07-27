<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui-v2.5.6/layui/css/layui.css"  >
	<script type="text/javascript" src = "<%=request.getContextPath()%>/static/js/jquery-1.12.4.js"></script>
	<script type="text/javascript">
		var bgmusic = document.getElementById('bgmusic');
		bgmusic.addEventListener('canplay', function(){
			this.play();
		}, false);
		window.addEventListener('load', function(){
			window.addEventListener('touchstart', once, false);
		}, false);
		function once(){
			bgmusic.play();
			window.removeEventListener('touchstart', once, false);
		}
	</script>
</head>
<body class="layui-layout-body">


<div class="layui-layout layui-layout-admin">
	<div class="layui-header">
		<div class="layui-logo">易泊车</div>
		<!-- 头部区域（可配合layui已有的水平导航） -->
		<ul class="layui-nav layui-layout-left">

		</ul>
		<ul class="layui-nav layui-layout-right">
			<li class="layui-nav-item">

					${user.userName}

			</li>
			<li class="layui-nav-item"><a href="<%=request.getContextPath()%>/index/toEsc">退出登录</a></li>
			<audio src="http://rm.sina.com.cn/wm/VZ2010050511043310440VK/music/MUSIC1005051622027270.mp3"
				   preload="meta" loop autoplay id="bgmusic" autoplay="autoplay" controls="controls" ></audio>
		</ul>
	</div>

	<div class="layui-side layui-bg-black">
		<div class="layui-side-scroll">
			<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
			<ul class="layui-nav layui-nav-tree"  lay-filter="test">

					<li class="layui-nav-item layui-nav-itemed">
						<a class="" href="<%=request.getContextPath()%>/user/toShow"  target="iframe">用户展示</a>
					</li>
					<li class="layui-nav-item">
						<a  href="<%=request.getContextPath()%>/truck/toList"  target="iframe">车位管理</a>
					</li>
					<li class="layui-nav-item">
						<a  href="<%=request.getContextPath()%>/truck/echarts"  target="iframe">车位echarts</a>
					</li>
					<li class="layui-nav-item">
						<a  href="<%=request.getContextPath()%>/message/toShow"  target="iframe">留言板</a>
					</li>
					<li class="layui-nav-item">
						<a  href="<%=request.getContextPath()%>/order/toList"  target="iframe">订单管理</a>
					</li>


			</ul>
		</div>
	</div>
	<style>
		.parent {
			position: relative;
		}
		.son {
			position: absolute;
			top: 0;
			bottom: 0;
			margin: auto;
		}
	</style>

	<div class="layui-body" >
		<!-- 内容主体区域 -->
		<div style="padding: 15px;">
			<div class="layadmin-tabsbody-item layui-show" >
				<iframe id = "mainFrame" frameborder="0" class="layadmin-iframe" name="iframe"  width="1300" height="580" frameborder="0" scrolling="no" scrolling="no" frameborder="0"></iframe>
			</div>
		</div>
	</div>



	<div class="layui-footer">
		<!-- 底部固定区域 -->
		© YiBoChe.com
	</div>
</div>
<script type="text/javascript" src = "<%=request.getContextPath()%>/static/layui-v2.5.6/layui/layui.js"></script>
<script type="text/javascript" language="javascript">
	function reinitIframe(){
		var iframe = document.getElementById("mainFrame");
		try{
			var bHeight = iframe.contentWindow.document.body.scrollHeight;
			var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
			var height = Math.max(bHeight, dHeight);
			iframe.height = height;
		}catch (ex){}
	}
	window.setInterval("reinitIframe()", 100);
</script>
<script>
	//JavaScript代码区域
	layui.use('element', function(){
		var element = layui.element;

	});

</script>
</body>









<%--<frameset rows="20%,80%">--%>
<%--	<frame src="<%=request.getContextPath()%>/index/toTop" name="top">--%>
<%--	<frameset cols="20%,80%">--%>
<%--		<frame src="<%=request.getContextPath()%>/index/toLeft" name="left" />--%>
<%--		<frame src="<%=request.getContextPath()%>/index/toRight" name="right" />--%>
<%--	</frameset>--%>
<%--</frameset>--%>
</html>