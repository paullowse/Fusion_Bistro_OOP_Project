package assignment;

import java.util.Scanner;
/**
 * Stores all the order details pertain in a single customer
 * @author Admin
 *
 */
public class Order {

	
	/**
	 * Count is used to generate a new unique orderID due to static
	 */
	private static int count = 1;
	
	/**
	 * Saves name of staff that took the order.
	 */
	private String staffName;

	/**
	 * Limit is used to set the max size of the ItemOrder Array
	 */
	private int limit = 50;
	/**
	 * orderID is the unique ID of the order.
	 * Used to select the particular order from OrderList
	 */
	private int orderID;
	/**
	 * tableID stores the ID of the table where the order originates from.
	 */
	private int tableID;
	
	/**
	 * Stores a menu object
	 */
	private Menu menu;
	/**
	 * Records number of itemOrders in the Order
	 */
	private int numofItems = 0;
	/**
	 * Saves details of customer tagged to order
	 */
	private Customer customer;
	/**
	 * Array of ItemOrder Objects
	 */
	private ItemOrder[] itemsInOrder = new ItemOrder[limit];				 
	Scanner sc= new Scanner(System.in);
	
	/**
	 * Instantiates the order object
	 * Adds the first item to the order before prompting for other updates
	 * @param customer The order's customer details
	 * @param tableID The order's tableID 
	 */ 
	public Order(Customer customer, int tableID, String staffName) {
		this.staffName = staffName;
		this.tableID = tableID;
		this.orderID = count;
		this.customer = customer;
		count++;
		
		
		System.out.println("----- Creating your order now -----");
		System.out.println("*** Add the first item to Order ***");
		MenuControl mControl = new MenuControl();
		menu = mControl.selectMenu();		
		
		if (menu == null) {
			return;
		}
		
		addOrderItem();
		updateOrder();
		System.out.println();
		
		
	}
	
	
	/**
	 * get staffName
	 * @return staffName as string
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * get the ItemsinOrder array
	 * @return the ItemsInOrder array
	 */
	public ItemOrder[] getItemsInOrder() {
		return itemsInOrder;
	}
	
	/**
	 * get Customer details
	 * @return Customer Object
	 */
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * get number of itemOrders in array
	 * @return number of itemOrders in array
	 */
	public int getNumOfItems() {
		return numofItems;
	}
	/**
	 * get orderID
	 * @return ID of order
	 */
	public int getOrderID() {
		return orderID;
	}
	
	/**
	 * get tableID
	 * @return tableID
	 */
	public int getTableID() {
		return tableID;
	}
	
	/**
	 * Controls what functions to run next for the order
	 * Calls the addOrderItem, removeOrderItem and changeQuantity methods
	 */
	public void updateOrder() {
		MenuControl mControl = new MenuControl();
		
		
		System.out.println("--- Order Modification Options ---");
		System.out.println("1. Add Item to Order\n2. Remove Item from Order\n3. Change Quantity of Order Item\n4. Exit");
		System.out.println(String.format("%" + 33 + "s", " ").replaceAll(" ", "-"));
		System.out.println("");
		System.out.println("*** Select an option ***");
		
		int option = Integer.parseInt(sc.nextLine());
		System.out.println("");
		while(option!= 4) {							
			switch(option) {
			case(1):
				if (numofItems == limit) {
					System.out.println("Sorry, the limit of " + limit + " items in an order has been reached");	
					break;
				}
				menu = mControl.selectMenu();	
				addOrderItem();
				break;
			case(2):
				removeOrderItem();
				break;
			case(3):
				changeQuantity();
				break;
			default:
				System.out.println("Please choose 1, 2 or 3\n");
				break;
			}
			
			
			System.out.println(String.format("%" + 30 + "s", " ").replaceAll(" ", "-"));
			System.out.println("1. Add Item to Order\n2. Remove Item from Order\n3. Change Quantity of Order Item\n4. Exit");
			System.out.println("");
			System.out.println("*** Select an option ***");
			option = Integer.parseInt(sc.nextLine());
			System.out.println("");
		}
	}
	
