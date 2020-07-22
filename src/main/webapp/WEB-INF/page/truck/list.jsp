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
<script type="text/javascript" src = "<%=request.getContextPath()%>/static/layui-v2.5.6/layui/layui.js"></script>
<script type="text/javascript">
	
	$(function () {
		search();
	})

	function search() {
        var index = layer.load(1,{shade:0.5});
		$.post("<%=request.getContextPath()%>/truck/show",
			$("#fm").serialize(),
			function(data){
                layer.close(index);
				var html = "";
                var pageInfo = "";
				for (var i = 0; i < data.data.list.length; i++) {
					var d = data.data.list[i];
                    html += "<tr>";
                    html += "<td>"+d.id+"</td>";
                    html += "<td>"+d.carNumber+"</td>";
                    html += "<td>"+d.price+"</td>";
                    if(d.carStatus==0){
                        html += "<td>空置</td>";
                    }
                    if(d.carStatus==1){
                        html += "<td>已预约</td>";
                    }
                    if (${user.level==3}){
                        html += "<td>"
                        html += "<div class='layui-btn-group'>"
                        html += "<button type='button' class='layui-btn layui-btn-sm' onclick='update("+d.id+")'>修改</button>"
                        html += "<button type='button' class='layui-btn layui-btn-sm' onclick='del("+d.id+")'>删除</button>"
                        html += "</div>"
                        html += "</td>"
                    }
                    html += "</tr>";
				}
                $("#tbd").html(html);
                pageInfo += "<input type = 'button' value='上一页' onclick='page(0, "+data.data.pages+")'/>";

                pageInfo += "<input type = 'button' value='下一页' onclick='page(1, "+data.data.pages+")'/>";
                $("#pageInfo").html(pageInfo);
			})
	}

    //分页
    function page(temp,pages){
        var page = $("#pageNo").val();
        if(temp == 0) {
            if(parseInt(page) - 1 < 1) {
                layer.msg("已是首页", {icon: 5, time: 2000});
                return;
            }
            $("#pageNo").val(parseInt(page) - 1);
        }
        if(temp == 1){
            if(parseInt(page) + 1 > pages){
                layer.msg("已是尾页", {icon: 5, time: 2000});
                return;
            }
            $("#pageNo").val(parseInt(page) + 1);
        }
        search();
    }

	function update(id) {
		layer.open({
			  type: 2,
			  title: '修改信息',
			  shadeClose: true,
			  shade: 0.8,
			  area: ['380px', '90%'],
			  content: '<%=request.getContextPath()%>/truck/toUpdate/'+id
			}); 
	}

	function del(id) {
		var index = layer.load(1,{shade:0.5});
		$.post(
			"<%=request.getContextPath()%>/truck/del",
			{"id":id},
			function(data){
				layer.close(index);
				if (data.code == 200) {
					layer.msg(data.msg, {icon:1});
					search();
				}
			}) 
	}

    function find() {
        $("#pageNo").val(1);
        search();
    }

</script>
</head>
<body>
<form id="fm" align="center">
<input type="hidden" value="1" id="pageNo" name="pageNo"/>
搜车位：<input type="text" name="carNumber" style="width: 230px; height: 30px" >
<input  type = "button" value = "搜索"  class="layui-btn" onclick="find()">
</form>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>车位列表</legend>
</fieldset>
<div class="layui-form">
  <table class="layui-table">
    <colgroup>
      <col width="150">
      <col width="150">
      <col width="150">
    </colgroup>
     <thead>
      <tr>
		<th>序号</th>
		<th>车位编号</th>
		<th>车位价格</th>
		<th>车位状态</th>
          <c:if test="${user.level==3}">
              <th>操作</th>
          </c:if>
      </tr> 
    </thead>
     <tbody id="tbd"></tbody>
  </table>
</div>
<div id="pageInfo" align="center">

</body>
</html>