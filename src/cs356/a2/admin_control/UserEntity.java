package cs356.a2.admin_control;

import java.util.UUID;

import cs356.a2.admin_control.visitors.UserEntityVisitor;

public interface UserEntity {
	public UUID getId();
	public void setName(String name);
	public String getName();
	public boolean isGroup();
	
	public void accept(UserEntityVisitor visitor);
}
