package assignment;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Serves as the parent class of all the menus. Consist the implementations 
 * of the common functions like displaying, creating and removing items from the menu.
 * @author Admin
 *
 */
public abstract class GenericMenu implements Menu{

	private static final long serialVersionUID = -8423604789389499703L;
	Scanner sc= new Scanner(System.in); 
	/**
	 * Takes in a menu item created based on the default constructor (which consists of null values) and prompts
	 * the user for their input to update each of these fields 
	 * @param item
	 */
	public void create(MenuItem item) {
		System.out.println("*** Please enter name of dish ***");
		String name = sc.nextLine();
		System.out.println("");
		
		System.out.println("*** Please enter description of dish ***");
		String description = sc.nextLine();
		System.out.println("");
		
		System.out.println("*** Please enter price of dish ***");
		double price = Reader.readingDouble();
		System.out.println("");
		
		
		item.setDescription(description);
		item.setName(name);
		item.setPrice(price);	
	

	}
	/**
	 * The generic update function used by all specialized menu to update their common fields (Name, Description and price)
	 */
	public void update(int option ,MenuItem tempItem,SalesRevenueReport rpt ) {
	
		
		switch(option) {
		case 1:
			System.out.println("Enter the new name");
			String newName = sc.nextLine();
			System.out.println("");
			rpt.updateName(tempItem, newName);
			tempItem.setName(newName);
			break;
			
		case 2:
			System.out.println("Enter the new description");
			String newDescription = sc.nextLine();
			System.out.println("");
			tempItem.setDescription(newDescription);
			break;
		
		case 3:
			System.out.println("Enter the new price");
			double price = Reader.readingDouble();
			System.out.println("");
			tempItem.setPrice(price);
			break;
		}
		
	}
	/**
	 * The generic remove function used by all specialized menu to remove an item
	 * @param MenuList
	 */
	public void remove(ArrayList<? extends MenuItem> MenuList) {
		display(MenuList);
		
		System.out.println("*** Please enter an option ***");
		int option = Reader.readingInt(0,MenuList.size() );
		System.out.println("");
		
		MenuItem m = getItem(option, MenuList);
		MenuList.remove(m);
		

	}
	
	/**
	 * Generic display function used to display the common fields (Name, Description and price)
	 * @param MenuList
	 */
	public void display(ArrayList<? extends MenuItem> MenuList) {
		int j = 1;
		System.out.println(String.format("%" + 145 + "s", " ").replaceAll(" ", "-"));
		for(MenuItem i: MenuList) {
			System.out.print(j++ + "  " );
			i.display();
		}
		System.out.println(String.format("%" + 145 + "s", " ").replaceAll(" ", "-"));
	}
	
	/**
	 * Generic function that all specialized menu class relies to return the Menu item object based on the index provided by the user
	 * @param i
	 * @param MenuList
	 * @return
	 */
	public MenuItem getItem(int i, ArrayList<? extends MenuItem> MenuList) {
		int upperLimit = MenuList.size();
		
		while(i<=0 || i> upperLimit) {
			System.out.println("Menu Item out of range please enter a valid index");
			i = Reader.readInt();
		}
		
		return (MenuItem) MenuList.get(i-1);
	}
	


}
