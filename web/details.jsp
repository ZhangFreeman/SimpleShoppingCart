<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ page import="entity.Item"%>
<%@ page import="service.ItemService" %>
<%@ page import="entity.Item" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    <head>
        <base href="<%=basePath%>">

        <title>Details</title>

        <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style.css" />
        <script type="text/javascript" src="<%=basePath%>/js/script.js"></script>
    </head>
    <body>
        <div class="container-center">
            <h1>Detail</h1>
            <a href="index.jsp" class="nav">Home</a>
            <hr>

            <div id="item-detail">
                <%
                    ItemService itemService = new ItemService();
                    Item item = itemService.getItem(Integer.parseInt(request.getParameter("id")));
                    if(item!=null)
                    {
                %>
                <div>
                    <img src="images/<%=item.getPicture()%>" width="200" height="160"/>
                </div>
                <%=item.getName() %>
                <div class="item-info">
                    Price：<%=item.getPrice() %>￥
                    Number：<span onclick="sub();">-</span><input type="text" id="number" name="number" value="1" size="2"/><span onclick="add();">+</span>
                </div>
                <div>
                    <button onclick="add_cart(<%=item.getId()%>, '<%=basePath%>')" class="btn">Add to cart</button>&nbsp;
                    <button onclick="show_cart(<%=item.getId()%>, '<%=basePath%>')" class="btn">Show cart</button>
                </div>
                <%
                    }
                %>
            </div>
        </div>
    </body>
</html>
