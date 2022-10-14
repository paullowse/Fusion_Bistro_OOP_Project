package assignment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
/**
 * Contains all functionalities specialized to the Main Course Menu
 * @author Admin
 *
 */
public class MainCourseMenu extends GenericMenu {
	/**
	 * MainCourse Menu consists of multiple Main Course items stored in an ArrayList
	 */
	protected ArrayList<MainCourse> MenuList = new ArrayList<MainCourse>();
	
	/**
	 * Main Course Menu is dynamically initialized based on contents from the text file
	 */
	public MainCourseMenu(){
		ObjectCreator w = new ObjectCreator();
		MenuList = (ArrayList<MainCourse>) w.readArray(MenuList,"mainCourseMenu.txt");
	}
	
	/**
	 * Creates a new Main Course Item in the menu
	 * @param rpt
	 */
	public void create(SalesRevenueReport rpt) {
		MainCourse mc = new MainCourse();
		super.create(mc);
		
		System.out.println("*** What free condiment add-ons are available? ***");
		String condiments = sc.nextLine();
		System.out.println("");
		mc.setAddCondiments(condiments);
		MenuList.add(mc);
		
		rpt.addIndividualSalesToReport(mc);
		
		ObjectCreator w = new ObjectCreator();
		w.writeHashMap(rpt.salesRevenueReport,"salesRevenueReport.txt");
		
		
	}
	/**
	 * Displays all items in the main course menu
	 */
	public void display() {
		System.out.println(String.format("%" + 145 + "s", " ").replaceAll(" ", "-"));
		System.out.println(String.format("%-3s%-30s%-75s%-10s%-12s","No","Name", "Description", "Price", "Free Condiment Add-ons"));
		super.display(MenuList);
	}
	/**
	 * Removes an item from the main course menu
	 */
	public void remove() {
		super.remove(MenuList);
	}
	/**
	 * Updates (Name,Description, price, Condiment) an item in the main course menu
	 */
	public void update(SalesRevenueReport rpt) {
		
		display();
		System.out.println("");
		System.out.println("*** Select the item you wish to update ***");
		int option = Reader.readInt();
		System.out.println("");
		MainCourse tempItem = (MainCourse) super.getItem(option,MenuList);
		
		System.out.println("*** Select your choice ***");
		System.out.println("1. Name\n2. Description\n3. Price\n4. Condiment Add-Ons");
		int choice = Reader.readingInt(0, 4);
		System.out.println("");
		
		if(choice>=1 && choice<=3)
			super.update(choice, tempItem,rpt);
		else if(choice == 4) {
			System.out.println("*** List Free Condiment Add-Ons *** ");
			String addCondiments = sc.nextLine();
			System.out.println("");
			tempItem.setAddCondiments(addCondiments);
			
		}

	}
	/**
	 * Returns the main course item based on the index provided by the user
	 * 
	 * @return MainCourse
	 */
	public MainCourse getItem(int i) {
		return (MainCourse) super.getItem(i, MenuList);
		
	}
	/**
	 * Commit the changes made to the menu by saving it to the text file
	 */
	public void write() {
		ObjectCreator w = new ObjectCreator();
		w.writeArray(MenuList, "mainCourseMenu.txt");
	}

	
}
