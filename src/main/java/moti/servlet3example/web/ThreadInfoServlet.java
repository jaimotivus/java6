package moti.servlet3example.web;



import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/thread-info")
public class ThreadInfoServlet  extends HtmlWriterServlet {    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {      
        ServletContext sc = req.getServletContext();
        // List<List<?>> rows = list(
        //     list("NAME", "VALUE"),
        //     list("Request Instance", req),
        //     list("Session Instance", req.getSession(false)),
        //     list("ServletContext Instance", sc),
        //     list("ThreadInfoServlet Instance", this),
        //     list("ServletContext Path", sc.getContextPath()),
        //     list("Singleton Application Instance", Application.getInstance()),
        //     list("Current Thread", Thread.currentThread()),
        //     list("Current Thread ContextClassLoader", Thread.currentThread().getContextClassLoader()),
        //     list("ContextClassLoader Tree", "<pre>" + getClassLoaderTreeInfo("") + "</pre>")
        // );
        
        // Using var for local variable (optional Java SE 21 feature)
        var html = createHtmlWriter(req, resp); 
        html.header()
            .h(1, "Thread Execution Information")
            // .table(rows)
            .footer();
    }

    public static String getClassLoaderTreeInfo(String indent) {
        // Using var for local variable (optional Java SE 21 feature)
        var classLoader = Thread.currentThread().getContextClassLoader();
        var result = new StringBuilder();
        getClassLoaderTreeInfo(classLoader, result, indent);
        return result.toString();
    }
    
    public static void getClassLoaderTreeInfo(ClassLoader classLoader, StringBuilder sb, String indent) {
        sb.append(indent);
        sb.append(String.format("ClassLoader: %s", classLoader));
        sb.append("\n");
        // Using var for local variable (optional Java SE 21 feature)
        var parent = classLoader.getParent();
        if (parent != null) {
            getClassLoaderTreeInfo(parent, sb, indent + "  ");
        }
    }
}