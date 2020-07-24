<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/7/22
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/static/js/jquery-1.12.4.js"></script>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
    <script type="text/javascript">
        //判断当前窗口路径与加载路径是否一致。
        if(window.top.document.URL != document.URL){
            //将窗口路径与加载路径同步
            window.top.location = document.URL;
        }
        function login() {
            var index = layer.load(1,{shade:0.5});
            $.post("<%=request.getContextPath()%>/user/login",
                $("#fm").serialize(),
                function (data) {
                    layer.close(index);
                    if(data.code != 200){
                        layer.msg(data.msg,{icon:2});
                        return;
                    }
                    layer.msg(data.msg,{icon:1},function(){
                        window.location.href ="<%=request.getContextPath()%>/index/toIndex";
                    });
                })
        }

        function toPhoneLogin() {
            layer.open({
                type: 2,
                title: '手机验证码登录',
                shade: 0.8,
                area: ['400px', '70%'],
                content: '<%=request.getContextPath()%>/user/toPhoneLogin'
            });
        }
    </script>

</head>
<body align="center">
<form id = "fm" >
    账号<input type="text" name="userName"/><br/>
    密码<input type="text" name="userPwd"/><br/>
    <button type="button" onclick="login()">登录</button>
    <button type="button" onclick="toPhoneLogin()">手机验证码登录</button><br/>
    <a href="<%=request.getContextPath()%>/user/toRegister">注册</a>

</form>
</body>
</html>
