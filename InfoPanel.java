package org.commackschools.reservation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

public class InfoPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public InfoPanel() {
		
		setBackground(new Color(255, 255, 102));	
		setBounds(new Rectangle(0, 0, 1280, 720));
		setLayout(null);
		
		JLabel titleLbl = new JLabel("ABOUT THE DEVELOPER");
		titleLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.BOLD, 52));
		titleLbl.setBounds(50, 50, 551, 60);
		add(titleLbl);
		
		JLabel infoLbl = new JLabel("<html>Welcome to the Commack Room Reservation System!<br><br>My name is Robin Hwang (CHS Class of 2022). I coded this application in Java 8 for an Internal Assessment for the course IB Computer Science SL.<br><br>In a society that is growing digitally by the day, we have the power to take advantage of the new technology available to us. Thanks to technology, we can greatly facilitate the maintenance behind everyday tasks. This application was created to do just that.<br><br>Almost 3000 lines of code in 13 Java classes power this user-friendly yet powerful application, and a wide variety of sort algorithms and search techniques were used.<br><br>A super special thank you to Scott Sukiel for the nice selection of Lofi Hip Hop music that turned out to be perfect to listen to during my coding adventure.<br><br>Another big thank you to Mrs. Kristin Holmes and Mr. Eric Biagi for always assisting me through the development process.</html>");
		infoLbl.setVerticalAlignment(SwingConstants.TOP);
		infoLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
		infoLbl.setBounds(50, 121, 1177, 435);
		add(infoLbl);
		
		JButton returnHomeBtn = new JButton("Return to Home");
		returnHomeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI.switchPanels(new WelcomePanel());
			}
		});
		returnHomeBtn.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
		returnHomeBtn.setForeground(new Color(255, 255, 0));
		returnHomeBtn.setBackground(new Color(25, 25, 112));
		returnHomeBtn.setBorderPainted(false);
		returnHomeBtn.setFocusTraversalKeysEnabled(false);
		returnHomeBtn.setFocusPainted(false);
		returnHomeBtn.setFocusable(false);
		returnHomeBtn.setBounds(50, 567, 220, 41);
		add(returnHomeBtn);
		
	}
}
