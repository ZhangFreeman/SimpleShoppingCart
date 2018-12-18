<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    <head>
        <base href="<%=basePath%>">

        <title>Success</title>
        <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style.css" />

    </head>

    <body>
        <div class="container-center">
            <div>Success</div>
            <a href="index.jsp">Home</a>
            <hr>
            <%= request.getAttribute("msg") + " succeed"%>
            <br>
            <a href=<%=request.getHeader("Referer")%>>Back</a>
            <br>
            <br>

        </div>
    </body>
</html>
