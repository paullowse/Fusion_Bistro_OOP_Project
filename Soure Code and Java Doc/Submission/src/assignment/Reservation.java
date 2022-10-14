package assignment;

import java.io.Serializable;
import java.util.*;
import java.time.*;
/**
 * Tracks the reservation for an entire day (Each reservation object corresponds to one day)
 * @author Admin
 *
 */
public class Reservation implements Serializable{
	private static final long serialVersionUID = -7538821397513595962L;
	private LocalDate date;
	private LocalDateTime currentTime;
	private String timeSlot[]={"10.00-12.00","12.00-14.00","14.00-16.00","16.00-18.00"
			,"18.00-20.00"};
	private ArrayList<Table> Tables=new ArrayList<Table>();
	/**
	 * 
	 * @param date
	 * initializes tables to false, and adds them in a table array
	 */
	public Reservation(LocalDate date, LocalDateTime currentTime){
		this.date = date;
		this.currentTime = currentTime;
		for(int i=1; i<=5; i++)
		Tables.add(new Table(i,2*i));
	}
	/**
	 * 
	 * @param time
	 * Prints unassigned tables depending upon the time slot
	 */
	public void showUnassignedTables(int time){
		System.out.println();
		System.out.println("###### LIST OF EMPTY TABLES (BY TIME) ######");
		System.out.println("Date: " + date + "\t" + "Time Slot: " + timeSlot[time-1]);
		System.out.println(String.format("%" + 50 + "s", " ").replaceAll(" ", "-"));
		System.out.println("Table ID\t" + "Pax");
		System.out.println(String.format("%" + 50 + "s", " ").replaceAll(" ", "-"));
			for (Table Table: Tables){
				if(!Table.isAssigned(time))
					System.out.println(Table.getID() + "\t\t" + Table.getPax());
				
		}
			System.out.println(String.format("%" + 50 + "s", " ").replaceAll(" ", "-"));
			System.out.println("");
	}
	/**
	 * 
	 * @param time
	 * Prints assigned tables depending upon the time slot
	 */
	public void showAssignedTables(int time){
		System.out.println();
		System.out.println("###### LIST OF ASSIGNED TABLES (BY TIME) ######");
		System.out.println("Date: " + date + "\t" + "Time Slot: " + timeSlot[time-1]);
		System.out.println(String.format("%" + 50 + "s", " ").replaceAll(" ", "-"));
		System.out.println("Table ID\t" + "Customer Name\t" + "Contact No.");
			for (Table Table: Tables){
				if(Table.isAssigned(time))
					System.out.println(Table.getID() + "\t\t" + Table.getCustomer(time).getName() + "\t\t" + Table.getCustomer(time).getNumber());
				
		}
			System.out.println(String.format("%" + 50 + "s", " ").replaceAll(" ", "-"));
	}
	/**
	 * Shows ALL unassigned tables for a day, for all time slots
	 */
	public void showDayUnassignedTables(){
		System.out.println();
		System.out.println("###### LIST OF EMPTY TABLES (BY DAY) ######");
		System.out.println("Date: " + date);
		System.out.println(String.format("%" + 50 + "s", " ").replaceAll(" ", "-"));
				for(int i=1; i<=5; i++){
					System.out.println("Time Slot:" + timeSlot[i-1]);
					System.out.println("Table ID\t" + "Pax");
					for (Table Table: Tables){
						if(!Table.isAssigned(i))
							System.out.println(Table.getID() + "\t\t" + Table.getPax());
						}
					System.out.println(String.format("%" + 50 + "s", " ").replaceAll(" ", "-"));
				}
	}
	/**
	 * Shows ALL assigned tables for a day, for all time slots
	 */
	public void showDayAssignedTables(){
		System.out.println();
		System.out.println("###### LIST OF ASSIGNED TABLES (BY DAY) ######");
		System.out.println("Date: " + date);
		System.out.println(String.format("%" + 50 + "s", " ").replaceAll(" ", "-"));
				for(int i=1; i<=5; i++){
					System.out.println("Time Slot: " + timeSlot[i-1]);
					System.out.println("Table ID\t" + "Customer Name\t" + "Contact No.");
					for (Table Table: Tables){
						if(Table.isAssigned(i))
							System.out.println(Table.getID() + "\t\t" + Table.getCustomer(i).getName() + "\t\t" + Table.getCustomer(i).getNumber());
						}
					System.out.println("");
					System.out.println(String.format("%" + 50 + "s", " ").replaceAll(" ", "-"));
				}
	}
	/**
	 * assigns tables for one time slot and for one customer
	 * @param pax
	 * @param person
	 * @param time
	 * @return returns tableId if assigned successfully or else returns 0
	 */
	public int assignTable(int pax, Customer person, int time){
		int noOfSeatReq=pax;
		if(pax%2!=0)
			noOfSeatReq++;
		for(int i=noOfSeatReq/2; i<=5; i++){
			if(i!=0 && !Tables.get(i-1).isAssigned(time))
			{
				Tables.get(i-1).assign(person,time);
				System.out.println();
				System.out.println("###### CUSTOMER RESERVATION DETAILS ######");
				System.out.println("Date: " + date);
				System.out.println("Customer name: " + person.getName());
				System.out.println("Contact No.: " + person.getNumber());
				System.out.println("Time Slot: " + timeSlot[time-1]);
				System.out.println("Table ID: " + Tables.get(i-1).getID());
				System.out.println("Pax: " + Tables.get(i-1).getPax());
				
				return Tables.get(i-1).getID();
			}
		}
		System.out.println();
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("CREATE RESERVATION: UNSUCCESSFUL");
		System.out.println("Error Message: All tables are full or they do not meet your seating capacity.");
		System.out.println("-------------------------------------------------------------------------------");
		return 0;
	}
	/**
	 * Unassigns table by time slot and tableId
	 * @param TableID
	 * @param time
	 */
	public void unassignTable(int TableID, int time){
		Tables.get(TableID-1).unassign(time);
	}
	
