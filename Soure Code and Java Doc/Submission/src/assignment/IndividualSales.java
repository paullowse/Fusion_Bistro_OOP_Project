package assignment;

import java.io.Serializable;

/**
 * Represents/tracks the sales of an individual item for a year
 * @author Admin
 *
 */

public class IndividualSales implements Serializable{
	
	//sales
	/**
	 * Class variables which are used as constants to determine the number of days and month in a year
	 */
	private static final int DAY = 31;
	private static final int MONTH = 12;
	
	/**
	 * The name of the menu Item whose sales is being tracked
	 */
	private String name;
	/**
	 * The quantity sold for each day is stored in a 2D array
	 */
	private double[][] qty = new double[DAY][MONTH];
	
	/**
	 * The revenue earned for each day is stored in a 2D array
	 */
	private double[][] revenue = new double[DAY][MONTH];
	
	/**
	 * Creates an individual sales object based on the menu Item name. By default, all values are intialized to 0
	 * @param name
	 */
	public IndividualSales(String name) {
		this.name = name;
		for(int i =0; i<DAY; i ++) {
			for(int j = 0; j<MONTH;j++) {
				qty[i][j] = 0;
				revenue[i][j] = 0;
			}
		}
	}
	
	/**
	 * Return name of the menu item that is being tracked
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Update the name of the menu item that is being tracked
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Used to update the sales and revenue whenever a transaction has occurred
	 * @param day
	 * @param month
	 * @param singleOrderItem
	 */
	public void addItem(int day, int month, ItemOrder singleOrderItem) {
		qty[day-1][month-1] += singleOrderItem.getQuantity();
		revenue [day-1][month-1] += singleOrderItem.getTotal();
	}
	/**
	 * Prints the total daily quantity sold and revenue earned for a particular menu item in a day
	 * @param day
	 * @param month
	 */
	public void dailyReport(int day,int month) {
		double dailyQty = getDailyQty(day,month);
		double dailyRevenue = getDailyRevenue(day,month);
		
		System.out.println(String.format("%-30s%10.0f%10.2f", name,dailyQty,dailyRevenue));
	}
	/**
	 * Prints the total monthly quantity sold and revenue earned for a particular menu item in a month
	 * @param month
	 */
	public void monthlyReport(int month) {
		double monthQty = getMonthlyQty(month);
		double monthSales = getMonthlyRevenue(month);
		
	
		
		System.out.println(String.format("%-30s%10.0f%10.2f", name,monthQty,monthSales));
	}
	/**
	 * Returns the total daily quantity sold for a particular menu item in a day
	 * @param day
	 * @param month
	 * @return quantity
	 */
	public double getDailyQty(int day,int month) {
		return qty[day-1][month-1];
	}
	/**
	 * Returns the total daily revenue earned for a particular menu item in a day
	 * @param day
	 * @param month
	 * @return revenue
	 */
	public double getDailyRevenue(int day,int month) {
		return revenue[day-1][month-1];
	}
	/**
	 * Returns the total monthly quantity sold for a particular menu item in a month
	 * @param month
	 * @return monthQty
	 */
	public double getMonthlyQty(int month) {
		double monthQty = 0;
		
		for(int i = 0; i<DAY;i++){
			monthQty += qty[i][month-1];
		}
		return monthQty;
	}
	/**
	 * Returns the total monthly revenue earned for a particular menu item in a month
	 * @param month
	 * @return monthSales
	 */
	public double getMonthlyRevenue(int month) {
		double monthSales = 0;
		
		for(int i = 0; i<DAY;i++){
			monthSales += revenue[i][month-1];
		}
		return monthSales;
	}
}
