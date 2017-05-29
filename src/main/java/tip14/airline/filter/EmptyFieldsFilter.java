package tip14.airline.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import tip14.airline.utils.EmptyChecker;

@WebFilter("/registration")
public class EmptyFieldsFilter implements Filter {

	private static final Logger logger = Logger.getLogger(EmptyFieldsFilter.class);
	private static final String REG_JSP_PATH = "WEB-INF/registration.jsp";
	private static final String EMAIL_EMPTY_ERROR = "email is empty";
	private static final String PASS_EMPTY_ERROR = "password is empty";
	private static final String REPEAT_PASS_EMPTY_ERROR = "repeated password is empty";
	private static final String EMAIL_FILLING_ERROR = "emailFillingError";
	private static final String PASS_FILLING_ERROR = "passFillingError";
	private static final String REPEAT_PASS_FILLING_ERROR = "passRepeatFillingError";
	private static final String EMAIL = "email";
	private static final String PASS = "pass";
	private static final String PASS_REPEAT = "pass-repeat";
	private static final String POST = "POST";
	private static final String GET_METHOD = "GET method. Request redirected to registration page without attributes";
	private static final String POST_METHOD = "POST method. Data is going to process";

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
					logger.debug(EMAIL_EMPTY_ERROR);
					request.setAttribute(EMAIL_FILLING_ERROR, EMAIL_EMPTY_ERROR);
				}
				if (notFilledFields[1] == false) {
					logger.debug(PASS_EMPTY_ERROR);
					request.setAttribute(PASS_FILLING_ERROR, PASS_EMPTY_ERROR);
				}
				if (notFilledFields[2] == false) {
					logger.debug(REPEAT_PASS_EMPTY_ERROR);
					request.setAttribute(REPEAT_PASS_FILLING_ERROR, REPEAT_PASS_EMPTY_ERROR);
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
