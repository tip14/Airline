package tip14.airline.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import tip14.airline.model.User;
import tip14.airline.storage.Storage;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(RegistrationServlet.class);
	private final String REG_JSP_PATH = "WEB-INF/registration.jsp";
	private final String HOME_JSP_PATH = "WEB-INF/home.jsp";
	private final String SUCCESS_REG = "Registration was successful";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getRequestDispatcher(REG_JSP_PATH).forward(request, response);
		
		logger.debug("Retrieved to registration page");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		String role = request.getParameter("role");
		
		Storage.addUser(new User(email, pass, role));
		
		logger.debug("New user was added");
		
		request.setAttribute("regSuccess", SUCCESS_REG);
		request.getRequestDispatcher(HOME_JSP_PATH).forward(request, response);
		
		logger.debug("Retrieved to home page");
		
	}

}
