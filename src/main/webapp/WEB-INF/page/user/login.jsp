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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/style.css">
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

        function toRegister() {
            layer.open({
                type: 2,
                title: '注册',
                shade: 0.8,
                area: ['400px', '70%'],
                content: '<%=request.getContextPath()%>/user/toRegister'
            });
        }


    </script>

</head>
<body align="center">
<main>
    <form class="form" id ="fm">
        <div class="form__cover"></div>
        <div class="form__loader">
            <div class="spinner active">
                <svg class="spinner__circular" viewBox="25 25 50 50">
                    <circle class="spinner__path" cx="50" cy="50" r="20" fill="none" stroke-width="4" stroke-miterlimit="10"></circle>
                </svg>
            </div>
        </div>
        <div class="form__content">
            <h1>易泊车</h1>
            <div class="styled-input">
                <input type="text" class="styled-input__input" name="userName">
                <div class="styled-input__placeholder"> <span class="styled-input__placeholder-text"></span> </div>
                <div class="styled-input__circle"></div>
            </div>
            <div class="styled-input">
                <input type="text" class="styled-input__input" name="userPwd">
                <div class="styled-input__placeholder"> <span class="styled-input__placeholder-text"></span> </div>
                <div class="styled-input__circle"></div>
            </div>

            <button type="button" class="styled-button" onclick="login()"> <span class="styled-button__real-text-holder"> <span class="styled-button__real-text">登录</span> <span class="styled-button__moving-block face"> <span class="styled-button__text-holder"> <span class="styled-button__text">登录</span> </span> </span><span class="styled-button__moving-block back"> <span class="styled-button__text-holder"> <span class="styled-button__text">登录</span> </span> </span> </span> </button>
            <button type="button" class="styled-button" onclick="toPhoneLogin()"> <span class="styled-button__real-text-holder"> <span class="styled-button__real-text">手机登录</span> <span class="styled-button__moving-block face"> <span class="styled-button__text-holder"> <span class="styled-button__text">手机登录</span> </span> </span><span class="styled-button__moving-block back"> <span class="styled-button__text-holder"> <span class="styled-button__text">手机登录</span> </span> </span> </span> </button>
            <button type="button" class="styled-button" onclick="toRegister()"> <span class="styled-button__real-text-holder"> <span class="styled-button__real-text">注册</span> <span class="styled-button__moving-block face"> <span class="styled-button__text-holder"> <span class="styled-button__text">注册</span> </span> </span><span class="styled-button__moving-block back"> <span class="styled-button__text-holder"> <span class="styled-button__text">注册</span> </span> </span> </span> </button>
        </div>
    </form>
</main>
<script  src="<%=request.getContextPath()%>/static/js/index.js"></script>
</body>
</html>
