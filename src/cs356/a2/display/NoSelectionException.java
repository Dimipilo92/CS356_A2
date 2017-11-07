package cs356.a2.display;

public class NoSelectionException extends Exception {
	
	private static final String message = "No user selected";
	public NoSelectionException(){
		
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
