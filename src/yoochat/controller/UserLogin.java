package yoochat.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zw531129 on 15/3/31
 * So, what's the fucking copyright?
 */
@WebServlet(name = "userLogin", displayName = "userLogin", urlPatterns = "/userLogin")
public class UserLogin extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
