package servlet;

import entity.Cart;
import entity.Item;
import service.CartService;
import service.ItemService;
import utils.MyUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CartServlet extends HttpServlet {

    private String action;
    private CartService cartService = new CartService();
    private ItemService itemService = new ItemService();

    public CartServlet() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        action = request.getParameter("action");

        //goto index if session existed
        if (request.getSession().getAttribute("uid") == null) {
            String base = MyUtil.extractBaseURL(request);
            request.getSession().setAttribute("redirectUrl",
                    base + "/details.jsp?id=" + request.getParameter("gid"));
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            //response.sendRedirect("login");
            return;
        }

        if ("add".equals(action)) {
            request.setAttribute("msg", "Add");
            if (addItemToCart(request,response)) {
                request.getRequestDispatcher("/success.jsp").forward(request, response);
                return;
            }
        }
        if ("show".equals(action)) {
            Cart cart = null;
            request.setAttribute("msg", "Show Cart");
            if ((cart = getCart(request,response)) != null) {
                request.setAttribute("cart", cart);
                request.getRequestDispatcher("/cart.jsp").forward(request, response);
                return;
            }
        }
        if ("delete".equals(action)) {
            request.setAttribute("msg", "Delete");
            if (deleteFromCart(request,response)) {
                request.getRequestDispatcher("/success.jsp").forward(request, response);
                return;
            }
        }
        request.getRequestDispatcher("/failure.jsp").forward(request, response);
    }

    /**
     * Add item to user cart
     * @param request
     * @param response
     * @return true if add to database
     */
    private boolean addItemToCart(HttpServletRequest request, HttpServletResponse response) {
        int uid = (Integer)request.getSession().getAttribute("uid");
        String sgid = request.getParameter("gid"); int gid = Integer.parseInt(sgid);
        String snum = request.getParameter("num"); int num = Integer.parseInt(snum);

        return cartService.addToCart(uid, gid, num);
    }

    /**
     * Get cart of user
     * @param request
     * @param response
     * @return user cart
     */
    private Cart getCart(HttpServletRequest request, HttpServletResponse response) {
        int uid = (Integer)request.getSession().getAttribute("uid");
        return cartService.getCart(uid);
    }

    /**
     * Delete item from cart
     * @param request
     * @param response
     * @return true if item deleted from database
     */
    private boolean deleteFromCart(HttpServletRequest request, HttpServletResponse response) {
        int uid = (Integer)request.getSession().getAttribute("uid");
        String sgid = request.getParameter("gid");
        int gid = Integer.parseInt(sgid);

        return cartService.deleteFromCart(gid, uid);
    }

}
