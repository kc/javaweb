package com.example.listeners;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class ActiveUsersListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ActiveUsers.INSTANCE.increment();

        long count = ActiveUsers.INSTANCE.count();
        System.out.println("sessionCreated; activeUsers=" + count);
        se.getSession().setAttribute("activeUsers", count);
        se.getSession().setMaxInactiveInterval(10); // each session has a timeout of 10 seconds (inactivity)
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ActiveUsers.INSTANCE.decrement();

        long count = ActiveUsers.INSTANCE.count();
        System.out.println("sessionDestroyed; activeUsers=" + count);
        se.getSession().setAttribute("activeUsers", count);
    }
}
