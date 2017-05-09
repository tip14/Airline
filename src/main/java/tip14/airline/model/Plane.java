package tip14.airline.model;

import org.apache.log4j.Logger;

public class Plane {
	
	private static final Logger logger = Logger.getLogger(Plane.class);
	private String model;
	private int capacity;
	private String buildDate;
	
	
	public Plane(String model, int capacity, String buildDate) {
		this.model = model;
		this.capacity = capacity;
		this.buildDate = buildDate;
		logger.info("New plane " + model + " was created");
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getBuildDate() {
		return buildDate;
	}

	public void setBuildDate(String buildDate) {
		this.buildDate = buildDate;
	}

}
