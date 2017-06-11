package tip14.airline.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import tip14.airline.dao.PlaneDAO;
import tip14.airline.utils.EmptyChecker;

@WebServlet("/add")
public class AddServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(AddServlet.class);

	private static final long serialVersionUID = 1L;
	private static final String EMPTY_ATTR = "empty";
	private static final String ADD_JSP_PATH = "WEB-INF/add.jsp";
	private static final String MODEL_PARAM = "model";
	private static final String BUILD_DATE_PARAM = "build-date";
	private static final String CAPACITY_PARAM = "capacity";
	private static final String SUCCESS = "success";
	private static final String SUCCESS_MESSAGE = "Plane was successfully added";
	private static final String ERROR = "error";
	private static final String ERROR_MESSAGE = "Error! not all fields filled";
	private static final String ALL_FIELDS_FILLED = "All fields for plane adding were filled";
	private static final String NOT_ALL_FIELDS_FILLED = "Not all fields for plane adding were filled";
	private static final String GET_REQ_RECEIVED = "GET request is recieved from ";
	private static final String POST_REQ_RECEIVED = "POST request is recieved from ";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.debug(GET_REQ_RECEIVED + request.getRemoteAddr());
		request.setAttribute(EMPTY_ATTR, EMPTY_ATTR);
		request.getRequestDispatcher(ADD_JSP_PATH).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug(POST_REQ_RECEIVED + request.getRemoteAddr());
		String newPlaneModel = request.getParameter(MODEL_PARAM);
		String newPlaneBuildDate = request.getParameter(BUILD_DATE_PARAM);
		String capacity = request.getParameter(CAPACITY_PARAM);

		boolean allFieldsFilled = EmptyChecker.isFieldsFilled(newPlaneModel, newPlaneBuildDate, capacity);

		if (allFieldsFilled) {
			logger.debug(ALL_FIELDS_FILLED);

			int newPlaneCapacity = Integer.parseInt(capacity);
			PlaneDAO pd = new PlaneDAO();
			pd.createPlane(newPlaneModel, newPlaneCapacity, newPlaneBuildDate);

			request.setAttribute(SUCCESS, SUCCESS_MESSAGE);
			request.getRequestDispatcher(ADD_JSP_PATH).forward(request, response);

		} else {
			logger.debug(NOT_ALL_FIELDS_FILLED);
			request.setAttribute(ERROR, ERROR_MESSAGE);
			request.getRequestDispatcher(ADD_JSP_PATH).forward(request, response);
		}
	}
}
