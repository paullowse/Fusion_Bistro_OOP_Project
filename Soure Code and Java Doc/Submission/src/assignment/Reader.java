package assignment;

import java.util.Scanner;
/**
 * Used to read input from the user with exception handling
 * @author Admin
 *
 */
public class Reader {
	
	public static int readingInt(int lowerLimit, int upperLimit) {
		int option = -1;
		Scanner sc= new Scanner(System.in);

		//System.out.println("*** Please enter an integer ***");
	    boolean done = false;
	    while (!done) {
	      try {
	    	option = Integer.parseInt(sc.nextLine());
	    	
	        while (option > upperLimit || option <= lowerLimit) {
				System.out.println("*** Please enter a valid option with " + (lowerLimit+1) + " to "  + upperLimit + " ***");
	        	option = Integer.parseInt(sc.nextLine());
	          }
	        
	    	done = true;
	      } catch (NumberFormatException e) {
	        System.out.println("*** Please enter a valid number ***");
	      }
	    }
	    return option;
	}
	
	public static double readingDouble(){
		Scanner sc= new Scanner(System.in);
	    boolean valid = false;
	    double price = -1;
	    while (!valid) {
	      try {
	    	price = Double.parseDouble(sc.nextLine());
	        valid = true;
	      } catch (NumberFormatException e) {
	        System.out.println("*** Please enter a double ***");
	      }
	    }
	    return price;
	}
	
	public static long readingLong(){
		Scanner sc= new Scanner(System.in);
	    boolean valid = false;
	    long price = -1;
	    while (!valid) {
	      try {
	    	price = Long.parseLong(sc.nextLine());
	        valid = true;
	      } catch (NumberFormatException e) {
	        System.out.println("*** Please enter a number ***");
	      }
	    }
	    return price;
	}
	
	public static int readInt() {
		int option = -1;
		Scanner sc= new Scanner(System.in);

		//System.out.println("*** Please enter an integer ***");
	    boolean done = false;
	    while (!done) {
	      try {
	    	option = Integer.parseInt(sc.nextLine());
	    	done = true;
	      } catch (NumberFormatException e) {
	        System.out.println("*** Please enter a valid number ***");
	      }
	    }
	    return option;
	}
}
