package assignment;

import java.util.ArrayList;
/**
 * Contains all functionalities specialized to the Promotional Menu
 * @author Admin
 *
 */
public class PromotionalMenu extends GenericMenu {
	/**
	 * Promotional Menu consists of multiple promotional set packages stored in an ArrayList
	 */
	protected ArrayList<PromotionalSetPackage> MenuList = new ArrayList<PromotionalSetPackage>();
	//private SalesRevenueReport rpt = new SalesRevenueReport();
	MenuControl overview = new MenuControl();
	Menu m;
	/**
	 * Promotional Menu is dynamically initialized based on contents from the text file
	 */
	public PromotionalMenu(){
		ObjectCreator w = new ObjectCreator();
		MenuList = (ArrayList<PromotionalSetPackage>) w.readArray(MenuList,"promotionalMenu.txt");
	}

	/**
	 * Creates a new Promotional Item in the menu
	 * @param rpt
	 */
	public void create(SalesRevenueReport rpt) {
		int option;
		PromotionalSetPackage set = new PromotionalSetPackage();
		
		System.out.println("*** Enter the name of the set ***");
		String name = sc.nextLine();
		System.out.println("");
		
		m = new MainCourseMenu();
		m.display();
		
		System.out.println("*** Select the item you wish to include ***");
		option = Reader.readInt();
		System.out.println("");
		set.setMainCourse((MainCourse) m.getItem(option));
		
		m = new DrinksMenu();
		m.display();
		
		System.out.println("*** Select the item you wish to include ***");
		option = Reader.readInt();
		System.out.println("");
		set.setDrinks((Drinks) m.getItem(option));
		
		m = new DessertsMenu();
		m.display();
		
		System.out.println("*** Select the item you wish to include ***");
		option = Reader.readInt();
		System.out.println("");
		set.setDesserts((Desserts) m.getItem(option));
		
		//Set details
		set.setName(name);
		set.setDescription(set.getMainCourse().getName() + ", " + set.getDrinks().getName()  + ", " + set.getDesserts().getName());
		set.setPrice((set.getMainCourse().getPrice() + set.getDrinks().getPrice() + set.getDesserts().getPrice())*0.80);
		
		MenuList.add(set);
		rpt.addIndividualSalesToReport(set);
		
		ObjectCreator w = new ObjectCreator();
		w.writeHashMap(rpt.salesRevenueReport,"salesRevenueReport.txt");
	}

	/**
	 * Updates (Name, Description, price, Items included in the set) an item in the Promotional menu
	 */
	public void update(SalesRevenueReport rpt) {
		display();
		System.out.println("*** Select the item you wish to update ***");
		int option = Reader.readInt();
		System.out.println("");
		
		PromotionalSetPackage set = (PromotionalSetPackage) getItem(option);
		
		System.out.println("*** Select your choice ***");
		System.out.println("1. Name\n2. MainCourse\n3. Drinks\n4. Dessert\n5. Price\n6. Quit");
		int choice = Reader.readingInt(0, 6);
		System.out.println("");
		
		
		if(choice>=1 && choice<=5) {
			switch(choice) {
			case 1:
				//1 denotes name
				super.update(1, set,rpt);
				break;
			case 2:
				//Main course
				m = new MainCourseMenu();
				m.display();
				
				System.out.println("*** Select the item you wish to include ***");
				//option = Integer.parseInt(sc.nextLine());
				option = Reader.readInt();
				System.out.println("");
				set.setMainCourse((MainCourse) m.getItem(option));
				break;
			case 3:
				//Drinks
				m = new DrinksMenu();
				m.display();
				
				System.out.println("*** Select the item you wish to include ***");
				//option = Integer.parseInt(sc.nextLine());
				option = Reader.readInt();
				System.out.println("");
				set.setDrinks((Drinks) m.getItem(option));
				
				break;
			case 4:
				//Dessert
				m = new DessertsMenu();
				m.display();
				
				System.out.println("*** Select the item you wish to include ***");
				//option = Integer.parseInt(sc.nextLine());
				option = Reader.readInt();
				System.out.println("");
				set.setDesserts((Desserts) m.getItem(option));
				break;
			case 5:
				//3 denotes price
				super.update(3, set,rpt);
				break;

			}
		}
		else if (choice == 6) {
			//Quit
		}
		else {
			System.out.println("*** Invalid Option ***");
		}
		
		//Price is updated as long as it's not option 5 
		if(choice != 5)
			set.setPrice((set.getMainCourse().getPrice() + set.getDrinks().getPrice() + set.getDesserts().getPrice())*0.8);
		
		//Description is always updated by default to account for the change in item
		set.setDescription(set.getMainCourse().getName() + ", " + set.getDrinks().getName()  + ", " + set.getDesserts().getName());
		
		
		
	}
	/**
	 * Removes an item from the Promotional menu
	 */
	public void remove() {
		display();
		System.out.println("");
		System.out.println("*** Select the item you wish to remove ***");
		int option = Reader.readInt();
		System.out.println("");
		
		if(option >= 1 && option<= MenuList.size()) {
			MenuList.remove(option-1);
	
		}else {
			System.out.println("*** Invalid option ***");

		}

	}
	/**
	 * Displays all items in the Promotional menu
	 */
	public void display() {
		int i = 0;
		System.out.println(String.format("%" + 150 + "s", " ").replaceAll(" ", "-"));
		for(PromotionalSetPackage set: MenuList) {
			System.out.println("Set number : " + ++i);
			System.out.println("Name of set: " + set.getName());
			System.out.println("Set includes: " + set.getDescription());
			System.out.println(String.format("Price of promo set: %-10.2f", set.getPrice()));
			System.out.println();
		}
		System.out.println(String.format("%" + 150 + "s", " ").replaceAll(" ", "-"));
		
	}
	/**
	 * Returns the Promotional package set based on the index provided by the user
	 * 
	 * @Override
	 * @return MenuItem 
	 */
	public MenuItem getItem(int i) {
		return (PromotionalSetPackage) super.getItem(i, MenuList);
	}
	
	
	/**
	 * Commit the changes made to the menu by saving it to the text file
	 */
	public void write() {
		ObjectCreator w = new ObjectCreator();
		w.writeArray(MenuList, "promotionalMenu.txt");
	}
	
	

}
