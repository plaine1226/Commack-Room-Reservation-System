# Commack Room-Reservation System
## Introduction
Welcome to the Commack Room Reservation System! My name is Robin Hwang (CHS Class of 2022). 
I coded this application in Java 8 for an Internal Assessment for the course IB Computer Science SL. In a society that is growing digitally by the day, we have the power to take advantage of the new technology available to us. Thanks to technology, we can greatly facilitate the maintenance behind everyday tasks. This application was created to do just that. Almost 3000 lines of code in 13 Java classes power this user-friendly yet powerful application, and a wide variety of sort algorithms and search techniques were used. A super special thank you to Scott Sukiel for the nice selection of Lofi Hip Hop music that turned out to be perfect to listen to during my coding adventure. Another big thank you to Mrs. Kristin Holmes and Mr. Eric Biagi for always assisting me through the development process.

## Problem
My client, Mr. Biagi, is an Assistant Principal at my high school. He had a problem with the current system that the teachers use to reserve computer labs for a certain period. He wants a new system, because at times, some teachers would cross out the names of other teachers, creating confusion and frustration across the entire administration. A second problem with the current system is that teachers must go to a single location to reserve a lab because it is written in a book. Third, if two teachers show up at the same lab for the same period, it is hard to determine what went wrong. Fourth, teachers often forget their reservations, leaving labs empty, which is not good because labs are a scarce resource. Fifth, teachers try to reserve a lab for multiple weeks, preventing other teachers from getting a reservation for the lab. For now, he is sticking with the pen and paper system.

## Proposed Solution
After discussion with Mr. Biagi, I proposed a digital reservation system where teachers would be able to log in to view and reserve computer labs for their classes. This login system would require the teachers to sign on to the database, just as they would for a school computer. Not only would this prevent teachers editing other teachers’ responses, but since this desktop application is to be installed on the school computers, it would also prevent students from accessing the application. Since this is a desktop application that will be able to run on any computer in thedistrict, it solves the problem of teachers needing to go to a single location to make a reservation. Administrators will be allowed to override reservations and view logs for all reservations made by a teacher, which can allow them to figure out what problems occurred. The program will place a cap on the number of reservations that can be made at once, which will prevent teachers from scheduling an excessive number of reservations. The reservation system will allow users to see who made a reservation for a lab on which day, which will solve the problem of empty labs. My application would be unique from a Google Form reservation system because it rids the need for someone to sort the responses and log them onto a separate calendar. It also allows teachers to view which periods are already taken.

## Success Criteria
* Get data from a Comma-Seperated Value (CSV) file to verify a username and password input from the GUI and determine whether the user is an administrator.
* Allow administrators to make new accounts for a teacher and update a CSV file with this data
* Update a CSV file whenever a new reservation is created or an existing reservation is edited/deleted.
* Display data as a table in a GUI, showing the lab number on the column and the period number on the row.
* Add a calendar display to allow users to select specific dates from the GUI to see reservations on the date selected.
* Cap the number of reservations allowed at one time to prevent an excessive number of reservations to be made.
* Allow administrators to override any decisions made by a teacher and view reports of a teacher’s reservations.
* Automatically add odd/even day and semester courses to the calendar so that teachers can’t book that time slot

### Notice
All notes/exchanges from the meetings with my clients are included in the appendix.
