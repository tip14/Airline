package tip14.airline.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(HomeServlet.class);
	
	private final String HOME_JSP_PATH = "WEB-INF/home.jsp";

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.debug("GET request is recieved from "+request.getRemoteAddr());
		
		request.getRequestDispatcher(HOME_JSP_PATH).forward(request, response);

	}
}
