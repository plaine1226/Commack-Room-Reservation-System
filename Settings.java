package org.commackschools.reservation;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class Settings extends JPanel {

	/**
	 * Create the panel.
	 */
	public Settings(Admin admin) {
		setBounds(new Rectangle(0, 0, 1280, 720));
		setBackground(new Color(255, 255, 102));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(400, 145, 851, 500);
		panel.setBackground(new Color(255, 255, 102));
		add(panel);
		panel.setLayout(null);
		
		JLabel currentModeLbl_1 = new JLabel("Welcome to the settings panel! Choose from the options to the left to get started.");
		currentModeLbl_1.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
		currentModeLbl_1.setBounds(0, 0, 796, 35);
		panel.add(currentModeLbl_1);
		
		JLabel titleLbl = new JLabel("ADMINISTRATOR CENTRAL: SETTINGS");
		titleLbl.setForeground(Color.RED);
		titleLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.BOLD, 52));
		titleLbl.setBounds(50, 50, 806, 75);
		add(titleLbl);
		
		JButton btnRooms = new JButton("Add/Remove Rooms");
		btnRooms.setForeground(Color.YELLOW);
		btnRooms.setBackground(Color.BLACK);
		btnRooms.setBorderPainted(false);
		btnRooms.setFocusable(false);
		btnRooms.setFocusTraversalKeysEnabled(false);
		btnRooms.setFocusPainted(false);
		btnRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				
				JPanel addRemoveRoomPanel = new JPanel();
				addRemoveRoomPanel.setBackground(new Color(255, 255, 102));
				addRemoveRoomPanel.setBounds(0,0,851, 537);
				addRemoveRoomPanel.setLayout(null);
				
				JLabel currentModeLbl = new JLabel("Settings: Add/Remove Rooms");
				currentModeLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.BOLD, 32));
				currentModeLbl.setBounds(0, 0, 796, 41);
				addRemoveRoomPanel.add(currentModeLbl);
				
				JLabel roomNameLbl = new JLabel("Add Room");
				roomNameLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
				roomNameLbl.setBounds(0, 52, 611, 28);
				addRemoveRoomPanel.add(roomNameLbl);
				
				JTextField roomNameField = new JTextField();
				roomNameField.setFont(new Font("Arial", Font.PLAIN, 20));
				roomNameField.setBounds(0, 80, 245, 35);
				addRemoveRoomPanel.add(roomNameField);
				
				JLabel successFailureLbl = new JLabel("");
				successFailureLbl.setFont(new Font("Arial", Font.PLAIN, 14));
				successFailureLbl.setBounds(0, 126, 450, 17);
				addRemoveRoomPanel.add(successFailureLbl);
				
				JButton addRoomButton = new JButton("Add");
				addRoomButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							successFailureLbl.setText(Admin.addRoomToDatabase(roomNameField.getText()));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				addRoomButton.setBackground(Color.BLACK);
				addRoomButton.setForeground(Color.YELLOW);
				addRoomButton.setFocusable(false);
				addRoomButton.setFocusTraversalKeysEnabled(false);
				addRoomButton.setFocusPainted(false);
				addRoomButton.setBorderPainted(false);
				addRoomButton.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 20));
				addRoomButton.setBounds(266, 80, 140, 35);
				addRemoveRoomPanel.add(addRoomButton);
				
				JLabel lblRemoveRoom = new JLabel("Remove Room");
				lblRemoveRoom.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
				lblRemoveRoom.setBounds(0, 196, 611, 28);
				addRemoveRoomPanel.add(lblRemoveRoom);
				
				JComboBox<String> removeComboBox = new JComboBox<String>();
				removeComboBox.setMaximumRowCount(10);
				try {
					removeComboBox.setModel(new DefaultComboBoxModel<String>(Reservation.getRoomsInDatabase()));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				removeComboBox.setFont(new Font("Arial", Font.PLAIN, 20));
				removeComboBox.setBounds(0, 235, 245, 35);
				addRemoveRoomPanel.add(removeComboBox);
				
				JLabel successFailureRemoveLbl = new JLabel();
				successFailureRemoveLbl.setFont(new Font("Arial", Font.PLAIN, 14));
				successFailureRemoveLbl.setBounds(416, 253, 404, 17);
				panel.add(successFailureRemoveLbl);
				
				JButton deleteRoomButton = new JButton("Remove");
				deleteRoomButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Admin.removeRoomFromDatabase(removeComboBox.getSelectedItem().toString());
							successFailureRemoveLbl.setText("Room successfully removed from database.");
							removeComboBox.setModel(new DefaultComboBoxModel<String>(Reservation.getRoomsInDatabase()));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				
				deleteRoomButton.setForeground(Color.YELLOW);
				deleteRoomButton.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 20));
				deleteRoomButton.setFocusable(false);
				deleteRoomButton.setFocusTraversalKeysEnabled(false);
				deleteRoomButton.setFocusPainted(false);
				deleteRoomButton.setBorderPainted(false);
				deleteRoomButton.setBackground(Color.BLACK);
				deleteRoomButton.setBounds(266, 235, 140, 35);
				addRemoveRoomPanel.add(deleteRoomButton);
				
				removeComboBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!(removeComboBox.getSelectedItem().toString().equals("Select a Room")))
							deleteRoomButton.setEnabled(true);
					}
				});
				
				panel.add(addRemoveRoomPanel);
				panel.repaint();
				panel.revalidate();
			}
		});
		btnRooms.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
		btnRooms.setBounds(50, 145, 300, 45);
		add(btnRooms);
		
		
		JButton btnAddRemoveUser = new JButton("Add/Remove User");
		btnAddRemoveUser.setFocusable(false);
		btnAddRemoveUser.setFocusTraversalKeysEnabled(false);
		btnAddRemoveUser.setFocusPainted(false);
		btnAddRemoveUser.setBorderPainted(false);
		btnAddRemoveUser.setBackground(Color.BLACK);
		btnAddRemoveUser.setForeground(Color.YELLOW);
		btnAddRemoveUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				
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
								User.register(firstNameField.getText(), lastNameField.getText(), emailField.getText());
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
				successFailureRemoveLbl.setBounds(150, 459, 554, 17);
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
				searchBtn.setBounds(0, 281, 140, 28);
				addRemoveUserPanel.add(searchBtn);
				
				JLabel directionsLbl = new JLabel("Search a user by last name.");
				directionsLbl.setFont(new Font("Arial", Font.BOLD, 14));
				directionsLbl.setBounds(150, 263, 646, 17);
				addRemoveUserPanel.add(directionsLbl);
				
				JButton removeUserBtn = new JButton("Remove");
				removeUserBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							String username = list.getSelectedValue().substring(list.getSelectedValue().indexOf("(")+1, list.getSelectedValue().indexOf("@"));
							String[] info = User.getUserInfo(username);
							User user = new User(info[0], info[1], info[2]);
							
							Admin.removeUser(user.getFirstName(),user.getLastName(),user.getEmail());
							list.setModel(new AbstractListModel<String>() {
								String[] values = new String[0];
								
								public int getSize() {
									return values.length;
								}
								public String getElementAt(int index) {
									return values[index];
								}
							});
							successFailureRemoveLbl.setText("User successfully removed.");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (NullPointerException e1) {
							successFailureRemoveLbl.setText("Error: No user selected. Please try again.");
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
				
				panel.add(addRemoveUserPanel);
				panel.repaint();
				panel.revalidate();
			}
		});
		btnAddRemoveUser.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
		btnAddRemoveUser.setBounds(50, 201, 300, 43);
		add(btnAddRemoveUser);
		
		JButton btnGenerateReports = new JButton("Generate Reservation Reports");
		btnGenerateReports.setBackground(Color.BLACK);
		btnGenerateReports.setFocusable(false);
		btnGenerateReports.setFocusTraversalKeysEnabled(false);
		btnGenerateReports.setFocusPainted(false);
		btnGenerateReports.setBorderPainted(false);
		btnGenerateReports.setForeground(Color.YELLOW);
		btnGenerateReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				
				currentModeLbl_1.setText("Settings: Generate Reservation Reports");
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
				
				JScrollPane reportScrollPane = new JScrollPane();
				reportScrollPane.setBounds(0, 330, 851, 170);
				generateReportPanel.add(reportScrollPane);
				
				JList<String> reservationJList = new JList<String>();
				reportScrollPane.setViewportView(reservationJList);
				reservationJList.setFont(new Font("Arial", Font.PLAIN, 18));
				
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
				
				JLabel directionsLbl = new JLabel("Search a user by last name.");
				directionsLbl.setFont(new Font("Arial", Font.BOLD, 14));
				directionsLbl.setBounds(150, 72, 646, 17);
				generateReportPanel.add(directionsLbl);
				
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
								displayStr[i] = reservations.get(i).getDate() + " | " + reservations.get(i).getRoom() + " | Period " + reservations.get(i).getPeriod();
							}
							JLabel reportTitleLbl = new JLabel("Reservation Report For: " + user.getLastName() + ", " + user.getFirstName());
							reportTitleLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
							reportTitleLbl.setBounds(0, 291, 611, 28);
							generateReportPanel.add(reportTitleLbl);
							
							reservationJList.setModel(new AbstractListModel<String>() {
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
				
				panel.add(generateReportPanel);
				panel.repaint();
				panel.revalidate();
			}
		});
		btnGenerateReports.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
		btnGenerateReports.setBounds(50, 309, 300, 45);
		add(btnGenerateReports);
		
		JButton btnAddremoveCourses = new JButton("Add/Remove Courses");
		btnAddremoveCourses.setForeground(Color.YELLOW);
		btnAddremoveCourses.setFocusable(false);
		btnAddremoveCourses.setFocusTraversalKeysEnabled(false);
		btnAddremoveCourses.setFocusPainted(false);
		btnAddremoveCourses.setBorderPainted(false);
		btnAddremoveCourses.setBackground(Color.BLACK);
		btnAddremoveCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				
				JPanel addRemoveCourse = new JPanel();
				addRemoveCourse.setBackground(new Color(255, 255, 102));
				addRemoveCourse.setBounds(0,0,851, 537);
				addRemoveCourse.setLayout(null);
				
				JLabel currentModeLbl = new JLabel("Add/Remove Courses");
				currentModeLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.BOLD, 32));
				currentModeLbl.setBounds(0, 0, 796, 41);
				addRemoveCourse.add(currentModeLbl);
				
				JLabel addCourseLbl = new JLabel("Add Course");
				addCourseLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
				addCourseLbl.setBounds(0, 52, 611, 28);
				addRemoveCourse.add(addCourseLbl);
				
				JLabel successFailureLbl = new JLabel("");
				successFailureLbl.setFont(new Font("Arial", Font.PLAIN, 14));
				successFailureLbl.setBounds(0, 255, 450, 17);
				addRemoveCourse.add(successFailureLbl);
				
				JLabel removeCourseLbl = new JLabel("Remove Course");
				removeCourseLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
				removeCourseLbl.setBounds(0, 274, 611, 28);
				addRemoveCourse.add(removeCourseLbl);
				
				JLabel successFailureRemoveLbl = new JLabel();
				successFailureRemoveLbl.setFont(new Font("Arial", Font.PLAIN, 14));
				successFailureRemoveLbl.setBounds(150, 459, 554, 17);
				addRemoveCourse.add(successFailureRemoveLbl);
				
				JLabel courseNameLbl = new JLabel("Course Name");
				courseNameLbl.setVerticalAlignment(SwingConstants.TOP);
				courseNameLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 16));
				courseNameLbl.setBounds(0, 91, 245, 17);
				addRemoveCourse.add(courseNameLbl);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(0, 350, 851, 80);
				addRemoveCourse.add(scrollPane);
				
				JList<String> list = new JList<String>();
				list.setFont(new Font("Arial", Font.PLAIN, 18));
				list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane.setViewportView(list);
				
				JTextField searchCourseField = new JTextField();
				searchCourseField.setFont(new Font("Arial", Font.PLAIN, 16));
				searchCourseField.setBounds(150, 311, 691, 28);
				addRemoveCourse.add(searchCourseField);
				
				JButton searchBtn = new JButton("Search");
				searchBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							successFailureRemoveLbl.setText("");
							String query = searchCourseField.getText();
							ArrayList<Course> results = Course.searchCourse(query);
							String[] displayStr = new String[results.size()];
							for (int i = 0; i < results.size(); i++) {
								String oddEven;
								if (results.get(i).getDay() == 1) {
									oddEven = "Odd Days";
								}
								else if (results.get(i).getDay() == 0) {
									oddEven = "Even Days";
								}
								else {
									oddEven = "Both Days";
								}
								
								String halfFull;
								if (results.get(i).getYear() == 0) {
									halfFull = "1st Half";
								}
								else if (results.get(i).getYear() == 1) {
									halfFull = "2nd Half";
								}
								else {
									halfFull = "Full Year";
								}
								displayStr[i] = results.get(i).getCourseName() + " (Period " + results.get(i).getPeriod() + " " + oddEven + ", " + halfFull + " | Room " + results.get(i).getRoom() + ")";
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
							successFailureRemoveLbl.setText("Error: No courses found with that name. Please search another name and try again.");
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
				searchBtn.setBounds(0, 311, 140, 28);
				addRemoveCourse.add(searchBtn);
				
				JLabel directionsLbl = new JLabel("Search a course by its name.");
				directionsLbl.setFont(new Font("Arial", Font.BOLD, 14));
				directionsLbl.setBounds(150, 283, 646, 17);
				addRemoveCourse.add(directionsLbl);
				
				JButton removeCourseBtn = new JButton("Remove");
				removeCourseBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							String courseName = list.getSelectedValue().substring(0, list.getSelectedValue().indexOf(" ("));
							String periodAndDayStr = list.getSelectedValue().substring(list.getSelectedValue().indexOf("(Period ")+8,list.getSelectedValue().indexOf(","));
							String halfStr = list.getSelectedValue().substring(list.getSelectedValue().indexOf(",")+2, list.getSelectedValue().indexOf(" |"));
							
							System.out.println(periodAndDayStr.substring(0,periodAndDayStr.indexOf(" ")));
							int period = Integer.valueOf(periodAndDayStr.substring(0,periodAndDayStr.indexOf(" ")));
							int day;
							
							String dayStr = periodAndDayStr.substring(periodAndDayStr.indexOf(" ")+1);
							if (dayStr.equals("Odd Days"))
								day = 1;
							else if (dayStr.equals("Even Days"))
								day = 0;
							else
								day = 2;
							
							System.out.println("halfStr: " + halfStr);
							int half;
							if (halfStr.equals("1st Half"))
								half = 0;
							else if (halfStr.equals("2nd Half"))
								half = 1;
							else
								half = 2;
							
							String roomStr = list.getSelectedValue().substring(list.getSelectedValue().indexOf("| Room") + 7, list.getSelectedValue().length()-1);
							Course.removeCourse(courseName, roomStr, period, day, half);
							
							list.setModel(new AbstractListModel<String>() {
								String[] values = new String[0];
								
								public int getSize() {
									return values.length;
								}
								public String getElementAt(int index) {
									return values[index];
								}
							});
							
							successFailureRemoveLbl.setText("Course successfully removed.");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (NullPointerException e1) {
							successFailureRemoveLbl.setText("Error: No course selected. Please try again.");
						}
					}
				});
				removeCourseBtn.setForeground(Color.WHITE);
				removeCourseBtn.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 20));
				removeCourseBtn.setFocusable(false);
				removeCourseBtn.setFocusTraversalKeysEnabled(false);
				removeCourseBtn.setFocusPainted(false);
				removeCourseBtn.setBorderPainted(false);
				removeCourseBtn.setBackground(Color.RED);
				removeCourseBtn.setBounds(0, 441, 140, 35);
				addRemoveCourse.add(removeCourseBtn);
				
				JComboBox<String> roomComboBox = new JComboBox<String>();
				roomComboBox.setMaximumRowCount(5);
				try {
					roomComboBox.setModel(new DefaultComboBoxModel<String>(Reservation.getRoomsInDatabase()));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				roomComboBox.setFont(new Font("Arial", Font.PLAIN, 18));
				roomComboBox.setBounds(0, 170, 245, 28);
				addRemoveCourse.add(roomComboBox);
				
				JTextField courseNameField = new JTextField();
				courseNameField.setFont(new Font("Arial", Font.PLAIN, 16));
				courseNameField.setBounds(0, 110, 375, 28);
				addRemoveCourse.add(courseNameField);
				courseNameField.setColumns(10);
				
				JComboBox<String> dayComboBox = new JComboBox<String>();
				dayComboBox.setFont(new Font("Arial", Font.PLAIN, 18));
				dayComboBox.setModel(new DefaultComboBoxModel(new String[] {"Odd", "Even", "Both"}));
				dayComboBox.setBounds(400, 110, 173, 28);
				addRemoveCourse.add(dayComboBox);
				
				JLabel oddEvenLbl = new JLabel("Odd Days, Even Days, or Both?");
				oddEvenLbl.setVerticalAlignment(SwingConstants.TOP);
				oddEvenLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 16));
				oddEvenLbl.setBounds(400, 91, 184, 17);
				addRemoveCourse.add(oddEvenLbl);
				
				JLabel lblFullYearOr = new JLabel("Full Year or Half Year?");
				lblFullYearOr.setVerticalAlignment(SwingConstants.TOP);
				lblFullYearOr.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 16));
				lblFullYearOr.setBounds(604, 91, 189, 17);
				addRemoveCourse.add(lblFullYearOr);
				
				JComboBox yearComboBox = new JComboBox();
				yearComboBox.setModel(new DefaultComboBoxModel(new String[] {"Full Year", "1st Half", "2nd Half"}));
				yearComboBox.setFont(new Font("Arial", Font.PLAIN, 18));
				yearComboBox.setBounds(604, 110, 173, 28);
				addRemoveCourse.add(yearComboBox);
				
				JTextField periodField = new JTextField();
				periodField.setFont(new Font("Arial", Font.PLAIN, 16));
				periodField.setColumns(10);
				periodField.setBounds(265, 171, 97, 28);
				addRemoveCourse.add(periodField);
				
				JLabel lblRoomName = new JLabel("Room Name");
				lblRoomName.setVerticalAlignment(SwingConstants.TOP);
				lblRoomName.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 16));
				lblRoomName.setBounds(0, 149, 245, 17);
				addRemoveCourse.add(lblRoomName);
				
				JLabel periodLbl = new JLabel("Period Number");
				periodLbl.setVerticalAlignment(SwingConstants.TOP);
				periodLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 16));
				periodLbl.setBounds(265, 149, 97, 17);
				addRemoveCourse.add(periodLbl);
				
				JLabel firstNameLbl = new JLabel("Teacher First Name");
				firstNameLbl.setVerticalAlignment(SwingConstants.TOP);
				firstNameLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 16));
				firstNameLbl.setBounds(400, 149, 140, 17);
				addRemoveCourse.add(firstNameLbl);
				
				JTextField firstNameField = new JTextField();
				firstNameField.setFont(new Font("Arial", Font.PLAIN, 16));
				firstNameField.setColumns(10);
				firstNameField.setBounds(400, 170, 173, 28);
				addRemoveCourse.add(firstNameField);
				
				JLabel lastNameLbl = new JLabel("Teacher Last Name");
				lastNameLbl.setVerticalAlignment(SwingConstants.TOP);
				lastNameLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 16));
				lastNameLbl.setBounds(604, 149, 140, 17);
				addRemoveCourse.add(lastNameLbl);
				
				JTextField lastNameField = new JTextField();
				lastNameField.setFont(new Font("Arial", Font.PLAIN, 16));
				lastNameField.setColumns(10);
				lastNameField.setBounds(604, 170, 173, 28);
				addRemoveCourse.add(lastNameField);
				
				JLabel emailLbl = new JLabel("Teacher Email");
				emailLbl.setVerticalAlignment(SwingConstants.TOP);
				emailLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 16));
				emailLbl.setBounds(604, 209, 140, 17);
				addRemoveCourse.add(emailLbl);
				
				JTextField emailField = new JTextField();
				emailField.setFont(new Font("Arial", Font.PLAIN, 16));
				emailField.setColumns(10);
				emailField.setBounds(604, 235, 173, 28);
				addRemoveCourse.add(emailField);
				
				JButton addCourseBtn = new JButton("Add");
				addCourseBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int dayType;
						if (dayComboBox.getSelectedItem().equals("Odd")) {
							dayType = 1;
						}
						else if (dayComboBox.getSelectedItem().equals("Even")) {
							dayType = 0;
						}
						else {
							dayType = 2;
						}
						
						int yearType;
						if (yearComboBox.getSelectedItem().equals("Full Year")) {
							yearType = 2;
						}
						else if (yearComboBox.getSelectedItem().equals("1st Half")) {
							yearType = 0;
						}
						else {
							yearType = 1;
						}
						if (courseNameField.getText().equals("") || lastNameField.getText().equals("") || firstNameField.getText().equals("") || emailField.getText().equals(""))
							successFailureLbl.setText("Error: Do not leave any fields blank.");
						else {
							Course course = new Course(courseNameField.getText(),new User(firstNameField.getText(),lastNameField.getText(),emailField.getText()),roomComboBox.getSelectedItem().toString(),Integer.valueOf(periodField.getText()),dayType,yearType);
							try {
								course.addCourse();
								successFailureLbl.setText("Course successfully added");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				});
				addCourseBtn.setForeground(Color.WHITE);
				addCourseBtn.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 20));
				addCourseBtn.setFocusable(false);
				addCourseBtn.setFocusTraversalKeysEnabled(false);
				addCourseBtn.setFocusPainted(false);
				addCourseBtn.setBorderPainted(false);
				addCourseBtn.setBackground(new Color(34, 139, 34));
				addCourseBtn.setBounds(0, 209, 140, 35);
				addRemoveCourse.add(addCourseBtn);
				
				panel.add(addRemoveCourse);
				panel.repaint();
				panel.revalidate();
			}
		});
		btnAddremoveCourses.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
		btnAddremoveCourses.setBounds(50, 255, 300, 43);
		add(btnAddremoveCourses);
		
		JButton backBtn = new JButton("Return to Database");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					GUI.switchPanels(new AdminPanel(admin));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		backBtn.setForeground(Color.YELLOW);
		backBtn.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
		backBtn.setFocusable(false);
		backBtn.setFocusTraversalKeysEnabled(false);
		backBtn.setFocusPainted(false);
		backBtn.setBorderPainted(false);
		backBtn.setBackground(new Color(0, 0, 128));
		backBtn.setBounds(50, 600, 300, 45);
		add(backBtn);
		
		JButton newYearBtn = new JButton("Start a New Year");
		newYearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int input = JOptionPane.showConfirmDialog(null, "Are you sure you would like to start a new year? This will reset all reservations, courses, bulk reservation requests, and date information (this will not reset rooms and user information).", "Start a New Year?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (input == 0) {
					try {
						Admin.reset();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.exit(0);
				}
			}
		});
		newYearBtn.setForeground(Color.WHITE);
		newYearBtn.setFont(new Font("Franklin Gothic Medium Cond", Font.BOLD, 30));
		newYearBtn.setFocusable(false);
		newYearBtn.setFocusTraversalKeysEnabled(false);
		newYearBtn.setFocusPainted(false);
		newYearBtn.setBorderPainted(false);
		newYearBtn.setBackground(Color.RED);
		newYearBtn.setBounds(50, 421, 300, 45);
		add(newYearBtn);
		
		JButton addRemoveAdminBtn = new JButton("Add/Remove Admins");
		addRemoveAdminBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				
				JPanel addRemoveUserPanel = new JPanel();
				addRemoveUserPanel.setBackground(new Color(255, 255, 102));
				addRemoveUserPanel.setBounds(0,0,851, 537);
				addRemoveUserPanel.setLayout(null);
				
				JLabel currentModeLbl = new JLabel("Settings: Add/Remove Admins");
				currentModeLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.BOLD, 32));
				currentModeLbl.setBounds(0, 0, 796, 41);
				addRemoveUserPanel.add(currentModeLbl);
				
				JLabel addUserLbl = new JLabel("Add Admin");
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
				
				JLabel removeUserLbl = new JLabel("Remove Admin");
				removeUserLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
				removeUserLbl.setBounds(0, 252, 611, 28);
				addRemoveUserPanel.add(removeUserLbl);
				
				JLabel successFailureRemoveLbl = new JLabel();
				successFailureRemoveLbl.setFont(new Font("Arial", Font.PLAIN, 14));
				successFailureRemoveLbl.setBounds(150, 459, 554, 17);
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
							successFailureRemoveLbl.setText("");
							String query = searchUserTxtField.getText();
							ArrayList<Admin> results = Admin.searchAdmin(query);
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
				searchBtn.setBounds(0, 281, 140, 28);
				addRemoveUserPanel.add(searchBtn);
				
				JLabel directionsLbl = new JLabel("Search an admin by last name.");
				directionsLbl.setFont(new Font("Arial", Font.BOLD, 14));
				directionsLbl.setBounds(150, 263, 646, 17);
				addRemoveUserPanel.add(directionsLbl);
				
				JButton removeUserBtn = new JButton("Remove");
				removeUserBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							String username = list.getSelectedValue().substring(list.getSelectedValue().indexOf("(")+1, list.getSelectedValue().indexOf("@"));
							System.out.println(username);
							String[] info = Admin.findUserInfo(username);
							Admin user = new Admin(info[0], info[1], info[2]);
							
							Admin.removeAdmin(user.getFirstName(),user.getLastName(),user.getEmail());
							list.setModel(new AbstractListModel<String>() {
								String[] values = new String[0];
								
								public int getSize() {
									return values.length;
								}
								public String getElementAt(int index) {
									return values[index];
								}
							});
							successFailureRemoveLbl.setText("User successfully removed.");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (NullPointerException e1) {
							successFailureRemoveLbl.setText("Error: No user selected. Please try again.");
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
				
				panel.add(addRemoveUserPanel);
				panel.repaint();
			}
		});
		addRemoveAdminBtn.setForeground(Color.YELLOW);
		addRemoveAdminBtn.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
		addRemoveAdminBtn.setFocusable(false);
		addRemoveAdminBtn.setFocusTraversalKeysEnabled(false);
		addRemoveAdminBtn.setFocusPainted(false);
		addRemoveAdminBtn.setBorderPainted(false);
		addRemoveAdminBtn.setBackground(Color.BLACK);
		addRemoveAdminBtn.setBounds(50, 365, 300, 45);
		add(addRemoveAdminBtn);
	}
}
