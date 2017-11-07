package cs356.a2.admin_control.visitors;

import java.util.UUID;

import cs356.a2.admin_control.User;
import cs356.a2.admin_control.UserEntity;
import cs356.a2.admin_control.UserGroup;

public class UserEntityFindByIdVisitor implements UserEntityVisitor{
	
	private UUID targetId;
	private UserEntity target;
	private boolean found;
	
	public UserEntityFindByIdVisitor(UUID targetId){
		this.targetId = targetId;
		target = null;
	}

	@Override
	public void visit(User u) {
		if (u.getId() == targetId) {
			target = u;
			found = true;
		}
	}

	@Override
	public void visit(UserGroup u) {
		if (u.getId() == targetId) {
			found = true;
			target = u;
		}
	}
	
	public void setTargetID(UUID id) {
		targetId = id;
		target = null;
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
