package tip14.airline.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter({ "/add", "/dashboard" })
public class AuthentificationFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		if(session is valid){
			chain.doFilter(request, response);
		} else {
			request.setAttribute("unauthorized", "Please, log in for access this page");
			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
		}

	public void destroy() {
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
