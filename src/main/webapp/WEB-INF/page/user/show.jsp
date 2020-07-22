<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/7/22
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui-v2.5.6/layui/css/layui.css"  media="all">
    <script type="text/javascript" src = "<%=request.getContextPath()%>/static/js/jquery-1.12.4.js"></script>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/static/layui-v2.5.6/layui/layui.js"></script>
    <script type="text/javascript">
        
        $(function () {
            show();
        })

        function search() {
            $("#pageNo").val(1);
            show();
        }

        function show() {
            var index = layer.load(1,{shade:0.5});
            $.post("<%=request.getContextPath()%>/user/show",
                $("#fm").serialize(),
                function (data) {
                    layer.close(index);
                    if(data.code != 200){
                        layer.msg(data.msg,{icon:2});
                        return;
                    }
                    var html = "";
                    var pageInfo = "";
                    for(var i = 0; i < data.data.user.length; i++){
                        var u = data.data.user[i];
                        html += "<tr>";
                        html += "<td>"+u.id+"</td>";
                        html += "<td>"+u.userName+"</td>";
                        html += "<td>"+u.phone+"</td>";
                        html += "<td>"+u.plateNumber+"</td>";
                        html += "<td>"+u.userStatus+"</td>";
                        html += "<td>"+u.createTime+"</td>";
                        html += "<td>"+u.level+"</td>";
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
            show();
        }

    </script>    
</head>
<body>
<form id = "fm" align="center">
    <input type="hidden" value="1" id="pageNo" name="pageNo"/>
<c:if test="${user.level == 3}">
    搜用户名：<input type="text" name="userName" style="width: 230px; height: 30px" >
    <input  type = "button" value = "搜索"  class="layui-btn" onclick="search()">
</c:if>
</form>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>用户展示</legend>
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
        <td>id</td>
        <td>姓名</td>
        <td>手机号</td>
        <td>车牌号</td>
        <td>状态</td>
        <td>创建时间</td>
        <td>等级</td>
    </tr>
    <tbody id = "tbd">
    </tbody>
</table>
</div>
<div id="pageInfo" align="center">
</div>
</body>
</html>
