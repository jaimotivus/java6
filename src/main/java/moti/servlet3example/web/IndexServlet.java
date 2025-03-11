package moti.servlet3example.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index")
public class IndexServlet extends HtmlWriterServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HtmlWriter html = createHtmlWriter(req, resp);
        String message = getMessage(req, html);
        
        html.header()
            .p(message)
            .h(1, "Welcome to Servlet 3 Example")
            .p("Let's explore Java Servlet 3.x Features.")
            .ul(
                html.link("Index", "/index"),
                html.link("Hello", "/hello"),
                html.link("Table Display", "/tables"),
                html.link("Form", "/form"),
                html.link("Sys Props", "/sys-props"),
                html.link("List of users", "/user"),
                html.link("Thread Execution Information", "/thread-info")
            )
            .footer();
    }
    
    private String getMessage(HttpServletRequest req, HtmlWriter html) {
        // Using var for local variable declarations (Java 10+ feature)
        var message = "";
        var loginSession = LoginServlet.getOptionalLoginSession(req);
        if (loginSession != null) {
            message = "Welcome " + loginSession.getUsername() + 
                    "! You have been logged in since " + loginSession.getDateCreated();
            message += "(" + html.link("Logout", "/login?logout") + ")";
        }
        return message;
    }
}