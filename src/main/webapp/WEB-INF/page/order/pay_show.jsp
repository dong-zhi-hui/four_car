<%--
  Created by IntelliJ IDEA.
  User: zhw
  Date: 2020/7/23
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.js">
    </script>
</head>
<body>



支付时间：${locus.orderDate}<br>
用户名：${locus.userName}<br>
支付方式：<span style="color: red">${locus.action}支付成功</span>
</body>
</html>
