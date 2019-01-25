package src;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import java.util.Scanner;

public class Menu {
	public static void mainMenu()
	{
		Scanner s = new Scanner(System.in);
		
		while(true)
		{
			System.out.println("CS 320 Assigment 1 - Bus Schedules - Main Menu");
			System.out.println("1. Initial Stops || 2. Route Schedules || 0. Quit");
			String userInput = s.nextLine();
			switch(userInput)
			{
			case "1":
				initialStopsMenu(s);
				break;
			case "2":
				routeScheduleMenu(s);
				break;
			case "0":
				s.close();
				return;
			default:
				System.out.println("Invalid Input");
			}
		}
	}
	
	public static void initialStopsMenu(Scanner s)
	{
		while(true)
		{
			System.out.println("CS 320 Assigment 1 - Bus Schedules - Initial Stops Menu");
			System.out.println("Please enter a letter that your destinations start with or 0 to quit:");
			String userInput = s.nextLine();
			if (userInput.equals("0"))
			{
				return;
			}
			Pattern p = Pattern.compile("[a-zA-Z]");
			Matcher m = p.matcher(userInput);
			if(userInput.length()==1 && m.find())
			{
				Queries.initialStops(userInput.toUpperCase());
			}
			else
			{
				System.out.println("Invalid Input");
			}
			
		}
	}
	
	public static void routeScheduleMenu(Scanner s)
	{
		while(true)
		{
			System.out.println("CS 320 Assigment 1 - Bus Schedules - Route Schedules Menu");
			System.out.println("Please enter a route ID as a string or 0 to quit:");
			String userInput = s.nextLine();
			if (userInput.equals("0"))
			{
				return;
			}
			if (userInput.length()>0)
			{
				Queries.routeSchedule(userInput);
			}
			else
			{
				System.out.println("Invalid Input");
			}
			
		}
	}

}
