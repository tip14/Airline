package tip14.airline.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import tip14.airline.filter.CredentialsFilter;
import tip14.airline.storage.UserStorage;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String LOG_JSP_PATH = "WEB-INF/login.jsp";
	private static final String EMAIL = "email";
	private static final String PASS = "pass";
	private static final String USER_EXISTS = "User exists with email";
	private static final String JSESSIONID = "JSESSIONID";
	private static final String AUTHORIZED = "authorized";
	private static final String LOGGED = "logged";
	private static final String AUTHORIZED_MSG = "You are LOGGED IN successfully";
	private static final String HOME_PAGE = "/airline";
	private static final String USER_DOESNT_EXISTS = "User with those credentials doesn't exist";
	private static final String NOT_EXISTS_ERROR_ATTR = "notExistsError";
	private static final String USER_MAIL = "userMail";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher(LOG_JSP_PATH).forward(request, response);

	}

	private static final Logger logger = Logger.getLogger(CredentialsFilter.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter(EMAIL);
		String pass = request.getParameter(PASS);

		boolean userExists = UserStorage.doesUserExist(email, pass);

		HttpSession session = request.getSession();

		if (userExists) {

			logger.debug(USER_EXISTS + email);
			int sessionLiveInMinutes = 30;
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(JSESSIONID)) {
					session.setAttribute(JSESSIONID, cookie.getValue());
					cookie.setMaxAge(sessionLiveInMinutes * 60);
					response.addCookie(cookie);
					break;
				}
			}

			session.setMaxInactiveInterval(sessionLiveInMinutes * 60);
			session.setAttribute(LOGGED, LOGGED);
			session.setAttribute(USER_MAIL, email);		
			
			session.setAttribute(AUTHORIZED, AUTHORIZED_MSG);
			
			response.sendRedirect(HOME_PAGE);

		} else {
			logger.debug(USER_DOESNT_EXISTS);
			request.setAttribute(NOT_EXISTS_ERROR_ATTR, USER_DOESNT_EXISTS);
			request.getRequestDispatcher(LOG_JSP_PATH).forward(request, response);
		}

	}

}
