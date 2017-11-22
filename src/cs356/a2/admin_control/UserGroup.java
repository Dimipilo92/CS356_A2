package cs356.a2.admin_control;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cs356.a2.admin_control.visitors.UserEntityVisitor;

public class UserGroup implements UserEntity{
	private UUID id = UUID.randomUUID();
	private String name;
	private long creationTime;
	
	private List<UserEntity> members;
	
	public UserGroup(){
		instantiate("no_name");
	}
	
	public UserGroup(String name) {
		instantiate(name);
	}
	
	private void instantiate(String name) {
		id = UUID.randomUUID();
		this.name = name;
		members = new ArrayList<>();
		creationTime = System.currentTimeMillis();
	}
	
	@Override
	public UUID getId() {
		return id;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public void addEntity(UserEntity entity) {
		members.add(entity);
	}
	
	public List<UserEntity> getAllMembers(){
		return members;
	}
	
	public UserEntity getMember(int pos) {
		return members.get(pos);
	}
	
	public int getMemberCount() {
		return members.size();
	}
	
	public void accept(UserEntityVisitor visitor) {
		if (visitor.isDone()) {
			return;
		}
		
		visitor.visit(this);
		for (int i = 0; i < members.size(); i++) {
			if (visitor.isDone())
				return;
			members.get(i).accept(visitor);
		}
	}
	
	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean isGroup() {
		return true;
	}

	@Override
	public long getCreationTime() {
		return creationTime;
	}
}