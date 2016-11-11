package by.htp.teploukhava.testing.filter;

import by.htp.teploukhava.testing.managers.HibernateUtil;
import org.hibernate.Session;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by Admin on 18.10.16.
 */
@WebFilter(urlPatterns = "/controller")
public class FilterSession implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Session session= HibernateUtil.getSession();
        System.out.println("Session get" + session.hashCode());
        chain.doFilter(request, response);
        System.out.println("Session close" + session.hashCode());
        HibernateUtil.closeSession();
    }

    @Override
    public void destroy() {

    }
}
