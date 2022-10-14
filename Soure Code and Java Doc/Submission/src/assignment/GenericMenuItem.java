package assignment;

import java.io.Serializable;
/**
 * Serves as the parent class of all the menu items
 * @author Admin
 *
 */
public abstract class GenericMenuItem implements MenuItem,Serializable{
	
	/**
	 * The name of this menu item 
	 */
	private String name;
	/**
	 * The description of this menu item 
	 */
	private String description;
	/**
	 * The price of this menu item 
	 */
	private double price;
	
	/**
	 * Creates a basic menu item object which consists of all the common fields.
	 * @param name
	 * @param description
	 * @param price
	 */
	public GenericMenuItem(String name,String description,double price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	/**
	 * Returns the name of the menu item
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Returns the price of the menu item
	 * @return price
	 */
	public Double getPrice() {
		return price;
	}
	
	/**
	 * Returns the description of the menu item
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * set the name of the menu item
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * set the price of the menu item
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * set the description of the menu item
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Displays all the common information pertaining to a menu item 
	 */
	public void display() {
		System.out.print(String.format("%-30s%-75s%-10.2f", name, description, price));
	}
	

}
