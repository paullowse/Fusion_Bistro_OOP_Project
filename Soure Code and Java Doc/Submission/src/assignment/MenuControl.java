package assignment;

import java.util.Scanner;
/**
 * Serves as a common class for the user to choose the appropriate menu (Main Course, Drinks, Etc)
 * and the actions they want to perform on the menu (Update, Create, Remove)
 */
public class MenuControl {
	//MenuApp
	/**
	 * Obtain the Menu of interest (MainCourse,etc) and allows the user to perform 
	 * the desired actions on the Menu
	 */
	public void menuOptions(SalesRevenueReport rpt) {
		
		Menu menu = selectMenu();
		
		if(menu != null) {
			Scanner sc= new Scanner(System.in);
			System.out.println("----Menu Options----");
			System.out.println(
					"1. Create \n" + 
					"2. Update \n" + 
					"3. Remove \n" + 
					"4. Display\n" + 
					"5. Quit" );
			System.out.println(String.format("%" + 20 + "s", " ").replaceAll(" ", "-") + "\n");
			System.out.println("*** Please enter an option ***");
			
			//int option = Integer.parseInt(sc.nextLine());
			int option = Reader.readingInt(0, 5);
			System.out.println("");
			
			switch(option) {
			case 1:
				menu.create(rpt);
				break;
			case 2:
				menu.update(rpt);
				break;
			case 3:
				menu.remove();
				break;
			case 4:
				menu.display();
				break;
			case 5:
				//Exit
				break;
				
			}
			
			menu.write();
		}else {
			//Exit condition
		}
		
		
		
	}
	
	
	public Menu selectMenu() {
		Scanner sc= new Scanner(System.in);
		Menu menu = null;
		
		System.out.println("------Menu List------");
		System.out.println(
				"1. Main Course menu \n" + 
				"2. Drinks Menu \n" + 
				"3. Dessert Menu\n" + 
				"4. Promotional Menu\n" + 
				"5. Quit" );
		System.out.println(String.format("%" + 22 + "s", " ").replaceAll(" ", "-") + "\n");
		System.out.println("*** Please enter an option ***");
		
		//int option = Integer.parseInt(sc.nextLine());
		int option = Reader.readingInt(0, 5);
		System.out.println("");
		
		switch(option) {
		case 1:
			menu = new MainCourseMenu();
			break;
			
		case 2:
			menu = new DrinksMenu();
			break;
		
		case 3:
			menu = new DessertsMenu();
			break;
			
		case 4:
			menu = new PromotionalMenu();
			//MUst remove below
			//menu.create();
			break;
		case 5:
			
			break;
		}
		
		//Never ever close the scanner 		
		return menu;
	}
	
}
