package cs356.a2.admin_control.visitors;

import cs356.a2.admin_control.User;

public interface UserVisitor {
	public void visit(User u);
	public boolean isDone();
	public Object get();
}
