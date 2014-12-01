import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class NameReader {
	
	// class variables
	private String [] superHeros = null;
	private File inputFile = null;
	
	// class constructor
	public NameReader (String fileName){
		this.inputFile = new File (fileName);
	}
	
	public String [] getSuperHeroNames(){
		Scanner input;		
		try{
			input = new Scanner (inputFile);
			String firstLine = input.nextLine();
			int numberOfHeros = Integer.parseInt(firstLine);
			this.superHeros = new String[numberOfHeros];
			
			for (int i = 0; i < numberOfHeros; i++){
				String fileLine = input.nextLine();
				superHeros[i] = fileLine;
			}
			
			// debugging
//			System.out.println(superHeros.length);
//			for (int i = 0; i < superHeros.length; i++){
//				System.out.println(superHeros[i]);
//			}
			
			input.close();
		}catch(FileNotFoundException e){
			System.out.println("ERROR: file \"" + inputFile + "\" not found.");
		}
		
		return this.superHeros;
	}

}
