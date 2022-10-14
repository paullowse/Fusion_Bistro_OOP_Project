package assignment;

import java.util.Date;
import java.util.Map;
/**
 * Used to generate the bill for the overall meal ordered by the customer
 * @author Admin
 *
 */
public class OrderInvoice {
	/**
	 * Name of the Staff handling this order
	 */
	private String staffName;
	/**
	 * Percentage discount available for members
	 */
	private double discountPercent = 0.10;
	/**
	 * Discount total value if customer is a member
	 */
	private double discount;
	/**
	 * Subtotal of final order invoice
	 */
	private double subtotal = 0;
	/**
	 * GST amount
	 */
	private double GST;
	/**
	 * Final total amount payable
	 */
	private double total;
	/**
	 * Order object used to generate the order Invoice
	 */
	private Order finalOrder;
	
/**
 * Create an orderInvoice with the staff name
 * @param order Order used for the orderInvoice
 * @param name Name of the staff attending to the order
 */
	public OrderInvoice(Order order){ 				
		
		GST = 0;
		this.finalOrder = order;
		this.staffName = order.getStaffName();
		
		calculateSubtotal(finalOrder);
		calculateDiscount();
		calculateTotal();
	}


/**
 * Creates the subtotal value given the order used
 * @param finalOrder Order used to calculate the subtotal
 */
public void calculateSubtotal(Order finalOrder){
	
	
	for (int i = 0; i< finalOrder.getNumOfItems(); i++) {
		ItemOrder io =  finalOrder.getItemsInOrder()[i];
		subtotal+= io.getTotal();
	}
	subtotal = Math.round(subtotal * 100.0) / 100.0;
}

/**
 * Creates the discount value given the order used
 */
public void calculateDiscount(){
	if(finalOrder.getCustomer().checkMember()) {
		discount = discountPercent * subtotal;
		discount = Math.round(discount * 100.0) / 100.0;
	}
	else
		discount = 0;
}

/**
 * Calculates the total value payable
 */
public void calculateTotal(){
	GST = 0.07 * (subtotal - discount);
	total = GST + subtotal - discount;
	total = Math.round(total * 100.0) / 100.0;
	GST = Math.round(GST * 100.0) / 100.0;
}


/**
 * Printing out the final oinvoice for the customer. 
 */
public void printInvoice() {						
	Date date=new Date();
	System.out.println();
	System.out.println("                     FUSION BISTRO                    ");
	System.out.println("                  50, Nanyang Avenue                  ");
	System.out.println("           Nanyang Technological University           ");
	System.out.println("                  Contact: 9634 5678                  ");
	System.out.println("                ##### ORDER NO: " + finalOrder.getOrderID() + " #####                ");  
	System.out.println("Server: " + String.format("%-14s",staffName) + "    "+date.toString()); 
	System.out.println("Name: " + String.format("%-34s",finalOrder.getCustomer().getName())  + String.format(" Table No: %3s",finalOrder.getTableID()));
	System.out.println("------------------------------------------------------"); 
	finalOrder.finaldisplay();
	System.out.println("------------------------------------------------------");
	System.out.println("                                  Subtotal" + String.format("%12.2f",subtotal));
	System.out.println("                                  Discount" + String.format("%12.2f",discount));
	System.out.println("                                    7% GST" + String.format("%12.2f",GST));
	System.out.println("                                     Total" + String.format("%12.2f",total));
	System.out.println("------------------------------------------------------");
	System.out.println("            Thank you for dining with us!            ");
	System.out.println();
	System.out.println();
}								//54 places

}