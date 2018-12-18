<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="entity.Item"%>
<%@ page import="service.ItemService"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Index</title>
        <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style.css" />
    </head>

    <body>
        <div class="container-center">
            <h1>Welcome</h1>
            <hr>
            <% Integer uid = (Integer)request.getSession().getAttribute("uid");
                if(uid != null){%>
            <%="Hello " + request.getSession().getAttribute("uname")%>
            <%}else{ %>
            <%="<a href=servlet/LoginServlet>Login</a>"%>
            <%}%>
            <div class="gallery-wrapper center">
                <%
                    ItemService itemService = new ItemService();
                    List<Item> list = itemService.getAllItems();
                    if(list!=null&&list.size()>0)
                    {
                        for(int i=0;i<list.size();i++)
                        {
                            Item item = list.get(i);
                %>
                <div class="item-wrapper container-center">
                    <dl>
                        <dt>
                            <a href="details.jsp?id=<%=item.getId()%>"><img src="images/<%=item.getPicture()%>" width="120" height="90" class="item-img center"/></a>
                        </dt>
                        <dd class="center"><%=item.getName() %></dd>
                        <dd class="center">Price:￥ <%=item.getPrice() %></dd>
                    </dl>
                </div>
                <%
                        }
                    }
                %>
            </div>
        </div>
    </body>
</html>
