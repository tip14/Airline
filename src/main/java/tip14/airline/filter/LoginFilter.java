package tip14.airline.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import tip14.airline.storage.UserStorage;
import tip14.airline.utils.EmptyChecker;

@WebFilter("/login")
public class LoginFilter implements Filter {

	private static final String EMAIL = "email";
	private static final String PASS = "pass";
	private static final String LOG_JSP_PATH = "WEB-INF/login.jsp";
	private static final String PASS_FILLING_ERROR_ATTR = "passFillingError";
	private static final String PASS_FILLING_ERROR_MESSAGE = "Email or password is empty";
	private static final String USER_DOESNT_EXISTS = "User with those credentials doesn't exist";
	private static final String NOT_EXISTS_ERROR_ATTR = "notExistsError";
	private static final String USER_MAIL = "userMail";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String email = request.getParameter(EMAIL);
		String pass = request.getParameter(PASS);

		if (EmptyChecker.isFieldsFilled(email, pass)) {

			boolean userExists = UserStorage.doesUserExist(email, pass);

			if (userExists) {

				request.setAttribute(USER_MAIL, email);
				chain.doFilter(request, response);

			} else {
				request.setAttribute(NOT_EXISTS_ERROR_ATTR, USER_DOESNT_EXISTS);
				request.getRequestDispatcher(LOG_JSP_PATH).forward(request, response);
			}

		} else {
			request.setAttribute(PASS_FILLING_ERROR_ATTR, PASS_FILLING_ERROR_MESSAGE);
			request.getRequestDispatcher(LOG_JSP_PATH).forward(request, response);
		}
	}

	public void destroy() {
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
