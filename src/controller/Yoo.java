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
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zw531129 on 15/4/5
 * So, what's the fucking copyright?
 */
@WebServlet(name = "yoo", displayName = "yoo", urlPatterns = "/yoo")
public class Yoo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer count = (Integer) request.getServletContext().getAttribute("count");
        if (count > 0) {
            request.getServletContext().setAttribute("count", --count);
            request.getRequestDispatcher("/WEB-INF/view/chatRoom.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();

            User user = (User) session.getAttribute("user");
            Map<Integer, String> names = UserStorage.names;

            names.put(user.getId(), user.getName());
            session.setAttribute("error", "chatRoom is full");

            request.getRequestDispatcher("/WEB-INF/view/broadcast.jsp").forward(request, response);
        }
    }
}
