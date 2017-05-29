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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
		
	}

	private static final Logger logger = Logger.getLogger(CredentialsFilter.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String pass = request.getParameter("pass");

		boolean userExists = UserStorage.doesUserExist(email, pass);

		HttpSession session = request.getSession();

		if (userExists) {
			
			logger.debug("User " + email + " exists");
			int sessionLiveInMinutes = 30;
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("JSESSIONID")) {
					session.setAttribute("JSESSIONID", cookie.getValue());
					cookie.setMaxAge(sessionLiveInMinutes*60);
					response.addCookie(cookie);
					break;
				}
			}

			session.setMaxInactiveInterval(sessionLiveInMinutes * 60);

			session.setAttribute("authorized", "authorized");
			response.sendRedirect("/airline");
			
		} else {
			logger.debug("User with those credentials doesn't exist");
			request.setAttribute("notExistsError", "User with those credentials doesn't exist");
			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
		}

	}

}
