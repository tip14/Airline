package tip14.airline.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import tip14.airline.utils.AccessDefiner;
import tip14.airline.utils.EmptyChecker;

@WebFilter("/*")
public class AuthentificationFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		Logger logger = Logger.getLogger(AuthentificationFilter.class);

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		boolean JSESSIONIDisExists = !EmptyChecker.isObjectNull(httpRequest.getSession().getAttribute("JSESSIONID"));
		boolean CookiesAreExist = !EmptyChecker.isObjectNull(httpRequest.getCookies());

		if (JSESSIONIDisExists && CookiesAreExist) {

			String sessionId = httpRequest.getSession().getAttribute("JSESSIONID").toString();
			String cookieId = null;

			Cookie[] cookies = httpRequest.getCookies();
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("JSESSIONID")) {
					cookieId = cookie.getValue();
					break;
				}
			}

			if (sessionId.equals(cookieId)) {
				logger.debug("Successful authorization for " + sessionId);
				if (AccessDefiner.isLoginAccessAvailable(httpRequest.getRequestURI())) {
					chain.doFilter(request, response);
				} else {
					httpResponse.sendRedirect("/airline");
				}

			} else {
				logger.debug("Unauthorized access to " + httpRequest.getRequestURI());
				request.setAttribute("unauthorized", "Please, log in for access this page");
				httpResponse.sendRedirect("login");
			}

		} else {
			
			if (AccessDefiner.isLogoutAccessAvailable(httpRequest.getRequestURI())) {
				chain.doFilter(request, response);
			} else {
				logger.debug("Unauthorized access to " + httpRequest.getRequestURI());
				request.setAttribute("unauthorized", "Please, log in for access this page");
				httpResponse.sendRedirect("login");
			}	
		}
	}

	public void destroy() {
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
