package tip14.airline.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String JSESSIONID = "JSESSIONID";
	private static final String LOGGED = "logged";
	private static final String UNAUTHORIZED = "unauthorized";
	private static final String UNAUTHORIZED_MSG = "You are LOGGED OUT successfully";
	private static final String HOME_JSP_PATH = "WEB-INF/home.jsp";
	private static final String USER_MAIL = "userMail";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getSession().removeAttribute(USER_MAIL);
		request.getSession().removeAttribute(JSESSIONID);
		request.getSession().removeAttribute(LOGGED);
		request.setAttribute(UNAUTHORIZED, UNAUTHORIZED_MSG);

		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(JSESSIONID)) {
				cookie.setMaxAge(0);
				break;
			}
		}

		request.getRequestDispatcher(HOME_JSP_PATH).forward(request, response);

	}

}
