
package assignment;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * main application that is used to coordinate all the functionalities
 * @author Admin
 *
 */
public class RestaurantApp {

	public static void main(String[] args) {
		

		Scanner sc= new Scanner(System.in);

		//initialize 
		SalesRevenueReport rpt = new SalesRevenueReport();
		ArrayList<Order> OrderList=new ArrayList<>();
		ObjectCreator w = new ObjectCreator();
		Map<LocalDate,Reservation> reservations=new HashMap<LocalDate,Reservation>();
		reservations=(Map<LocalDate, Reservation>)w.readHashMap( reservations, "reservations.txt");


		LocalDateTime currentTime = LocalDateTime.now();
		String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
		String formatDateTime = currentTime.format(formatter);
		DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime current = LocalDateTime.parse(formatDateTime, FORMATTER);

		Customer newCustomer = null;
		Reservation todayReservation = null;

		int option = -1;

		Staff staffUser = new Staff();
		String staffName = staffUser.getName();

		System.out.println("\nPlease Enter Today's Date (in format yyyy-MM-dd):");    

		int valid = 1;
		LocalDate date = null;


		while(valid == 1) {
			try {
				String dateOfVist = sc.nextLine();

				DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				date = LocalDate.parse(dateOfVist,format);
				valid = 0;
			}catch(DateTimeParseException e) {
				System.out.println("\nPlease Enter A Valid date in the following format yyyy-MM-dd");
			}
		}


		if(!reservations.containsKey(date)) {
			todayReservation = new Reservation(date, current);
			reservations.put(date,todayReservation);
			
		}
		else {
			todayReservation = reservations.get(date);
		}
		MenuControl mc =new MenuControl();



		while (option != 6) {

			menu();
			System.out.println("\n*** Please enter an option ***");
			//option = Integer.parseInt(sc.nextLine());
			option = Reader.readingInt(0, 6);
			System.out.println("");


			if (option == 1) {
				mc.menuOptions(rpt);

			} else if (option == 2) {
				System.out.println(String.format("%" + 17 + "s", " ").replaceAll(" ", "-"));
				System.out.println("1. Create Order\n2. Update Order\n3. View Order\n4. Quit");
				System.out.println(String.format("%" + 17 + "s", " ").replaceAll(" ", "-"));
				System.out.println("\n*** Please enter an option ***");
				int orderOption;
				orderOption = Integer.parseInt(sc.nextLine());
				System.out.println("");


				switch(orderOption) {
				case 1:

					int table;

					newCustomer = new Customer();
					table = todayReservation.createReservationToday(newCustomer, current);
					reservations.put(date,todayReservation);
					
					System.out.println(""); 
					Order newOrder = new Order(newCustomer, table, staffName);
					OrderList.add(newOrder);

					System.out.println("--- Order has been added ---");

					System.out.println("customer name is: " + newOrder.getCustomer().getName()); 
					displayAllOrderID(OrderList);

					break;
				case 2:
					displayAllOrderID(OrderList);

					Order o = getOrderFromID(OrderList);
					if (o == null) {
						break;
					} else {
						o.displayOrder();
						o.updateOrder();
					}
					break;
				case 3:

					displayAllOrderID(OrderList);

					Order order = getOrderFromID(OrderList);
					if (order == null) {
						break;
					} else {
						order.displayOrder();
					}
					break;
				default:
					break;
				}


			} else if (option == 3) {
				// Create/check/remove reservation item


				System.out.println("Date and Time now: " + current);
				System.out.println("\n" + String.format("%" + 22 + "s", " ").replaceAll(" ", "-"));
				System.out.println("1.Create Reservation\n2.Remove Reservation\n3.Check Reservation\n4.Exit");
				System.out.println(String.format("%" + 22 + "s", " ").replaceAll(" ", "-"));
				System.out.println("\n*** Please enter an option ***");
				int sel = Reader.readingInt(0,4);
				System.out.println("");

				switch(sel){
				case 1: // create reservation
					System.out.println("\nPlease enter the date of reservation (in format yyyy-MM-dd)");

					int valid2 = 1;
					LocalDate createDate = null;
					while(valid2 == 1) {
						try {
							String dateOfVist = sc.nextLine();
							DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
							createDate = LocalDate.parse(dateOfVist,format);
							valid2 = 0;
						}catch(DateTimeParseException e) {
							System.out.println("Please Enter A Valid date in the following format yyyy-MM-dd");
						}
					}  
					if(createDate.isAfter(date)){
						Reservation createReservation=null;
						if(!reservations.containsKey(createDate)){
							createReservation=new Reservation(createDate, current);
							reservations.put(createDate,createReservation);
							System.out.println("Created new reservation on " + createDate + ".");
							System.out.println("");
						}
						else{
							createReservation=reservations.get(createDate);
						}
						Customer resCustomer=new Customer();
						createReservation.createReservation(resCustomer, current);
					}
					else{
						System.out.println();
						System.out.println("Error Message: Reservation can only be created in advance.");
					}
					break;

				case 2: // remove reservation

					System.out.println("\nPlease enter the date of reservation (in format yyyy-MM-dd)");

					int valid3 = 1;
					LocalDate remDate = null;
					while(valid3 == 1) {
						try {
							String dateOfVist = sc.nextLine();
							DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
							remDate = LocalDate.parse(dateOfVist,format);
							valid3 = 0;
						}catch(DateTimeParseException e) {
							System.out.println("Please Enter A Valid date in the following format yyyy-MM-dd");
						}
					}  
					Reservation removeReservation = new Reservation(remDate, current);
					if(!reservations.containsKey(remDate)) {
						System.out.println();
						System.out.println("--------------------------------");
						System.out.println("REMOVE RESERVATION: UNSUCCESSFUL");
						System.out.println("Error Message: No reservation on this date found.");
						System.out.println("--------------------------------");
					}
					else{
						removeReservation=reservations.get(remDate);
						removeReservation.showDayAssignedTables();
						System.out.println("Please enter customer name:");
						String name = sc.nextLine();
						System.out.println("Please enter customer contact no.:");
						long number = Long.parseLong(sc.nextLine());
						removeReservation.removeReservation(name, number, current);  //   remove reservation
					}
					break;

				case 3: // check reservation

					System.out.println("\nPlease enter the date of reservation (in format yyyy-MM-dd)");

					int valid4 = 1;
					LocalDate checkDate = null;
					while(valid4 == 1) {
						try {
							String dateOfVist = sc.nextLine();
							DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
							checkDate = LocalDate.parse(dateOfVist,format);
							valid4 = 0;
						}catch(DateTimeParseException e) {
							System.out.println("Please Enter A Valid date in the following format yyyy-MM-dd");
						}
					}  
					//Reservation checkReservation = new Reservation(checkDate, current);
					Reservation checkReservation  = null;
					if(!reservations.containsKey(checkDate)) {
						System.out.println();
						System.out.println("Error Message: No reservation on this date found.");
					}
					else{
						checkReservation = reservations.get(checkDate);
						Reservation test = null;
						
						
						System.out.println("");
						System.out.println("------ Check Reservation ------\n1. List all reservations \n2. List individual customer's reservation details \n3. Exit");
						System.out.println("");
						System.out.println("*** Please enter an option ***");
						
						int sel2 = Reader.readingInt(0,3);
						System.out.println("");
						
						switch (sel2) {
						case 1:
							checkReservation.hasReservation("", 2147483647, current);
							checkReservation.showDayAssignedTables();		
							break;
							//checkReservation
						case 2:
							System.out.println("Please enter customer name:");
							String name = sc.nextLine();
							System.out.println("");
							System.out.println("Please enter customer contact no.:");
							long number = Long.parseLong(sc.nextLine());
							checkReservation.checkReservation(name, number, current);    // print out customer reservation details
							break;  
						case 3:
							break;
						}
					
					}
					break;

				}

			} else if (option == 4) {
				//Print order invoice
				int day = date.getDayOfMonth();
				int month = date.getMonthValue();

				displayAllOrderID(OrderList);    
				Order o = getOrderFromID(OrderList);
			

				IndividualSales s = null;

				if (o!= null) {
					for(int i = 0; i<o.getNumOfItems();i++) {

						s = rpt.getValue(o.getItemsInOrder()[i].getMenuItem());

						if(s != null) {
							s.addItem(day, month, o.getItemsInOrder()[i]);
						}
						//Add to sales report
					}
					w.writeHashMap(rpt.salesRevenueReport,"salesRevenueReport.txt");          // RELOAD sales revenue report


					// remove reservation!!!
					String name = o.getCustomer().getName();
					long number = o.getCustomer().getNumber();

					todayReservation.removeReservation(name, number, current);  //   remove reservation

					OrderInvoice invoice = new OrderInvoice(o);

					invoice.printInvoice();

					// REMOVE order from list
					OrderList.remove(o);
					displayAllOrderID(OrderList);
				}





			}else if (option == 5) {

				int day;
				int month;

				System.out.println(String.format("%" + 20 + "s", " ").replaceAll(" ", "-"));
				System.out.println("1. Daily Report\n2. Monthly Report");
				System.out.println(String.format("%" + 20 + "s", " ").replaceAll(" ", "-") + "\n");
				System.out.println("*** Please enter an option ***");
				option = Integer.parseInt(sc.nextLine());
				System.out.println("");

				switch(option) {
				case 1:
					System.out.println("Please Enter the Day (dd):");
					day = Reader.readingInt(0,31);
					System.out.println("");
					
					System.out.println("Please Enter the Month (mm):");
					month = Reader.readingInt(0,12);
					System.out.println("");
					
					rpt.dailyReport(day, month);
					break;
				
				case 2:
					System.out.println("Please Enter the Month (mm)");
					month = Reader.readingInt(0,12);
					
					rpt.monthlyReport(month);
					break;
				}

			}else if (option == 6) {
				w.writeHashMap(reservations, "reservations.txt");
				System.out.println("Thank you for using the application");
			}
			else {
				System.out.println("Invalid option!");
			}


		}

	}

