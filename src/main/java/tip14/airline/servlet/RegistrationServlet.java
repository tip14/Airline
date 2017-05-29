package tip14.airline.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import tip14.airline.model.User;
import tip14.airline.storage.UserStorage;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(RegistrationServlet.class);

	private static final String REG_JSP_PATH = "WEB-INF/registration.jsp";
	private static final String HOME_JSP_PATH = "WEB-INF/home.jsp";
	private static final String SUCCESS_REG = "Registration was successful";
	private static final String TO_HOME_PAGE = "Redirected to home page";
	private static final String TO_REG_PAGE = "Redirected to registration page";
	private static final String EMAIL = "email";
	private static final String PASS = "pass";
	private static final String ROLE = "role";
	private static final String USER_ADDED = "User added with email";
	private static final String SUCCESS_REG_ATTR = "regSuccess";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher(REG_JSP_PATH).forward(request, response);

		logger.debug(TO_REG_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter(EMAIL);
		String pass = request.getParameter(PASS);
		String role = request.getParameter(ROLE);

		UserStorage.addUser(new User(email, pass, role));

		logger.debug(USER_ADDED + email);

		request.setAttribute(SUCCESS_REG_ATTR, SUCCESS_REG);
		request.getRequestDispatcher(HOME_JSP_PATH).forward(request, response);

		logger.debug(TO_HOME_PAGE);

	}

}
