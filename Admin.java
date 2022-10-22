package org.commackschools.reservation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class Admin extends User{
	private final static String CURRENT = new File("Admin.java").getAbsolutePath();
	private final static String PATH = CURRENT.substring(0,CURRENT.length()-10);
	
	public Admin(String inFirstName, String inLastName, String inEmail) {
		super(inFirstName, inLastName, inEmail);
	}
	
	/* method takes in a username
	 * and checks to see if username is registered in database
	 * @param loginUser the input username to check
	 * @return whether the username has been found
	 */
	public static boolean checkRegistration(String inUsername) throws FileNotFoundException {
		FileReader fr = new FileReader(PATH + "\\resources\\registeredAdmins.csv");
		BufferedReader br = new BufferedReader(fr);
		try {
			String line = br.readLine();
			while (line != null) {
				String[] values = line.split(",");
				String currentUsernameFromLine = values[2].substring(0, values[2].indexOf("@"));
				if (currentUsernameFromLine.equals(inUsername)) {
					return true;
				}
				line = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static String[] findUserInfo(String inUsername) throws FileNotFoundException {
		FileReader fr = new FileReader(PATH + "\\resources\\registeredAdmins.csv");
		BufferedReader br = new BufferedReader(fr);
		
		String[] info = new String[3];
		try {
			String line = br.readLine();
			while (line != null) {
				String[] values = line.split(",");
				String currentUsernameFromLine = values[2].substring(0, values[2].indexOf("@"));
				if (currentUsernameFromLine.equals(inUsername)) {
					info = values;
				}
				line = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return info;
	}
	
	public void removeReservation(Reservation rs) throws IOException { 
		FileReader fr = new FileReader(PATH + "\\resources\\reservations.csv");
		BufferedReader br = new BufferedReader(fr);
		
		ArrayList<Reservation> list = new ArrayList<Reservation>();
		try {
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				String[] values = currentLine.split(",");
				String[] rsValues = {rs.getUser().getFirstName(),rs.getUser().getLastName(), rs.getUser().getEmail(), rs.getRoom(), rs.getDate(), String.valueOf(rs.getPeriod())};
				if (!(Arrays.equals(values,rsValues))) {
					list.add(new Reservation(new User(values[0],values[1],values[2]), values[3], values[4], Integer.parseInt(values[5])));
				}
			}
			FileWriter fw = new FileWriter(PATH + "\\resources\\reservations.csv",false);
			BufferedWriter bw = new BufferedWriter(fw);
			
			if (list.size() > 0) {
				int i;
		        for (i = 0; i < list.size()-1; i++) {
		        	bw.write(list.get(i).getUser().getFirstName() + "," + list.get(i).getUser().getLastName() + "," + list.get(i).getUser().getEmail() + "," + list.get(i).getRoom() + "," + list.get(i).getDate() + "," + String.valueOf(list.get(i).getPeriod()));
		        	bw.newLine();
		        }
		        bw.write(list.get(i).getUser().getFirstName() + "," + list.get(i).getUser().getLastName() + "," + list.get(i).getUser().getEmail() + "," + list.get(i).getRoom() + "," + list.get(i).getDate() + "," + String.valueOf(list.get(i).getPeriod()));
		        
		        bw.close();
			}
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
		br.close();
	}
	
	public static String addRoomToDatabase(String room) throws IOException {
		FileWriter fw = new FileWriter(PATH + "\\resources\\rooms.csv",true);
		BufferedWriter bw = new BufferedWriter(fw);
		try {
			bw.newLine();
			bw.write(room);
		} catch (IOException e) {
			e.printStackTrace();
		}
		bw.close();
		return "Room successfully added.";
	}
	
	public static String removeRoomFromDatabase(String room) throws IOException {
		FileReader fr = new FileReader(PATH + "\\resources\\rooms.csv");
		BufferedReader reader = new BufferedReader(fr);
		String currentLine = "";

		if (room.equals("Select a Room")) {
			reader.close();
			return "Error: No room name was provided. Try again!";
		}
	    
		ArrayList<String> rooms = new ArrayList<String>();
		while ((currentLine = reader.readLine()) != null) {
        	if (!(currentLine.equals(room)))
        		rooms.add(currentLine);
        }
		
        FileWriter fw = new FileWriter(PATH + "\\resources\\rooms.csv");
		BufferedWriter writer = new BufferedWriter(fw);
		
		int i;
        for (i = 0; i < rooms.size()-1; i++) {
        	writer.write(rooms.get(i));
        	writer.newLine();
        }
        
        writer.write(rooms.get(i));
        reader.close();
        writer.close();
        
        removeReservationsForRoom(room);
        
        return "Room successfully removed from database.";
	}
	
	public static void removeReservationsForRoom(String room) throws IOException {
		FileReader fr = new FileReader(PATH + "\\resources\\reservations.csv");
		BufferedReader reader = new BufferedReader(fr);
		String currentLine = "";

		if (room.equals("Select a Room")) {
			reader.close();
		}
	    
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		while ((currentLine = reader.readLine()) != null) {
			String[] values = currentLine.split(",");
        	if (!(values[3].equals(room)))
        		reservations.add(new Reservation(new User(values[0],values[1],values[2]),values[3],values[4],Integer.valueOf(values[5])));
        }
		
        FileWriter fw = new FileWriter(PATH + "\\resources\\reservations.csv");
		BufferedWriter writer = new BufferedWriter(fw);
		if (reservations.size() > 0) {
			int i;
	        for (i = 0; i < reservations.size()-1; i++) {
	        	writer.write(reservations.get(i).getUser().getFirstName() + "," + reservations.get(i).getUser().getLastName() + "," + reservations.get(i).getUser().getEmail() + "," +  reservations.get(i).getRoom() + "," + reservations.get(i).getDate() + "," + reservations.get(i).getPeriod());
	        	writer.newLine();
	        }
	        writer.write(reservations.get(i).getUser().getFirstName() + "," + reservations.get(i).getUser().getLastName() + "," + reservations.get(i).getUser().getEmail() + "," +  reservations.get(i).getRoom() + "," + reservations.get(i).getDate() + "," + reservations.get(i).getPeriod());
		}
        reader.close();
        writer.close();
	}
	
	public static void removeReservationsForUser(String email) throws IOException {
		FileReader fr = new FileReader(PATH + "\\resources\\reservations.csv");
		BufferedReader reader = new BufferedReader(fr);
		String currentLine = "";
	    
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		while ((currentLine = reader.readLine()) != null) {
			String[] values = currentLine.split(",");
        	if (!(values[2].equals(email)))
        		reservations.add(new Reservation(new User(values[0],values[1],values[2]),values[3],values[4],Integer.valueOf(values[5])));
        }
		
        FileWriter fw = new FileWriter(PATH + "\\resources\\reservations.csv");
		BufferedWriter writer = new BufferedWriter(fw);
		int i;
        for (i = 0; i < reservations.size()-1; i++) {
        	writer.write(reservations.get(i).getUser().getFirstName() + "," + reservations.get(i).getUser().getLastName() + "," + reservations.get(i).getUser().getEmail() + "," +  reservations.get(i).getRoom() + "," + reservations.get(i).getDate() + "," + reservations.get(i).getPeriod());
        	writer.newLine();
        }
        
        writer.write(reservations.get(i).getUser().getFirstName() + "," + reservations.get(i).getUser().getLastName() + "," + reservations.get(i).getUser().getEmail() + "," +  reservations.get(i).getRoom() + "," + reservations.get(i).getDate() + "," + reservations.get(i).getPeriod());
        reader.close();
        writer.close();
	}
	
	public static void setNumPeriods(int inNum) {
		Reservation.setNumPeriodsInDay(inNum,true);
	}
	
	public static void register(String inFirstName, String inLastName, String inEmail) throws IOException {
		File file = new File(PATH + "\\resources\\registeredAdmins.csv");
		FileWriter fw = new FileWriter(PATH + "\\resources\\registeredAdmins.csv", true);
		BufferedWriter bw = new BufferedWriter(fw);
		try {
			if (file.length() > 0)
				bw.newLine();
			bw.write(inFirstName + "," + inLastName + "," + inEmail);
		} catch (IOException e) {
			e.printStackTrace();
		}
		bw.close();
	}
	
	public static void registerAdmins(String inFirstName, String inLastName, String inEmail) throws IOException {
		File file = new File(PATH + "\\resources\\registeredAdmins.csv");
		FileWriter fw = new FileWriter(PATH + "\\resources\\registeredAdmins.csv", true);
		BufferedWriter bw = new BufferedWriter(fw);
		try {
			if (file.length() > 0)
				bw.newLine();
			bw.write(inFirstName + "," + inLastName + "," + inEmail);
		} catch (IOException e) {
			e.printStackTrace();
		}
		bw.close();
	}
	
	public static void removeUser(String inFirstName, String inLastName, String inEmail) throws IOException {
		FileReader fr = new FileReader(PATH + "\\resources\\registeredUsers.csv");
		BufferedReader reader = new BufferedReader(fr);
		String currentLine = "";
	    
		ArrayList<User> users = new ArrayList<User>();
		while ((currentLine = reader.readLine()) != null) {
			String[] values = currentLine.split(",");
        	if (!(values[2].equals(inEmail)))
        		users.add(new User(values[0],values[1],values[2]));
        }
		
        FileWriter fw = new FileWriter(PATH + "\\resources\\registeredUsers.csv");
		BufferedWriter writer = new BufferedWriter(fw);
		int i;
        for (i = 0; i < users.size()-1; i++) {
        	writer.write(users.get(i).getFirstName() + "," + users.get(i).getLastName() + "," + users.get(i).getEmail());
        	writer.newLine();
        }
        
        writer.write(users.get(i).getFirstName() + "," + users.get(i).getLastName() + "," + users.get(i).getEmail());
        reader.close();
        writer.close();
        
        removeReservationsForUser(inEmail);
        
	}
	
	public static void removeAdmin(String inFirstName, String inLastName, 
	String inEmail) throws IOException {
		FileReader fr = new FileReader(PATH + "\\resources\\registeredAdmins.csv");
		BufferedReader reader = new BufferedReader(fr);
		String currentLine = "";
	    
		ArrayList<Admin> users = new ArrayList<Admin>();
		while ((currentLine = reader.readLine()) != null) {
			String[] values = currentLine.split(",");
        	if (!(values[2].equals(inEmail)))
        		users.add(new Admin(values[0],values[1],values[2]));
        }
		
        FileWriter fw = new FileWriter(PATH + "\\resources\\registeredAdmins.csv");
		BufferedWriter writer = new BufferedWriter(fw);
		int i;
        for (i = 0; i < users.size()-1; i++) {
        	writer.write(users.get(i).getFirstName() + "," + users.get(i).getLastName() + 
			"," + users.get(i).getEmail());
        	writer.newLine();
        }
        
        writer.write(users.get(i).getFirstName() + "," + users.get(i).getLastName() + 
"," + users.get(i).getEmail());
        reader.close();
        writer.close();
        
        removeReservationsForUser(inEmail);
        
	}
	
	/* 
	 * searches the registeredUsers.csv file based on last name
	 * @param query the last name to search for
	 * @return all search results as an ArrayList of User
	 * @throws IOException when csv file not found
	 */
	public static ArrayList<Admin> searchAdmin(String query) throws IOException {
		FileReader fr = new FileReader(PATH + "\\resources\\registeredAdmins.csv");
		BufferedReader br = new BufferedReader(fr);
		ArrayList<Admin> searchResults = new ArrayList<Admin>();
        try {
        	String currentLine;
	        while ((currentLine = br.readLine()) != null) {
	        	String[] values = currentLine.split(",");
	        	if (values[1].toUpperCase().contains(query.toUpperCase())) {
	        		searchResults.add(new Admin(values[0],values[1],values[2]));
	        	}
	        }
        } catch (IOException e) {
        	e.printStackTrace();
        }
        if (searchResults.size() == 0)
        	searchResults = null;
        br.close();
        return searchResults;
	}
	
	public String[] getRequestsForBulk() throws IOException {
		FileReader fr = new FileReader(PATH + "\\resources\\adminMessages.csv");
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
        System.out.println(Arrays.toString(arr));
        return arr;
	}
	
	public static void reset() throws IOException {
		FileWriter fw = new FileWriter(PATH + "\\resources\\reservations.csv");
		BufferedWriter writer = new BufferedWriter(fw);
		
		FileWriter fw1 = new FileWriter(PATH + "\\resources\\courses.csv");
		BufferedWriter writer1 = new BufferedWriter(fw1);
		
		FileWriter fw2 = new FileWriter(PATH + "\\resources\\schoolYearDates.csv");
		BufferedWriter writer2 = new BufferedWriter(fw1);
		
		FileWriter fw3 = new FileWriter(PATH + "\\resources\\adminMessages.csv");
		BufferedWriter writer3 = new BufferedWriter(fw1);
		
		writer.close();
		writer1.close();
		writer2.close();
		writer3.close();
	}
}