	private static void menu() {

		System.out.println("\n" + String.format("%" + 42 + "s", " ").replaceAll(" ", "-"));
		System.out.println(String.format("%" + 8 + "s", " ") + "WELCOME TO FUSION BISTRO");
		System.out.println(String.format("%" + 42 + "s", " ").replaceAll(" ", "-"));
		System.out.println(
				"1. Create/Update/Remove/View menu \n" + 
						"2. Create/Update/View order \n" + 
						"3. Create/check/remove reservation item\n" + 
						"4. Print order invoice\n" + 
						"5. Print sale revenue report\n" + 
				"6. Quit" );
		System.out.println(String.format("%" + 42 + "s", " ").replaceAll(" ", "-"));
	}

	private static void displayAllOrderID(ArrayList<Order> OrderList) {			// prints out all order IDs
		System.out.println("\n***** Order IDs in system: *****");
		System.out.println("Order ID:     TableID:     Customer Name:");
		for (int i = 0; i < OrderList.size(); i++) {
		    System.out.println(String.format("%-14s",OrderList.get(i).getOrderID())  + String.format("%-13s",OrderList.get(i).getTableID()) + String.format("%-20s", OrderList.get(i).getCustomer().getName()));
		}
		System.out.println();
	}


	private static Order getOrderFromID(ArrayList<Order> OrderList) { // returns an order of choice depending on ID
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the ID of the order you want to view (Enter 0 to quit)");
		int viewOption = Integer.parseInt(sc.nextLine());
		if (viewOption == 0) {
			return null;
		}
		for (int i = 0; i < OrderList.size(); i++) {
			if (OrderList.get(i).getOrderID() == viewOption) {
				return OrderList.get(i);

			}
		}
		System.out.println("");
		System.out.println("Error: an Order ID that does not exist was entered\n");
		return null;

		// NEED A CATCH to avoid the null
	}

}