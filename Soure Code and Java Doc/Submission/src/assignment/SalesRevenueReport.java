package assignment;

import java.text.DateFormatSymbols;
import java.util.*;
/**
 * Represents the class that is used to perform analysis on the sales made
 * @author Admin
 *
 */
public class SalesRevenueReport {
	/**
	 * Represents the collection of all the individual sales. That is salesRevenueReport keeps track of the sales of all transaction that
	 * has occurred in the restaurant. All sales pertaining to a particular menu item is stored within the same individual sales
	 */
	protected HashMap <MenuItem, IndividualSales> salesRevenueReport = new HashMap <MenuItem, IndividualSales>();
	//Sales Report
	/**
	 * SalesRevenueReport is dynamically initialized based on contents from the text file
	 */
	public SalesRevenueReport(){
		
		ObjectCreator w = new ObjectCreator();
		salesRevenueReport = (HashMap <MenuItem, IndividualSales>) w.readHashMap(salesRevenueReport,"salesRevenueReport.txt");
	}
	/**
	 * Adds a new individual sales Item into the report whenever a menu item is created
	 * @param rpt
	 */
	public void addIndividualSalesToReport(MenuItem m) {
		IndividualSales s= new IndividualSales(m.getName());
		salesRevenueReport.put(m,s);
	}
	
	/**
	 * Displays the name of all the menu items that is stored in the sales revenue report
	 */
	public void displayItem() {
		

		for(IndividualSales s: salesRevenueReport.values()) {
			System.out.println(s.getName());
		}
	}
	/**
	 * Prints the daily report which contains the total revenue and the breakdown of the number of items sold on a particular day
	 * @param day
	 * @param month
	 */
	public void dailyReport(int day, int month) {
		String monthInWords = new DateFormatSymbols().getMonths()[month-1];
		double totalRevenue = 0;
		System.out.println(String.format("--------------------------------------------------"));
		System.out.println(String.format("----------Daily Report For " + day + " " + monthInWords + "------------"));
		System.out.println(String.format("%-30s%10s%10s", "Name","Quantity","Revenue"));
		System.out.println(String.format("--------------------------------------------------"));
		for(IndividualSales s: salesRevenueReport.values()) {
			s.dailyReport(day,month);
			totalRevenue += s.getDailyRevenue(day, month);
		}
		System.out.println(String.format("--------------------------------------------------"));
		System.out.println(String.format("%44s%-10.2f", "Total Revenue:",totalRevenue));
	}
	/**
	 * Prints the monthly report which contains the total revenue and the breakdown of the number of items sold on a particular month
	 * @param month
	 */
	public void monthlyReport(int month) {
		String monthInWords = new DateFormatSymbols().getMonths()[month-1];
		double totalRevenue = 0;
		System.out.println(String.format("-------Monthly Report For Month of " + monthInWords + "--------"));
		System.out.println(String.format("%-30s%10s%10s", "Name","Quantity","Revenue"));
		System.out.println(String.format("--------------------------------------------------"));
		for(IndividualSales s: salesRevenueReport.values()) {
			s.monthlyReport(month);
			totalRevenue += s.getMonthlyRevenue(month);
		}
		System.out.println(String.format("--------------------------------------------------"));
		System.out.println(String.format("%44s%-10.2f", "Total Revenue: ",totalRevenue));
	}
	/**
	 * Returns the individual sales for a particular menu item
	 * @param m
	 * @return IndividualSales
	 */
	public IndividualSales getValue(MenuItem m) {
		
		for(MenuItem mm: salesRevenueReport.keySet()) {

			if((mm.getName()).equalsIgnoreCase(m.getName()) ) {
				
				return salesRevenueReport.get(mm);
				
			}
		}
		
		return null;
	}
	/**
	 * Updates the name of the menu item in the sales report. Used whenever the user updates the name of the menu item through the
	 * menu class object.
	 * @param m
	 * @param newName
	 */
	public void updateName(MenuItem m,String newName) {
		System.out.print(salesRevenueReport.keySet());
		
		for(MenuItem mm: salesRevenueReport.keySet()) {
			System.out.print(mm.getName()  +  "  " + m.getName());
			
			if((mm.getName()).equalsIgnoreCase(m.getName()) ) {
				
				mm.setName(newName);
				System.out.print(mm.getName());
				//set string name in individual sales
				salesRevenueReport.get(mm).setName(newName);
				System.out.print(salesRevenueReport.get(mm));
				
			}
		}
		
		//return null;
	}
	/**
	 * Used to write/save the sales report to a text file
	 */
	public void write() {
		ObjectCreator w = new ObjectCreator();
		w.writeHashMap(salesRevenueReport,"salesRevenueReport.txt");
	}
	
}
