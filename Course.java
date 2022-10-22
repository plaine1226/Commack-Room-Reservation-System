package org.commackschools.reservation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Course {

	private String courseName;
	private User user;
	private String room;
	private int period;
	private int day; //0 = even, 1 = odd, 2 = every day
	private int year; //0 = first half, 1 = second half, 2 = full year
	
	private final static String CURRENT = new File("Course.java").getAbsolutePath();
	private final static String PATH = CURRENT.substring(0,CURRENT.length()-11);
	
	public Course(String inCourseName, User inUser, String inRoom, int inPeriod, int inDay, int inYear) {
		courseName = inCourseName;
		user = inUser;
		room = inRoom;
		period = inPeriod;
		day = inDay;
		year = inYear;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public User getUser() {
		return user;
	}
	
	public String getRoom() {
		return room;
	}
	
	public int getPeriod() {
		return period;
	}
	
	public int getDay() {
		return day;
	}
	
	public int getYear() {
		return year;
	}
	
	public static void addImportantDates(String[] list) throws IOException {
		FileWriter fw = new FileWriter(PATH + "\\resources\\schoolYearDates.csv");
		BufferedWriter bw = new BufferedWriter(fw);
		try {
			int i;
	        for (i = 0; i < list.length-1; i++) {
	        	bw.write(list[i]);
	        	bw.newLine();
	        }
	        bw.write(list[i]);
	        
	        bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Course> searchCourse(String query) throws IOException {
		FileReader fr = new FileReader(PATH + "\\resources\\courses.csv");
		BufferedReader br = new BufferedReader(fr);
		ArrayList<Course> searchResults = new ArrayList<Course>();
        try {
        	String currentLine;
	        while ((currentLine = br.readLine()) != null) {
	        	String[] values = currentLine.split(",");
	        	if (values[0].toUpperCase().contains(query.toUpperCase())) {
	        		searchResults.add(new Course(values[0],new User(values[1],values[2],values[3]),values[4],Integer.valueOf(values[5]),Integer.valueOf(values[6]),Integer.valueOf(values[7])));
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
	
	public static String[] getUserInfo(String loginUser) throws IOException {
		FileReader fr = new FileReader(PATH + "\\resources\\registeredUsers.csv");
		BufferedReader br = new BufferedReader(fr);
		String[] info = new String[3];

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
	
	public void addCourse() throws IOException {
		File file = new File(PATH + "\\resources\\courses.csv");
		FileWriter fw = new FileWriter(PATH + "\\resources\\courses.csv",true);
		BufferedWriter bw = new BufferedWriter(fw);
		try {
			if (file.length() != 0)
				bw.newLine();
			bw.write(courseName + "," + user.getFirstName() + "," + user.getLastName() + "," + user.getEmail() + "," + room + "," + period + "," + String.valueOf(day) + "," + String.valueOf(year));
		} catch (IOException e) {
			e.printStackTrace();
		}
		bw.close();
	}
	
	public static void removeCourse(String inCourseName, String inRoomName, int inPeriod, int inDay, int inHalf) throws IOException {
		FileReader fr = new FileReader(PATH + "\\resources\\courses.csv");
		BufferedReader reader = new BufferedReader(fr);
		String currentLine = "";
	    
		ArrayList<Course> courses = new ArrayList<Course>();
		System.out.println("CourseName from Course Class: " + inCourseName);
		System.out.println("Period from Course Class: " + inPeriod);
		System.out.println("Day: " + inDay);
		System.out.println("Half from Course Class: " + inHalf);
		while ((currentLine = reader.readLine()) != null) {
			String[] values = currentLine.split(",");
        	if (!(values[0].equals(inCourseName) && values[4].equals(inRoomName) && values[5].equals(Integer.toString(inPeriod)) && values[6].equals(Integer.toString(inDay)) && values[7].equals(Integer.toString(inHalf)))) {
        		courses.add(new Course(values[0],new User(values[1],values[2],values[3]),values[4],Integer.valueOf(values[5]),Integer.valueOf(values[6]),Integer.valueOf(values[7])));
        		System.out.println("match not found");
        	}
        }
		
		
        FileWriter fw = new FileWriter(PATH + "\\resources\\courses.csv");
		BufferedWriter writer = new BufferedWriter(fw);
		if (courses.size() > 0) {
			int i;
	        for (i = 0; i < courses.size()-1; i++) {
	        	writer.write(courses.get(i).getCourseName() + "," + courses.get(i).getUser().getFirstName() + "," + courses.get(i).getUser().getLastName() + "," + courses.get(i).getUser().getEmail() + "," + courses.get(i).getRoom() + "," + Integer.toString(courses.get(i).getPeriod()) + "," + Integer.toString(courses.get(i).getDay()) + "," + Integer.toString(courses.get(i).getYear()));
	        	writer.newLine();
	        }
	        
	        writer.write(courses.get(i).getCourseName() + "," + courses.get(i).getUser().getFirstName() + "," + courses.get(i).getUser().getLastName() + "," + courses.get(i).getUser().getEmail() + "," + courses.get(i).getRoom() + "," + Integer.toString(courses.get(i).getPeriod()) + "," + Integer.toString(courses.get(i).getDay()) + "," + Integer.toString(courses.get(i).getYear()));
		}
	    reader.close();
        writer.close();
        
	}
	
}
