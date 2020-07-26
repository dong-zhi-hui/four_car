<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/7/24
  Time: 10:10
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
    <script type="text/javascript" src = "<%=request.getContextPath()%>/static/jquery-validation-1.14.0/dist/jquery.validate.js"></script>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/static/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
    <script type="text/javascript">
        $.validator.setDefaults({
            submitHandler: function() {
                var index = layer.load(2, {shade: 0.3});
                $.post("<%=request.getContextPath()%>/user/updateUser",
                    $("#fm").serialize(),
                    function (data) {
                        if(data.code != "200"){
                            layer.msg(data.msg, {icon: 5});
                            layer.close(index);
                            return;
                        }
                        layer.msg(data.msg, {
                            icon: 6,
                            time: 1000 //2秒关闭（如果不配置，默认是3秒）
                        }, function(){
                            layer.close(index);
                            parent.location.href= "<%=request.getContextPath()%>/user/toShow";
                        });
                    })
            }
        });
        $().ready(function() {
            $("#fm").validate({
                rules : {
                    userName : {
                        required: true,
                        rangelength: [1, 4],
                    },
                    phone :{
                        required : true,
                        rangelength:[11,11],

                    },
                    plateNumber :{
                        required : true,
                        maxlength:7,

                    },
                },
                messages : {
                    userName : {
                        required : "请输入用户名",
                        rangelength : "用户名最少一个,最多4个字母组成",
                    },
                    phone :{
                        required : "请输入手机号",
                        rangelength:"必须写11位",
                    },
                    plateNumber :{
                        required : "请输入车牌号号",
                        maxlength:"车牌号必须7位",
                    },
                },
            });
        });

    </script>
    <style>
        .error{
            color:red;
        }
    </style>
</head>
<body>
<form id = "fm">
    <input type="hidden" name="id" value="${updateUser.id}">
    用户名<input type="text" name="userName" value="${updateUser.userName}" style="width: 230px; height: 30px" /><br>
    手机号<input type="text" name="phone" value="${updateUser.phone}" style="width: 230px; height: 30px" /><br>
    车牌号<input type="text" name="plateNumber" value="${updateUser.plateNumber}" style="width: 230px; height: 30px" /><br>
    状态:无效<input type="radio" name="userStatus"  <c:if test="${updateUser.userStatus == 0}"> checked = "checked" </c:if> value="0" />
        有效<input type="radio" name="userStatus" <c:if test="${updateUser.userStatus == 1}"> checked = "checked" </c:if> value="1"/><br>
    <input  type = "submit" value = "修改"  class="layui-btn" >
</form>
</body>
</html>
