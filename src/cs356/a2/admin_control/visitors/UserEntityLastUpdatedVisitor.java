package cs356.a2.admin_control.visitors;

import cs356.a2.admin_control.User;
import cs356.a2.admin_control.UserGroup;

public class UserEntityLastUpdatedVisitor implements UserEntityVisitor {
	
	private User lastVisited;
	
	public UserEntityLastUpdatedVisitor(){
		lastVisited = null;
	}
	
	@Override
	public void visit(User u) {
		if (lastVisited == null ||
				u.getLastUpdatedTime() > lastVisited.getLastUpdatedTime()) {
			lastVisited = u;
		}
	}

	@Override
	public void visit(UserGroup u) {
	}

	@Override
	public boolean isDone() {
		return false;
	}

	@Override
	public Object get() {
		return lastVisited;
	}

}
