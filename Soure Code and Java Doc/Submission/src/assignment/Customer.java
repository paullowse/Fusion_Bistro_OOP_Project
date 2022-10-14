package assignment;

import java.util.Scanner;
import java.io.Serializable;
/**
 * 
 * Represents a single customer
 *
 */
public class Customer implements Serializable {

	
	private static final long serialVersionUID = 6352164382651793654L;
	/**
	 * Store the name of the customer
	 */
	private String name;
	/**
	 * Indicates whether a customer is a member
	 */
	private boolean member;
	/**
	 * Stores the contact number of the customer
	 */
	private long contactNumber;
	/**
	 * Stores the member id
	 */
	private double member_id;
	
	/**
	 * Customer constructor which is used to create a customer object upon being called
	 */
	public Customer() {
		Scanner sc = new Scanner(System.in); 
		System.out.println("");
		System.out.println("Hello! Please enter the customer's name: ");
		this.name = sc.nextLine().toLowerCase();	
		System.out.println("");
		
		System.out.println("Enter phone number:");
		this.contactNumber = Reader.readingLong();
		System.out.println("");
		
		System.out.println("Is the customer a member? (Yes / No)");
		String ans = sc.nextLine().toLowerCase();
		while (!ans.equals("yes") && !ans.equals("no")) {							
			System.out.println("Please key in Yes or No only:");
			ans = sc.nextLine().toLowerCase();
			System.out.println("");
		}
		if (ans.equals("yes")) {
			System.out.println("Enter Customer Membership ID:");
			this.member_id = Reader.readingDouble();
			System.out.println("");
			this.member = true;
		} else {
			this.member_id = 0;
			this.member = false;
		}
		
	}
	
	/**
	 * Returns the name of the customer
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Returns whether or not a customer is a member
	 * @return member
	 */
	public Boolean checkMember() {
		return member;
	}
	/**
	 * Returns the contact number of the customer
	 * @return contactNumber
	 */
	public long getNumber()  {
		return contactNumber;
	}
	/**
	 * Returns the ID of the member
	 * @return
	 */
	public double getID() {
		return member_id;
	}
	
	
}
