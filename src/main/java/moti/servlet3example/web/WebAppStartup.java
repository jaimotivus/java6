package moti.servlet3example.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import moti.servlet3example.service.Application;


@WebListener
public class WebAppStartup implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent event) {
        // Using var for local variable type inference (Java 10+)
        var app = Application.getInstance();
        app.init();
                
        // Since we are providing singleton access to application, storing
        // it into ServletContext is really redudant, but for demo purpose, one
        // can store it there and it can be access by all web components without
        // having to call the singleton access as alternative.
        event.getServletContext().setAttribute(Application.SERVLET_CONTEXT_KEY, app);

        
        logAppInfo(event);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // Using var for local variable type inference (Java 10+)
        var app = Application.getInstance();
        app.destroy();
  
    }

    private void logAppInfo(ServletContextEvent event) {
        // Using var for local variable type inference (Java 10+)
        var sc = event.getServletContext();
        var sb = new StringBuilder("Application Information:\n");
        sb.append(String.format("\tServerInfo: %s", sc.getServerInfo()));
        sb.append(String.format("\n\tServlet Version: %s.%s", sc.getMajorVersion(), sc.getMinorVersion()));
        sb.append(String.format("\n\tServletContext Instance: %s", sc));
        sb.append(String.format("\n\tServletContext Name: %s, Path: %s", sc.getServletContextName(), sc.getContextPath()));
        sb.append(String.format("\n\tSingleton Application Instance: %s", Application.getInstance()));
        sb.append(String.format("\n\tWebAppStartup Listener Instance: %s", this));
        sb.append(String.format("\n\tCurrent Thread: %s", Thread.currentThread()));
        sb.append(String.format("\n\tCurrent Thread ContextClassLoader: %s", Thread.currentThread().getContextClassLoader()));


    }
}