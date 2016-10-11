package by.htp.teploukhava.testing.filter;



import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * Servlet Filter implementation class EcodingFilter
 */

@WebFilter( urlPatterns={"/controller"},
			initParams={
			@WebInitParam(name="ecoding", value="UTF-8", description="Encoding Param")})

public class EcodingFilter implements Filter {
	private String code;

	public void destroy() {
		code=null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String codeRequest=request.getCharacterEncoding();
		if(code!=null&&!code.equalsIgnoreCase(codeRequest)){
			request.setCharacterEncoding(code);
			response.setCharacterEncoding(code);
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		code=fConfig.getInitParameter("ecoding");
	}

}
