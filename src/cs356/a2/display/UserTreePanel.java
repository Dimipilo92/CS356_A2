package cs356.a2.display;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;

import cs356.a2.admin_control.User;
import cs356.a2.admin_control.UserEntity;
import cs356.a2.admin_control.UserGroup;

public class UserTreePanel extends JPanel {
	private static final long serialVersionUID = 1L;
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
        this.add(userListPane);
	}
	
	public void addUser(String userName){
		UserEntity u = (UserEntity) userTree.getLastSelectedPathComponent();
		UserGroup group = null;
		
		if (treeModel.isLeaf(u)) {
			group = (UserGroup) treeModel.getParent(u);
		}
		else {
			group = (UserGroup) u;
		}
		
		if (u == null)
			treeModel.insertNodeInto(new User(userName), (UserGroup)treeModel.getRoot());
		else
			treeModel.insertNodeInto(new User(userName),group);
	}

	public void addGroup(String text) {
		// TODO Auto-generated method stub
		
	}
}
