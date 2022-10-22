package org.commackschools.reservation;

import java.awt.Font;
import java.awt.Rectangle;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReservationTablePanel extends JPanel {

	private Reservation[][] reservationTable;
	private JTable table;
	
	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
	public ReservationTablePanel(String date) throws IOException, NumberFormatException, ParseException {
		super();
		reservationTable = Reservation.generateTable(date);
		setBounds(new Rectangle(0, 0, 1180, 380));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1180, 380);
		add(scrollPane);
			
		table = new JTable();
		table.setFont(new Font("Arial", Font.PLAIN,18));
		table.setModel(new DefaultTableModel(Reservation.getNumPeriodsInDay()+1,Reservation.getNumRoomsInDatabase()+1) {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		});
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
		{
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		    {
		    	final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        if (row == 0 || column == 0) {
		        	c.setBackground(Color.LIGHT_GRAY);
		        	c.setFont(new Font("Arial", Font.BOLD, 22));
		        }
		        else {
		        	c.setBackground(null);
		        }
		        return c;
		    }
		});
		table.setTableHeader(null);
		table.setCellSelectionEnabled(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		for (int i = 1; i <= Reservation.getNumPeriodsInDay(); i++) {
			for (int j = 1; j <= Reservation.getNumRoomsInDatabase(); j++) {
				if (reservationTable[i-1][j-1] == null) {
					table.setValueAt("Empty", i, j);
					continue;
				}
				if (!(reservationTable[i-1][j-1].getDate().indexOf("/") == 2 && reservationTable[i-1][j-1].getDate().substring(reservationTable[i-1][j-1].getDate().indexOf("/")+1).indexOf("/") == 2))
					table.setValueAt("COURSE: " + reservationTable[i-1][j-1].getUser().getLastName() + ", " + reservationTable[i-1][j-1].getUser().getFirstName(), i, j);
				else
					table.setValueAt(reservationTable[i-1][j-1].getUser().getLastName() + ", " + reservationTable[i-1][j-1].getUser().getFirstName(), i, j);
			}
		}
		
		String[] roomsList = Reservation.getRoomsInDatabase();
		for (int i = 1; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(290);
			table.setRowHeight(36);
			table.getModel().setValueAt(roomsList[i-1], 0, i);
		}
		
		for (int period = 1; period <= Reservation.getNumPeriodsInDay(); period++) {
			table.getModel().setValueAt(Integer.toString(period), period, 0);
		}
		table.setBounds(50, 200, 1170, 510);
		scrollPane.setViewportView(table);
	}
	
	public Reservation[][] getReservation2DArray() {
		return reservationTable;
	}
	
	public JTable getReservationTable() {
		return table;
	}

}
