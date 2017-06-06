package tip14.airline.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	private static final String DRIVER = "org.postgresql.Driver";
	private static final String CREDENTIALS = "postgres";
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/Airline";

	public static Connection connect() {
		Connection connection = null;
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(DB_URL, CREDENTIALS, CREDENTIALS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;

	}

}
