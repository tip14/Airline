package tip14.airline.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import tip14.airline.model.Plane;
import tip14.airline.storage.PlaneStorage;

@WebServlet("/dashboard")
public class DisplayServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(DisplayServlet.class);

	private static final String DASHBOARD_JSP_PATH = "WEB-INF/dashboard.jsp";
	private static final String PLANES_FOR_DISPLAY = "planesForDisplay";
	private static final String GET_REQ_RECEIVED = "GET request is recieved from ";

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.debug(GET_REQ_RECEIVED + request.getRemoteAddr());

		List<Plane> planesForDisplayList = PlaneStorage.getPlaneStorage();

		request.setAttribute(PLANES_FOR_DISPLAY, planesForDisplayList);
		request.getRequestDispatcher(DASHBOARD_JSP_PATH).forward(request, response);
	}
}
