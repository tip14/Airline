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
	private static final String AUTHORIZED = "authorized";
	private static final String HOME_PAGE = "/airline";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute(JSESSIONID);
		request.getSession().removeAttribute(AUTHORIZED);

		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(JSESSIONID)) {
				cookie.setMaxAge(0);
				break;
			}
		}

		response.sendRedirect(HOME_PAGE);

	}

}
