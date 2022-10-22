package org.commackschools.reservation;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BulkReservationRequestPanel extends JPanel {

	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public BulkReservationRequestPanel(Admin admin) throws IOException {
		setBackground(new Color(255, 255, 102));
		setBounds(new Rectangle(0, 0, 1280, 720));
		setLayout(null);
		
		JLabel titleLbl = new JLabel("BULK RESERVATION REQUESTS");
		titleLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.BOLD, 52));
		titleLbl.setBounds(50, 50, 670, 55);
		add(titleLbl);
		
		JTextArea requestTextArea = new JTextArea();
		requestTextArea.setEditable(false);
		requestTextArea.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
		String[] requests = admin.getRequestsForBulk();
		String strDisplay = requests[0];
		for (int i = 1; i < requests.length; i++) {
			strDisplay = strDisplay + "\n" + requests[i];
		}
		requestTextArea.setText(strDisplay);
		requestTextArea.setBounds(50, 116, 1175, 460);
		add(requestTextArea);
		
		JButton btnNewButton = new JButton("Return to Database");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					GUI.switchPanels(new DatabasePanel(admin)); 
				} catch (NumberFormatException | IOException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 24));
		btnNewButton.setBackground(new Color(25, 25, 112));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setFocusTraversalKeysEnabled(false);
		btnNewButton.setFocusable(false);
		btnNewButton.setBounds(50, 587, 249, 45);
		add(btnNewButton);
	}
}
