package cs356.a2.admin_control.visitors;

import cs356.a2.admin_control.User;
import cs356.a2.admin_control.UserGroup;

public class UserEntityTotalMessagesVisitor implements UserEntityVisitor {

	private int total;
	
	public UserEntityTotalMessagesVisitor() {
	}

	@Override
	public void visit(User u) {
		total+=u.getMessageCount();
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
