package moti.servlet3example.web;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import moti.servlet3example.service.Application;
import moti.servlet3example.service.Config;

@WebServlet("/sys-props")
public class SysPropsServlet extends HtmlWriterServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Using var for local variable type inference (Java 10+ feature)
        var appConfig = new TreeMap<String, String>();
        var config = Application.getInstance().getConfig();
        for (String key : config.getKeys()) {
            appConfig.put(key, config.getValue(key));
        }
        
        // Using var for local variable type inference
        var sysProps = new TreeMap<Object, Object>(System.getProperties());
        
        HtmlWriter html = createHtmlWriter(req, resp);
        html.header()
            .h(1, "Application Config Properties")
            .table(appConfig)
            .h(1, "Java System Properties")
            .table(sysProps)
            .footer();
    }
}