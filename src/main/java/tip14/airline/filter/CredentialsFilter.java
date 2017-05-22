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

import tip14.airline.utils.CredentialsChecker;

public class CredentialsFilter implements Filter {
	//DO LOGGGING
	//DO LOGGGING AND MAKE MORE INFORMATIVE
	//DO LOGGGING

	private static final Logger logger = Logger.getLogger(CredentialsFilter.class);
	private final String REG_JSP_PATH = "WEB-INF/registration.jsp";
	private final String EMAIL_FILLING_ERROR = "e-mail is not filled correctly";
	private final String PASS_FILLING_ERROR = "password length < 4";
	private final String PASS_REPEAT_FILLING_ERROR = "repeated password isn't same";
	private final String EMAIL = "email";
	private final String PASS = "pass";
	private final String PASS_REPEAT = "pass-repeat";
	private final String POST = "POST";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		if (httpRequest.getMethod().equals(POST)) {

			logger.debug("POST method data is going to process...");

			String email = request.getParameter(EMAIL);
			String pass = request.getParameter(PASS);
			String passRepeat = request.getParameter(PASS_REPEAT);

			if (CredentialsChecker.isCreadentialsValid(email, pass, passRepeat)) {

				logger.debug("Creadentials are valid");

				chain.doFilter(request, response);
				
			} else {

				logger.debug("Creadentials are not valid");

				boolean[] notValidCredentials = CredentialsChecker.whatIsNotValid(email, pass, passRepeat);

				if (notValidCredentials[0] == false) {
					request.setAttribute("emailFillingError", EMAIL_FILLING_ERROR);
				}
				if (notValidCredentials[1] == false) {
					request.setAttribute("passFillingError", PASS_FILLING_ERROR);
				}
				if (notValidCredentials[2] == false) {
					request.setAttribute("passRepeatFillingError", PASS_REPEAT_FILLING_ERROR);
				}

				request.getRequestDispatcher(REG_JSP_PATH).forward(request, response);
			}

		} else {
			logger.debug("GET method was retrieved to registration page without attributes");

			chain.doFilter(request, response);
		}
	}

	public void destroy() {
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
