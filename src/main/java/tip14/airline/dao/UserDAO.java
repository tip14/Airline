package tip14.airline.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tip14.airline.model.User;

public class UserDAO {

	private Connection dbConnection = DBConnect.connect();
	private Statement statement = null;
	private String sql = "";

	public void createUser(String email, String password, String role) {

		sql = "INSERT INTO public.\"Users\" VALUES (" + "'" + email + "'" + "," + "'" + password + "'" + "," + "'"
				+ role + "'" + ");";

		try {
			statement = dbConnection.createStatement();
			statement.execute(sql);
			statement.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public List<User> readUsers() {
		List<User> userList = new ArrayList<User>();
		String mail;
		String password;
		String role;

		sql = "SELECT * FROM public.\"Users\";";
		try {
			statement = dbConnection.createStatement();
			ResultSet res = statement.executeQuery(sql);

			while (res.next()) {
				mail = res.getString("email");
				password = res.getString("password");
				role = res.getString("role");

				userList.add(new User(mail, password, role));
			}

			return userList;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userList;

	}

	public User readUserByEmail(String email) {
		User u = null;
		String mail;
		String password;
		String role;

		sql = "SELECT * FROM public.\"Users\" WHERE " + "email = '" + email + "';";

		try {
			statement = dbConnection.createStatement();
			ResultSet res = statement.executeQuery(sql);

			if (res.next()) {
				mail = res.getString("email");
				password = res.getString("password");
				role = res.getString("role");

				u = new User(mail, password, role);
			}

			return u;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;

	}

	public boolean doesUserExists(String email, String pass) {

		sql = "SELECT * FROM public.\"Users\" WHERE " + "email = '" + email + "' and password = '" + pass + "';";

		try {
			statement = dbConnection.createStatement();
			ResultSet res = statement.executeQuery(sql);

			if (res.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	public void deleteUser(String email) {

		sql = "DELETE FROM public.\"Users\" WHERE " + "email = '" + email + "';";

		try {
			statement = dbConnection.createStatement();
			statement.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
