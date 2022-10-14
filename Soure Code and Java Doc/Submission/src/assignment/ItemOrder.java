package assignment;
/**
 * Stores the details (Qty and total price) of a single item within an order
 * @author Admin
 *
 */
public class ItemOrder {

	
	/**
	 * Stores a menuitem object with its details
	 */
	private MenuItem menuitem;
	/**
	 * Stores the quantity of the menuitem in the Item Order
	 */
	private int quantity;
	/**
	 * Stores the total price of the item Order
	 */
	private double totalPrice;
	
	
	
	
	/**
	 * Creates an Item Order
	 * @param menuItem MenuItem of the item order
	 * @param quantity Quantity of MenuItems in the item order
	 */
	public ItemOrder (MenuItem menuItem, int quantity) {
		
		this.quantity = quantity;
		this.menuitem = menuItem;
		
		
		double total = menuItem.getPrice() * quantity;
		this.totalPrice = Math.round(total * 100.0) / 100.0;
		
	}
	
	/**
	 * get the name of the menuItem
	 * @return name the name of the menuItem
	 */
	public String getName() {
		return menuitem.getName();
	}
	
	/**
	 * get the Price of the menuItem
	 * @return return the Price of the menuItem
	 */
	public  double getPrice() {
		return menuitem.getPrice();
	}
	
	
	/**
	 * get quantity of the menuItem in the item Order
	 * @return quantity of the menuItem in the item Order
	 */
	
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * get MenuItem Object from the ItemOrder
	 * @return MenuItem Object
	 */
	
	public MenuItem getMenuItem() {
		return menuitem;
	}
	
	/**
	 * get total Price of the item Order
	 * @return total Price of the item Order
	 */
	public double getTotal() {
		return totalPrice;
	}
	
	/**
	 * Set new quantity of the itemOrder
	 * @param updated Updated quantity of the itemOrder
	 */
	public void setQuantity(int updated) {
		quantity = updated;
		double total = menuitem.getPrice() * quantity;
		this.totalPrice = Math.round(total * 100.0) / 100.0;
	}
}
