package assignment;

import java.util.ArrayList;
/**
 * Contains all functionalities specialized to the Drinks Menu
 * @author Admin
 *
 */
public class DrinksMenu extends GenericMenu {
	
	private static final long serialVersionUID = 1L;
	protected ArrayList<Drinks> MenuList = new ArrayList<Drinks>();
	/**
	 * Drinks Menu is dynamically initialized based on contents from the text file
	 */
	DrinksMenu(){
		ObjectCreator w = new ObjectCreator();
		MenuList = (ArrayList<Drinks>) w.readArray(MenuList, "drinksMenu.txt");
	}
	/**
	 * Creates a new Drinks Item in the menu
	 * @param rpt
	 */
	public void create(SalesRevenueReport rpt) {
		Drinks ds = new Drinks();
		System.out.println("*** Please enter name of drink ***");
		String name = sc.nextLine();
		System.out.println("");
		
		System.out.println("*** Please enter description of drink ***");
		String description = sc.nextLine();
		System.out.println("");
		
		System.out.println("*** Please enter price of drink ***");
		double price = Reader.readingDouble();
		System.out.println("");
		
		ds.setDescription(description);
		ds.setName(name);
		ds.setPrice(price);	
	
		System.out.println("*** Hot or Cold? ***");
		String toppings = sc.nextLine();
		System.out.println("");
		ds.setHotOrCold(toppings);
		MenuList.add(ds);
		
		rpt.addIndividualSalesToReport(ds);
		
		ObjectCreator w = new ObjectCreator();
		w.writeHashMap(rpt.salesRevenueReport, "salesRevenueReport.txt");
	}
	/**
	 * Displays all items in the Drinks menu
	 */
	public void display() {
		System.out.println(String.format("%" + 145 + "s", " ").replaceAll(" ", "-"));
		System.out.println(String.format("%-3s%-30s%-75s%-10s%-12s","No","Name", "Description", "Price","Hot/Cold"));
		super.display(MenuList);
		
	}
	/**
	 * Removes an item from the Drinks menu
	 */
	public void remove() {
		super.remove(MenuList);
	}
	/**
	 * Updates (Name,Description, price, Hot/Cold) an item in the Drinks menu
	 */
	public void update(SalesRevenueReport rpt) {
		
		display();
		System.out.println("*** Select the item you wish to update ***");
		int option = Reader.readInt();
		System.out.println("");
		Drinks tempItem = (Drinks) super.getItem(option,MenuList);
		
		System.out.println("*** Select your choice ***");
		System.out.println("1. Name\n2. Description\n3. Price\n4. Hot or Cold?");
		int choice = Reader.readingInt(0, 4);
		System.out.println("");
		
		if(choice>=1 && choice<=3)
			super.update(choice, tempItem,rpt);
		else if(choice == 4) {
			System.out.println("Hot or Cold?");
			String hotOrCold = sc.nextLine();
			System.out.println("");
			tempItem.setHotOrCold(hotOrCold);
			
		}
		
	}
	/**
	 * Returns the Drinks item based on the index provided by the user
	 * 
	 * @return Drinks 
	 */
	public Drinks getItem(int i) {
		return (Drinks) super.getItem(i, MenuList);
		
	}
	/**
	 * Commit the changes made to the menu by saving it to the text file
	 */
	public void write() {
		ObjectCreator w = new ObjectCreator();
		w.writeArray(MenuList, "drinksMenu.txt");
	}
	
}


