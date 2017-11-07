package cs356.a2.display.deprecated;

import java.util.UUID;

import cs356.a2.admin_control.UserEntity;

public class UserEntityDisplay {
	String name;
	UUID id;
	
	UserEntityDisplay(UserEntity u){
		name = u.getName();
		id = u.getId();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	public String toString() {
		return name;
	}
}
