package cs356.a2.display;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TweetPanel extends JPanel{

	private JList messageList;
	private JButton tweetMessageButton;
	private JTextField tweetMessageTextField;
	
	TweetPanel(){
		buildGUI();
	}
	
	private void buildGUI(){
		tweetMessageButton = new JButton("Tweet");
	}
}
