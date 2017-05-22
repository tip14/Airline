package tip14.airline.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import tip14.airline.utils.EmptyChecker;

public class EmptyFieldsFilter implements Filter {
	//DO LOGGGING
	//DO LOGGGING
	//DO LOGGGING

	private static final Logger logger = Logger.getLogger(EmptyFieldsFilter.class);
	private final String REG_JSP_PATH = "WEB-INF/registration.jsp";
	private final String EMAIL_EMPTY_ERROR = "email is empty";
	private final String PASS_EMAIL_EMPTY_ERROR_ERROR = "password is empty";
	private final String PASS_EMAIL_EMPTY_ERROR_FILLING_ERROR = "repeated password is empty";
	private final String EMAIL = "email";
	private final String PASS = "pass";
	private final String PASS_REPEAT = "pass-repeat";
	private final String POST = "POST";
	private final String GET_METHOD = "GET method. Request redirected to registration page without attributes";
	private final String POST_METHOD = "POST method. Data is going to process";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		if (httpRequest.getMethod().equals(POST)) {

			logger.debug(POST_METHOD);
			
			String email = request.getParameter(EMAIL);
			String pass = request.getParameter(PASS);
			String passRepeat = request.getParameter(PASS_REPEAT);

			if (!EmptyChecker.isFieldsFilled(email, pass, passRepeat)) {

				boolean[] notFilledFields = EmptyChecker.whatIsNotFilled(email, pass, passRepeat);

				if (notFilledFields[0] == false) {
					logger.debug("email is empty");
					request.setAttribute("emailFillingError", EMAIL_EMPTY_ERROR);
				}
				if (notFilledFields[1] == false) {
					logger.debug("password is empty");
					request.setAttribute("passFillingError", PASS_EMAIL_EMPTY_ERROR_ERROR);
				}
				if (notFilledFields[2] == false) {
					logger.debug("repeated password is empty");
					request.setAttribute("passRepeatFillingError", PASS_EMAIL_EMPTY_ERROR_FILLING_ERROR);
				}

				request.getRequestDispatcher(REG_JSP_PATH).forward(request, response);
				
			} else {
				
				chain.doFilter(request, response);
			}

		} else {
			logger.debug(GET_METHOD);
			
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}
}