package org.commackschools.reservation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

public class ReportTestPanel extends JPanel {

	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public ReportTestPanel() throws IOException {
		JPanel generateReportPanel = new JPanel();
		generateReportPanel.setBackground(new Color(255, 255, 102));
		generateReportPanel.setBounds(0,0,851, 537);
		generateReportPanel.setLayout(null);
		
		JLabel currentModeLbl = new JLabel("Settings: Generate Reservation Reports");
		currentModeLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.BOLD, 32));
		currentModeLbl.setBounds(0, 0, 796, 41);
		generateReportPanel.add(currentModeLbl);
		
		JLabel removeUserLbl = new JLabel("Search User");
		removeUserLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
		removeUserLbl.setBounds(0, 52, 611, 28);
		generateReportPanel.add(removeUserLbl);
		
		JLabel successFailureRemoveLbl = new JLabel();
		successFailureRemoveLbl.setFont(new Font("Arial", Font.PLAIN, 14));
		successFailureRemoveLbl.setBounds(0, 279, 738, 17);
		generateReportPanel.add(successFailureRemoveLbl);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 130, 851, 100);
		generateReportPanel.add(scrollPane);
		
		JList<String> list = new JList<String>();
		scrollPane.setViewportView(list);
		list.setFont(new Font("Arial", Font.PLAIN, 18));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JTextField searchUserTxtField = new JTextField();
		searchUserTxtField.setBounds(150, 91, 691, 28);
		generateReportPanel.add(searchUserTxtField);
		
		JButton searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					successFailureRemoveLbl.setText("");
					String query = searchUserTxtField.getText();
					ArrayList<User> results = User.searchUser(query);
					String[] displayStr = new String[results.size()];
					for (int i = 0; i < results.size(); i++) {
						displayStr[i] = results.get(i).getLastName() + ", " + results.get(i).getFirstName() + " (" + results.get(i).getEmail() + ")";
					}
					list.setModel(new AbstractListModel<String>() {
						String[] values = displayStr;
						
						public int getSize() {
							return values.length;
						}
						public String getElementAt(int index) {
							return values[index];
						}
					});
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NullPointerException e1) {
					successFailureRemoveLbl.setText("Error: No users found with that last name. Please search a last name and try again.");
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
		searchBtn.setBounds(0, 91, 140, 28);
		generateReportPanel.add(searchBtn);
		
		JScrollPane reportScrollPane = new JScrollPane();
		reportScrollPane.setBounds(0, 330, 851, 170);
		generateReportPanel.add(reportScrollPane);
		
		JList<String> reservationJList = new JList<String>();
		reportScrollPane.setViewportView(reservationJList);
		
		JLabel directionsLbl = new JLabel("Search a user by last name.");
		directionsLbl.setFont(new Font("Arial", Font.BOLD, 14));
		directionsLbl.setBounds(150, 72, 646, 17);
		generateReportPanel.add(directionsLbl);
		
		String username = list.getSelectedValue().substring(list.getSelectedValue().indexOf("(")+1, list.getSelectedValue().indexOf("@"));
		String[] info = User.getUserInfo(username);
		User user = new User(info[0], info[1], info[2]);
		JLabel reportTitleLbl = new JLabel("Reservation Report For: " + user.getLastName() + ", " + user.getFirstName());
		reportTitleLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
		reportTitleLbl.setBounds(0, 291, 611, 28);
		generateReportPanel.add(reportTitleLbl);
		
		JButton generateButton = new JButton("Generate Reservation Report");
		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String username = list.getSelectedValue().substring(list.getSelectedValue().indexOf("(")+1, list.getSelectedValue().indexOf("@"));
					String[] info = User.getUserInfo(username);
					User user = new User(info[0], info[1], info[2]);
					
					user.updateReservationsAsArrayList();
					ArrayList<Reservation> reservations = user.getReservations();
					String[] displayStr = new String[reservations.size()];
					for (int i = 0; i < reservations.size(); i++) {
						displayStr[i] = reservations.get(i).getDate() + " | " + reservations.get(i).getRoom() + " | " + reservations.get(i).getPeriod();
					}
					
					list.setModel(new AbstractListModel<String>() {
						String[] values = displayStr;
						
						public int getSize() {
							return values.length;
						}
						public String getElementAt(int index) {
							return values[index];
						}
					});
					successFailureRemoveLbl.setText("Report successfully generated.");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NullPointerException e1) {
					successFailureRemoveLbl.setText("Error: No user selected. Please try again.");
				}
			}
		});
		generateButton.setForeground(Color.WHITE);
		generateButton.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 20));
		generateButton.setFocusable(false);
		generateButton.setFocusTraversalKeysEnabled(false);
		generateButton.setFocusPainted(false);
		generateButton.setBorderPainted(false);
		generateButton.setBackground(new Color(0, 128, 0));
		generateButton.setBounds(0, 241, 295, 35);
		generateReportPanel.add(generateButton);
	}
}
