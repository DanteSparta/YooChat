package controller;

import storage.UserStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zw531129 on 15/4/5
 * So, what's the fucking copyright?
 */
@WebServlet(name = "userLogin", displayName = "userLogin", urlPatterns = "/userLogin")
public class UserLogin extends HttpServlet {
    AtomicInteger loginId = new AtomicInteger(0);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");

        Map<Integer, String> users = UserStorage.users;

        //  Check username exists
        for (String string : users.values()) {
            if (string.equals(username)) {
                System.out.println("Already exists");
                return;
            }
        }

        users.put(loginId.getAndIncrement(), username);
        request.getRequestDispatcher("WEB-INF/view/publicChat.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
