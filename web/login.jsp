<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style.css" />
    </head>
    <body>
        <%
            if (request.getSession().getAttribute("uid") != null) {
                response.sendRedirect(basePath + "/index.jsp");
                return;
            }
        %>
        <div class="container-center">
            <h1>Login</h1>
            <a href="<%=basePath%>index.jsp">Home</a>
            <hr>
            <div>
                <form action="LoginServlet" method="post">
                    <label>Uer ID: </label>
                    <input name="userID" value=""/>
                    <label>Password: </label>
                    <input type="password" name="password" value="">
                    <p class="space">
                        <input type="submit" value="Login" class="btn"/>
                    </p>
                </form>
            </div>
            <div>
                <a href="<%=basePath%>/servlet/RegisterServlet">Register</a>
            </div>
        </div>
    </body>
</html>