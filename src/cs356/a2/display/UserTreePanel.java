package cs356.a2.display;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreeSelectionModel;

import cs356.a2.admin_control.User;
import cs356.a2.admin_control.UserEntity;
import cs356.a2.admin_control.UserGroup;

public class UserTreePanel extends JPanel {
	private static final long serialVersionUID = -2661786322628721659L;
	
	private UserEntityTreeModel treeModel;
	private JTree userTree;
	
	public UserTreePanel(UserGroup root){
		treeModel = new UserEntityTreeModel((root));
        userTree = new JTree(treeModel);
        buildGUI();
	}
	
	private void buildGUI(){
        userTree.setEditable(true);
        userTree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);
        JScrollPane userListPane = new JScrollPane(userTree);
        userListPane.setPreferredSize(new Dimension(380,380));
        this.add(userListPane);
	}
	
	public void addUser(String userName){
		UserEntity u = (UserEntity) userTree.getLastSelectedPathComponent();

		if (u == null) {
			treeModel.insertNodeInto(new User(userName), (UserGroup)treeModel.getRoot());
			return;
		}
		else
			treeModel.insertNodeInto(new User(userName),getGroup(u));
	}

	public void addGroup(String groupName) {
		UserEntity u = (UserEntity) userTree.getLastSelectedPathComponent();

		if (u == null) {
			treeModel.insertNodeInto(new UserGroup(groupName), (UserGroup)treeModel.getRoot());
			return;
		}
		else
			treeModel.insertNodeInto(new UserGroup(groupName),getGroup(u));
	}
	
	private UserGroup getGroup(UserEntity u) {
		UserGroup group = null;
		
		if (u.isGroup()) {
			group = (UserGroup) u;
		}
		else {
			group = (UserGroup) treeModel.getParent(u);
		}
		
		return group;
	}
	
	public UserEntity getSelected() {
		return (UserEntity) userTree.getLastSelectedPathComponent();
	}
}
