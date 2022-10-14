package assignment;

/**
 * Represents a specific drink item in the drink menu
 * @author Admin
 *
 */
public class Drinks extends GenericMenuItem {
	/**
	 * Denotes whether the drink is served hot or cold
	 */
	private String hotOrCold;
		
	/**
	 * Creates a new drink object using the 4 fields
	 * @param name
	 * @param description
	 * @param price
	 * @param hotOrCold
	 */
		public Drinks(String name, String description, double price, String hotOrCold) {
			super(name, description, price);
			this.hotOrCold = hotOrCold;
		}
		
		/**
		 * Default constructor used to create drink object. The attributes of the drink object will be updated by the user during
		 * runtime
		 * 
		 */
		public Drinks() {
			super("nil", "nil", -1);
			hotOrCold = "Cold";
		}
		
		/**
		 * Display all information related to the drink
		 */
		public void display() {
			super.display();
			System.out.println(String.format("%-12s", hotOrCold));
		}
		
		/**
		 * Set the attribute of the drink (Hold Or Cold)
		 */
		public void setHotOrCold(String hotOrCold) {
			this.hotOrCold = hotOrCold;
		}
}
