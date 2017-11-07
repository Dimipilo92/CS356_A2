package cs356.a2.admin_control;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import cs356.a2.admin_control.visitors.*;

public class AdminControl {
	private static AdminControl INSTANCE = new AdminControl();
	private UserGroup root = new UserGroup("root");
	private Set<String> users;
	
	private AdminControl() {
		users = new HashSet<>();
	}
	
	public static AdminControl getInstance() {
		return INSTANCE;
	}
	
	public UserGroup getRoot() {
		return root;
	}
	
	public void addNewUser(String userName, UserGroup group) throws UserExistsException {
		addEntityToGroup(new User(userName), group);
	}
	
	public void addNewUserById(String userName, UUID groupId) throws UserExistsException {
		UserGroup group = (UserGroup)findEntityById(groupId);
		addEntityToGroup(new User(userName), group);
	}
	
	public void addGroup (UserGroup group) throws UserExistsException {
		addEntityToGroup(group, root);
	}
	
	public void addNewGroup(String groupName, UserGroup group) throws UserExistsException {
		addEntityToGroup(new UserGroup(groupName), group);
	}
	
	public void addNewGroupById(String groupName, UUID groupId) throws UserExistsException {
		UserGroup group = (UserGroup)findEntityById(groupId);
		addEntityToGroup(new UserGroup(groupName), group);
	}
	
	private void addEntityToGroup(UserEntity e, UserGroup g) throws UserExistsException {
		
		if (!users.contains(e.getName())) {
			users.add(e.getName());
		}
		else {
			throw new UserExistsException();
		}
		g.addEntity(e);
	}
	
	private UserEntity findEntityById(UUID groupId) {
		UserEntityFindByIdVisitor findGroup = new UserEntityFindByIdVisitor(groupId);
		root.accept(findGroup);
		return (UserGroup)findGroup.getEntity();
	}
	
	public boolean containsUser(UUID id) {
		UserEntityFindByIdVisitor findUser = new UserEntityFindByIdVisitor(id);
		root.accept(findUser);
		return findUser.getEntity() == null;
	}
	
	public boolean containsUser(String name) {
		UserEntityFindByNameVisitor findUser = new UserEntityFindByNameVisitor(name);
		root.accept(findUser);
		return findUser.getEntity() != null;
	}
	
	public int getTotalUsers() {
		return (int) visitRoot(new UserEntityTotalUsersVisitor());
	}
	
	public int getTotalGroups() {
		return (int) visitRoot(new UserEntityTotalGroupsVisitor());
	}
	
	public int getTotalMessages() {
		return (int) visitRoot(new UserEntityTotalMessagesVisitor());
	}
	
	public double getPositiveMessagePercent() {
		return (double) 
				visitRoot(new UserEntityPositiveMessagePercentVisitor());
	}
	
	private Object visitRoot(UserEntityVisitor visitor) {
		root.accept(visitor);
		return visitor.get();
	}
}
