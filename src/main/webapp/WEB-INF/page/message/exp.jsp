
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

        $(function() {
            search();
        })

        function search() {
            var index = layer.load(0,{shade:0.3});
            $.post("<%=request.getContextPath() %>/message/findMessageList",
                $("#fm").serialize(),
                function(data){
                    layer.close(index);
                    if(data.code != 200){
                        layer.msg(data.msg,{icon:5});
                        return;
                    }
                    var html = "";
                    var pageHtml = "";
                    for (var i = 0; i <  data.data.length; i++) {
                        var m = data.data[i];
                        html+="<tr>";
                        html+="<td>"+m.id+"</td>";
                        html+="<td>"+m.messageContents+"</td>";
                        html+="<td>"+m.createTime+"</td>";
                        html+="<td>"+m.userName+"</td>";
                        html+="<td>"+m.response+"</td>";
                        html+="<td>"+m.responseTime+"</td>";
                        html+="<td>"+m.responseName+"</td>";
                        html+="<td>";
                        html+="<input type='button' value='删除' class=\"layui-btn\" onclick='del("+m.id+")'/>";
                        html+="</td>";
                        html+="</tr>";
                    }
                    $("#tbd").html(html);
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
                        window.location.href = "<%=request.getContextPath() %>/message/toFindMessageExp";
                    });
                }
            );
        }

    </script>
</head>
<body style="text-align: center">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>我的留言记录</legend>
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
            <td>序号</td>
            <td>留言内容</td>
            <td>留言时间</td>
            <td>留言人</td>
            <td>回复内容</td>
            <td>回复时间</td>
            <td>回复人</td>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="tbd"></tbody>
    </table>
</div>

</body>
</html>
