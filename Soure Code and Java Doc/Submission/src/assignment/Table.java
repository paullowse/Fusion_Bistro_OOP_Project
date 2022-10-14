package assignment;

import java.io.Serializable;
/**
 * Stores the details of a single table (corresponding to a particular pax)
 * @author Admin
 *
 */
public class Table implements Serializable {
	private static final long serialVersionUID = 7745543069809306342L;
	private int tableID;
	private int pax;
	private Customer cust[];
	private boolean timeSlot[];
	
	/**
	 * 
	 * @param tableID
	 * @param pax
	 */
	public Table(int tableID, int pax){
		this.tableID = tableID;
		this.pax = pax;
		timeSlot=new boolean[5];
		cust=new Customer[5];
		for(int i=0; i<5; i++){
			timeSlot[i]=false;
		}
	}
	/**
	 * 
	 * @return tableID
	 */
	public int getID(){
		return tableID;
	}	
	/**
	 * 
	 * @return max no. of people table can take
	 */
	public int getPax(){
		return pax;
	}
/**
 * 
 * @param slot
 * @return boolean value - assigned or unassigned
 */
	public boolean isAssigned(int slot){
		return timeSlot[slot-1];
	}
/**
 * assigns table for one particular time slot
 * @param cust
 * @param slot
 */
	public void assign(Customer cust,int slot){
		if(!timeSlot[slot-1]){
		this.cust[slot-1]=cust;
		timeSlot[slot-1]=true;
		}
	}
	public Customer getCustomer(int slot){
		return cust[slot-1];
	}
	/**
	 * unassigns the table for one particular time slot
	 * @param slot
	 */
	public void unassign(int slot){
		timeSlot[slot-1]=false;
		cust[slot-1]=null;
		
	}


}