	/**
	 * getter
	 * @return Tables: an array of tables
	 */
	public ArrayList<Table> getTables() {
		return Tables;
	}
	/**
	 * display time slot available to book
	 */
	public void showTimeSlot(){
		for(int i=1; i<=5; i++){
			System.out.println(i+". "+timeSlot[i-1]+"hrs");
		}
	}
	/**
	 * Creates order
	 * @param person
	 */
	public void createReservation(Customer person, LocalDateTime currentTime){
		Scanner Sc=new Scanner(System.in);
		if(!hasReservation(person.getName(), person.getNumber(), currentTime)){
			showDayUnassignedTables();
			System.out.println();
			System.out.println("--- List of Time Slots ---");
			System.out.println("(1) 10.00-12.00\n(2) 12.00-14.00\n(3) 14.00-16.00\n(4) 16.00-18.00 \n(5) 18.00-20.00\n");
			System.out.println("Please choose a timeslot:");
			int time = Sc.nextInt();
			while (time < 1 || time > 5) {
				System.out.println();
				System.out.println("Please choose a timeslot within 1-5:");
				time = Sc.nextInt();
			}
			showUnassignedTables(time);
			System.out.println("Please enter number of people at the table:");
			int pax=Sc.nextInt();
			while (pax < 1 || pax > 10){
				System.out.println();
				System.out.println("Please enter number of people at the table within 1-10:");
				pax=Sc.nextInt();
			}
			System.out.println();
			System.out.println("--------------------------------");
			System.out.println("CREATE RESERVATION: SUCCESSFUL");
			System.out.println("--------------------------------");
			assignTable(pax,person,time);
			showDayAssignedTables();
			return;
			
		}
		else {
			System.out.println();
			System.out.println("--------------------------------");
			System.out.println("CREATE RESERVATION: UNSUCCESSFUL");
			System.out.println("Error Message: Each customer only allowed to make a reservation once.");	
			System.out.println("--------------------------------");
			return;
		}
	}
	
	
	public int createReservationToday(Customer person, LocalDateTime currentTime){
		Scanner Sc=new Scanner(System.in);
		int table = 0;
		if (!hasReservation(person.getName(), person.getNumber(), currentTime)) {
			int current = 0;
			if (currentTime.isAfter(LocalDateTime.of(date, LocalTime.of(9,59,59))) && currentTime.isBefore(LocalDateTime.of(date, LocalTime.of(12,1,1)))) {
				current = 1;
			}
			else if (currentTime.isAfter(LocalDateTime.of(date, LocalTime.of(11,59,59))) && currentTime.isBefore(LocalDateTime.of(date, LocalTime.of(14,1,1)))) {
				current = 2;
			}
			else if (currentTime.isAfter(LocalDateTime.of(date, LocalTime.of(13,59,59))) && currentTime.isBefore(LocalDateTime.of(date, LocalTime.of(16,1,1)))) {
				current = 3;
			}
			else if (currentTime.isAfter(LocalDateTime.of(date, LocalTime.of(15,59,59))) && currentTime.isBefore(LocalDateTime.of(date, LocalTime.of(18,1,1)))) {
				current = 4;
			}
			else if (currentTime.isAfter(LocalDateTime.of(date, LocalTime.of(17,59,59))) && currentTime.isBefore(LocalDateTime.of(date, LocalTime.of(23,59,59)))) {
				current = 5;
			}
			if (current == 0) {
				System.out.println("Restaurant is closed. Opening hours: 10am to 8pm.");
				return 0;
			}
		
			showUnassignedTables(current);
			System.out.println("Please enter number of people at the table:");
			int pax=Sc.nextInt();
			while (pax < 1 || pax > 10){
				System.out.println();
				System.out.println("Please enter number of people at the table within 1-10:");
				pax=Sc.nextInt();
			}
			System.out.println();
			System.out.println("-------------------------------------");
			System.out.println("WALK IN TABLE ASSIGNMENT: SUCCESSFUL");
			System.out.println("-------------------------------------");
			table = assignTable(pax,person,current);
			showDayAssignedTables();
			return table;
		}
		else {
			table = checkReservation(person.getName(), person.getNumber(), currentTime);
			return table;
		}
		
			
}
	
	
	
