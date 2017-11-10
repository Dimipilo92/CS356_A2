package cs356.a2.admin_control;

public class PathNotFoundException extends RuntimeException {
		
	private static final long serialVersionUID = 1L;
	private static final String default_message = "Path not found";
	private String message;
	
	public PathNotFoundException() {
		message = default_message;
	}
	
	public PathNotFoundException(UserEntity source, UserEntity target) {
		message = "Path from " + source + " to " + target + " was not found";
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
