package tip14.airline.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import tip14.airline.utils.EmptyChecker;

@WebFilter("/login")
public class LoginFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		
		if(EmptyChecker.isFieldsFilled(email, pass)){
			chain.doFilter(request, response);
		} else {
			request.setAttribute("passFillingError", "email or password is empty");
			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
		}
		
		
	}

	public void destroy() {}
	
	public void init(FilterConfig fConfig) throws ServletException {}

}
