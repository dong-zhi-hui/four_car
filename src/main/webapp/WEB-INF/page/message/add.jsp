<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/7/22
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui-v2.5.6/layui/css/layui.css"  media="all">
    <script type="text/javascript" src = "<%=request.getContextPath()%>/static/js/jquery-1.12.4.js"></script>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/static/layui-v2.5.6/layui/layui.js"></script>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/static/jquery-validation-1.14.0/dist/jquery.validate.js"></script>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/static/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
    <script type="text/javascript">
        function add() {
            var index = layer.load(0,{shade:0.3});
            $.post(
                "<%=request.getContextPath()%>/message/add",
                $("#fm").serialize(),
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
                        parent.location.href = "<%=request.getContextPath() %>/message/toShow";
                    });
                }
            );
        }
    </script>
</head>
<body>
<form id = "fm" align="center">
    留言内容: <input type="text" name="messageContents" id = "messageContents" style="width: 230px; height: 30px" >

    <input  type = "button" value = "新增"  class="layui-btn" onclick="add()">
</form>
</body>
</html>
