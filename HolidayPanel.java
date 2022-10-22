package org.commackschools.reservation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class HolidayPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public HolidayPanel() {
		setBackground(new Color(255, 255, 102));
		setBounds(0,0,1280,720);
		setLayout(null);
		
		JLabel currentModeLbl = new JLabel("Welcome to a New Year!");
		currentModeLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.BOLD, 32));
		currentModeLbl.setBounds(26, 30, 796, 41);
		add(currentModeLbl);
		
		JLabel addCourseLbl = new JLabel("First/Last Day");
		addCourseLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
		addCourseLbl.setBounds(26, 82, 611, 28);
		add(addCourseLbl);
		
		JLabel successFailureLbl = new JLabel("");
		successFailureLbl.setFont(new Font("Arial", Font.PLAIN, 14));
		successFailureLbl.setBounds(176, 643, 450, 17);
		add(successFailureLbl);
		
		JLabel holidayLbl = new JLabel("Holidays");
		holidayLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
		holidayLbl.setBounds(26, 263, 611, 28);
		add(holidayLbl);
		
		JLabel courseNameLbl = new JLabel("First Day (mm/dd/yyyy; ie: 09/01/2021)");
		courseNameLbl.setVerticalAlignment(SwingConstants.TOP);
		courseNameLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 16));
		courseNameLbl.setBounds(26, 121, 245, 17);
		add(courseNameLbl);
		
		JLabel directionsLbl = new JLabel("Write each holiday in mm/dd/yyyy format, separated by a comma (ie: 12/24/2021,12/25/2021,01/01/2022)");
		directionsLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 18));
		directionsLbl.setBounds(26, 302, 751, 17);
		add(directionsLbl);
		
		JTextField firstDayField = new JTextField();
		firstDayField.setFont(new Font("Arial", Font.PLAIN, 16));
		firstDayField.setBounds(26, 140, 375, 28);
		add(firstDayField);
		firstDayField.setColumns(10);
		
		JLabel lblLastDaymmddyyyy = new JLabel("Last Day (mm/dd/yyyy; ie: 06/25/2021)");
		lblLastDaymmddyyyy.setVerticalAlignment(SwingConstants.TOP);
		lblLastDaymmddyyyy.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 16));
		lblLastDaymmddyyyy.setBounds(500, 121, 245, 17);
		add(lblLastDaymmddyyyy);
		
		JTextField lastDayField = new JTextField();
		lastDayField.setFont(new Font("Arial", Font.PLAIN, 16));
		lastDayField.setColumns(10);
		lastDayField.setBounds(500, 140, 375, 28);
		add(lastDayField);
		
		JTextField secondQuarterEndField = new JTextField();
		secondQuarterEndField.setFont(new Font("Arial", Font.PLAIN, 16));
		secondQuarterEndField.setBounds(500, 224, 375, 28);
		add(secondQuarterEndField);
		secondQuarterEndField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 330, 1200, 282);
		add(scrollPane);
		
		JTextArea holidaysTextArea = new JTextArea();
		scrollPane.setViewportView(holidaysTextArea);
		holidaysTextArea.setFont(new Font("Arial", Font.PLAIN, 18));
		
		JButton updateBtn = new JButton("Update");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (firstDayField.getText().equals("") || lastDayField.getText().equals("") || secondQuarterEndField.getText().equals("")) {
					successFailureLbl.setText("First day, second quarter end date, and last day cannot be blank.");
				}
				else {
					String[] holidays = holidaysTextArea.getText().split(",");
					String[] dates = new String[holidays.length+3];
					dates[0] = firstDayField.getText();
					dates[1] = secondQuarterEndField.getText();
					dates[2] = lastDayField.getText();
					for (int i = 3; i < dates.length; i++) {
						dates[i] = holidays[i-3];
					}
					try {
						Course.addImportantDates(dates);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					GUI.switchPanels(new WelcomePanel());
				}
			}
		});
		updateBtn.setForeground(Color.WHITE);
		updateBtn.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 20));
		updateBtn.setFocusable(false);
		updateBtn.setFocusTraversalKeysEnabled(false);
		updateBtn.setFocusPainted(false);
		updateBtn.setBorderPainted(false);
		updateBtn.setBackground(new Color(34, 139, 34));
		updateBtn.setBounds(26, 625, 140, 35);
		add(updateBtn);
		
		JLabel secondQuarterEndLbl = new JLabel("Second Quarter End Date (mm/dd/yyyy; ie: 01/28/2021)");
		secondQuarterEndLbl.setVerticalAlignment(SwingConstants.TOP);
		secondQuarterEndLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 16));
		secondQuarterEndLbl.setBounds(500, 207, 345, 17);
		add(secondQuarterEndLbl);
	}
}
