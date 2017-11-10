package cs356.a2.admin_control.visitors;

import java.util.ArrayList;
import java.util.List;

import cs356.a2.admin_control.User;
import cs356.a2.admin_control.UserEntity;
import cs356.a2.admin_control.UserGroup;

public class UserEntityGetMembersVisitor implements UserEntityVisitor{
	
	private List<UserEntity> members;
	boolean done = false;
	
	public UserEntityGetMembersVisitor () {
	}
	
	@Override
	public void visit(User u) {
		members = new ArrayList<UserEntity>();
		done = true;
	}

	@Override
	public void visit(UserGroup u) {
		members = u.getAllMembers();
		done = true;
	}
	
	public List<UserEntity> getMembers() {
		return members;
	}
	
	@Override
	public Object get() {
		return members;
	}

	@Override
	public boolean isDone() {
		return done;
	}
	
}
