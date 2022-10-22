package org.commackschools.reservation;

import javax.swing.JPanel;
import java.awt.Rectangle;
import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

public class DatabasePanel extends JPanel {
	
	private User user;
	private ReservationTablePanel reservationTablePanel;
	private String strDate;

	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
	public DatabasePanel(User inUser) throws IOException, NumberFormatException, ParseException {
		inUser.updateReservationsAsArrayList();
		
		setBackground(new Color(255, 255, 102));
		user = inUser;
		Format f = new SimpleDateFormat("MM/dd/yyyy");
		strDate = f.format(new Date());
		
		setBounds(new Rectangle(0, 0, 1280, 720));
		setLayout(null);
		
		JLabel titleLbl = new JLabel("DATABASE PANEL");
		titleLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.BOLD, 52));
		titleLbl.setBounds(50, 50, 392, 60);
		add(titleLbl);
		
		JLabel topNameLbl = new JLabel("Logged In As: " + user.getFirstName() + " " + user.getLastName());
		topNameLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 28));
		topNameLbl.setBounds(50, 109, 620, 33);
		add(topNameLbl);
		
		JLabel dateName = new JLabel("Date: " + strDate);
		dateName.setHorizontalAlignment(SwingConstants.RIGHT);
		dateName.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
		dateName.setBounds(729, 60, 501, 38);
		add(dateName);
		
		reservationTablePanel = new ReservationTablePanel(strDate);
		reservationTablePanel.setBounds(50,153,1180, 380);
		add(reservationTablePanel);
		
		JLabel oddEvenLbl = new JLabel();
		oddEvenLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 28));
		oddEvenLbl.setBounds(891, 109, 339, 33);
		add(oddEvenLbl);
		
		//Date Frame
		JFrame dateFrame = new JFrame();
		dateFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		dateFrame.setBounds(0,0,250,360);
		JPanel contentPane = new JPanel();
		dateFrame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select a Date");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 216, 25);
		contentPane.add(lblNewLabel);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(10, 50, 216, 25);
		contentPane.add(dateChooser);
		
		JButton dateBtn = new JButton("Select Date");
		dateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Format f = new SimpleDateFormat("MM/dd/yyyy");
					strDate = f.format(dateChooser.getDate());
					dateName.setText("Date: " + strDate);
					reservationTablePanel.removeAll();
					reservationTablePanel = new ReservationTablePanel(strDate);
					if (Reservation.calculateOddEvenDay(strDate) == 1)
						oddEvenLbl.setText("Today is an Odd Day");
					else
						oddEvenLbl.setText("Today is an Even Day");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					JOptionPane.showMessageDialog(dateFrame, "No date has been selected. Please select a date", "No Date Selected", JOptionPane.ERROR_MESSAGE);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				reservationTablePanel.setBounds(50,153,1180, 380);
				add(reservationTablePanel);
				dateFrame.setVisible(false);
			}
		});
		dateBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dateBtn.setFocusable(false);
		dateBtn.setFocusTraversalKeysEnabled(false);
		dateBtn.setFocusPainted(false);
		dateBtn.setBounds(55, 285, 128, 25);
		contentPane.add(dateBtn);
		/////////
		
		if (Reservation.calculateOddEvenDay(strDate) == 1)
			oddEvenLbl.setText("Today is an Odd Day");
		else
			oddEvenLbl.setText("Today is an Even Day");
		
		JButton dateSelectBtn = new JButton("Select Date");
		dateSelectBtn.setBackground(Color.BLACK);
		dateSelectBtn.setForeground(Color.YELLOW);
		dateSelectBtn.setFocusable(false);
		dateSelectBtn.setFocusTraversalKeysEnabled(false);
		dateSelectBtn.setFocusPainted(false);
		dateSelectBtn.setBorderPainted(false);
		dateSelectBtn.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 22));
		dateSelectBtn.setBounds(891, 60, 151, 38);
		add(dateSelectBtn);
		
		
		dateSelectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dateFrame.setVisible(true);
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton selectBtn = new JButton("Create Reservation");
		selectBtn.setForeground(Color.YELLOW);
		selectBtn.setBackground(Color.BLACK);
		selectBtn.setBorderPainted(false);
		selectBtn.setFocusable(false);
		selectBtn.setFocusPainted(false);
		selectBtn.setFocusTraversalKeysEnabled(false);
		selectBtn.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
		selectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int selectedRow = reservationTablePanel.getReservationTable().getSelectedRow();
					int selectedCol = reservationTablePanel.getReservationTable().getSelectedColumn();
					String selectedValue = reservationTablePanel.getReservationTable().getValueAt(selectedRow, selectedCol).toString();
					if (selectedRow == 0 || selectedCol == 0) {
						JOptionPane.showMessageDialog(reservationTablePanel, "This is not a valid reservation slot. Please choose a valid reservation slot.", "Invalid Reservation Slot Chosen", JOptionPane.ERROR_MESSAGE);
					}
					else if (!(selectedValue.toUpperCase().equals("EMPTY"))) {
						JOptionPane.showMessageDialog(reservationTablePanel, "This reservation slot has been reserved. Please choose a different reservation slot.", "Reservation Slot Taken", JOptionPane.ERROR_MESSAGE);
					}
					else {
						Reservation newReservation = new Reservation(inUser, reservationTablePanel.getReservationTable().getValueAt(0, selectedCol).toString(), strDate, Integer.valueOf(reservationTablePanel.getReservationTable().getValueAt(selectedRow, 0).toString()));
						inUser.addReservation(newReservation);
						user.updateReservationsAsArrayList();
						reservationTablePanel.getReservationTable().setValueAt(newReservation.getUser().getLastName() + ", " + newReservation.getUser().getFirstName(), selectedRow, selectedCol);
						reservationTablePanel.removeAll();
						reservationTablePanel = new ReservationTablePanel(strDate);
						reservationTablePanel.setBounds(50,153,1180,380);
						add(reservationTablePanel);
						JOptionPane.showMessageDialog(reservationTablePanel, "Reservation successfully created!", "Reservation Created", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(reservationTablePanel, "No reservation slot has been chosen. Please select a reservation slot.", "No Reservation Slot Chosen", JOptionPane.ERROR_MESSAGE);
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
		selectBtn.setBounds(50, 544, 282, 45);
		add(selectBtn);
		
		JButton detailsBtn = new JButton("View Reservation Details");
		detailsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int selectedRow = reservationTablePanel.getReservationTable().getSelectedRow();
					int selectedCol = reservationTablePanel.getReservationTable().getSelectedColumn();
					String selectedValue = reservationTablePanel.getReservationTable().getValueAt(selectedRow, selectedCol).toString();
					if (selectedRow == 0 || selectedCol == 0) {
						JOptionPane.showMessageDialog(reservationTablePanel, "This is not a valid reservation. Please choose a valid reservation.", "Invalid Reservation Chosen", JOptionPane.ERROR_MESSAGE);
					}
					else if (selectedValue.toUpperCase().equals("EMPTY")) {
						JOptionPane.showMessageDialog(reservationTablePanel, "There is no reservation scheduled for this slot. Please choose a different reservation.", "No Reservation Found", JOptionPane.ERROR_MESSAGE);
					}
					else {
						String[] info = reservationTablePanel.getReservation2DArray()[selectedRow-1][selectedCol-1].getReservationInformation();
						String display = info[1] + ", " + info[0] + "\n" + "Email: " + info[2] + "\n" + "Room: " + info[3] + "\n" + info[4] +"\n" + "Period: " + info[5];
						JOptionPane.showMessageDialog(reservationTablePanel, display, "Reservation Details", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(reservationTablePanel, "No reservation slot has been chosen. Please select a reservation slot.", "No Reservation Slot Chosen", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton removeBtn = new JButton("Remove Reservation");
		removeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int selectedRow = reservationTablePanel.getReservationTable().getSelectedRow();
					int selectedCol = reservationTablePanel.getReservationTable().getSelectedColumn();
					String selectedValue = reservationTablePanel.getReservationTable().getValueAt(selectedRow, selectedCol).toString();
					if (selectedRow == 0 || selectedCol == 0) {
						JOptionPane.showMessageDialog(reservationTablePanel, "This is not a valid reservation. Please choose a valid reservation.", "Invalid Reservation Chosen", JOptionPane.ERROR_MESSAGE);
					}
					else if (selectedValue.toUpperCase().equals("EMPTY")) {
						JOptionPane.showMessageDialog(reservationTablePanel, "There is no reservation scheduled for this slot. Please choose a different reservation.", "No Reservation Found", JOptionPane.ERROR_MESSAGE);
					}
					else {
						if (!(reservationTablePanel.getReservation2DArray()[selectedRow-1][selectedCol-1].getUser().getEmail().equals(inUser.getEmail()))) {
							JOptionPane.showMessageDialog(reservationTablePanel, "You do not have permission to remove this reservation. Please choose one of your own reservations to delete.", "Reservation Removal: Lacking Permissions", JOptionPane.ERROR_MESSAGE);
						}
						else {
							Reservation newReservation = reservationTablePanel.getReservation2DArray()[selectedRow-1][selectedCol-1];
							inUser.removeReservation(newReservation);
							user.updateReservationsAsArrayList();
							reservationTablePanel.getReservationTable().setValueAt("Empty", selectedRow, selectedCol);
							reservationTablePanel.removeAll();
							reservationTablePanel = new ReservationTablePanel(strDate);
							reservationTablePanel.setBounds(50,153,1180,380);
							add(reservationTablePanel);
							JOptionPane.showMessageDialog(reservationTablePanel, "Reservation successfully removed!", "Reservation Removed", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				} catch (ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(reservationTablePanel, "No reservation slot has been chosen. Please select a reservation slot.", "No Reservation Slot Chosen", JOptionPane.ERROR_MESSAGE);
				} catch(IOException e1) {
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
		removeBtn.setForeground(Color.YELLOW);
		removeBtn.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
		removeBtn.setFocusable(false);
		removeBtn.setFocusTraversalKeysEnabled(false);
		removeBtn.setFocusPainted(false);
		removeBtn.setBorderPainted(false);
		removeBtn.setBackground(Color.BLACK);
		removeBtn.setBounds(342, 544, 282, 45);
		add(removeBtn);
		detailsBtn.setForeground(Color.YELLOW);
		detailsBtn.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
		detailsBtn.setFocusable(false);
		detailsBtn.setFocusTraversalKeysEnabled(false);
		detailsBtn.setFocusPainted(false);
		detailsBtn.setBorderPainted(false);
		detailsBtn.setBackground(Color.BLACK);
		detailsBtn.setBounds(634, 544, 282, 45);
		add(detailsBtn);
		
		JFrame requestFrame = new JFrame("Bulk Reservation Request");
		requestFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		requestFrame.setBounds(0,0,470,320);
		JPanel contentPane1 = new JPanel();
		requestFrame.setContentPane(contentPane1);
		contentPane1.setLayout(null);
		
		JLabel requestTitleLbl = new JLabel("Bulk Reservation Request Form");
		requestTitleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		requestTitleLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.BOLD, 24));
		requestTitleLbl.setBounds(65, 10, 318, 28);
		contentPane1.add(requestTitleLbl);
		
		JLabel directionsLbl = new JLabel("<html>Please request specfic dates/periods on this form if you would like to reserve more than 30 reservations.<html>");
		directionsLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 16));
		directionsLbl.setBounds(10, 39, 434, 40);
		contentPane1.add(directionsLbl);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 90, 434, 139);
		contentPane1.add(textArea);
		
		JButton sendRequestBtn = new JButton("Send Request");
		sendRequestBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					user.sendRequestToAdmin(textArea.getText());
					JOptionPane.showMessageDialog(requestFrame, "The request has been successfully sent.", "Request Successfully Sent", JOptionPane.PLAIN_MESSAGE);
					requestFrame.setVisible(false);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		sendRequestBtn.setFocusTraversalKeysEnabled(false);
		sendRequestBtn.setFocusPainted(false);
		sendRequestBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sendRequestBtn.setBounds(161, 242, 128, 28);
		contentPane1.add(sendRequestBtn);
		
		/////////
		JButton sendToAdminBtn = new JButton("Bulk Reservation Request");
		sendToAdminBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestFrame.setVisible(true);
			}
		});
		sendToAdminBtn.setForeground(Color.YELLOW);
		sendToAdminBtn.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
		sendToAdminBtn.setFocusable(false);
		sendToAdminBtn.setFocusTraversalKeysEnabled(false);
		sendToAdminBtn.setFocusPainted(false);
		sendToAdminBtn.setBorderPainted(false);
		sendToAdminBtn.setBackground(Color.BLACK);
		sendToAdminBtn.setBounds(926, 544, 282, 45);
		add(sendToAdminBtn);
		
		JButton returnToHome = new JButton("Return To Home");
		returnToHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI.switchPanels(new WelcomePanel());
			}
		});
		returnToHome.setForeground(new Color(255, 255, 0));
		returnToHome.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
		returnToHome.setFocusable(false);
		returnToHome.setFocusTraversalKeysEnabled(false);
		returnToHome.setFocusPainted(false);
		returnToHome.setBorderPainted(false);
		returnToHome.setBackground(new Color(0, 0, 128));
		returnToHome.setBounds(50, 600, 282, 45);
		add(returnToHome);
		
	}
}