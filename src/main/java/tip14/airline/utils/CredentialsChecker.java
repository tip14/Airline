package tip14.airline.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class CredentialsChecker {
	
	private static final Logger logger = Logger.getLogger(CredentialsChecker.class);

	public static boolean isEmailValid(String email) {

		Pattern pt = Pattern.compile("[a-z_A-Z.0-9]{4,31}@[a-zA-Z]+[a-zA-Z0-9]*.[a-zA-Z]{2,3}");
		Matcher match = pt.matcher(email);
		
		logger.debug("Email validity was checked");

		return match.matches();
	}

	public static boolean isPassworValid(String pass) {
		
		int passLength = pass.length();
		
		logger.debug("Password validity was checked");
		
		return passLength > 4 && passLength < 26;
	}

	public static boolean isRepeatPasswordValid(String pass, String repeatPass) {
		
		logger.debug("Repeat password validity was checked");
		
		return pass.equals(repeatPass);
	}

	public static boolean isCreadentialsValid(String email, String pass, String repeatPass) {

		boolean validEmail = isEmailValid(email);
		boolean validPassword = isPassworValid(pass);
		boolean validRepeatPassword = isRepeatPasswordValid(pass, repeatPass);
		
		logger.debug("Credentials validity was checked");

		return validEmail && validPassword && validRepeatPassword;
	}

	public static boolean[] whatIsNotValid(String... credentials) {

		int stringsLength = credentials.length;

		boolean[] validFields = new boolean[stringsLength];

		validFields[0] = isEmailValid(credentials[0]);
		validFields[1] = isPassworValid(credentials[1]);
		validFields[2] = isRepeatPasswordValid(credentials[1], credentials[2]);
		
		logger.debug("Not valid credentials were checked");

		return validFields;

	}

}
