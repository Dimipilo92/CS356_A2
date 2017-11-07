package cs356.a2.admin_control;

import java.util.Date;
import java.util.UUID;

public class Message {
	private UUID userId;
	private String message;
	private String userName;
	private Date timeStamp;
	
	public Message(String userId, String user, String message, Date date) {
		this.userId = UUID.fromString(userId);
		this.message = message;
		this.userName = user;
		this.timeStamp = date;
	}
	
	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String user) {
		this.userName = user;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date date) {
		this.timeStamp = date;
	}
	
	@Override
	public String toString() {
		return userName + ": "+ message;
	}
}
