package org.commackschools.reservation;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WelcomePanel extends JPanel {
	
	private final static String CURRENT = new File("WelcomePanel.java").getAbsolutePath();
    private final static String PATH = CURRENT.substring(0,CURRENT.length()-17);

	/**
	 * Create the panel.
	 */
	public WelcomePanel() {
		setBackground(new Color(255, 255, 102));
		setBounds(0, 0, 1280, 720);
		setLayout(null);
		JLabel titleLbl = new JLabel("COMMACK HIGH SCHOOL");
		titleLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 72));
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setBounds(285, 200, 695, 65);
		add(titleLbl);
		
		JLabel title2Lbl = new JLabel("ROOM RESERVATION SYSTEM");
		title2Lbl.setHorizontalAlignment(SwingConstants.CENTER);
		title2Lbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 72));
		title2Lbl.setBounds(244, 276, 795, 78);
		add(title2Lbl);
		
		JLabel logoLbl = new JLabel(new ImageIcon(PATH + "\\resources\\logo.png"));
		logoLbl.setBounds(525, 11, 200, 200);
		add(logoLbl);
		
		JButton loginBtn = new JButton("Log In");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (User.checkRegistration(System.getProperty("user.name").toLowerCase())) {
						String userFirstName = User.findUserInfo(System.getProperty("user.name").toLowerCase())[0];
						String userLastName = User.findUserInfo(System.getProperty("user.name").toLowerCase())[1];
						String userEmail = User.findUserInfo(System.getProperty("user.name").toLowerCase())[2];
						User user = new User(userFirstName, userLastName, userEmail);
						GUI.switchPanels(new DatabasePanel(user));
					}
					else JOptionPane.showMessageDialog(loginBtn, "Unfortunately, the username logged into this computer (" + System.getProperty("user.name") + ") was not found to be registered as an user in the databse. Please contact your administrator for assistance.", "User Not Found", JOptionPane.ERROR_MESSAGE);
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
		loginBtn.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 36));
		loginBtn.setBorder(null);
		loginBtn.setBackground(new Color(0, 0, 0));
		loginBtn.setBorderPainted(false);
		loginBtn.setForeground(new Color(255, 255, 255));
		loginBtn.setFocusPainted(false);
		loginBtn.setFocusTraversalKeysEnabled(false);
		loginBtn.setFocusable(false);
		loginBtn.setBounds(430, 380, 379, 65);
		add(loginBtn);
		
		JButton adminLoginBtn = new JButton("Administrator Log In");
		adminLoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (Admin.checkRegistration(System.getProperty("user.name").toLowerCase())) {
						String userFirstName = Admin.findUserInfo(System.getProperty("user.name").toLowerCase())[0];
						String userLastName = Admin.findUserInfo(System.getProperty("user.name").toLowerCase())[1];
						String userEmail = Admin.findUserInfo(System.getProperty("user.name").toLowerCase())[2];
						Admin user = new Admin(userFirstName, userLastName, userEmail);
						GUI.switchPanels(new AdminPanel(user));
					}
					else JOptionPane.showMessageDialog(adminLoginBtn, "Unfortunately, the username logged into this computer (" + System.getProperty("user.name") + ") was not found to be registered as an administrator in the databse.", "Administrator Not Found", JOptionPane.ERROR_MESSAGE);
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
		adminLoginBtn.setForeground(Color.WHITE);
		adminLoginBtn.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 32));
		adminLoginBtn.setFocusable(false);
		adminLoginBtn.setFocusTraversalKeysEnabled(false);
		adminLoginBtn.setFocusPainted(false);
		adminLoginBtn.setBorderPainted(false);
		adminLoginBtn.setBorder(null);
		adminLoginBtn.setBackground(new Color(25, 25, 112));
		adminLoginBtn.setBounds(430, 456, 379, 50);
		add(adminLoginBtn);
		
		JButton infoBtn = new JButton("About the Developer");
		infoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI.switchPanels(new InfoPanel());
			}
		});
		infoBtn.setForeground(Color.WHITE);
		infoBtn.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 32));
		infoBtn.setFocusable(false);
		infoBtn.setFocusTraversalKeysEnabled(false);
		infoBtn.setFocusPainted(false);
		infoBtn.setBorderPainted(false);
		infoBtn.setBorder(null);
		infoBtn.setBackground(new Color(25, 25, 112));
		infoBtn.setBounds(430, 517, 379, 50);
		add(infoBtn);
	}
}