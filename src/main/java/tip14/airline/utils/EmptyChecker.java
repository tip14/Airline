package tip14.airline.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class EmptyChecker {
	private static final Logger logger = Logger.getLogger(EmptyChecker.class);
	private static final String EMPTY_STRINGS = "Some of strings is empty";
	private static final String FILLED_FIELDS = "All fields are filled";
	private static final String EMPTY_FIELDS_CHECKED = "Not filled fields were cheched";
	private static final String EMPTY_LIST = "List is empty";

	public static boolean isFieldsFilled(String... strings) {

		for (String string : strings) {

			if (StringUtils.isBlank(string)) {
				logger.debug(EMPTY_STRINGS);
				return false;
			}
		}

		logger.debug(FILLED_FIELDS);

		return true;
	}

	public static boolean[] whatIsNotFilled(String... strings) {

		int stringsLength = strings.length;

		boolean[] filledFields = new boolean[stringsLength];

		for (int i = 0; i < stringsLength; i++) {
			if (StringUtils.isBlank(strings[i])) {
				filledFields[i] = false;
			} else {
				filledFields[i] = true;
			}
		}

		logger.debug(EMPTY_FIELDS_CHECKED);

		return filledFields;
	}

	public static boolean isListEmpty(List<String> list) {

		if (list != null) {
			if (list.size() > 0) {
				return false;
			}
		}

		logger.debug(EMPTY_LIST);

		return true;
	}

	public static boolean isObjectNull(Object obj) {

		return obj == null;

	}
}
