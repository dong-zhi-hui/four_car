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
        $.validator.setDefaults({
            submitHandler: function() {
                var index = layer.load(2, {shade: 0.3});
                $.post("<%=request.getContextPath()%>/user/register",
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
                    userName : {
                        required: true,
                        rangelength: [1, 4],
                        remote: {
                            url: "<%=request.getContextPath()%>/user/findUserNameOrPhoneOrPlateNumber",
                            type: "post",
                            data: {
                                userName: function() {
                                    return $("#userName").val();
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
                        required : true,
                        minlength :3,
                    },
                    phone :{
                        required : true,
                        rangelength:[11,11],
                        remote: {
                            url: "<%=request.getContextPath()%>/user/findUserNameOrPhoneOrPlateNumber",
                            type: "post",
                            data: {
                                phone: function() {
                                    return $("#phone").val();
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
                    plateNumber :{
                        required : true,
                        maxlength:8,
                        remote: {
                            url: "<%=request.getContextPath()%>/user/findUserNameOrPhoneOrPlateNumber",
                            type: "post",
                            data: {
                                plateNumber: function() {
                                    return $("#plateNumber").val();
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
                    userName : {
                        required : "请输入用户名",
                        rangelength : "用户名最少一个,最多4个字母组成",
                        remote : "用户已存在",
                    },

                    userPwd : {
                        required : "请输入密码",
                        minlength :"密码不得少于3位",
                    },
                    phone :{
                        required : "请输入手机号",
                        rangelength:"必须写11位",
                        remote : "手机号已存在",
                    },
                    plateNumber :{
                        required : "请输入车牌号号",
                        maxlength:"车牌号最少8位",
                        remote : "车牌号已存在",
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
    用户名: <input type="text" name="userName" id = "userName" style="width: 230px; height: 30px" ><br/>
     密码： <input type="text" name="userPwd" style="width: 230px; height: 30px" ><br/>
    手机号: <input type="text" name="phone" id = "phone" style="width: 230px; height: 30px" ><br/>
    车牌号: <input type="text" name="plateNumber" id = "plateNumber" style="width: 230px; height: 30px" ><br/>
    级别： 普通用户<input type="radio" name="level" value="0" checked>
          管理员<input type="radio" name="level" value="3" ><br/>
    <input type="hidden" name="userStatus" value="0"/><br/>
    <input  type = "submit" value = "注册"  class="layui-btn" >
</form>
</body>
</html>
