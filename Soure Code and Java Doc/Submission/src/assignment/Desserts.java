package assignment;

/**
 * Represents a specific Desserts item in the Desserts menu
 * @author Admin
 *
 */
public class Desserts extends GenericMenuItem {
	/**
	 * Denotes toppings included in the Desserts 
	 */
	private String addToppings;
	
	/**
	 * Creates a new Desserts object using the 4 fields
	 * @param name
	 * @param description
	 * @param price
	 * @param addToppings
	 */
	public Desserts(String name, String description, double price, String addToppings) {
		super(name, description, price);
		this.addToppings = addToppings;
	}
	
	
	/**
	 * Default constructor used to create Desserts object. The attributes of the Desserts object will be updated by the user during
	 * runtime
	 * 
	 */
	public Desserts() {
		super("nil", "nil", -1);
		addToppings = "NIL";
	}
	
	/**
	 * Display all information related to the drink
	 */
	public void display() {
		super.display();
		System.out.println(String.format("%-50s", addToppings));
	}
	
	/**
	 * Takes in the toppings provided by user and updates the addToppings field
	 * @param addToppings
	 */
	public void setAddToppings(String addToppings) {
		this.addToppings = addToppings;
	}
}
