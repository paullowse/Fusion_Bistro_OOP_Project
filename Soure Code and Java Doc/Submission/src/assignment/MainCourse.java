package assignment;

/**
 * Represents a specific MainCourse item in the MainCourse menu
 * @author Admin
 *
 */
public class MainCourse extends GenericMenuItem {
	/**
	 * Denotes condiments contained by the maincourse
	 */
	private String addCondiments;
	//private SalesRevenueReport rpt;
	
	/**
	 * Creates a new MainCourse object using the 4 fields
	 * @param name
	 * @param description
	 * @param price
	 * @param addCondiments
	 */
	public MainCourse(String name,String description,double price, String addCondiments) {
		super(name, description, price);
		this.addCondiments = addCondiments;
	}
	
	/**
	 * Default constructor used to create MainCourse object. The attributes of the MainCourse object will be updated by the user during
	 * runtime
	 * 
	 */
	public MainCourse() {
		super("nil","nil",-1);
		addCondiments = "NIL";
	}
	/**
	 * Display all information related to the drink
	 */
	public void display() {
		super.display();
		System.out.println(String.format("%-12s", addCondiments));

	}
	/**
	 * Takes in the condiments provided by user and updates the addCondiments field
	 * @param addCondiments
	 */
	public void setAddCondiments(String addCondiments) {
		this.addCondiments = addCondiments;
	}
}
