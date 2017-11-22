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
	
	public void setRoot (UserGroup group){
		root = group;
	}
	
	public UserGroup getRoot() {
		return root;
	}
	
	public void addNewUser(String userName, UserGroup group)
			throws UserExistsException {
		addEntityToGroup(new User(userName), group);
	}
	
	public void addNewUserById(String userName, UUID groupId)
			throws UserExistsException {
		UserGroup group = (UserGroup)findEntity(groupId);
		addEntityToGroup(new User(userName), group);
	}
	
	public void addNewGroup(String groupName, UserGroup group) 
			throws UserExistsException {
		addEntityToGroup(new UserGroup(groupName), group);
	}
	
	public void addNewGroupById(String groupName, UUID groupId) 
			throws UserExistsException {
		UserGroup group = (UserGroup)findEntity(groupId);
		addEntityToGroup(new UserGroup(groupName), group);
	}
	
	public void addEntityToGroup(UserEntity e, UserGroup g){
		
		if (!users.contains(e.getName())) {
			users.add(e.getName());
		}
		else {
			throw new UserExistsException();
		}
		g.addEntity(e);
	}

	public UserEntity findEntity(UUID id) {
		UserEntityFindByIdVisitor findEntity 
			= new UserEntityFindByIdVisitor(id);
		return (UserEntity)visitRoot(findEntity);
	}
	
	public UserEntity findEntity(String name) {
		UserEntityFindByNameVisitor findEntity 
			= new UserEntityFindByNameVisitor(name);
		return (UserEntity)visitRoot(findEntity);
	}
	
	public boolean containsEntity(UUID id) {
		return findEntity(id) != null;
	}
	
	public boolean containsEntity(String name) {
		return findEntity(name) != null;
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
	
	public boolean isValid() {
		return (boolean)
				visitRoot(new UserEntityValidIDVisitor());
	}

	public User getLastUpdatedUser() {
		return (User)
				visitRoot(new UserEntityLastUpdatedVisitor());
	}
	
	private Object visitRoot(UserEntityVisitor visitor) {
		root.accept(visitor);
		return visitor.get();
	}

}
