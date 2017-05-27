package tip14.airline.storage;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import tip14.airline.model.User;

public class UserStorage {

	private static final Logger logger = Logger.getLogger(UserStorage.class);

	private static List<User> userStorage = new ArrayList<User>();

	public static List<User> getUserStorage() {
		return userStorage;
	}

	public static void addUser(User user) {
		userStorage.add(user);
		logger.info("User " + user.getEmail() + " was added to storage");
	}

	public static User getUser(String email, String pass) {
		User selectedUser;
		for (User user : userStorage) {
			if (user.getEmail().equals(email) && user.getPassword().equals(pass)) {
				selectedUser = user;
				return selectedUser;
			}
		}
		return null;
	}

	public static boolean doesUserExist(String email, String pass) {
		if (getUser(email, pass) != null) {
			return true;
		}
		return false;
	}
}
