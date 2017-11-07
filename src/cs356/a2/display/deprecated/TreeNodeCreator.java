package cs356.a2.display.deprecated;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import cs356.a2.admin_control.AdminControl;
import cs356.a2.admin_control.UserEntity;
import cs356.a2.admin_control.visitors.UserEntityGetMembersVisitor;

public class TreeNodeCreator{

	public static DefaultMutableTreeNode createTree(AdminControl ac) {
		UserEntityDisplay userData = new UserEntityDisplay(ac.getRoot());
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(userData);
		createTree(ac.getRoot(), root);
		return root;
		
	}
	
	private static void createTree(UserEntity user, DefaultMutableTreeNode curNode){
		UserEntityGetMembersVisitor visitor = new UserEntityGetMembersVisitor();
		List<UserEntity> children = visitor.getMembers();
		
		for (int i = 0; i < children.size(); i++) {
			UserEntityDisplay userData = new UserEntityDisplay(children.get(i));
			curNode.add(new DefaultMutableTreeNode(userData));
			createTree(children.get(i), (DefaultMutableTreeNode) curNode.getLastChild());
		}
		
	}

}
