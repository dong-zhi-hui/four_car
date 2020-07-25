
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui-v2.5.6/layui/css/layui.css"  media="all">
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.js"></script>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/static/layui-v2.5.6/layui/layui.js"></script>
    <script type="text/javascript">
        var aa = "";
        $(function() {
            search();
        })

        var pages = 1;

        function search() {
            $("#pageNo").val(1);
            locdUser();
        }

        function locdUser() {
            var index = layer.load(0,{shade:0.3});
            $.post("<%=request.getContextPath() %>/message/show",
                $("#fm").serialize(),
                function(data){
                    layer.close(index);
                    if(data.code != 200){
                        layer.msg(data.msg,{icon:5});
                        return;
                    }
                    var html = "";
                    var pageHtml = "";
                    for (var i = 0; i <  data.data.list.length; i++) {
                        var m = data.data.list[i];
                        html+="<tr>";
                        html+="<td>"+m.id+"</td>";
                        html+="<td>"+m.messageContents+"</td>";
                        html+="<td>"+m.createTime+"</td>";
                        html+="<td>"+m.userName+"</td>";
                        if(m.response == null){
                            html+="<td>"+aa+"</td>";
                        }else{
                            html+="<td>"+m.response+"</td>";
                        }
                        if(m.responseTime == null){
                            html+="<td>"+aa+"</td>";
                        }else {
                            html+="<td>"+m.responseTime+"</td>";
                        }
                        if(m.responseName == null){
                            html+="<td>"+aa+"</td>";
                        }else {
                            html+="<td>"+m.responseName+"</td>";
                        }

                        if ('${user.level}' == 3) {
                            html+="<td>";
                            html += "<div class='layui-btn-group'>"
                            if (m.response == null) {
                                html+="<input type='button' value='回复' class=\"layui-btn\" onclick='upd("+m.id+")'/>";
                            }
                            html+="<input type='button' value='删除' class=\"layui-btn\" onclick='del("+m.id+")'/>";
                            html += "</div>"
                            html+="</td>";
                        }
                        html+="</tr>";
                    }
                    $("#tbd").html(html);
                    pages = data.data.pages;
                    pageHtml += "<input type = 'button' class=\"layui-btn\" value='上一页' onclick='page(0)'/>";
                    pageHtml +="第"+$("#pageNo").val()+"/"+pages+"页";
                    pageHtml += "<input type = 'button' class=\"layui-btn\" value='下一页' onclick='page(1)'/>";
                    $("#pageInfo").html(pageHtml);
                });

        }
        //分页
        function page(temp){
            var page = $("#pageNo").val();
            if (temp == 0) {
                if (page == 1) {
                    layer.msg("已是首页", {icon: 5, time: 2000});
                    return;
                }
                $("#pageNo").val(parseInt(page) - 1);
            }
            if (temp == 1) {
                if (parseInt(page) + 1 > pages ) {
                    layer.msg("已是尾页", {icon: 5, time: 2000});
                    return;
                }
                $("#pageNo").val(parseInt(page) + 1);
            }
            locdUser();
        }

        //回复
        function upd(id){
            layer.open({
                type: 2,
                title: '回复',
                shade: 0.8,
                area: ['400px', '70%'],
                content: '<%=request.getContextPath()%>/message/toUpdate?id='+id
            });
        }

        //新增
        function add(){
            layer.open({
                type: 2,
                title: '新增留言',
                shade: 0.8,
                area: ['400px', '70%'],
                content: '<%=request.getContextPath()%>/message/toAdd'
            });
        }


        //删除
        function del(id){
            var index = layer.load(0,{shade:0.3});
            $.post(
                "<%=request.getContextPath()%>/message/del",
                {"id" : id},
                function(data){
                    layer.close(index);
                    if (data.code != 200) {
                        layer.msg(data.msg,{icon:5});
                        return;
                    }
                    layer.msg(data.msg, {
                        icon: 6,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    }, function(){
                        window.location.href = "<%=request.getContextPath() %>/message/toShow";
                    });
                }
            );
        }

    </script>
</head>
<body style="text-align: center">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>留言板</legend>
</fieldset>

<form id = "fm" align="center">
    <!-- 分页按钮 -->
    <input type="hidden" value="1" id="pageNo" name="pageNo"/>
    <c:if test="${user.level != 3}">
        <input type = "button" value="我要留言" class="layui-btn" onclick="add()"/>
        留言:<input type="text" name="messageContents" style="width: 230px; height: 30px"/>
        <input type = "button" value="搜索" class="layui-btn" onclick="search()"/>
    </c:if>
</form>
<div class="layui-form">
    <table class="layui-table">
        <colgroup>
            <col width="150">
            <col width="150">
            <col width="150">
        </colgroup>
        <thead>
        <tr>
            <td>序号</td>
            <td>留言内容</td>
            <td>留言时间</td>
            <td>留言人</td>
            <td>回复内容</td>
            <td>回复时间</td>
            <td>回复人</td>
            <c:if test="${user.level==3}">
                <th>操作</th>
            </c:if>
        </tr>
        </thead>
        <tbody id="tbd"></tbody>
    </table>
</div>
<div id = "pageInfo"></div>

</body>
</html>
