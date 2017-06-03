package tip14.airline.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import tip14.airline.utils.AccessDefiner;
import tip14.airline.utils.EmptyChecker;

@WebFilter("/*")
public class AuthentificationFilter implements Filter {

	private final Logger logger = Logger.getLogger(AuthentificationFilter.class);

	private static final String JSESSIONID = "JSESSIONID";
	private static final String HOME_PAGE = "/airline";
	private static final String UNAUTH_ACCESS = "Unauthorized access to ";
	private static final String UNAUTH_ATTR = "unauthorized";
	private static final String UNAUTH_MSG = "Please, log in for access this page";
	private static final String LOGIN_PAGE = "login";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		boolean JSESSIONIDisExists = !EmptyChecker.isObjectNull(httpRequest.getSession().getAttribute(JSESSIONID));

		if (JSESSIONIDisExists) {

			if (AccessDefiner.isLoginAccessAvailable(httpRequest.getRequestURI())) {
				chain.doFilter(request, response);
			} else {
				httpResponse.sendRedirect(HOME_PAGE);
			}

		} else {

			if (AccessDefiner.isLogoutAccessAvailable(httpRequest.getRequestURI())) {
				chain.doFilter(request, response);
			} else {
				logger.debug(UNAUTH_ACCESS + httpRequest.getRequestURI());
				request.setAttribute(UNAUTH_ATTR, UNAUTH_MSG);
				httpResponse.sendRedirect(LOGIN_PAGE);
			}
		}
	}

	public void destroy() {
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
