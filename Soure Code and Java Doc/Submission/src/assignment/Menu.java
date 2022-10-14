package assignment;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A Menu interface which to allow the use of polymorphism.
 */
public interface Menu extends Serializable{
	
	/**
	 * Create a menu item 
	 * @param rpt
	 */
	public void create(SalesRevenueReport rpt);
	
	/**
	 * Updates an existing menu item
	 * @param rpt
	 */
	public void update(SalesRevenueReport rpt);
	
	/**
	 * Remove an existing menu item 
	 */
	public void remove();
	
	/**
	 * Displays all existing item in a main (MainCourse, etc)
	 */
	public void display(); 
	
	/**
	 * Return the MenuItem chosen by user based on the index position in the menu for further processing 
	 * @param indexOfItem in the menu
	 * @return MenuItem
	 */
	public MenuItem getItem(int indexOfItem);
	
	/**
	 * Commit the changes made to the menu by saving it to the text file
	 */
	public void write();
	
}
