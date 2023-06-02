import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
public class HighScore {
private int[] hsList = new int[10];
	public HighScore() {
		// TODO Auto-generated method stub
		
		int i = 0;
		try {
			Scanner scanner = new Scanner(new File("highscores"));

			// reading lines from the scanner
			while (i<10) {
				// read one line at a time
				String line = scanner.nextLine();
				
				
				
				String[] individual = line.split(" ");
				
				hsList[i]=Integer.valueOf(individual[0]);
				i++;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void list(int tempList[]) {

		int[] newList = new int[10];
		newList=Arrays.copyOf(tempList, 10);
		try {
			
				

				FileWriter writer = new FileWriter("highscores");
				for (int i = 0; i < 10; i++) {
					writer.write(newList[i]+"\n");
				}

				writer.close();

			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int[] origList() {
		return hsList;
	}
	
	
	
	public void testList() {
		for(int i = 0;i<hsList.length;i++) {
			System.out.println(hsList[i]);
		}
	}
}
