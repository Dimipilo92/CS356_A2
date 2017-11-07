package cs356.a2.admin_control;

public class UserExistsException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private static final String message = "No user selected";
	
	@Override
	public String getMessage() {
		return message;
	}
}
