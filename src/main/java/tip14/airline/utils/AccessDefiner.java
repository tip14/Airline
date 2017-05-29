package tip14.airline.utils;

public class AccessDefiner {

	private static final String LOGIN_PAGE = "/airline/login";
	private static final String REG_PAGE = "/airline/registration";
	private static final String HOME_PAGE = "/airline/";
	private static final String FILES_EXTENTION_PATTERN = ".*(css|jpg|png|gif|js)$";

	public static boolean isLoginAccessAvailable(String url) {
		if (url.equals(LOGIN_PAGE) || url.equals(REG_PAGE)) {
			return false;
		}
		return true;
	}

	public static boolean isLogoutAccessAvailable(String url) {
		if (url.equals(HOME_PAGE) || url.equals(LOGIN_PAGE) || url.equals(REG_PAGE)
				|| url.matches(FILES_EXTENTION_PATTERN)) {
			return true;
		}
		return false;
	}

}
