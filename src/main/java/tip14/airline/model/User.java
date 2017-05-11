package tip14.airline.model;

import org.apache.log4j.Logger;

public class User {
	
	private static final Logger logger = Logger.getLogger(User.class);
	private String email;
	private String password;
	private String role;
	
	public User(String email, String password, String role){
		this.email = email;
		this.password = password;
		this.role = role;
		logger.info("New user " + email + " was created");
	}
	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}
	
}
