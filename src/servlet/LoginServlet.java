package servlet;

import dao.UserDao;
import entity.User;
import service.CartService;
import service.UserService;
import utils.MyUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class LoginServlet extends HttpServlet {

    private UserService userService = new UserService();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //goto index if session existed
        response.setContentType("text/html");
        if (request.getSession().getAttribute("uid") != null) {
            response.sendRedirect(MyUtil.extractBaseURL(request) + "/index.jsp");
            return;
        }
        //checking parameters
        if (request.getParameter("userID") == null || request.getParameter("password") == null  ||
                request.getParameter("userID").equals("") || request.getParameter("password").equals("") ||
                !isInteger(request.getParameter("userID"))) {
            String base = MyUtil.extractBaseURL(request);
            request.getSession().setAttribute("redirectUrl",
                    base + "/index.jsp");
            //response.sendRedirect("login");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        String suid = request.getParameter("userID");
        int uid = Integer.parseInt(suid);
        String passwd = request.getParameter("password");
        User user = null;

        if ((user = userService.getUser(uid)) != null) {
            if (user.getPasswd().equals(UserService.pwdHashing(passwd))) {
                request.getSession().setAttribute("uid", uid);
                request.getSession().setAttribute("uname", user.getUname());
                //goto pre-saved redirect URL
                String redirectUrl = (String)request.getSession().getAttribute( "redirectUrl");
                if (redirectUrl!=null){
                    request.getSession().removeAttribute( "redirectUrl");
                    response.sendRedirect(redirectUrl);
                } else {
                    String base = MyUtil.extractBaseURL(request);
                    response.sendRedirect(base + "/index.jsp");
                    //request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
                return;
            }
        }
        //show failure page if something wrong
        String msg = "Wrong Pass Word or User not Exist, Login";
        request.setAttribute("msg", msg);
        request.getRequestDispatcher("/failure.jsp").forward(request, response);

    }

    /**
     * Checking if string is integer format
     * @param strNum string to check
     * @return return true if strNum is in integer format
     */
    private static boolean isInteger(String strNum) {
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

}
