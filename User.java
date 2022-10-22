package org.commackschools.reservation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class User {
	private String firstName;
	private String lastName;
	private String email;
	
	private ArrayList<Reservation> reservationsList = new ArrayList<Reservation>();;
	
	private final static String CURRENT = new File("User.java").getAbsolutePath();
	private final static String PATH = CURRENT.substring(0,CURRENT.length()-9);
	
	public User(String inFirstName, String inLastName, String inEmail) {
		firstName = inFirstName;
		lastName = inLastName;
		email = inEmail;
	}
	
	///Accessor Methods
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public ArrayList<Reservation> getReservations() {
		return reservationsList;
	}
	
	public static void register(String inFirstName, String inLastName, String inEmail) throws IOException {
		File file = new File(PATH + "\\resources\\registeredUsers.csv");
		FileWriter fw = new FileWriter(PATH + "\\resources\\registeredUsers.csv", true);
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
	
	/* method takes in a username
	 * and checks to see if username is registered in database
	 * @param loginUser the input username to check
	 * @return whether the username has been found
	 */
	public static boolean checkRegistration(String inUsername) throws FileNotFoundException {
		FileReader fr = new FileReader(PATH + "\\resources\\registeredUsers.csv");
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
		FileReader fr = new FileReader(PATH + "\\resources\\registeredUsers.csv");
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
	
	public void sendRequestToAdmin(String message) throws IOException {
		FileWriter fw = new FileWriter(PATH + "\\resources\\adminMessages.csv", true);
		BufferedWriter bw = new BufferedWriter(fw);
		try {
			bw.newLine();
			bw.write(email + " " + message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		bw.close();
	}
	
	public boolean isAdmin(String loginUser) throws IOException {
		FileReader fr = new FileReader(PATH + "\\resources\\registeredAdmins.csv");
		BufferedReader br = new BufferedReader(fr);
		boolean infoFound = false;
		try {
			String currentLine;
	        while ((currentLine = br.readLine()) != null) {
	        	String[] values = currentLine.split(",");
	        	if (values[2].indexOf(loginUser) >= 0) {
	        		infoFound = true;
	        	}
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
        br.close();
        return infoFound;
	}
	
	/*
	 * gets firstname, lastname, and email based on provided username
	 * @param loginUser the username
	 * @return array of strings in this order: firstname,lastname,email
	 * @throws IOException when csv file not found
	 */
	public static String[] getUserInfo(String loginUser) throws IOException {
		FileReader fr = new FileReader(PATH + "\\resources\\registeredUsers.csv");
		BufferedReader br = new BufferedReader(fr);
		String[] info = new String[3];
        if (checkRegistration(loginUser) == false) {
        	br.close();
        	return null;
        }
        try {
        	String currentLine;
	        while ((currentLine = br.readLine()) != null) {
	        	String[] values = currentLine.split(",");
	        	if (values[2].indexOf(loginUser) >= 0) {
	        		info = values;
	        	}
	        }
        } catch (IOException e) {
        	e.printStackTrace();
        }
        br.close();
        return info;
	}
	
	/* 
	 * searches the registeredUsers.csv file based on last name
	 * @param query the last name to search for
	 * @return all search results as an ArrayList of User
	 * @throws IOException when csv file not found
	 */
	public static ArrayList<User> searchUser(String query) throws IOException {
		FileReader fr = new FileReader(PATH + "\\resources\\registeredUsers.csv");
		BufferedReader br = new BufferedReader(fr);
		ArrayList<User> searchResults = new ArrayList<User>();
        try {
        	String currentLine;
	        while ((currentLine = br.readLine()) != null) {
	        	String[] values = currentLine.split(",");
	        	if (values[1].toUpperCase().contains(query.toUpperCase())) {
	        		searchResults.add(new User(values[0],values[1],values[2]));
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
	
	public void updateReservationsAsArrayList() throws IOException {
		File file = new File(PATH + "\\resources\\reservations.csv");
		FileReader fr = new FileReader(PATH + "\\resources\\reservations.csv");
		BufferedReader br = new BufferedReader(fr);
		reservationsList.clear();
		if (file.length() > 0) {
			try {
				String currentLine;
				while ((currentLine = br.readLine()) != null) {
					String[] values = currentLine.split(",");
					if ((this.getFirstName() + "," + this.getLastName()).equals(values[0] + "," + values[1])) {
						Reservation reservation = new Reservation(this,values[3],values[4],Integer.parseInt(values[5]));
						reservationsList.add(reservation);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		br.close();
	}
	
	public void addReservation(Reservation rs) throws IOException {
		File file = new File(PATH + "\\resources\\reservations.csv");
		FileWriter fw = new FileWriter(PATH + "\\resources\\reservations.csv",true);
		BufferedWriter bw = new BufferedWriter(fw);
		try {
			if (file.length() != 0)
				bw.newLine();
			bw.write(rs.getUser().getFirstName() + "," + rs.getUser().getLastName() + "," + rs.getUser().getEmail() + "," + rs.getRoom() + "," + rs.getDate() + "," + String.valueOf(rs.getPeriod()));
			updateReservationsAsArrayList();
		} catch (IOException e) {
			e.printStackTrace();
		}
		bw.close();
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileWriter fw = new FileWriter(PATH + "\\resources\\reservations.csv",false);
		BufferedWriter bw = new BufferedWriter(fw);
		try {
			if (list.size() > 0) {
				int i;
		        for (i = 0; i < list.size()-1; i++) {
		        	bw.write(list.get(i).getUser().getFirstName() + "," + list.get(i).getUser().getLastName() + "," + list.get(i).getUser().getEmail() + "," + list.get(i).getRoom() + "," + list.get(i).getDate() + "," + String.valueOf(list.get(i).getPeriod()));
		        	bw.newLine();
		        }
		        bw.write(list.get(i).getUser().getFirstName() + "," + list.get(i).getUser().getLastName() + "," + list.get(i).getUser().getEmail() + "," + list.get(i).getRoom() + "," + list.get(i).getDate() + "," + String.valueOf(list.get(i).getPeriod()));
			}
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
		br.close();
		bw.close();
	}
}
