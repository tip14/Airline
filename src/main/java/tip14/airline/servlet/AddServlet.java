package tip14.airline.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import tip14.airline.model.Plane;
import tip14.airline.storage.PlaneStorage;
import tip14.airline.utils.EmptyChecker;

@WebServlet("/add")
public class AddServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(AddServlet.class);
	
	private static final long serialVersionUID = 1L;
	private final String EMPTY_ATTR = "empty";
	private final String ADD_JSP_PATH = "WEB-INF/add.jsp";
	private final String MODEL_PARAM = "model";
	private final String BUILD_DATE_PARAM = "build-date";
	private final String CAPACITY_PARAM = "capacity";
	private final String SUCCESS = "success";
	private final String SUCCESS_MESSAGE = "Plane was successfully added";
	private final String ERROR = "error";
	private final String ERROR_MESSAGE = "Error! not all fields filled";
	private final String ALL_FIELDS_FILLED = "All fields for plane adding were filled";
	private final String NOT_ALL_FIELDS_FILLED = "Not all fields for plane adding were filled";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.debug("GET request is recieved from "+request.getRemoteAddr());
		request.setAttribute(EMPTY_ATTR, EMPTY_ATTR);
		request.getRequestDispatcher(ADD_JSP_PATH).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("POST request is recieved from "+request.getRemoteAddr());
		String newPlaneModel = request.getParameter(MODEL_PARAM);
		String newPlaneBuildDate = request.getParameter(BUILD_DATE_PARAM);
		String capacity = request.getParameter(CAPACITY_PARAM);

		boolean allFieldsFilled = EmptyChecker.isFieldsFilled(newPlaneModel, newPlaneBuildDate, capacity);

		if (allFieldsFilled) {
			logger.debug(ALL_FIELDS_FILLED);
			
			int newPlaneCapacity = Integer.parseInt(capacity);

			Plane newPlane = new Plane(newPlaneModel, newPlaneCapacity, newPlaneBuildDate);
			PlaneStorage.addPlane(newPlane);

			request.setAttribute(SUCCESS, SUCCESS_MESSAGE);
			request.getRequestDispatcher(ADD_JSP_PATH).forward(request, response);

		} else {
			logger.debug(NOT_ALL_FIELDS_FILLED);
			request.setAttribute(ERROR, ERROR_MESSAGE);
			request.getRequestDispatcher(ADD_JSP_PATH).forward(request, response);
		}
	}
}
