<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/7/23
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/static/js/jquery-1.12.4.js"></script>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/static/jquery-validation-1.14.0/dist/jquery.validate.js"></script>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/static/layui-v2.5.6/layui/layui.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui-v2.5.6/layui/css/layui.css"  media="all">


    <script type="text/javascript">
        function login() {
            var index = layer.load(3,{shade:0.5});
            $.post("<%=request.getContextPath()%>/user/phoneLogin",
                $("#fm").serialize(),
                function(data){
                    if (data.code != "200"){
                        layer.msg(data.msg,{icon:2},function(){
                            window.location.href = "<%=request.getContextPath() %>/user/toPhoneLogin";
                        })
                        return;
                    }
                    layer.msg(data.msg,{icon:1},function(){
                        parent.window.location.href = "<%=request.getContextPath() %>/index/toIndex";
                    })
                })
        }

        function getCode(val){
            settime(val);
            var index = layer.load(3, {shade: [0.1,'#fff']});
            $.post("<%=request.getContextPath()%>/user/getCode",
                $("#fm").serialize(),
                function(data){
                    if(data.code != 200){
                        layer.msg(data.msg, {icon: 5});
                        layer.close(index);
                        return;
                    }
                    layer.msg("验证码:"+data.data, {
                        icon: 6,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    }, function(){
                        $("#code").val(data.data);
                        layer.close(index);

                    });
                }
            )
        }
        var countdown=30;
        function settime(val) {
            if (countdown == 0) {
                val.removeAttribute("disabled");
                val.value="获取验证码";
                countdown = 20;
                return;
            } else {
                val.setAttribute("disabled", true);
                val.value="重新发送(" + countdown + ")";
                countdown--;
            }
            setTimeout(function() {
                settime(val)
            },1000)
        }


    </script>
    <style>
        .error{
            color:red;
        }
    </style>
</head>
<body>

<form id="fm">
    手机号：    <input type="text" name="phone" style="width: 230px; height: 30px"/><br/>
    验证码：<input type="text" name="code" id = "code" style="width: 230px; height: 30px"/>
    <input type="button" value="获取验证码" onclick="getCode(this)" class="layui-btn"/><br/>
    <input type="button" value="登录" onclick="login()" class="layui-btn"/>

</form>

</body>
</html>
