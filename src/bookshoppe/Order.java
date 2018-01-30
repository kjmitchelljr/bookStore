/* Name: Kenneth Mitchell Jr.
   Course: CNT 4714 – Spring 2018
   Assignment title: Program 1 – Event-driven Programming
   Date: Sunday January 28, 2018
*/

package bookshoppe;

public class Order {
	private Book book;
	private int quantityForItem;
	private float discountPerc;
	private float subTotal;
	
	public void setQuantity(int quantity) {
		this.quantityForItem = quantity;
	}
	
	public void setBook(Book selectedBook) {
		this.book = selectedBook;
	}
	
	public void setDiscount(float discount) {
		this.discountPerc = discount;
	}
	
	public void setSubtotal(float subTotal) {
		this.subTotal = subTotal;
	}
	
	public Book getBookItems() {
		return this.book;
	}
	
	public int getQuantity() {
		return this.quantityForItem;
	}
	
	public float getDiscount() {
		return this.discountPerc;
	}
	
	public float getSubtotal() {
		return this.subTotal;
	}
}