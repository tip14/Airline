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

import tip14.airline.utils.CredentialsChecker;

@WebFilter("/registration")
public class CredentialsFilter implements Filter {

	private static final Logger logger = Logger.getLogger(CredentialsFilter.class);
	private static final String REG_JSP_PATH = "WEB-INF/registration.jsp";
	private static final String EMAIL_FILLING_ERROR = "e-mail is not filled correctly";
	private static final String PASS_FILLING_ERROR = "password length < 4";
	private static final String PASS_REPEAT_FILLING_ERROR = "repeated password isn't same";
	private static final String EMAIL = "email";
	private static final String PASS = "pass";
	private static final String PASS_REPEAT = "pass-repeat";
	private static final String POST = "POST";
	private static final String GET_METHOD = "GET method. Request redirected to registration page without attributes";
	private static final String POST_METHOD = "POST method. Data is going to process";
	private static final String VALID_CREDENTIALS = "All creadentials are valid";
	private static final String NOT_VALID_CREDENTIALS = "Creadentials are not valid";
	private static final String NOT_VALID_EMAIL = "not valid email: ";
	private static final String EMAIL_FILLING_ERROR_ATTR = "emailFillingError";
	private static final String NOT_VALID_PASS = "not valid pass: ";
	private static final String PASS_FILLING_ERROR_ATTR = "passFillingError";
	private static final String NOT_VALID_REPEAT_PASS = "not valid repeated pass: ";
	private static final String REPEAT_PASS_FILLING_ERROR_ATTR = "passRepeatFillingError";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		if (httpRequest.getMethod().equals(POST)) {

			logger.debug(POST_METHOD);

			String email = request.getParameter(EMAIL);
			String pass = request.getParameter(PASS);
			String passRepeat = request.getParameter(PASS_REPEAT);

			if (CredentialsChecker.isCreadentialsValid(email, pass, passRepeat)) {

				logger.debug(VALID_CREDENTIALS);

				chain.doFilter(request, response);

			} else {

				logger.debug(NOT_VALID_CREDENTIALS);

				boolean[] notValidCredentials = CredentialsChecker.whatIsNotValid(email, pass, passRepeat);

				if (notValidCredentials[0] == false) {
					logger.debug(NOT_VALID_EMAIL + email);
					request.setAttribute(EMAIL_FILLING_ERROR_ATTR, EMAIL_FILLING_ERROR);
				}
				if (notValidCredentials[1] == false) {
					logger.debug(NOT_VALID_PASS + pass);
					request.setAttribute(PASS_FILLING_ERROR_ATTR, PASS_FILLING_ERROR);
				}
				if (notValidCredentials[2] == false) {
					logger.debug(NOT_VALID_REPEAT_PASS + passRepeat);
					request.setAttribute(REPEAT_PASS_FILLING_ERROR_ATTR, PASS_REPEAT_FILLING_ERROR);
				}
				request.getRequestDispatcher(REG_JSP_PATH).forward(request, response);
			}

		} else {

			logger.debug(GET_METHOD);

			chain.doFilter(request, response);
		}
	}

	public void destroy() {
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
