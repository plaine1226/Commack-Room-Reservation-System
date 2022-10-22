package org.commackschools.reservation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class Reservation {
	private User user;
	private String room;
	private String date;
	private int period;
	
	private static int numPeriodsInDay = 9;
	
	private final static String CURRENT = new File("Reservation.java").getAbsolutePath();
	private final static String PATH = CURRENT.substring(0,CURRENT.length()-16);
	
	public Reservation(User inUser, String inRoom, String inDate, int inPeriod) {
		user = inUser;
		room = inRoom;
		date = inDate;
		period = inPeriod;
	}

	//Accessor Methods
	public User getUser() {
		return user;
	}
	
	public String getRoom() {
		return room;
	}
	
	public String getDate() {
		return date;
	}
	
	public int getPeriod() {
		return period;
	}
	
	public static int getNumPeriodsInDay() {
		return numPeriodsInDay;
	}
	
	
	public static String setNumPeriodsInDay(int num, boolean isAdmin) {
		if (isAdmin == true) {
			numPeriodsInDay = num;
			return "You do not have sufficient permissions to update this.";
		}
		return "Number of periods in a day succesfully updated.";
	}
	
	/*
	 * reads a csv file of classroom names to store
	 * in a string array
	 * @return a list of the school's rooms stored in a string array
	 */
	public static String[] getRoomsInDatabase() throws IOException {
		FileReader fr = new FileReader(PATH + "\\resources\\rooms.csv");
		BufferedReader br = new BufferedReader(fr);
		ArrayList<String> roomList = new ArrayList<String>();
		int count = 0;
		String[] arr = null;
		
		try {
			String currentLine = "";
	        while ((currentLine = br.readLine()) != null) {
	        	roomList.add(currentLine);
	        	count++;
	        }
	        
	        arr = new String[count];
	        
	        for (int i = 0; i < count; i++) {
	        	arr[i] = roomList.get(i);
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        br.close();
        return arr;
	}
	
	public static int getNumRoomsInDatabase() throws IOException {
		FileReader fr = new FileReader(PATH + "\\resources\\rooms.csv");
		BufferedReader br = new BufferedReader(fr);
		int count = 0;
		
		try {
			String currentLine = "";
	        while ((currentLine = br.readLine()) != null) {
	        	count++;
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        br.close();
        return count;
	}
	
	public static int calculateOddEvenDay(String inDate) throws ParseException, IOException {
		FileReader fr = new FileReader(PATH + "\\resources\\schoolYearDates.csv"); 
		BufferedReader br = new BufferedReader(fr);
		
		SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = f.parse(br.readLine());
		Date endDate = f.parse(inDate);
		Calendar startCal = Calendar.getInstance();
	    startCal.setTime(startDate);        

	    Calendar endCal = Calendar.getInstance();
	    endCal.setTime(endDate);
	    
	    String currentLine;
	    int numHolidays = 0;
		while ((currentLine = br.readLine()) != null) {
	        if (f.parse(currentLine).after(startDate) && f.parse(currentLine).before(endDate))
	        	numHolidays++;
        }
	    
		
		Instant now = startDate.toInstant();
		Instant end = endDate.toInstant();
		int days = (int) ChronoUnit.DAYS.between(now,end) - numHolidays;
		return days%2;
	}
	
	public static int calculateHalfOfYear(String inDate) throws IOException, ParseException {
		FileReader fr = new FileReader(PATH + "\\resources\\schoolYearDates.csv"); 
		BufferedReader br = new BufferedReader(fr);
		
		
		SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy");
	    Date date1 = f.parse(inDate);
		br.readLine();
		Date lastDayOfSecondQuarter = f.parse(br.readLine());
		
		br.close();
		if (date1.after(lastDayOfSecondQuarter))
			return 1;
		return 0;
	}
	
	/*
	 * generates a table of reservations
	 * for a selected date
	 * @param inDate the selected date
	 * @return a 2d array of reservations for the selected date (rows: periods, cols: rooms)
	 */
	public static Reservation[][] generateTable(String inDate) throws IOException, NumberFormatException, ParseException {
		FileReader fr = new FileReader(PATH + "\\resources\\reservations.csv"); 
		BufferedReader br = new BufferedReader(fr);
		Reservation[][] table = new Reservation[numPeriodsInDay][getRoomsInDatabase().length];
		
		try {
			String currentLine;
	        while ((currentLine = br.readLine()) != null) {
	        	String[] values = currentLine.split(",");
	        	if (values[4].equals(inDate)) {
	        		table[Integer.parseInt(values[5])-1][Arrays.asList(getRoomsInDatabase()).indexOf(values[3])] = new Reservation(new User(values[0],values[1],values[2]),values[3],values[4],Integer.parseInt(values[5]));
	        	}
	        }
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
		br.close();
		
		
		FileReader fr1 = new FileReader(PATH + "\\resources\\courses.csv"); 
		BufferedReader br1 = new BufferedReader(fr1);
		try {
			String currentLine1;
	        while ((currentLine1 = br1.readLine()) != null) {
	        	String[] values = currentLine1.split(",");
	        	if (calculateHalfOfYear(inDate) == Integer.valueOf(values[7]) || Integer.valueOf(values[7]) == 2) {
	        		if (calculateOddEvenDay(inDate) == Integer.valueOf(values[6]) || Integer.valueOf(values[6]) == 2) {
	        			table[Integer.parseInt(values[5])-1][Arrays.asList(getRoomsInDatabase()).indexOf(values[4])] = new Reservation(new User(values[1],values[2],values[3]),values[4],values[0],Integer.parseInt(values[5]));
	        		}
	        	}
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		br1.close();
		return table;
	}
	
	public String[] getReservationInformation() {
		String[] info = {user.getFirstName(), user.getLastName(), user.getEmail(), room, date, Integer.toString(period)};
		return info;
	}
	
}
