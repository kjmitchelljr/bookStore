/* Name: Kenneth Mitchell Jr.
   Course: CNT 4714 – Spring 2018
   Assignment title: Program 1 – Event-driven Programming
   Date: Sunday January 28, 2018
*/

package bookshoppe;

import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


/*Generates invoice message that will be displayed to the
 * user once the order is complete.
 */

public class Invoice{
	private ArrayList<Order> bookOrder;
	
	private int numberItems = 0;
	private float subtotal = 0;
	private float orderTotal = 0;
	private String timeStamp;
	private String transactionStamp;
	
	public Invoice() {
		this.bookOrder = new ArrayList<Order>();
	}
	
	public void setOrder(int numberItems) {
		this.numberItems = numberItems;
	}
	
	public void setSubTotal(float subtotal) {
		this.subtotal = subtotal;
	}
	
	public void setOrderTotal(float orderTotal) {
		this.orderTotal = orderTotal;
	}
	
	public void setDate() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a z");
		this.timeStamp = dateFormat.format(date);
		dateFormat = new SimpleDateFormat("yyMMddHHmmss");
		this.transactionStamp = dateFormat.format(date);
	}
	
	public void addOrder(Order order) {
		this.bookOrder.add(order);
	}
	
	public int getOrder() {
		return this.numberItems;
	}
	
	public float getSubTotal() {
		return this.subtotal;
	}
	
	public float getOrderTotal() {
		return this.orderTotal;
	}
	
	public ArrayList<Order> getOrders(){
		return this.bookOrder;
	}
	
	public String getTimeStamp() {
		return this.timeStamp;
	}
	
	public String getTransactionStamp() {
		return this.transactionStamp;
	}
}