package cs356.a2.admin_control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cs356.a2.admin_control.visitors.UserEntityGetMembersVisitor;

public class UserEntityPathFinder {
	
	public static List<UserEntity> getPath(UserEntity root, UserEntity target){
		List<UserEntity> path = getPathRec(root,target);
		
		if (path == null) {
			throw new PathNotFoundException(root,target);
		}
		
		// make sure the path appears from root to node
		Collections.reverse(path);
		return new ArrayList<UserEntity>(path);
	}
	
	private static List<UserEntity> getPathRec(UserEntity current, UserEntity target){
		// preorder traverse tree.
		if (current == target) {
			return new ArrayList<UserEntity>();
		}
		
		List<UserEntity> children = getMembers(current);
		
		if (children.isEmpty()) {
			return null;
		}
		
		List<UserEntity> path = null;
		for (int i = 0; i < children.size(); i++) {
			path = getPathRec(children.get(i), target);
			if (path != null) {
				path.add((UserGroup)current);
				return path;
			}
		}
		// node does not exist in tree
		return path;
	}
	
	private static List<UserEntity> getMembers(UserEntity entity) {
		UserEntityGetMembersVisitor visitor = new UserEntityGetMembersVisitor();
		entity.accept(visitor);
		return visitor.getMembers();
	}
}
