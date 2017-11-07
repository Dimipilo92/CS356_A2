package cs356.a2.admin_control.visitors;

import cs356.a2.admin_control.User;
import cs356.a2.admin_control.UserGroup;

public interface UserEntityVisitor {
	
	public void visit(User u);
	public void visit(UserGroup u);
	public boolean isDone();
	public Object get();
}
