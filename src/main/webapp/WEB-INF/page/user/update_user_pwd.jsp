<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/7/23
  Time: 19:27
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
                            parent.location.href= "<%=request.getContextPath()%>/user/toLogin";
                        });
                    })
            }
        });
        $().ready(function() {
            $("#fm").validate({
                rules : {
                    password : {
                        required: true,
                        rangelength: [3, 8],
                        remote: {
                            url: "<%=request.getContextPath()%>/user/findUserPwd",
                            type: "post",
                            data: {
                                password: function() {
                                    return $("#password").val();
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

                    userPwd : {
                        required: true,
                        rangelength: [3, 8],
                        remote: {
                            url: "<%=request.getContextPath()%>/user/userPwd",
                            type: "post",
                            data: {
                                userPwd: function() {
                                    return $("#userPwd").val();
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
                messages : {
                    password : {
                        required : "请输入旧密码",
                        rangelength : "密码最少3位,最多8个位",
                        remote : "旧密码错误",
                    },

                    userPwd : {
                        required : "请输入密码",
                        minlength :"密码不得少于3位",
                        remote : "新密码不可以与旧密码一直",
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
<form id = "fm" align="center">
    <input type="hidden" value="${updateUserPwd.id}" name="id">
    旧密码<input type="text" name="password" id = "password" style="width: 230px; height: 30px" /><br>
    新密码<input type="text" name="userPwd" id = "userPwd" style="width: 230px; height: 30px" /><br/>
    <input  type = "submit" value = "确认"  class="layui-btn" ><br/>
</form>
</body>
</html>
