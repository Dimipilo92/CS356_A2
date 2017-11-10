package cs356.a2.display;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import cs356.a2.admin_control.Message;
import cs356.a2.admin_control.User;

public class UserTweetPanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 6513317269074472313L;

	private User user;

	private JList<Message> messageList;
	private JButton tweetMessageButton;
	private JTextField tweetMessageTextField;
	
	UserTweetPanel(User user){
		this.user = user;
		buildGUI();
		messageList = new JList<Message>((new Vector<Message>(user.getFeed())));
		JScrollPane messagePane = new JScrollPane(messageList);
		
		tweetMessageButton = new JButton("Tweet");
		tweetMessageButton.addActionListener(this);
		tweetMessageTextField = new JTextField(30);
		JPanel commands = new JPanel();
		commands.add(tweetMessageTextField, BorderLayout.LINE_START);
		commands.add(tweetMessageButton, BorderLayout.LINE_END);
		
		add(messagePane, BorderLayout.PAGE_START);
		add(commands,BorderLayout.PAGE_END);
	}
	
	private void buildGUI(){
		tweetMessageButton = new JButton("Tweet");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		//e.getActionCommand()
		String message = tweetMessageTextField.getText();
		user.broadcastMessage(message);
		refresh();
		
	}
	
	public void refresh() {
		messageList.setListData(new Vector<Message>(user.getFeed()));
	}
}
