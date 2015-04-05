package listener;

import model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zw531129 on 15/4/5
 * So, what's the fucking copyright?
 */
@WebListener
public class Listener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        final List<User> users = new ArrayList<>();
        ServletContext application = servletContextEvent.getServletContext();
        application.setAttribute("users", users);

        AtomicInteger counts = new AtomicInteger(1);
        application.setAttribute("counts", counts);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
