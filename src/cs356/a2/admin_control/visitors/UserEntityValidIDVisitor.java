package cs356.a2.admin_control.visitors;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import cs356.a2.admin_control.User;
import cs356.a2.admin_control.UserEntity;
import cs356.a2.admin_control.UserGroup;

public class UserEntityValidIDVisitor implements UserEntityVisitor{
	
	boolean isValid;
	Set<UUID> idList;
	boolean done;
	
	public UserEntityValidIDVisitor() {
		isValid = true;
		idList = new HashSet<>();
		done = false;
	}

	@Override
	public void visit(User u) {
		checkValidity(u);
	}

	@Override
	public void visit(UserGroup u) {
		checkValidity(u);
		
	}
	
	private void checkValidity(UserEntity u) {
		if (!idList.contains(u.getId())) {
			idList.add(u.getId());
		}
		else {
			isValid = false;
			done = true;
		}
	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return done;
	}

	@Override
	public Object get() {
		// TODO Auto-generated method stub
		return isValid;
	}

}
