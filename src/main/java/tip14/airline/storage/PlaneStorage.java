package tip14.airline.storage;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import tip14.airline.model.Plane;

public class PlaneStorage {

	private static final Logger logger = Logger.getLogger(PlaneStorage.class);
	private static final String PLANE_ADDED = "Plane was added to storage with model name ";
	private static final String PLANE_DELETED = "Plane was deleted from storage with model name ";

	private static List<Plane> planeStorage = new ArrayList<Plane>();
	private static List<Plane> foundPlanes = new ArrayList<Plane>();

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
		logger.info(PLANE_DELETED + planeModel);

	}

	public static void addPlane(Plane plane) {
		planeStorage.add(plane);
		logger.info(PLANE_ADDED + plane.getModel());
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
