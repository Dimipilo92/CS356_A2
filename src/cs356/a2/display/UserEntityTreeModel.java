package cs356.a2.display;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import cs356.a2.admin_control.UserEntity;
import cs356.a2.admin_control.UserEntityPathFinder;
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
		if (target == root)  {
			return null;
		}
		UserGroup current = root;
		return getParent((UserEntity)target, current);
	}
	
	private Object getParent(UserEntity target, UserEntity current) {
		
		UserEntityGetMembersVisitor visitor = new UserEntityGetMembersVisitor();
		current.accept(visitor);
		List<UserEntity> children = visitor.getMembers();
		
		for (int i = 0; i < children.size(); i++) {
			if (children.get(i) == target) {
				return current;
			}
			getParent(target, current);
		}
		
		return null;
	}

	@Override
	public boolean isLeaf(Object node) {
		return !((UserEntity)node).isGroup();
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		treeModelListeners.remove(l);
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		
	}
	
	public void insertNodeInto(UserEntity child, UserGroup parent) {
		
		parent.addEntity(child);
		
		Object[] path =  UserEntityPathFinder.getPath(root, child).toArray();
		int[] childIndices = getIndexedArray(parent.getMemberCount());
		Object[] children = parent.getAllMembers().toArray();
		TreeModelEvent e = new TreeModelEvent(this, path, childIndices, children);
		
		
		for(int i = 0; i < treeModelListeners.size(); i++ ) {
			treeModelListeners.get(i).treeNodesInserted(e);
		}
	}
	
	private int[] getIndexedArray (int size){
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) {
			arr[i] = i;
		}
		return arr;
	}

}
