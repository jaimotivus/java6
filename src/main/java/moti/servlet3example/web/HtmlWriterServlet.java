package moti.servlet3example.web;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class HtmlWriterServlet extends HttpServlet {

    protected static final long serialVersionUID = 1L;
    
    protected HtmlWriter createHtmlWriter(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // Create a HtmlWriter that's customized for this application, such
            // as header and footer.
            HtmlWriter writer = new HtmlWriter();
            writer.setWriter(resp.getWriter());
            writer.setContextPath(req.getContextPath());
            writer.setHeader(header(writer));
            writer.setFooter(footer(writer));
            return writer;
        } catch (IOException e) {
            throw new RuntimeException("Failed to create HtmlWriter.", e);
        }
    }
    
    protected String header(HtmlWriter html) {
        String contextPath = getServletContext().getContextPath();
        // Using var for local variable type inference (Java 10+)
        var sb = new StringBuilder();
        sb.append("<!DOCTYPE html>");
        sb.append("<html>");
        sb.append("<header>");
        sb.append("  <link rel='stylesheet' type='text/css' href='" + contextPath + "/main.css'>");
        sb.append("</header>");
        sb.append("<body>");
        sb.append("<ul class='navlist'>");
        sb.append("<li>" + html.link("Home", "/index") + "</li>");
        sb.append("<li>" + html.link("Users", "/user") + "</li>");
        sb.append("<li>" + html.link("System", "/sys-props") + "</li>");
        sb.append("</ul>");
        return sb.toString();
    }

    protected String footer(HtmlWriter html) {
        // Using var for local variable type inference (Java 10+)
        var sb = new StringBuilder();
        sb.append("<body>");
        sb.append("</html>");
        return sb.toString();
    }
}