package cs356.a2.display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import cs356.a2.admin_control.User;

public class UserWindowDisplay implements ActionListener{
	private User user;
	
	private JFrame mainWindow;
	
	
	private JList followeeList;
	private JButton followUserButton;
	private JTextField followUserField;
	
	public UserWindowDisplay(){
		buildGUI();
	}
	
	public UserWindowDisplay(User user){
		this.user = user;
		buildGUI();
	}
	
	private void buildGUI() {
		
		
		followUserButton = new JButton("Follow User");
	}
	
	private void show() {
		mainWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
