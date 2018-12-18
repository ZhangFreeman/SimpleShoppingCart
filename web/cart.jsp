<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="entity.Cart" %>
<%@ page import="entity.Item" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    <head>
        <base href="<%=basePath%>">
        <title>ShoppingCart</title>
        <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style.css" />
        <script language="javascript">
	    function delcfm() {
	        if (!confirm("Confirm Delete？")) {
	            window.event.returnValue = false;
	        }
	    }
   </script>
    </head>

    <body>
        <div class="container-center">
            <h1>My Shopping Cart</h1>
            <a href="index.jsp">Home</a>
            <hr>
            <div id="shopping">
                <form action="" method="">
                    <table>
                        <tr>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Total price</th>
                            <th>Number</th>
                            <th>Action</th>
                        </tr>
                        <%
                            if(request.getAttribute("cart")!=null)
                            {
                        %>
                        <%

                            Cart cart = (Cart)request.getAttribute("cart");
                            Map<Item,Integer> goods = cart.getGoods();
                            Set<Item> items = goods.keySet();
                            Iterator<Item> it = items.iterator();

                            while(it.hasNext())
                            {
                                Item i = it.next();
                        %>
                        <tr>
                            <td class="thumb"><img src="images/<%=i.getPicture()%>" width="120" height="80"/> <a href="details.jsp?id=<%=i.getId()%>"><%=i.getName()%></a></td>
                            <td class="number"><%=i.getPrice() %></td>
                            <td class="price" id="price_id_1">
                                <%=i.getPrice()*goods.get(i) %>
                            </td>
                            <td class="number">
                                <%=goods.get(i)%>
                            </td>
                            <td class="delete">
                                <a href="servlet/CartServlet?action=delete&gid=<%=i.getId()%>" onclick="delcfm();">Delete</a>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </table>
                    <div class="total">Total：<%=cart.getTotalPrice() %>￥</div>
                    <%
                        }
                    %>

                </form>
            </div>
        </div>
    </body>
</html>
