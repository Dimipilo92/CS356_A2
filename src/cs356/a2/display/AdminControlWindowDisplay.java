package cs356.a2.display;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;

import cs356.a2.admin_control.AdminControl;
import cs356.a2.admin_control.UserEntity;
import cs356.a2.admin_control.UserGroup;
import cs356.a2.admin_control.visitors.UserEntityFindByNameVisitor;
import cs356.a2.admin_control.visitors.UserEntityTotalGroupsVisitor;
import cs356.a2.admin_control.visitors.UserEntityTotalUsersVisitor;

public class AdminControlWindowDisplay implements ActionListener{
	
	private AdminControl ac;
	
	private JFrame mainWindow;
	
	private UserTreePanel userTreePanel;
	
	private JTextField addUserTextField;
	private JButton addUserButton;
	
	private JTextField addGroupTextField;
	private JButton addGroupButton;
	
	private JButton openUserViewButton;
	
	private JButton showUserTotalButton;
	private JButton showGroupTotalButton;
	private JButton showMessagesTotalButton;
	private JButton showPositivePercentageButton;
	
	public AdminControlWindowDisplay() {
        buildGUI();
        ac = AdminControl.getInstance();
	}
	
	private void buildGUI() {
		
		ac = AdminControl.getInstance();
		
        //Create and set up the window.
        mainWindow = new JFrame("Admin Control");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		userTreePanel = new UserTreePanel(ac.getRoot());
		
        
        addUserTextField = new JTextField(10);
        addUserButton = new JButton("Add User");
        addUserButton.setActionCommand("addUser");
        addUserButton.addActionListener(this);
        JPanel addUserPanel = new JPanel();
        addUserPanel.add(addUserTextField, BorderLayout.LINE_START);
        addUserPanel.add(addUserButton, BorderLayout.LINE_END);
        
        addGroupTextField = new JTextField(10);
        addGroupButton = new JButton("Add Group");
        addGroupButton.setActionCommand("addGroup");
        addGroupButton.addActionListener(this);
        JPanel addGroupPanel = new JPanel();
        addGroupPanel.add(addGroupTextField, BorderLayout.LINE_START);
        addGroupPanel.add(addGroupButton, BorderLayout.LINE_END);
        
        
        openUserViewButton = new JButton("Open User View");
        openUserViewButton.setActionCommand("openUserView");
        openUserViewButton.addActionListener(this);
        
        JPanel userListOptionsPanel = new JPanel();
        userListOptionsPanel.setLayout(new BoxLayout(userListOptionsPanel, BoxLayout.PAGE_AXIS));
        userListOptionsPanel.add(addUserPanel);
        userListOptionsPanel.add(addGroupPanel);
        userListOptionsPanel.add(openUserViewButton);
        userListOptionsPanel.setBorder(BorderFactory.createCompoundBorder(
        		new TitledBorder("User Options"),new EmptyBorder(10, 10, 10, 10)));
        
        
        showUserTotalButton = new JButton("Total Users");
        showUserTotalButton.setActionCommand("showUserTotal");
        showUserTotalButton.addActionListener(this);
        
    	showGroupTotalButton = new JButton("Total Groups");
    	showGroupTotalButton.setActionCommand("showGroupTotal");
    	showGroupTotalButton.addActionListener(this);
    	
    	showMessagesTotalButton = new JButton("Total Messages");
    	showMessagesTotalButton.setActionCommand("showMessagesTotal");
    	showMessagesTotalButton.addActionListener(this);
    	
    	showPositivePercentageButton = new JButton("Positive Messages (%)");
    	showPositivePercentageButton.setActionCommand("showPositivePercentage");
    	showPositivePercentageButton.addActionListener(this);
    	
    	JPanel displayStatisticsPanel = new JPanel();
    	displayStatisticsPanel.setLayout(new GridLayout(2,2,10,10));
    	displayStatisticsPanel.add(showUserTotalButton);
    	displayStatisticsPanel.add(showGroupTotalButton);
    	displayStatisticsPanel.add(showMessagesTotalButton);
    	displayStatisticsPanel.add(showPositivePercentageButton);
    	displayStatisticsPanel.setBorder(BorderFactory.createCompoundBorder(
        		new TitledBorder("Statistics"),new EmptyBorder(10, 10, 10, 10)));
    	
    	JPanel AdminPanel  = new JPanel();
    	AdminPanel.setLayout(new BoxLayout(AdminPanel, BoxLayout.PAGE_AXIS));
    	AdminPanel.add(userListOptionsPanel);
    	AdminPanel.add(displayStatisticsPanel);
    	AdminPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        mainWindow.getContentPane().add(userTreePanel,BorderLayout.LINE_START);
        mainWindow.getContentPane().add(AdminPanel,BorderLayout.CENTER);
        
	}
	
	private void show() {
		//Display the window.
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
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case "addUser":
				String name = addUserTextField.getText();
				System.out.println(ac.getRoot().getMemberCount());
				System.out.println(ac.containsUser(name));
				if(ac.containsUser(name)) {
					String message = "User must be unique!";
					JOptionPane.showMessageDialog(mainWindow, message,
							 "Choose Different user", 
							JOptionPane.PLAIN_MESSAGE);
					break;
				}
				System.out.println("Add User");
				userTreePanel.addUser(name);
				break;
			case "addGroup":
				System.out.println("Add Group");
				userTreePanel.addGroup(addGroupTextField.getText());
				break;
			case "openUserView":
				UserWindowDisplay window = new UserWindowDisplay();
				window.run();
				break;
			case "showUserTotal":
				JOptionPane.showMessageDialog(mainWindow, "Total Users: " 
						+ ac.getTotalUsers(), "Total Users", 
						JOptionPane.PLAIN_MESSAGE);
				break;
			case "showGroupTotal":
				JOptionPane.showMessageDialog(mainWindow, "Total Groups: " 
						+ ac.getTotalGroups(), "Total Groups", 
						JOptionPane.PLAIN_MESSAGE);
				break;
			case "showMessagesTotal":
				JOptionPane.showMessageDialog(mainWindow, "Total Messages: " 
						+ ac.getTotalMessages(), "Total Messages", 
						JOptionPane.PLAIN_MESSAGE);
				break;
			case "showPositivePercentage":
				JOptionPane.showMessageDialog(mainWindow, 
						"Percentage of Positive Messages: " 
								+ ac.getPositiveMessagePercent(), 
						"Positive Messages", JOptionPane.PLAIN_MESSAGE);
				break;
		}
	}
}
