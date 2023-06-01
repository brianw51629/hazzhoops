import java.io.FileWriter;
import java.io.IOException;

public class HighScore {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		FileWriter file = new FileWriter("HighScore.txt", true);
		
		if(true) {
			System.out.println("waiting for nick to finish");
		}
		
		

	
	}

}
/*
 * 
 * package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DriverWritingExample {

	int[] highscoreList = new int[10];
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		try {
			Scanner scanner = new Scanner( new File("highscores") );
			 
			//reading lines from the scanner
			while(scanner.hasNextLine()) {
 
				//read one line at a time
				String line = scanner.nextLine();
				
				
				
				String[] individual = line.split(" ");
				
				System.out.println(Integer.valueOf(individual[0]));
				
			FileWriter writer = new FileWriter("highscores", true);
				
			
			writer.close();
			
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}

 * 
 * 
 * 
 * 
 * 
 * 
 */
