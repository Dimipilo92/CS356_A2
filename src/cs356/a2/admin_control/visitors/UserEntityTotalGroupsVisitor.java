package cs356.a2.admin_control.visitors;

import cs356.a2.admin_control.User;
import cs356.a2.admin_control.UserGroup;

public class UserEntityTotalGroupsVisitor implements UserEntityVisitor{
	private int total;
	
	public UserEntityTotalGroupsVisitor() {
	}

	@Override
	public void visit(User u) {
		return;
	}

	@Override
	public void visit(UserGroup u) {
		total++;
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
