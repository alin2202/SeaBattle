import java.util.Random;

public class CommonFunctions {
	
	//random generator for integers
	public static int randomInt (int min, int max){
		Random randomGenerator = new Random();
		int randomNumber = randomGenerator.nextInt((max - min) + 1) + min;
		return randomNumber;
	}

	// random boolean
	public static boolean randomBoolean (){
		boolean randomBoolean;
		int number = randomInt(0, 1);
		if (number==0){
			randomBoolean = false;
		}else{
			randomBoolean = true;
		}
		return randomBoolean;
	}
	
	//function checking if element is integer
	public static boolean isInteger(String s) {
		  try { 
		      Integer.parseInt(s); 
		   } catch(NumberFormatException e) { 
		      return false; 
		   }
		   // only got here if we didn't return false
		   return true;
		}

}
