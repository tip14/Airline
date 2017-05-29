package tip14.airline.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class CredentialsChecker {

	private static final Logger logger = Logger.getLogger(CredentialsChecker.class);
	private static final String EMAIL_PATTERN = "[a-z_A-Z.0-9]{4,31}@[a-zA-Z]+[a-zA-Z0-9]*.[a-zA-Z]{2,3}";
	private static final String EMAIL_VALIDITY_CHECKED = "Email validity was checked";
	private static final String PASS_VALIDITY_CHECKED = "Password validity was checked";
	private static final String REPEAT_PASS_VALIDITY_CHECKED = "Repeat password validity was checked";
	private static final String CREDENTIALS_VALIDITY_CHECKED = "Credentials validity was checked";
	private static final String NOT_VALID_CREDENTIALS_CHECKED = "Not valid credentials were checked";

	public static boolean isEmailValid(String email) {

		Pattern pt = Pattern.compile(EMAIL_PATTERN);
		Matcher match = pt.matcher(email);

		logger.debug(EMAIL_VALIDITY_CHECKED);

		return match.matches();
	}

	public static boolean isPassworValid(String pass) {

		int passLength = pass.length();

		logger.debug(PASS_VALIDITY_CHECKED);

		return passLength >= 4 && passLength <= 26;
	}

	public static boolean isRepeatPasswordValid(String pass, String repeatPass) {

		logger.debug(REPEAT_PASS_VALIDITY_CHECKED);

		return pass.equals(repeatPass);
	}

	public static boolean isCreadentialsValid(String email, String pass, String repeatPass) {

		boolean validEmail = isEmailValid(email);
		boolean validPassword = isPassworValid(pass);
		boolean validRepeatPassword = isRepeatPasswordValid(pass, repeatPass);

		logger.debug(CREDENTIALS_VALIDITY_CHECKED);

		return validEmail && validPassword && validRepeatPassword;
	}

	public static boolean[] whatIsNotValid(String... credentials) {

		int stringsLength = credentials.length;

		boolean[] validFields = new boolean[stringsLength];

		validFields[0] = isEmailValid(credentials[0]);
		validFields[1] = isPassworValid(credentials[1]);
		validFields[2] = isRepeatPasswordValid(credentials[1], credentials[2]);

		logger.debug(NOT_VALID_CREDENTIALS_CHECKED);

		return validFields;

	}

}
