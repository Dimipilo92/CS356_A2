package cs356.a2.admin_control;

import java.util.UUID;

public class UserExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private static final String default_message = "User already exists";
	private String message;
	
	public UserExistsException() {
		message = default_message;
	}
	
	public UserExistsException(String name) {
		message = "User with name " + name +
				" already exists.";
	}
	
	public UserExistsException(UUID id) {
		message = "User with id " + id +
				" already exists.";
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
