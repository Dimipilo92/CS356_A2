package cs356.a2.display;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import cs356.a2.admin_control.User;
import cs356.a2.admin_control.UserEntity;
import cs356.a2.admin_control.UserGroup;
import cs356.a2.admin_control.visitors.UserEntityGetMembersVisitor;

public class UserEntityTreeModel implements TreeModel{
	
	private UserGroup root;
	private List<TreeModelListener> treeModelListeners;
	
	public UserEntityTreeModel(UserGroup root) {
		this.root = root;
		this.treeModelListeners = new ArrayList<TreeModelListener>();
	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		treeModelListeners.add(l);
	}

	@Override
	public Object getChild(Object parent, int index) {
		UserGroup u = (UserGroup)parent;
		return u.getMember(index);
	}

	@Override
	public int getChildCount(Object parent) {
		UserGroup u = (UserGroup)parent;
		return u.getMemberCount();
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		UserGroup p = (UserGroup)parent;
		UserEntity c = (UserEntity)child;
		int index;
		for (index = 0; index < p.getMemberCount(); index++) {
			if(c.getId().equals(p.getMember(index).getId()));
		}
		return index;
	}

	@Override
	public Object getRoot() {
		return root;
	}
	
	public Object getParent(Object target) {
		if (target == root) 
			return null;
		UserGroup current = root;
		return getParent((UserEntity)target, current);
	}
	
	private Object getParent(UserEntity target, UserEntity current) {
		
		UserEntityGetMembersVisitor visitor = new UserEntityGetMembersVisitor();
		current.accept(visitor);
		List<UserEntity> children = visitor.getMembers();
		
		for (int i = 0; i < children.size(); i++) {
			if (children.get(i) == target)
				return current;
			getParent(target, current);
		}
		
		return null;
	}

	@Override
	public boolean isLeaf(Object node) {
		return node instanceof User;
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		treeModelListeners.remove(l);
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		// TODO Auto-generated method stub
		
	}
	/*
	public void insertNodeInto(Object child, Object parent) {
		UserGroup group = (UserGroup) parent;
		group.addEntity((UserEntity) child);
				
		TreeModelEvent e = new TreeModelEvent(this, 
                new Object[] {root});
		for(int i = 0; i < treeModelListeners.size(); i++ ) {
			treeModelListeners.get(i).treeNodesInserted(e);
		}
	}
	*/
	
	public void insertNodeInto(UserEntity child, UserGroup parent) {
		System.out.println(root.getMemberCount());
		parent.addEntity((UserEntity) child);
				
		TreeModelEvent e = new TreeModelEvent(this, 
                new Object[] {root});
		for(int i = 0; i < treeModelListeners.size(); i++ ) {
			treeModelListeners.get(i).treeNodesInserted(e);
		}
	}

}
