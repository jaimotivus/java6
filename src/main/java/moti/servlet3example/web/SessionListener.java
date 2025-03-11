package moti.servlet3example.web;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Just a session listener demo that LOG a message whenever a HTTP session object is created.
 * @author zedeng
 */
@WebListener
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // No changes needed for Java 21 upgrade
        // Could use var here, but the original code doesn't have a local variable declaration
        System.out.printf("Session created %s", se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // No changes needed for Java 21 upgrade
        // Could use var here, but the original code doesn't have a local variable declaration
        System.out.printf("Session destroyed %s", se.getSession());
    }
}