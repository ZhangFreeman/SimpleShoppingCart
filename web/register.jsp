<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
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
            <h1>Register</h1>
            <a href="<%=basePath%>index.jsp">Home</a>
            <hr>
            <div>
                <form action="servlet/RegisterServlet" method="post">
                    <label>User Name: </label>
                    <input name="userName" value="" />
                    <label>Password: </label>
                    <input type="password" name="password" value="">
                    <p class="space">
                        <input type="submit" value="Register" class="btn"/>
                    </p>
                </form>
            </div>
        </div>
    </body>
</html>