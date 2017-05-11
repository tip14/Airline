package tip14.airline.storage;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import tip14.airline.model.Plane;
import tip14.airline.model.User;

public class Storage {
	
	private static final Logger logger = Logger.getLogger(Storage.class);
	private static List<Plane> planeStorage = new ArrayList<Plane>();
	private static List<Plane> foundPlanes = new ArrayList<Plane>();
	private static List<User> userStorage = new ArrayList<User>();

	public static List<User> getUserStorage() {
		return userStorage;
	}
	
	public static void addUser(User user) {
		userStorage.add(user);
		logger.info("User "+user.getEmail()+" was added to storage");
	}
	
	public static List<Plane> getPlaneStorage() {
		return planeStorage;
	}

	public static List<Plane> getFoundPlanes(String modelForSearch) {
		searchPlane(modelForSearch);
		return foundPlanes;
	}

	public static void deletePlane(String planeModel) {
		Plane foundedPlane = getFoundPlanes(planeModel).get(0);
		planeStorage.remove(foundedPlane);
		logger.info("Plane "+planeModel+" was deleted");
		
	}

	public static void addPlane(Plane plane) {
		planeStorage.add(plane);
		logger.info("Plane "+plane.getModel()+" was added to storage");
	}

	public static void searchPlane(String modelForSearch) {
		foundPlanes.clear();
		for (Plane plane : planeStorage) {
			if (plane.getModel().equals(modelForSearch)) {
				foundPlanes.add(plane);
			}
		}

	}

}
