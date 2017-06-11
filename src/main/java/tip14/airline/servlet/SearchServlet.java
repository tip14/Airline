package tip14.airline.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import tip14.airline.dao.PlaneDAO;
import tip14.airline.model.Plane;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(SearchServlet.class);

	private static final String SEARCH_FIELD = "search-field";
	private static final String NOT_FOUND = "notfound";
	private static final String NOT_FOUND_MESSAGE = "Nothing found for: ";
	private static final String PLANES_FOUND = "planesFound";
	private static final String DASHBOARD_JSP_PATH = "WEB-INF/dashboard.jsp";
	private static final String POST_REQ_RECEIVED = "POST request is recieved from ";
	private static final String PLANES_FOUND_MESSAGE = "Planes found for ";

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.debug(POST_REQ_RECEIVED + request.getRemoteAddr());

		String modelForSearch = request.getParameter(SEARCH_FIELD);
		PlaneDAO pd = new PlaneDAO();

		List<Plane> foundPlanesList = pd.readPlanesByModel(modelForSearch);

		if (foundPlanesList.isEmpty()) {
			request.setAttribute(NOT_FOUND, NOT_FOUND_MESSAGE + modelForSearch);
			logger.info(NOT_FOUND_MESSAGE + modelForSearch);
		} else {
			logger.info(PLANES_FOUND_MESSAGE + modelForSearch);
			request.setAttribute(PLANES_FOUND, foundPlanesList);
		}

		request.getRequestDispatcher(DASHBOARD_JSP_PATH).forward(request, response);

	}
}
