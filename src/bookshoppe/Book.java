/* Name: Kenneth Mitchell Jr.
   Course: CNT 4714 – Spring 2018
   Assignment title: Program 1 – Event-driven Programming
   Date: Sunday January 28, 2018
*/

package bookshoppe;

//Define a book object

public class Book{
	private int bookID;
	private float bookPrice;
	private String bookName;
	private String bookInfo;
	
	public Book() {
		setInfo("Book ID " + getID() + " not in file");
	}
	
	public void setID(int bookID) {
		this.bookID = bookID;
	}
	
	public void setName(String bookName) {
		this.bookName = bookName;
	}
	
	public void setPrice(float bookPrice) {
		this.bookPrice = bookPrice;
	}
	
	public void setInfo(String bookInfo) {
		this.bookInfo = bookInfo;
	}
	
	public int getID() {
		return this.bookID;
	}
	
	public String getName() {
		return this.bookName;
	}
	
	public float getPrice() {
		return this.bookPrice;
	}
	
	public String getInfo() {
		return this.bookInfo;
	}
}