package yoochat.listener;

import jdk.nashorn.internal.runtime.regexp.joni.ApplyCaseFoldArg;
import yoochat.model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zw531129 on 15/3/31
 * So, what's the fucking copyright?
 */
@WebListener
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        System.out.println("Init.........");
        //  Get application variable
        ServletContext application = servletContextEvent.getServletContext();
        //  Initialize users
        List<User> users = new ArrayList<User>();
        //  Set into application variable
        application.setAttribute("users", users);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //  Useless in this toy application
    }
}
