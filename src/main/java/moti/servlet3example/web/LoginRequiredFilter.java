package moti.servlet3example.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


/**
 * Restrict web resources to require users to be authenticated and LoginSession
 * token to be present.
 * 
 * @author zedeng
 */
@WebFilter(urlPatterns={"/sys-props", "/user"})
public class LoginRequiredFilter implements Filter {
    
    public static final String LOGIN_REDIRECT = "LOGIN_REDIRECT";
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            // Using var for local variable type inference (Java 10+)
            var req = (HttpServletRequest) request;
            System.out.printf("Checking LoginSession token for uri=%s", req.getRequestURI());
            LoginSession loginSession = LoginServlet.getOptionalLoginSession(req);
            if (loginSession == null) {
                System.out.println("No LoginSession token found; forwarding request to login page.");
                // We need to save the old URI so we can auto redirect after login.
                req.setAttribute(LOGIN_REDIRECT, req.getRequestURI());
                req.getRequestDispatcher("/login").forward(request, response);
                return;
            } else {
                System.out.printf("Request allowed using LoginSession token=%s", loginSession.getId());
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Do nothing.
    }

}