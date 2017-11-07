package cs356.a2.admin_control.visitors;

import java.util.ArrayList;
import java.util.List;

import cs356.a2.admin_control.User;
import cs356.a2.admin_control.UserEntity;
import cs356.a2.admin_control.UserGroup;

public class UserEntityGetMembersVisitor implements UserEntityVisitor{
	
	private List<UserEntity> members;
	
	public UserEntityGetMembersVisitor () {
		members = new ArrayList<>();
	}
	
	@Override
	public void visit(User u) {
		return;
	}

	@Override
	public void visit(UserGroup u) {
		for (int i = 0; i < u.getMemberCount(); i++) {
			members.add(u.getMember(i));
		}
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
		// TODO Auto-generated method stub
		return true;
	}
	
}
