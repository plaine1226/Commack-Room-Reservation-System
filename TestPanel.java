package org.commackschools.reservation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.AbstractListModel;

public class TestPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public TestPanel() {
		JPanel addRemoveUserPanel = new JPanel();
		addRemoveUserPanel.setBackground(new Color(255, 255, 102));
		addRemoveUserPanel.setBounds(0,0,851, 537);
		addRemoveUserPanel.setLayout(null);
		
		JLabel currentModeLbl = new JLabel("Settings: Add/Remove Users");
		currentModeLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.BOLD, 32));
		currentModeLbl.setBounds(0, 0, 796, 41);
		addRemoveUserPanel.add(currentModeLbl);
		
		JLabel addUserLbl = new JLabel("Add User");
		addUserLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
		addUserLbl.setBounds(0, 52, 611, 28);
		addRemoveUserPanel.add(addUserLbl);
		
		JTextField firstNameField = new JTextField();
		firstNameField.setFont(new Font("Arial", Font.PLAIN, 20));
		firstNameField.setBounds(0, 80, 245, 35);
		addRemoveUserPanel.add(firstNameField);
		
		JTextField lastNameField = new JTextField();
		lastNameField.setFont(new Font("Arial", Font.PLAIN, 20));
		lastNameField.setBounds(366, 80, 245, 35);
		addRemoveUserPanel.add(lastNameField);
		
		JTextField emailField = new JTextField();
		emailField.setFont(new Font("Arial", Font.PLAIN, 20));
		emailField.setBounds(0, 175, 245, 35);
		addRemoveUserPanel.add(emailField);
		
		JLabel successFailureLbl = new JLabel("");
		successFailureLbl.setFont(new Font("Arial", Font.PLAIN, 14));
		successFailureLbl.setBounds(0, 235, 450, 17);
		addRemoveUserPanel.add(successFailureLbl);
		
		JButton addUserBtn = new JButton("Add");
		addUserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (firstNameField.getText().equals("") || lastNameField.getText().equals("") || emailField.getText().equals(""))
						successFailureLbl.setText("Error: Do not leave any fields blank.");
					else {
						Admin.register(firstNameField.getText(), lastNameField.getText(), emailField.getText());
						successFailureLbl.setText("User successfully registered to database.");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					successFailureLbl.setText("Error: IOException - Please try again.");
				}
			}
		});
		addUserBtn.setBackground(new Color(34, 139, 34));
		addUserBtn.setForeground(Color.WHITE);
		addUserBtn.setFocusable(false);
		addUserBtn.setFocusTraversalKeysEnabled(false);
		addUserBtn.setFocusPainted(false);
		addUserBtn.setBorderPainted(false);
		addUserBtn.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 20));
		addUserBtn.setBounds(366, 175, 140, 35);
		addRemoveUserPanel.add(addUserBtn);
		
		JLabel removeUserLbl = new JLabel("Remove User");
		removeUserLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
		removeUserLbl.setBounds(0, 252, 611, 28);
		addRemoveUserPanel.add(removeUserLbl);
		
		JLabel successFailureRemoveLbl = new JLabel();
		successFailureRemoveLbl.setFont(new Font("Arial", Font.PLAIN, 14));
		successFailureRemoveLbl.setBounds(150, 459, 404, 17);
		addRemoveUserPanel.add(successFailureRemoveLbl);
		
		
		JLabel firstNameLbl = new JLabel("First Name");
		firstNameLbl.setVerticalAlignment(SwingConstants.TOP);
		firstNameLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 16));
		firstNameLbl.setBounds(0, 116, 245, 17);
		addRemoveUserPanel.add(firstNameLbl);
		
		JLabel emailLbl = new JLabel("Email");
		emailLbl.setVerticalAlignment(SwingConstants.TOP);
		emailLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 16));
		emailLbl.setBounds(0, 212, 245, 17);
		addRemoveUserPanel.add(emailLbl);
		
		JLabel lastNameLbl = new JLabel("Last Name");
		lastNameLbl.setVerticalAlignment(SwingConstants.TOP);
		lastNameLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 16));
		lastNameLbl.setBounds(366, 116, 245, 17);
		addRemoveUserPanel.add(lastNameLbl);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 320, 851, 110);
		addRemoveUserPanel.add(scrollPane);
		
		JList<String> list = new JList<String>();
		list.setFont(new Font("Arial", Font.PLAIN, 18));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		JTextField searchUserTxtField = new JTextField();
		searchUserTxtField.setBounds(150, 281, 691, 28);
		addRemoveUserPanel.add(searchUserTxtField);
		
		JButton searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = searchUserTxtField.getSelectedText();
					ArrayList<User> results = User.searchUser(query);
					String[] displayStr = new String[results.size()];
					for (int i = 0; i < results.size(); i++) {
						displayStr[i] = results.get(i).getLastName() + ", " + results.get(i).getFirstName() + " (" + results.get(i).getEmail() + ")";
					}
					list.setModel(new AbstractListModel() {
						String[] values = displayStr;
						
						public int getSize() {
							return values.length;
						}
						public Object getElementAt(int index) {
							return values[index];
						}
					});
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		searchBtn.setForeground(Color.BLACK);
		searchBtn.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 18));
		searchBtn.setFocusable(false);
		searchBtn.setFocusTraversalKeysEnabled(false);
		searchBtn.setFocusPainted(false);
		searchBtn.setBorderPainted(false);
		searchBtn.setBackground(UIManager.getColor("Button.background"));
		searchBtn.setBounds(0, 281, 140, 28);
		addRemoveUserPanel.add(searchBtn);
		
		JLabel directionsLbl = new JLabel("Search a user by last name.");
		directionsLbl.setFont(new Font("Arial", Font.BOLD, 14));
		directionsLbl.setBounds(150, 263, 646, 17);
		addRemoveUserPanel.add(directionsLbl);
		
		JButton removeUserBtn = new JButton("Remove");
		removeUserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedValue().equals(null)) {
					successFailureRemoveLbl.setText("Error: No user selected. Please try again");
				}
				else {
					try {
						String username = list.getSelectedValue().substring(list.getSelectedValue().indexOf("(")+1, list.getSelectedValue().indexOf("@"));
						String[] info = User.getUserInfo(username);
						User user = new User(info[0], info[1], info[2]);
						
						Admin.removeUser(user.getFirstName(),user.getLastName(),user.getEmail());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		removeUserBtn.setForeground(Color.WHITE);
		removeUserBtn.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 20));
		removeUserBtn.setFocusable(false);
		removeUserBtn.setFocusTraversalKeysEnabled(false);
		removeUserBtn.setFocusPainted(false);
		removeUserBtn.setBorderPainted(false);
		removeUserBtn.setBackground(Color.RED);
		removeUserBtn.setBounds(0, 441, 140, 35);
		addRemoveUserPanel.add(removeUserBtn);
	}
}
