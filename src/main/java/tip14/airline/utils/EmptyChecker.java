package tip14.airline.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class EmptyChecker {
	private static final Logger logger = Logger.getLogger(EmptyChecker.class);

	public static boolean isFieldsFilled(String... strings) {

		for (String string : strings) {

			if (StringUtils.isBlank(string)) {
				logger.debug("Some of strings is empty");
				return false;
			}
		}

		logger.debug("All fields are filled");

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

		logger.debug("Not filled fields were cheched");

		return filledFields;
	}

	public static boolean isListEmpty(List<String> list) {

		if (list != null) {
			if (list.size() > 0) {
				return false;
			}
		}
		
		logger.debug("List is empty");
		
		return true;
	}
	
	public static boolean isObjectNull(Object obj){
		
		return obj == null;
		
	}
}
