package assignment;

import java.io.Serializable;
import java.util.Scanner;
/**
 * Stores the detaisl pertaining to a single staff
 * @author Admin
 *
 */
public class Staff implements Serializable {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = -791497979493749781L;
	/**
	 * name stores staffName
	 */
	private String name;
	/**
	 * gender of staff
	 */
	private String gender;
	/**
	 * employee_Id of staff
	 */
	private double employee_id;
	/**
	 * job title of staff
	 */
	private String jobtitle;
	Scanner sc= new Scanner(System.in);
	
	/**
	 * instantiation of staff
	 */
	public Staff() {
		System.out.println("Please Enter Staff Name:");
		this.name = sc.nextLine().toLowerCase();
		System.out.println("");
		System.out.println("Please Enter your gender (M/F): ");
		this.gender = sc.nextLine().toLowerCase();
		System.out.println("");
		System.out.println("Please Enter your employee ID: ");
		this.employee_id = Reader.readingDouble();
		System.out.println("");
		System.out.println("Please Enter your Job Title: ");
		this.jobtitle = sc.nextLine().toLowerCase();;
	}
	
	/**
	 * get name of staff
	 * @return name of staff
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * get gender of staff
	 * @return gender of staff
	 */
	public String getGender() {
		return gender;
	}
	
	/**
	 * get employee ID of staff
	 * @return employee ID of staff
	 */
	public double getEmployeeID() {
		return employee_id;
	}
	
	/**
	 * get job title of staff
	 * @return job title of staff
	 */
	public String getJobtitle() {
		return jobtitle;
	}
	
	
}
