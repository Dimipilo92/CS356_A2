package cs356.a2.admin_control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import cs356.a2.admin_control.visitors.UserEntityVisitor;

public class User implements UserEntity{
	
	private UserObserver observer;
	private UUID id;
	private String name;
	private List<Message> messages;
	private List<User> usersFollowed;
	
	public User(){
		 instantiate("no_name");
	}
	
	public User(String name){
		 instantiate(name);
	}
	
	private void instantiate(String name) {
		id = UUID.randomUUID();
		this.name = name;
		messages = new ArrayList<>();
		usersFollowed = new ArrayList<>();
		observer = new UserObserver();
		observer.addObserver(observer);
	}
	
	@Override
	public UUID getId(){
		return id;
	}
	
	public List<Message> getFeed() {
		return observer.getFeed();
	}
	
	public Message getFeedItem(int pos) {
		return observer.getFeed().get(pos);
	}
	
	public int getFeedCount(int pos) {
		return observer.getFeed().size();
	}
	
	public Message getMessage(int pos) {
		return messages.get(pos);
	}
	
	public int getMessageCount() {
		return messages.size();
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	public void broadcastMessage(String message) {
		
		Message newMessage = new Message(id.toString(), name, message, new Date());
		messages.add(newMessage);
		observer.addMessage(newMessage);
	}
	
	public void accept (UserEntityVisitor visitor) {
		visitor.visit(this);
	}
	
	public boolean isFollowing(User user) {
		return usersFollowed.contains(user);
	}
	
	public void follow(User user) {
		usersFollowed.add(user);
		user.getObserver().addObserver(observer);
	}
	
	public User getUserFollowed(int pos) {
		return usersFollowed.get(pos);
	}
	
	public List<User> getAllUsersFollowed() {
		return usersFollowed;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public UserObserver getObserver() {
		return observer;
	}
	
	public void displayFeed() {
		for (int i = 0; i < observer.getFeed().size(); i++) {
			System.out.println(observer.getFeed().get(i));
		}
		
	}

	@Override
	public boolean isGroup() {
		return false;
	}
}
