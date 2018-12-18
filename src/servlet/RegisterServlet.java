package servlet;

import entity.User;
import service.UserService;
import utils.MyUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    private UserService userService = new UserService();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        //goto index if session existed
        if (request.getSession().getAttribute("uid") != null) {
            response.sendRedirect(MyUtil.extractBaseURL(request) + "/index.jsp");
            return;
        }
        //checking parameters
        if (request.getParameter("userName") == null || request.getParameter("userName").equals("") ||
                request.getParameter("password") == null ||  request.getParameter("password").equals("")) {
            String base = MyUtil.extractBaseURL(request);
            response.sendRedirect(base + "register.jsp");
            return;
        }
        String userName = request.getParameter("userName");
        String passwd = request.getParameter("password");
        int res = userService.addUser(userName, UserService.pwdHashing(passwd));
        if (res > 0) {
            request.getSession().setAttribute("uid", res);
            request.getSession().setAttribute("uname", userName);
            //show ID to user
            request.setAttribute("msg", "Your user ID is " + res + ", register");
            request.getRequestDispatcher("/success.jsp").forward(request, response);
            return;
        } else {
            String msg = "Register ";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/failure.jsp").forward(request, response);
        }

    }
}