	/**
	 * Remove a reservation at a particular time
	 * @param name
	 * @param contactNo
	 */
	public void removeReservation(String name, long contactNo, LocalDateTime currentTime) {
		if (hasReservation(name, contactNo, currentTime)) {
			for(Table removeTable: Tables) {
				for(int i=1; i<=5; i++) {
					if(removeTable.getCustomer(i)!=null) {
						if(name.equalsIgnoreCase(removeTable.getCustomer(i).getName())) {
							if (contactNo == removeTable.getCustomer(i).getNumber()) {
								int tableId = removeTable.getID();
								unassignTable(tableId, i);
								System.out.println();
								System.out.println("--------------------------------");
								System.out.println("REMOVE RESERVATION: SUCCESSFUL");
								System.out.println("--------------------------------");
								showDayAssignedTables();
								return;
							}
						}
					}
				}
			}
		}
		
		else {
			System.out.println();
			System.out.println("--------------------------------");
			System.out.println("REMOVE RESERVATION: UNSUCCESSFUL");
			System.out.println("Error Message: No reservation record found. Customer's name or contact no. entered incorrectly.");
			System.out.println("--------------------------------");
			return;
		}
	}
	/**
	 * Checks if a customer has a reservation at a particular time
	 * @param name
	 * @param timeSlot
	 * @return true/false if someone has reservation
	 */
	public boolean hasReservation(String name, long contactNo, LocalDateTime currentTime){
	    int current = 0;
	    if (currentTime.isAfter(LocalDateTime.of(date, LocalTime.of(20,0,0)))) {
	      current = 5;
	    }
	    else if (currentTime.isAfter(LocalDateTime.of(date, LocalTime.of(18,0,0)))) {
	      current = 4;
	    }
	    else if (currentTime.isAfter(LocalDateTime.of(date, LocalTime.of(16,0,0)))) {
	      current = 3;
	    }
	    else if (currentTime.isAfter(LocalDateTime.of(date, LocalTime.of(14,0,0)))) {
	      current = 2;
	    }
	    else if (currentTime.isAfter(LocalDateTime.of(date, LocalTime.of(12,0,0)))) {
	      current = 1;
	    }
	    //System.out.println("current: " + current);
	    for (int j=1; j<=current; j++) {
	       for (int k=1; k<=5; k++) {
	         if (Tables.get(k-1).isAssigned(j)) {
	           unassignTable(k,j);
	         }
	       }
	    }
	    
	    for(Table tables: Tables) {
	      for(int i=1; i<=5; i++) {
	        if(tables.getCustomer(i)!=null) {
	          if(name.equalsIgnoreCase(tables.getCustomer(i).getName())) {
	            if(contactNo == tables.getCustomer(i).getNumber()) {
	              if(tables.isAssigned(i)) {
	                return true;
	              }
	            }
	          }
	        }
	      }
	    }
	    return false;
	  }
	/**
	 * Prints reservation details if a customer has a reservation at a particular time
	 * @param name
	 * @param timeSlot
	 * @return true/false if someone has reservation
	 */
	public int checkReservation(String name, long contactNo, LocalDateTime currentTime){	// check for one customer
		
		if (hasReservation(name, contactNo, currentTime)) {
			for(Table checkTable: Tables) {
				for(int i=1; i<=5; i++) {
					if(checkTable.getCustomer(i)!=null) {
						if(name.equalsIgnoreCase(checkTable.getCustomer(i).getName())) {
							if(checkTable.isAssigned(i)) {
								System.out.println();
								System.out.println("###### CUSTOMER RESERVATION DETAILS ######");
								System.out.println("Date reserved: " + date);
								System.out.println("Customer name: " + checkTable.getCustomer(i).getName());
								System.out.println("Contact No.: " + checkTable.getCustomer(i).getNumber());
								System.out.println("Time Slot: " + timeSlot[i-1]);
								System.out.println("Table ID: " + checkTable.getID());
								System.out.println("Pax: " + checkTable.getPax());
								return checkTable.getID();
							}
						}
					}
				}
			}
		}
		else {
			System.out.println();
			System.out.println("Error Message: No reservation record found. Customer's name or contact no. entered incorrectly.");
			System.out.println();
			showDayAssignedTables();
			System.out.println();
			System.out.println("Create new reservation? (yes/no)");
			Scanner sc = new Scanner(System.in); 
			String yesOrNo=sc.next();
			if(yesOrNo.equalsIgnoreCase("yes")) {
				Customer resCust=new Customer();
				createReservation(resCust, currentTime);
			}
		}
		
		return 0;
	}
}
