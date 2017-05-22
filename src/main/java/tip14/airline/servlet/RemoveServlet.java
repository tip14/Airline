package tip14.airline.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import tip14.airline.storage.PlaneStorage;

@WebServlet("/remove")
public class RemoveServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(RemoveServlet.class);
	
	private final String DASHBOARD_JSP_PATH = "WEB-INF/dashboard.jsp";
	private final String PLANE_MODEL_FOR_DELETE = "planeModelForDelete";
	private final String NOT_DELETED = "notdeleted";
	private final String NOT_DELETED_MESSAGE = "Error while deleting";
	private final String DELETED = "deleted";
	private final String DELETED_MESSAGE = "Deleted successful";
	
	
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String planeModel = request.getParameter(PLANE_MODEL_FOR_DELETE);
		try {
			logger.info("Trying to delete " + planeModel);
			PlaneStorage.deletePlane(planeModel);
		} catch (Exception e) {
			logger.error("Exception" + e.getClass().getSimpleName());
			request.setAttribute(NOT_DELETED, NOT_DELETED_MESSAGE);
			request.getRequestDispatcher(DASHBOARD_JSP_PATH).forward(request, response);
		}
		request.setAttribute(DELETED, DELETED_MESSAGE);
		request.getRequestDispatcher(DASHBOARD_JSP_PATH).forward(request, response);
	}
}
