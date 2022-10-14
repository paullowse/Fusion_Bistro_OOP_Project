package assignment;

import java.io.FileInputStream;
import java.nio.file.SecureDirectoryStream;
import java.util.ArrayList;

/**
 * Contains all functionalities specialized to the Desserts Menu
 * @author Admin
 *
 */

public class DessertsMenu extends GenericMenu {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Desserts Menu consists of multiple Desserts items stored in an ArrayList
	 */
	protected ArrayList<Desserts> MenuList = new ArrayList<Desserts>();
	/**
	 * Desserts Menu is dynamically initialized based on contents from the text file
	 */
	public DessertsMenu(){
		ObjectCreator w = new ObjectCreator();
		MenuList = (ArrayList<Desserts>) w.readArray(MenuList, "dessertsMenu.txt");
	}
	/**
	 * Creates a new Desserts Item in the menu
	 * @param rpt
	 */
	public void create(SalesRevenueReport rpt) {
		Desserts ds = new Desserts();
		super.create(ds);;
		
		System.out.println("*** List free topping add-ons ***");
		String toppings = sc.nextLine();
		System.out.println("");
		ds.setAddToppings(toppings);
		MenuList.add(ds);
		
		rpt.addIndividualSalesToReport(ds);
		
		ObjectCreator w = new ObjectCreator();
		w.writeHashMap(rpt.salesRevenueReport, "salesRevenueReport.txt");
	}
	/**
	 * Displays all items in the Desserts menu
	 */
	public void display() {
		System.out.println(String.format("%" + 145 + "s", " ").replaceAll(" ", "-"));
		System.out.println(String.format("%-3s%-30s%-75s%-10s%12s","No","Name", "Description", "Price","Free Toppings"));
		super.display(MenuList);
		
	}
	/**
	 * Removes an item from the Desserts menu
	 */
	public void remove() {
		super.remove(MenuList);
	}
	/**
	 * Updates (Name,Description, price, toppings) an item in the Desserts menu
	 */
	public void update(SalesRevenueReport rpt ) {
		
		display();
		System.out.println("*** Select the item you wish to update ***");
		int option = Reader.readInt();
		System.out.println("");
		Desserts tempItem = (Desserts) super.getItem(option,MenuList);
		
		System.out.println("*** Select your choice ***");
		System.out.println("1. Name\n2. Description\n3. Price\n4. Free Toppings");
		int choice = Reader.readingInt(0, 4);
		System.out.println("");
		
		if(choice>=1 && choice<=3)
			super.update(choice, tempItem,rpt);
		else if(choice == 4) {
			System.out.println("Add free toppings?");
			String addToppings = sc.nextLine();
			System.out.println("");
			tempItem.setAddToppings(addToppings);
			
		}
			
	}
	/**
	 * Returns the Desserts item based on the index provided by the user
	 * 
	 * @return Desserts
	 */
	public Desserts getItem(int i) {
		return (Desserts) super.getItem(i, MenuList);
		
	}
	/**
	 * Commit the changes made to the menu by saving it to the text file
	 */
	public void write() {
		ObjectCreator w = new ObjectCreator();
		w.writeArray(MenuList, "dessertsMenu.txt");
	}
	
}
