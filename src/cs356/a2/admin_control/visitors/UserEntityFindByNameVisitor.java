package cs356.a2.admin_control.visitors;

import java.util.UUID;

import cs356.a2.admin_control.User;
import cs356.a2.admin_control.UserEntity;
import cs356.a2.admin_control.UserGroup;

public class UserEntityFindByNameVisitor implements UserEntityVisitor{
	
	private String targetName;
	private UserEntity target;
	private boolean found;
	
	public UserEntityFindByNameVisitor(String targetName){
		this.targetName = targetName;
		target = null;
		found = false;
	}

	@Override
	public void visit(User u) {
		if (u.getName() == targetName) {
			target = u;
			found = true;
		}
	}

	@Override
	public void visit(UserGroup u) {
		if (u.getName() == targetName) {
			target = u;
			found = true;
		}
	}
	
	public void setTargetName(String name) {
		targetName = name;
		target = null;
		found = false;
	}
	
	public UserEntity getEntity() {
		return target;
	}
	
	@Override 
	public Object get() {
		return target;
	}

	@Override
	public boolean isDone() {
		return found;
	}

}
