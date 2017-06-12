package tip14.airline.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import tip14.airline.model.User;

public class UserDAO {

	private Connection dbConnection = DBConnect.connect();
	private Statement statement = null;
	private String sql = "";
	private static final Logger logger = Logger.getLogger(UserDAO.class);
	private static final String SQL_EXCEPTION = "Error in connectond to db: SQLException";
	private static final String SELECT_ALL_USERS = "SELECT * FROM public.\"Users\";";	
	private static final String USER_ADDED = "User was added to DB";
	private static final String EMAIL = "email";
	private static final String PASSWORD = "password";
	private static final String ROLE = "role";
	private static final String DELETED_SUCCESS = "User deleted succesfully with email: ";

	public void createUser(String email, String password, String role) {

		sql = "INSERT INTO public.\"Users\" VALUES (" + "'" + email + "'" + "," + "'" + password + "'" + "," + "'"
				+ role + "'" + ");";

		try {
			statement = dbConnection.createStatement();
			statement.execute(sql);
			logger.debug(USER_ADDED);
			statement.close();
		} catch (SQLException e) {
			logger.debug(SQL_EXCEPTION);
		}

	}

	public List<User> readUsers() {

		List<User> userList = new ArrayList<User>();
		String mail;
		String password;
		String role;

		try {
			statement = dbConnection.createStatement();
			ResultSet res = statement.executeQuery(SELECT_ALL_USERS);

			while (res.next()) {

				mail = res.getString(EMAIL);
				password = res.getString(PASSWORD);
				role = res.getString(ROLE);

				userList.add(new User(mail, password, role));
			}

			return userList;

		} catch (SQLException e) {
			logger.debug(SQL_EXCEPTION);
		}

		return userList;

	}

	public User readUserByEmail(String email) {

		User u = null;
		String mail;
		String password;
		String role;

		sql = "SELECT * FROM public.\"Users\" WHERE email = '" + email + "';";

		try {
			statement = dbConnection.createStatement();
			ResultSet res = statement.executeQuery(sql);

			if (res.next()) {
				mail = res.getString(EMAIL);
				password = res.getString(PASSWORD);
				role = res.getString(ROLE);

				u = new User(mail, password, role);
			}

			return u;

		} catch (SQLException e) {
			logger.debug(SQL_EXCEPTION);
		}
		return u;

	}

	public boolean doesUserExists(String email, String pass) {

		sql = "SELECT * FROM public.\"Users\" WHERE email = '" + email + "' and password = '" + pass + "';";

		try {
			statement = dbConnection.createStatement();
			ResultSet res = statement.executeQuery(sql);

			if (res.next()) {
				return true;
			}

		} catch (SQLException e) {
			logger.debug(SQL_EXCEPTION);
		}

		return false;

	}

	public void deleteUser(String email) {

		sql = "DELETE FROM public.\"Users\" WHERE email = '" + email + "';";

		try {
			statement = dbConnection.createStatement();
			statement.executeUpdate(sql);
			logger.debug(DELETED_SUCCESS + email);

		} catch (SQLException e) {
			logger.debug(SQL_EXCEPTION);
		}

	}

}
