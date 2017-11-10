package cs356.a2.display;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import cs356.a2.admin_control.User;

public class UserWindowDisplay {
	private User user;
	
	private JFrame mainWindow;
	private UserTweetPanel tweetPanel;
	private UsersFollowedPanel usersFollowedPanel;
	
	
	public UserWindowDisplay(){
		user = new User();
		buildGUI();
	}
	
	public UserWindowDisplay(User user){
		this.user = user;
		buildGUI();
	}
	
	private void buildGUI() {
		mainWindow = new JFrame(user.getName());
		mainWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		tweetPanel = new UserTweetPanel(user);
		usersFollowedPanel = new UsersFollowedPanel(user);
		mainWindow.getContentPane().add(tweetPanel, BorderLayout.PAGE_START);
		mainWindow.getContentPane().add(usersFollowedPanel, BorderLayout.PAGE_END);
		
	}
	
	private void show() {
		mainWindow.pack();
		mainWindow.setVisible(true);
	}
	
	public void run() {
		//Schedule a job for the event-dispatching thread:
	    //creating and showing this application's GUI.
	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            show();
	        }
	    });
	}
}
