package cs356.a2.display;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import cs356.a2.admin_control.AdminControl;
import cs356.a2.admin_control.User;
import cs356.a2.admin_control.UserEntity;

public class UsersFollowedPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1645719408944593094L;
	private User user;
	private AdminControl ac;

	private JList<User> usersFollowedList;
	private JButton followButton;
	private JTextField followTextField;
	
	UsersFollowedPanel(User user){
		this.user = user;
		ac = AdminControl.getInstance();
		
		buildGUI();
		usersFollowedList = new JList<User>(
				new Vector<User>(user.getAllUsersFollowed()));
		JScrollPane usersFollowedPane = new JScrollPane(usersFollowedList);
		
		followButton = new JButton("Follow");
		followButton.addActionListener(this);
		followTextField = new JTextField(30);
		JPanel commands = new JPanel();
		commands.add(followTextField, BorderLayout.LINE_START);
		commands.add(followButton, BorderLayout.LINE_END);
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(usersFollowedPane);
		add(commands);
		setBorder(BorderFactory.createCompoundBorder(
        		new TitledBorder("Users Followed"),
        		new EmptyBorder(10, 10, 10, 10)));
	}
	
	private void buildGUI(){
		followButton = new JButton("Tweet");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		//e.getActionCommand()
		String userName = followTextField.getText();
		UserEntity entity = ac.findEntity(userName);
		if (entity == null ||entity.isGroup()) {
			JOptionPane.showMessageDialog(this, 
					"That user does not exist." 
					, "User Does Not Exist", 
					JOptionPane.PLAIN_MESSAGE);
			return;
		}
		User newUser = (User)entity;
		if (user.isFollowing(newUser)) {
			JOptionPane.showMessageDialog(this, 
					"That user is already being followed." 
					, "User Already Followed", 
					JOptionPane.PLAIN_MESSAGE);
			return;
		}
		user.follow(newUser);
		refresh();
		
	}
	
	public void refresh() {
		usersFollowedList.setListData(
				new Vector<User>(user.getAllUsersFollowed()));
	}
}
