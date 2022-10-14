package assignment;
/**
 * A menu item interface which to allow the use of polymorphism
 */
public interface MenuItem  {
	//implements Serializable
	/**
	 * Returns the name of the menu item
	 * @return name
	 */
	public String getName();
	
	/**
	 * Returns the price of the menu item
	 * @return price
	 */
	public Double getPrice();
	/**
	 * Returns the description of the menu item
	 * @return description
	 */
	public String getDescription();
	
	/**
	 * Updates the name of the menu item 
	 * @param name
	 */
	public void setName(String name);
	
	/**
	 * Updates the price of the menu item 
	 * @param price
	 */
	public void setPrice(Double price);
	
	/**
	 * Updates the description of the menu item 
	 * @param description
	 */
	public void setDescription(String description);
	
	/**
	 * Displays all the relevant information for a specific menu item 
	 */
	public void display();
	
}
