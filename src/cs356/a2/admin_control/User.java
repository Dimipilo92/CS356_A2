package cs356.a2.admin_control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

import cs356.a2.admin_control.visitors.UserEntityVisitor;

public class User extends Observable implements UserEntity, Observer {
	
	private UUID id;
	private String name;
	private List<Message> activity;
	private List<Message> feed;
	private List<User> followees;
	
	public User(){
		 instantiate("no_name");
	}
	
	public User(String name){
		 instantiate(name);
	}
	
	private void instantiate(String name) {
		id = UUID.randomUUID();
		 this.name = name;
		 feed = new ArrayList<>();
		 activity = new ArrayList<>();
		 followees = new ArrayList<>();
		 this.addObserver(this);
	}
	
	@Override
	public UUID getId(){
		return id;
	}
	
	public Message getMessage(int pos) {
		return activity.get(pos);
	}
	
	public int getMessageCount() {
		return activity.size();
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		Message message = (Message)arg;
		feed.add(message);
	}
	
	public void broadcastMessage(String message) {
		this.setChanged();
		Message newMessage = new Message(id.toString(), name, message, new Date());
		activity.add(newMessage);
		this.notifyObservers(newMessage);
	}
	
	/*
	public void addFollower(User u) {
		followers.add(u);
		this.addObserver(u);
	}
	*/
	
	public void displayMessages() {
		for (int i = 0; i < feed.size(); i++) {
			System.out.println(feed.get(i).getMessage());
		}
	}
	
	public void accept (UserEntityVisitor visitor) {
		visitor.visit(this);
	}
	
	public void follow(User user) {
		followees.add(user);
		user.addObserver(this);
	}
	
	public User getFollowee(int pos) {
		return followees.get(pos);
	}
	
	public List<User> getAllFollowees() {
		return followees;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
