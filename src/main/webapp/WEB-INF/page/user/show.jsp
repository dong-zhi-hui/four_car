<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/7/22
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/static/js/jquery-1.12.4.js"></script>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
    <script type="text/javascript">
        
        $(function () {
            show();
        })

        function show() {
            var index = layer.load(1,{shade:0.5});
            $.post("<%=request.getContextPath()%>/user/show",
                $("#tbd").serialize(),
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
            var index = layer.load(1,{shade:0.5});
            var page = $("#pageNo").val();
            if(temp == 0) {
                layer.close(index);
                if(parseInt(page) - 1 < 1) {
                    layer.msg("已是首页", {icon: 6});
                    return;
                }
                $("#pageNo").val(parseInt(page) - 1);
            }
            if(temp == 1){
                layer.close(index);
                if(parseInt(page) + 1 > pages){
                    layer.msg("已经尾页了", {icon: 6});
                    return;
                }
                $("#pageNo").val(parseInt(page) + 1);

            }
            show();

        }

    </script>    
</head>
<body>
<form id = "fm">
    <input type="hidden" value="1" id="pageNo" name="pageNo"/>
</form>
<table align="center" border = "1px" cellpadding = "10" cellspacing = "0">
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
<div id="pageInfo" align="center">
</div>
</body>
</html>
