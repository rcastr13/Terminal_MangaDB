package MangaList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class MangaListSystemVer3 extends MangaListDriverVer3{
	static Scanner input = new Scanner(System.in);
	
	//For the jar file.
//	public String list = "MangaList.txt";
	//For Eclipse.
	public String list = "/Users/castro/Documents/PersonalProjects/2016Winter/Terminal_MangaDB/Terminal_MangaDB/src/MangaList/MangaList.txt";

	public String menu = "\nPlease enter a manga you'd like to search up\n(if you want, type \"ALL\" to see all manga in database): ";

	public String addMangaName = "Manga name (if possible: Romaji/English): ";
	public String addMangaTag = "Tag(s) (separate with commas): ";
	public String addMangaka = "Mangaka(s) (separate with commas): ";
	public String addGenre = "Genre(s) (separate with commas): ";
	public String addYearReleased = "Year released: ";
	public String addReadingStatus = "Reading status: ";

	public String results = "\nResults:\n--------\n";

	public String updateReadingStatus = "New reading status\neg.\nIF Completed: \"CM\"\nIF In Progress: -insert chapter here-, \"IP\"\nIF Caught Up: -insert chapter here-, \"CU\": ";

	public String[] tempArray = new String[7];
	
	public String line = null;
	
	ArrayList<MangaListObjectPropertiesVer3> mangaListArrayList = new ArrayList<MangaListObjectPropertiesVer3>();

	MangaListObjectPropertiesVer3 mangaListObj;

	public MangaListSystemVer3(){
	}

	public void readMangaList(){
		try{
			FileReader fReader = new FileReader(list);
			BufferedReader bReader = new BufferedReader(fReader);

			while((this.line = bReader.readLine()) != null){
				tempArray = line.split("\t");

				mangaListObj = new MangaListObjectPropertiesVer3(Integer.parseInt(tempArray[0]), tempArray[1], tempArray[2], 
				  tempArray[3], tempArray[4], Integer.parseInt(tempArray[5]), tempArray[6], tempArray[7]);

				mangaListArrayList.add(mangaListObj);
			}

			bReader.close();
		}catch(Exception e){
			System.out.println("\nThere are no manga stored in the MangaList.txt.");
		}
	}
	
	public void addManga(){
		try{
			FileReader fReader = new FileReader(list);
			BufferedReader bReader = new BufferedReader(fReader);
			
			int counter = 1;
			
			while((this.line = bReader.readLine()) != null){
			  counter++;
			}
			
			System.out.print("\n" + addMangaName);
			String mangaName = input.nextLine();
			
			System.out.print(addMangaTag);
			String mangaTag = input.nextLine();
			
			System.out.print(addMangaka);
			String mangakaName = input.nextLine();
			
			System.out.print(addGenre);
			String genre = input.nextLine();
			
			System.out.print(addYearReleased);
			String yearReleased = input.nextLine();
			
			System.out.print(addReadingStatus);
			String readingStatus = input.nextLine();
			
			String currentDate = new Date().toString();
			
			mangaListObj = new MangaListObjectPropertiesVer3(counter, mangaTag, mangaName, mangakaName, genre, 
				Integer.parseInt(yearReleased), readingStatus, currentDate);
			
			mangaListArrayList.add(mangaListObj);
			
			System.out.println("\nThe manga \"" + mangaName + "\" has been added to MangaList.txt.");
			
			
			bReader.close();
		}catch(Exception e){
			System.out.println("\nInvalid input.");
		}
	}
	
	public void writeInMangaList(){
		try{
			FileWriter fWriter = new FileWriter(list);
			BufferedWriter bWriter = new BufferedWriter(fWriter);
			    
			for(MangaListObjectPropertiesVer3 x: mangaListArrayList){
				bWriter.write(x.writeToFile());
				bWriter.newLine();
			}
	
		bWriter.close();
		}catch(Exception e){
			System.out.println("Error in writing to MangaList.txt.");
		}
	}
	
	public void searchForManga(){
		try{
			System.out.print("\nManga name (if you want to see all manga in\nMangaList.txt, enter \"All\"): ");
			
			String userChoice = input.nextLine();
			
			userChoice = userChoice.toUpperCase();
			
			if("all".toUpperCase().equals(userChoice)){
				System.out.print(results);
				
				int count = 0;
				
				for (MangaListObjectPropertiesVer3 x: mangaListArrayList){
					System.out.println("\nID: " + x.getID() + 
						"\nTag(s): " + x.getTag() + 
						"\nManga: " + x.getMangaName() + 
						"\nAuthor(s): " + x.getMangakaName() + 
						"\nGenre(s): " + x.getGenre() + 
						"\nYear released: " + x.getYearReleased() + 
						"\nReading status: " + x.getReadingStatus() + 
						"\nRevised: " + x.getCurrentDate());
					count++;
				}
				System.out.println("\nThere are " + count + " manga in the MangaDB.");
			}
			else{
				System.out.print(results);
				
				int counter = 0;
				
				for(MangaListObjectPropertiesVer3 x: mangaListArrayList){
					if(x.getMangaName().toUpperCase().contains(userChoice)){
						counter++;
						
						System.out.println("\nID: " + x.getID() + 
							"\nTag(s): " + x.getTag() + 
							"\nManga: " + x.getMangaName() + 
							"\nAuthor(s): " + x.getMangakaName() + 
							"\nGenre(s): " + x.getGenre() + 
							"\nYear released: " + x.getYearReleased() + 
							"\nReading status: " + x.getReadingStatus() + 
							"\nRevised: " + x.getCurrentDate());
					}
				}
				
				if(counter == 0){
					System.out.println("\nNo such manga exists in the MangaList.txt.");
				}
			}
		}catch(Exception e){
			System.out.println("There are no manga stored within the mangaListArrayList.");
		}
	}
	
	public void updateReadingStatus(){
		try{
			System.out.print("\n" + addMangaName);
			String mangaName = input.nextLine();
			
			String alteredMangaName = mangaName.toUpperCase();
			
			
			String manga = "";
			String readingStatus = "";
			String lastRevised = "";
			ArrayList<String> mangaWithSameNames = new ArrayList<String>();
			
			int counter = 0;
			
			for(MangaListObjectPropertiesVer3 x: mangaListArrayList){
				if(x.getMangaName().toUpperCase().contains(alteredMangaName)){
					manga = manga + x.getMangaName() + "\n";
					readingStatus = readingStatus + x.getReadingStatus() + "\n";
					lastRevised = lastRevised + x.getCurrentDate();
					mangaWithSameNames.add("ID: " + x.getID() + "\n" + 
						"Manga title: " + x.getMangaName() + "\n" + 
						"Reading status: " + x.getReadingStatus() + "\n" + 
						"Revised: " + x.getCurrentDate() + "\n");

					counter++;
				}
			}
			
			if(counter == 0){
				System.out.println("\nThere is no " + mangaName + " in the MangaList.txt.\n" + 
					"You can, however, add it.");
			}
			else if(counter >= 2){
				System.out.println("\nToo many results.");
				//TODO Narrow down results.
			}else if(counter == 1){
				System.out.print("\nIs:\n\nManga title: " + manga + "Reading status: " + readingStatus + 
					"Revised: " + lastRevised + 
					"\n\nthe manga you are " + 
					"looking to update?\n" + "(\"Yes\" will equate to confirming and any other input\nwill result in cancelling " + 
					"the reading status update): ");

				String userConfirm = input.nextLine();

				userConfirm = userConfirm.toUpperCase();
				
				if("yes".toUpperCase().equals(userConfirm)){
					System.out.print("\n" + updateReadingStatus);
					String updateReadingStat = input.nextLine();
					
					for(MangaListObjectPropertiesVer3 x: mangaListArrayList){
						if(x.getMangaName().toUpperCase().contains(alteredMangaName)){
							x.setReadingStatus(updateReadingStat);
							x.setCurrentDate();
							
							System.out.println("\nID: " + x.getID() + "\nTag(s): " + 
								x.getTag() + "\nManga: " + x.getMangaName() + 
								"\nAuthor(s): " + x.getMangakaName() + 
								"\nGenre(s): " + x.getGenre() + 
								"\nYear released: " + x.getYearReleased() + 
								"\n||~UPDATED~|| Reading status: " + x.getReadingStatus() + 
								"\n||~UPDATED~|| Revised: " + x.getCurrentDate());
						}
					}
				}
				else if(!"yes".toUpperCase().equals(userConfirm)){
					System.out.println("\nUpdate cancelled.");
				}
			}
		}catch(Exception e){
			System.out.println("No manga found/invalid input.");
		}
	}
	
	public void searchCompleted(){
		try{
			System.out.println(results);
			
			int counter = 0;
			ArrayList<String> completedList = new ArrayList<String>();
			
			for(MangaListObjectPropertiesVer3 x: mangaListArrayList){
				if(x.getReadingStatus().contains("CM")){
					completedList.add(x.getMangaName());
			    
					counter++;
				}
			}
			
			if(counter <= 0){
				System.out.println("There are currently no manga that have been completely read...\nYet.");
			}else{
				alphabetizeResults(completedList);
			}
		}catch(Exception e){
			System.out.println("There are currently no manga that have a reading\nstatus of: \"CM\".");
		}
	}
	
	public void searchInProgress()
	{
		try{
			System.out.println(results);
			
			int counter = 0;
			ArrayList<String> inProgressList = new ArrayList<String>();
			
			for(MangaListObjectPropertiesVer3 x: mangaListArrayList){
				if(x.getReadingStatus().contains("IP")){
					inProgressList.add(x.getMangaName() + "; " + x.getReadingStatus());
					
					counter++;
				}
			}
			
			if(counter <= 0){
				System.out.println("There are currently no manga that are being read.");
			}else{
				alphabetizeResults(inProgressList);
			}
		}catch(Exception e){
			System.out.println("There are currently no manga that hava a reading\nstatus of: \"IP\".");
		}
	}
	
	public void searchCaughtUp(){
		try{
			System.out.println(results);
			
			int counter = 0;
			ArrayList<String> caughtUpList = new ArrayList<String>();
			
			for(MangaListObjectPropertiesVer3 x: mangaListArrayList){
				if (x.getReadingStatus().contains("CU")){
					caughtUpList.add(x.getMangaName() + "; " + x.getReadingStatus());
					
					counter++;
				}
			}
			
			if(counter <= 0){
				System.out.println("There are currently no manga that are being read.");
			}else{
				alphabetizeResults(caughtUpList);
				System.out.println(counter + " in total.");
			}
	  	}catch(Exception e){
	  		System.out.println("There are currently no manga that hava a reading\nstatus of: \"CU\".");
		}
	}
	
	public void searchGenre(){
		try{
			System.out.print("\nGenre type: ");
			
			String userGenre = input.nextLine();
			
			userGenre = userGenre.toUpperCase();
			
			int counter = 0;
			
			ArrayList<String> mangaWithGenreList = new ArrayList<String>();
			
			for(MangaListObjectPropertiesVer3 x: mangaListArrayList){
				if(x.getGenre().toUpperCase().contains(userGenre)){
					mangaWithGenreList.add(x.getMangaName() + "; " + x.getReadingStatus());
					
					counter++;
				}
			}
			
			if(counter <= 0){
				System.out.println("\nThere are currently no manga that have that genre style.\n");
			}else{
				System.out.println(results);
				alphabetizeResults(mangaWithGenreList);
			}
		}catch(Exception e){
			System.out.println("\nThere are currently no manga that have that genre style.\n");
		}
	}
	
	public static void alphabetizeResults(ArrayList<String> mangaNames){
		int index;

		for(int i = 0; i < mangaNames.size(); i++){
			String temp = (String)mangaNames.get(i);
			index = i;
			
			for(int k = i + 1; k < mangaNames.size(); k++){
				if(((String)mangaNames.get(k)).compareTo(temp) < 0){
					temp = (String)mangaNames.get(k);
					index = k;
				}
			}
			
			mangaNames.set(index, (String)mangaNames.get(i));
			mangaNames.set(i, temp);
		}
		
		for(String listOfNames : mangaNames){
			System.out.println(listOfNames + "\n");
		}
	}

	public void loopMenu(){
		readMangaList();
		
		System.out.println("\t    ||=============================||\n\t    ||~Rolf's Manga Database Model~||\n\t    ||=============================||\n");
		
		System.out.println("\t\t||======================||\n\t\t||\t!WARNING!\t||\n\t\t||======================||\n!!!WHEN ADDING A NEW MANGA DO NOT FORCEFULLY EXIT VIA \"Ctrl + C\"!!!\n!!!   THIS WILL DELETE ALL THE CONTENTS OF THE mangalist.txt    !!!\n\t\t-STILL REQUIRES DEBUGGING-\n\n*NOTE: When attempting to add a manga, please make sure to CHECK IF IT ALREADY EXISTS\n BEFORE ADDING IT INTO THE DATABASE MODEL.\n");
		
		System.out.println("*NOTE: If reading status contains two separate chapters,\nthe gap between has been skipped (for reason(s) withheld from the\ndatabase owner).");
		
		System.out.println("\n*NOTE: Use the database in the jarPrograms folder for\na more accurate manga list database.");
		
		System.out.println("\nKEY:\nCM - \"COMPLETE\"\nIP - \"IN PROGRESS\" (Refer to HELP)\nCU - \"CAUGHT UP\" (Refer to HELP)");
		
		String mainMenu = "\nPlease enter a valid option on the menu:\n1 - Add manga.\n2 - Search for manga.\n3 - Update reading status of a manga.\n4 - Display completed reading list.\n5 - Display in progress list.\n6 - Display caught up list.\n7 - Search by genre.\n123 - Quit program.\nHELP - Display help options.\nYour option: ";
	
		String help = "\n\n-------------\nHELP OPTIONS:\n-------------\n||OPTION 1|| -- Add manga.\n -The user is asked for the following information of the manga:\n  --Manga Name (preferred format: Romaji/English)\n    *IF ROMAJI IS DIFFICULT TO FIND, ENGLISH SHALL SUFFICE*\n  --Manga Tag (tag types: NSFW, NSFL, FAV, REGRETS)\n    *ALL TAGS MUST BE IN ALL CAPS*\n  --Mangaka (preferred format: last name, first name)\n    *ALL PEN NAMES MUST BE IN ALL CAPS*\n  --Genre\n  --Year Released\n  --Reading Status (refer to ||OPTION 3|| for reading status format)\n\t\t||***WARNING***||\nDo NOT terminate (Ctrl + C or Ctrl + Z) the program while adding a manga!\nIT WILL DELETE ALL OF THE INFORMATION IN MangaList.txt!\nThis still requires heavy debugging!\n\t\t||***WARNING***||\n -This will add the manga information that has been provided by the user into the database model.\n\n\n||OPTION 2|| -- Search for manga.\n -The user is asked for a MANGA TITLE in ROMAJI AND ENGLISH (if Romaji is unknown, English will suffice).\n  --This will then display the results in the order of Manga title of insertion (ID numbers).\n\n\n||OPTION 3|| -- Update reading status of a manga.\n *NOTE: IF the format is NOT followed accordingly, OPTIONS 4 and 5 WILL be rendered USELESS as it seeks for the SPECIFIC ACRONYMS at the END OF THE READING STATUS! Therefore, add the required acronym suffix to better organize and have better accuracy in using YOUR MangaList.txt database model!\n -The user is asked for a SPECIFIC MANGA (singular) to update the reading status of (IF multiple manga are within the user'(s) choice, it will display an error instead).\n -Once a manga has been found, it will ask to confirm the manga that has been returned if that was the desired manga to alter the reading status of (any other types of input other than the required will be considered as a cancellation of update.\n -Finally, the rules of modifying the reading status of manga are as follows:\n  --Chapter abreviation then the chapter number (in a 3 digit format)\n    eg. \"Ch. 888\" or \"Ch. 008\"\n  --A required acronym suffix after the current chapter followed by a comma:\n    ie. IP = \"In Progress\" or CM = \"Complete\" or CU = \"Caught Up\"\n    eg. \"Ch. 888, IP\" or \"Ch. 008, IP\" or \"Ch. 000, CU\"\n\n\n||OPTION 4|| -- Display completed reading list.\n -The user will be given a list of completed manga titles that the user has added the acronym suffix \"CM\" to the reading status.\n\n\n||OPTION 5|| -- Display in progress list.\n -The user will be given a list of in progress manga titles that the user has added the acronym suffix \"IP\" to the reading status.\n\n\n||OPTION 6|| -- Display caught up list.\n -The user will be given a list of caught up manga titles that the user has added the acronym suffix \"CU\" to the reading status.\n\n\n||OPTION 7|| -- Search by genre.\n -The user will be asked for a genre type of their choosing to search for.\n\n\n||HELP|| -- Display help options -It will do as it is written, display the help options.\n";

		String farewell = "\nProgram will now terminate. Farewell~!";
		
		System.out.print(mainMenu);
		
		
		boolean loop = true;
		
		while (loop) {
			String userInput = input.nextLine();
			
			if (userInput.equals("1")){
				System.out.println("\nOPTION 1: ADD MANGA");
				addManga();
				writeInMangaList();
				
				System.out.print(mainMenu);
			}else if(userInput.equals("2")){
				System.out.println("\nOPTION 2: SEARCH FOR MANGA");
				searchForManga();
				  
				System.out.print(mainMenu);
			}else if(userInput.equals("3")){
				System.out.println("\nOPTION 3: UPDATE READING STATUS OF A MANGA");
				updateReadingStatus();
				writeInMangaList();
				
				System.out.print(mainMenu);
			}else if(userInput.equals("4")){
				System.out.println("\nOPTION 4: DISPLAY COMPLETED READING LIST");
				searchCompleted();
				
				System.out.print(mainMenu);
			}else if(userInput.equals("5")){
				System.out.println("\nOPTION 5: DISPLAY IN PROGRESS LIST");
				searchInProgress();
				
				System.out.print(mainMenu);
			}else if(userInput.equals("6")){
				System.out.println("\nOPTION 6: DISPLAY CAUGHT UP LIST");
				searchCaughtUp();
				
				System.out.print(mainMenu);
			}else if(userInput.equals("7")){
				System.out.println("\nOPTION 7: SEARCH BY GENRE");
				searchGenre();
				
				System.out.print(mainMenu);
			}else if(userInput.equals("123")){
				System.out.println(farewell);
				
				
				System.exit(0);
			}else if(userInput.equalsIgnoreCase("HELP")){
				System.out.println("\nOPTION HELP: DISPLAY HELP OPTIONS");
				System.out.println(help);
				
				System.out.print(mainMenu);
			}else{
				System.out.println("You have entered an invalid input.");
				System.out.print(mainMenu);
			}
		}
	}
}
