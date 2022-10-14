package assignment;

import java.io.Serializable;

/**
 * Represents a PromotionalSetPackage item in the promotional menu
 * @author Admin
 *
 */
public class PromotionalSetPackage implements MenuItem,Serializable {
	
	MainCourse mc;
	Drinks drink;
	Desserts dessert;
	
	private String name;
	private String description;
	//Description should store names of Main Course, Drink and Dessert
	private double price;
	
	/**
	 * Default constructor used to create PromotionalSetPackage object. The attributes of the PromotionalSetPackage object will be updated by the user during
	 * runtime
	 * 
	 */
	public PromotionalSetPackage() {
		this.name = null;
		this.description = null;
		this.price = -1;
		this.mc = null;
		this.drink = null;
		this.dessert  = null;
	}
	
	/**
	 * Returns the main course stored
	 * @return MainCourse
	 */
	public MainCourse getMainCourse() {
		return mc;
	}
	/**
	 * Returns the Drinks stored
	 * @return Drinks
	 */
	public Drinks getDrinks() {
		return drink;
	}
	
	/**
	 * Returns the Desserts stored
	 * @return Desserts
	 */
	public Desserts getDesserts() {
		return dessert;
	}
	
	/**
	 * Takes in a main course object and sets it as the new main course
	 * @param mc
	 */
	public void setMainCourse(MainCourse mc) {
		this.mc = mc;
	}
	
	/**
	 * Takes in a drink object and sets it as the new drink
	 * @param drink
	 */
	public void setDrinks(Drinks drink) {
		this.drink = drink;
	}
	/**
	 * Takes in a dessert object and sets it as the new Desserts
	 * @param Desserts
	 */
	public void setDesserts(Desserts dessert) {
		this.dessert  = dessert;
	}


	/**
	 * Returns the name of the promotional package
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the price of the promotional package
	 * @return price
	 */
	
	public Double getPrice() {
		return price;
	}


	/**
	 * Returns the description of the promotional package
	 * @return description
	 */
	public String getDescription() {
		return description;
	}



	/**
	 * Takes in a string and sets it as the new set name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Takes in a double and sets it as the new price
	 */
	public void setPrice(Double price) {
		this.price = price;
		
	}
	/**
	 * Takes in a string and sets it as the new description
	 */
	public void setDescription(String description) {
		this.description = description;

	}

	/**
	 * Display all information related to one promotional package
	 */
	@Override
	public void display() {
		//actual display method
		
	}

}
