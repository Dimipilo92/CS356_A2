package cs356.a2;


import cs356.a2.admin_control.*;

import cs356.a2.display.AdminControlWindowDisplay;
import cs356.a2.display.deprecated.TreeNodeCreator;

public class Driver {

	public static void main(String[] args) {
		AdminControlWindowDisplay a = new AdminControlWindowDisplay();
		a.run();
		

		/*
		UserGroup g1 = new UserGroup();
		g1.addEntity(new User("1"));
		System.out.println(g1.getMemberCount());
		UserGroup g2 = ((UserGroup)((UserEntity)g1));
		g2.addEntity(new User("2"));
		System.out.println(g1.getMemberCount());
		User u1 = new User();
		User u2 = new User();
		User u3 = new User();
		u1.addFollower(u2);
		u1.addFollower(u3);
		u1.broadcastMessage("Hi");
		u1.broadcastMessage("Hey");
		u1.broadcastMessage("Howdy");
		u2.broadcastMessage("Howdeedoo");
		u2.broadcastMessage("Yo yo");
		u1.displayMessages();
		UserGroup g1 = new UserGroup("Group 1");
		g1.addEntity(u1);
		g1.addEntity(u2);
		g1.addEntity(u3);
		g1.addEntity(new UserGroup("Group 2"));
		
		AdminControl ac = AdminControl.getInstance();
		ac.addGroup(g1);
		System.out.println(ac.getTotalUsers());
		System.out.println(ac.getTotalGroups());
		System.out.println(ac.getTotalMessages());
		System.out.println(ac.getPositiveMessagePercent());
		TreeNodeCreator.createTree(ac);
		*/
	}
}
