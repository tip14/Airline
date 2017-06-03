package tip14.airline.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String LOG_JSP_PATH = "WEB-INF/login.jsp";
	private static final String JSESSIONID = "JSESSIONID";
	private static final String AUTHORIZED = "authorized";
	private static final String LOGGED = "logged";
	private static final String AUTHORIZED_MSG = "You are LOGGED IN successfully";
	private static final String HOME_PAGE = "/airline";
	private static final String USER_MAIL = "userMail";
	private static final String SESSION_CREATED = "Session created for ";
	
	private static final Logger logger = Logger.getLogger(LoginServlet.class);


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher(LOG_JSP_PATH).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userMail = request.getAttribute(USER_MAIL).toString();
		HttpSession session = request.getSession(true);
		int sessionLiveInMinutes = 30;
		session.setMaxInactiveInterval(sessionLiveInMinutes*60);
		session.setAttribute(JSESSIONID, session.getId());
		session.setAttribute(AUTHORIZED, AUTHORIZED_MSG);
		session.setAttribute(LOGGED, LOGGED);
		session.setAttribute(USER_MAIL, userMail);
		
		logger.debug(SESSION_CREATED + userMail);
		
		response.sendRedirect(HOME_PAGE);
		
	}

}
