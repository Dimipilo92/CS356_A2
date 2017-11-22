package cs356.a2.admin_control;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class UserObserver extends Observable implements Observer {

	private List<Message> feed;
	private long lastUpdatedTime;
	
	public UserObserver() {
		feed = new ArrayList<>();
		lastUpdatedTime = System.currentTimeMillis();
	}
	
	public void addMessage(Message m) {
		this.setChanged();
		this.notifyObservers(m);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		Message message = (Message)arg;
		feed.add(message);
		lastUpdatedTime = System.currentTimeMillis();
	}
	
	public List<Message> getFeed() {
		return feed;
	}
	
	public long getLastUpdated() {
		return lastUpdatedTime;
	}
}
