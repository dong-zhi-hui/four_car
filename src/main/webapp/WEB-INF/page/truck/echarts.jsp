<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Insert title here</title>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/echarts.min.js"></script>

</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的DOM容器  -->
<div id="daa" style="width: 800px;height:400px;"></div>

<script type="text/javascript">
	$(function(){
		var chart = echarts.init($("#daa")[0]);
		$.post(
				"<%=request.getContextPath() %>/truck/findTruckByCount",
				{},
				function(data){
					var option = {
						title: {
							text: '车位状态',
							/* left: 'center' */
						},
						//提示框组件
						tooltip:{
							//坐标轴触发，主要用于柱状图，折线图等
							trigger: 'item',
							formatter: '{a} <br/>{b} : {c} ({d}%)'
						},
						//图例
						legend:{
							data:['总车位', '空置', '已预约']
						},

						/* 系列列表。每个系列通过type决定自己的图表类型 */
						series: [{
							name: "车位",
							radius: '55%',
							center: ['50%', '60%'],
							/* 饼状图 */
							type: 'pie',/* bar line */
							data: [
								{name : "总车位", value : data.data.countAll},
								{name : "空置", value : data.data.count1},
								{name : "已预约", value : data.data.count2}
							]
						}]
					};
					/*  使用刚指定的配置项和数据显示图表。	 */
					chart.setOption(option);
				}
		);
	})

</script>
</body>
</html>