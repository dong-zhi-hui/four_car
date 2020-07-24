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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui-v2.5.6/layui/css/layui.css"  media="all">
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.12.4.js"></script>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/static/layui-v2.5.6/layui/layui.js"></script>
    <script type="text/javascript">

        function update() {
            var index = layer.load(1,{shade:0.5});
            $.post("<%=request.getContextPath()%>/order/updatePay",
                $("#fm").serialize(),
                function(data){
                    layer.close(index);
                    if (data.code != 200) {
                        layer.msg(data.msg,{icon:2});
                        return;
                    }
                    layer.msg(data.msg,{icon:1},function(){
                        parent.window.location.href="<%=request.getContextPath()%>/order/toList"
                    });
                })
        }

    </script>
</head>
<body>
<form id="fm">
    <input type="hidden" name="id" value="${orderCar.id}" />
        支付：
        <select name="pay">
            <c:forEach items="${payList}" var="p">
                <option value="${p.baseName}">${p.baseName}</option>
            </c:forEach>
        </select>
        <br/>
        <input type="hidden" name="orderStatus" value="1">
        支付金额${orderCar.price}
        <br/>
        <input type="button" onclick="update()" value="确认支付">
</form>
</body>
</html>
