package cs356.a2.admin_control.visitors;

import cs356.a2.admin_control.User;
import cs356.a2.admin_control.UserGroup;

public class UserEntityTotalUsersVisitor implements UserEntityVisitor{
	private int total;
	
	public UserEntityTotalUsersVisitor() {
	}

	@Override
	public void visit(User u) {
		total++;
	}

	@Override
	public void visit(UserGroup u) {
		return;
	}
	
	@Override
	public Object get() {
		return total;
	}

	@Override
	public boolean isDone() {
		return false;
	}
}
