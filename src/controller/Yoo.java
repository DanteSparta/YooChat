package controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zw531129 on 15/4/5
 * So, what's the fucking copyright?
 */
@WebServlet(name = "yoo", displayName = "yoo", urlPatterns = "/yoo")
public class Yoo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AtomicInteger counts = (AtomicInteger) request.getServletContext().getAttribute("counts");
        counts.decrementAndGet();
        request.getRequestDispatcher("/WEB-INF/view/chatRoom.jsp").forward(request, response);
    }
}