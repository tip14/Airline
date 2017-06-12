package tip14.airline.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DBConnect {

	private static final Logger logger = Logger.getLogger(DBConnect.class);
	private static final String DRIVER = "org.postgresql.Driver";
	private static final String CREDENTIALS = "postgres";
	private static final String CONNECTED_TO_DB = "Connected to Database";
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/Airline";
	private static final String CLASS_NOT_FOUND = "Error in connectond to db: ClassNotFoundException";
	private static final String SQL_EXCEPTION = "Error in connectond to db: SQLException";

	public static Connection connect() {
		Connection connection = null;
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(DB_URL, CREDENTIALS, CREDENTIALS);
			logger.debug(CONNECTED_TO_DB);
		} catch (ClassNotFoundException e) {
			logger.debug(CLASS_NOT_FOUND);
		} catch (SQLException e) {
			logger.debug(SQL_EXCEPTION);
		}
		return connection;

	}

}
