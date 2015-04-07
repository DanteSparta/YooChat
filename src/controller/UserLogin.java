package controller;

import model.User;
import storage.UserStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zw531129 on 15/4/5
 * So, what's the fucking copyright?
 */
@WebServlet(name = "userLogin", displayName = "userLogin", urlPatterns = "/userLogin")
@SuppressWarnings("unchecked")
public class UserLogin extends HttpServlet {
    AtomicInteger loginId = new AtomicInteger(0);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  Set characterEncoding
        request.setCharacterEncoding("UTF-8");
        //  Get http session
        HttpSession session = request.getSession();
        //  Get username
        String username = request.getParameter("username");
        //  Get map for name storage
        Map<Integer, String> names = UserStorage.names;

        //  Check username exists
        for (String temp : names.values()) {
            if (temp.equals(username)) {
                response.sendRedirect("/login.jsp");
                session.setAttribute("exist", "Nickname already exits!");
                return;
            }
        }

        //  put username into names
        names.put(loginId.getAndIncrement(), username);
        //  Create user model
        User user = new User();
        user.setId(loginId.get());
        user.setName(username);
        //  Set into session
        session.setAttribute("user", user);
        List<User> users = (List<User>) request.getServletContext().getAttribute("users");
        users.add(user);
        //  Forward to broadcast.jsp
        request.getRequestDispatcher("WEB-INF/view/broadcast.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  Forward to doPost method to handle request
        doPost(request, response);
    }
}
