package org.commackschools.reservation;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.File;
import java.io.IOException;

import javax.swing.JLayeredPane;

public class GUI extends JFrame {

	private JPanel contentPane;
	private static JLayeredPane layeredPane;
	
	private final static String CURRENT = new File("GUI.java").getAbsolutePath();
    private final static String PATH = CURRENT.substring(0,CURRENT.length()-8);
    
    private User user;
    

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		ImageIcon img = new ImageIcon(PATH + "\\resources\\icon.jpg");
		setIconImage(img.getImage());
		setTitle("Commack High School Room Reservation System");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1280, 720);
		contentPane.add(layeredPane);
		
		File file = new File(PATH + "\\resources\\schoolYearDates.csv");
		if (file.length() == 0)
			layeredPane.add(new HolidayPanel());
		else
			layeredPane.add(new WelcomePanel());
	}
	
	public static void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
}
