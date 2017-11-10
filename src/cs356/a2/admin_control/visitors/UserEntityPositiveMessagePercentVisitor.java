package cs356.a2.admin_control.visitors;

import cs356.a2.admin_control.User;
import cs356.a2.admin_control.UserGroup;

public class UserEntityPositiveMessagePercentVisitor implements UserEntityVisitor{
	
	private int totalPositive;
	private int totalMessages;
	
	public UserEntityPositiveMessagePercentVisitor() {
	}

	@Override
	public void visit(User u) {
		for (int i = 0; i < u.getMessageCount(); i++) {
			String message = u.getMessage(i).getMessage();
			if (message.contains("good")) {
				totalPositive++;
			}
			else if (message.contains("happy")) {
				totalPositive++;
			}
			else if (message.contains("great")) {
				totalPositive++;
			}
			totalMessages++;
		}
	}

	@Override
	public void visit(UserGroup u) {
		return;
	}
	
	@Override
	public Object get() {
		if (totalMessages == 0) {
			return Double.NaN;
		}
		return ((double)(totalPositive)/totalMessages);
	}

	@Override
	public boolean isDone() {
		return false;
	}
}
