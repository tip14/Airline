package tip14.airline.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import tip14.airline.storage.Storage;


public class EmptyChecker {
	private static final Logger logger = Logger.getLogger(Storage.class);
	
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
	
	public static boolean isListEmpty(List<String> list) {

		if(list!=null){
			if(list.size()>0){
				return false;
			}
		}

		return true;

	}

}
