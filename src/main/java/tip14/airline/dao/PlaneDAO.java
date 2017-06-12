package tip14.airline.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import tip14.airline.model.Plane;

public class PlaneDAO {

	private Connection dbConnection = DBConnect.connect();
	private Statement statement = null;
	private String sql = "";
	private static final Logger logger = Logger.getLogger(PlaneDAO.class);
	private static final String SELECT_PLANES = "SELECT * FROM public.\"Planes\";";
	
	private static final String MODEL = "model";
	private static final String CAPACITY = "capacity";
	private static final String BUILD_DATE = "buildDate";
	private static final String SQL_EXCEPTION = "Error in connectond to db: SQLException";
	private static final String PLANE_ADDED = "Plane was added with model: ";

	public void createPlane(String model, int capacity, String buildDate) {

		sql = "INSERT INTO public.\"Planes\" VALUES (" + "'" + model + "'" + "," + "'" + capacity + "'" + "," + "'"
				+ buildDate + "'" + ");";

		try {
			statement = dbConnection.createStatement();
			statement.execute(sql);
			statement.close();
			
			logger.debug(PLANE_ADDED + model);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public List<Plane> readPlanes() {
		List<Plane> PlaneList = new ArrayList<Plane>();

		String model;
		int capacity;
		String buildDate;

		try {
			statement = dbConnection.createStatement();
			ResultSet res = statement.executeQuery(SELECT_PLANES);

			while (res.next()) {

				model = res.getString(MODEL);
				capacity = Integer.parseInt(res.getString(CAPACITY));
				buildDate = res.getString(BUILD_DATE);

				PlaneList.add(new Plane(model, capacity, buildDate));
			}

			statement.close();

			return PlaneList;

		} catch (SQLException e) {
			logger.debug(SQL_EXCEPTION);
		}
		return PlaneList;

	}

	public List<Plane> readPlanesByModel(String model) {
		List<Plane> planeList = new ArrayList<Plane>();

		int capacity;
		String buildDate;

		sql = "SELECT * FROM public.\"Planes\" WHERE model like '%" + model + "%';";

		try {
			statement = dbConnection.createStatement();
			ResultSet res = statement.executeQuery(sql);

			while (res.next()) {
				model = res.getString(MODEL);
				capacity = Integer.parseInt(res.getString(CAPACITY));
				buildDate = res.getString(BUILD_DATE);

				planeList.add(new Plane(model, capacity, buildDate));
			}

			statement.close();

			return planeList;

		} catch (SQLException e) {
			logger.debug(SQL_EXCEPTION);
		}
		return planeList;

	}

	public void deletePlane(String model) {

		sql = "DELETE FROM public.\"Planes\" WHERE " + "model = '" + model + "';";
		try {
			statement = dbConnection.createStatement();
			statement.executeUpdate(sql);
			statement.close();

		} catch (SQLException e) {
			logger.debug(SQL_EXCEPTION);
		}

	}

}
