package yoochat.controller;

import yoochat.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by zw531129 on 15/3/31
 * So, what's the fucking copyright?
 */
@WebServlet(name = "userRegister", displayName = "userRegister", urlPatterns = "/userRegister")
@SuppressWarnings("unchecked")
public class UserRegister extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  Get username and password
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //  Get printWriter to print stream
        PrintWriter out = response.getWriter();
        //  Get users loaded in InitListener
        List<User> users = (List<User>) request.getServletContext().getAttribute("users");
        //  Iterate to check if user is in the users
        for (User user : users) {
            //  Found just reject
            if (username.equals(user.getUsername())) {
                out.println("User has already registered!");
                return;
            }
        }
        //  Initialize new User
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        //  Set new user in users
        users.add(newUser);
        //  Refresh the users in application variable
        request.getServletContext().setAttribute("users", users);
        //  Forward to chatRoom.jsp page
        request.getRequestDispatcher("/WEB-INF/view/chatRoom.jsp").forward(request, response);
    }
}