	/**
	 * Displays all the menuitems in Menu, gets the menuItem desired from user to create an itemorder 
	 * If menuitem already chosen in order, the quantity will be added to the current item order. 
	 */
	public void addOrderItem() {
		menu.display();
		
		System.out.println("");
		System.out.print("Select an item to order: ");
		int itemchoice = Integer.parseInt(sc.nextLine());
		System.out.println("");
		
		MenuItem newMenuItem;
		
		try{
			newMenuItem=(MenuItem) menu.getItem(itemchoice); 
		if (newMenuItem==null)
			throw new NullPointerException();
		} catch (NullPointerException e) {
			System.out.println("Wrong entry!");
			return;
		}
		
		System.out.print("Quantity: ");
		int quantity = Integer.parseInt(sc.nextLine());
		System.out.println("");
		
		
		boolean addcheck = false;
		for (int i = 0; i<numofItems; i++) {
			if (itemsInOrder[i].getName().equalsIgnoreCase(newMenuItem.getName())){		
				int newQuantity = itemsInOrder[i].getQuantity() + quantity;
				itemsInOrder[i].setQuantity(newQuantity);
				addcheck = true;
			}
		}
		if (!addcheck) {
			itemsInOrder[numofItems] = new ItemOrder(newMenuItem, quantity);
			numofItems++;
		}
		
		System.out.println(String.format("%" + 60 + "s", " ").replaceAll(" ", "-"));
		System.out.println(String.format("%" + 25 + "s", " ") + "Order List");
		System.out.println(String.format("%" + 60 + "s", " ").replaceAll(" ", "-"));
		displayOrder();
		System.out.println("");
	
		
	}
	
	
	/**
	 * Displays order details and Removes the selected ItemOrder from the list
	 */
	public void removeOrderItem() {
		displayOrder();
		System.out.println("Enter item number to be removed:");
		int removeNum = Integer.parseInt(sc.nextLine());
		System.out.println("");
		
		// have check for the number inputted
		if (removeNum == numofItems) {			// end of list
			itemsInOrder[removeNum-1] = null;
			numofItems--;
		} else {													// if not end of list, shift down everything by 1 
			itemsInOrder[removeNum-1] = null;
			for (int i = removeNum; i<numofItems; i++) {
				itemsInOrder[i-1] = itemsInOrder[i];   	
			}
			numofItems--;
			 
		}
		displayOrder();
		
		
	}
	
	/**
	 * Prints out the name, price, quantity and total price of Item Orders in Order
	 */
	public void displayOrder()  {				// THIS METHOD sets limits to quantity and price range to 5 digits
		//55
		
		System.out.println("*********************** Table ID: " + tableID + " ***********************"); 
		System.out.println("*********************** Order ID: " + orderID + " ***********************"); 
		System.out.println("Staff Name: " + staffName);
		System.out.println("");
		System.out.println(String.format("%-4s%-30s%-9s%-8s%-10s","No.","Name", "Price","Qty", "Total"));	
		System.out.println(String.format("%" + 60 + "s", " ").replaceAll(" ", "-"));
		
		for (int i = 0; i < numofItems; i++) {
			String name = itemsInOrder[i].getName();
			double price = itemsInOrder[i].getPrice();
			int quantity = itemsInOrder[i].getQuantity();
			double total = itemsInOrder[i].getTotal();
			System.out.println(String.format("%-4s%-30s",i+1,name)+"$"+String.format("%-7.2f",price)+"X " + String.format("%-5d",quantity)+ "= $" + String.format("%-20.2f",total));
			
		}
		System.out.println();
	}
	
	/**
	 * Changes quantity of selected ItemOrder
	 */
	public void changeQuantity() {
		displayOrder();

		System.out.println("Enter the item number for changing order quantity (press 0 to quit): ");
		int changeIndex = Integer.parseInt(sc.nextLine()) - 1;
		
		while (changeIndex < -1 || changeIndex > numofItems-1) {
			System.out.println("Wrong entry: Please enter a number from 0 to " + numofItems);
			changeIndex = Integer.parseInt(sc.nextLine()) - 1;
		}
		if (changeIndex == -1) {			
			displayOrder();
			return;
		} else {
			System.out.println(itemsInOrder[changeIndex].getName());
			System.out.println("Current order quantity: " + itemsInOrder[changeIndex].getQuantity());
			System.out.println("");
			System.out.println("Change order quantity to? ");
			int changeQty = Reader.readInt();
			System.out.println("");
			itemsInOrder[changeIndex].setQuantity(changeQty);
			
			displayOrder();
		}
		
	}

	
	
	/**
	 * Prints the required information for the order invoice
	 */
	public void finaldisplay()  {				
		
		for (int i = 0; i < numofItems; i++) {
			String name = itemsInOrder[i].getName();
			int quantity = itemsInOrder[i].getQuantity();
			double total = itemsInOrder[i].getTotal();
			total = Math.round(total * 100.0) / 100.0;
			System.out.printf(String.format("%-5s%-39s%10s",quantity,name,total));
			System.out.println();
			
		}
		System.out.println();
		
	
	}

	
}




