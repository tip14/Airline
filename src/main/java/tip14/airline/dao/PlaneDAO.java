package tip14.airline.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tip14.airline.model.Plane;

public class PlaneDAO {

	private Connection dbConnection = DBConnect.connect();
	private Statement statement = null;
	private String sql = "";

	public void createPlane(String model, int capacity, String buildDate) {

		sql = "INSERT INTO public.\"Planes\" VALUES (" + "'" + model + "'" + "," + "'" + capacity + "'" + "," + "'"
				+ buildDate + "'" + ");";

		try {
			statement = dbConnection.createStatement();
			statement.execute(sql);
			statement.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public List<Plane> readPlanes() {
		List<Plane> PlaneList = new ArrayList<Plane>();

		String model;
		int capacity;
		String buildDate;

		sql = "SELECT * FROM public.\"Planes\";";

		try {
			statement = dbConnection.createStatement();
			ResultSet res = statement.executeQuery(sql);

			while (res.next()) {

				model = res.getString("model");
				capacity = Integer.parseInt(res.getString("capacity"));
				buildDate = res.getString("buildDate");

				PlaneList.add(new Plane(model, capacity, buildDate));
			}

			statement.close();

			return PlaneList;

		} catch (SQLException e) {
			e.printStackTrace();
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
				model = res.getString("model");
				capacity = Integer.parseInt(res.getString("capacity"));
				buildDate = res.getString("buildDate");

				planeList.add(new Plane(model, capacity, buildDate));
			}

			statement.close();

			return planeList;

		} catch (SQLException e) {
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
