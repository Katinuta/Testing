package by.htp.teploukhava.testing.security;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Admin on 28.09.16.
 */
@WebFilter (urlPatterns = {"/jsp/*"},
        initParams = {@WebInitParam(name="INDEX_PATH",value="/login.jsp")})
public class PageRedirectSecurityFilter implements Filter {
    private String indexPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        indexPath=filterConfig.getInitParameter("INDEX_PATH");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest= (HttpServletRequest) request;
        HttpServletResponse  httpResponse=(HttpServletResponse) response;
        httpResponse.sendRedirect(httpRequest.getContextPath()+indexPath);
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
