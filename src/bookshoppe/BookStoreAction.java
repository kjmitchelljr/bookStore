/* Name: Kenneth Mitchell Jr.
   Course: CNT 4714 – Spring 2018
   Assignment title: Program 1 – Event-driven Programming
   Date: Sunday January 28, 2018
*/

package bookshoppe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class BookStoreAction{
	private ArrayList<Book> books;
	private Invoice bookInvoice;
	private boolean read = false;
	
	private final float TAX = 0.06f; 
	private final String FILE_IN = "inventory.txt";
	private final String FILE_OUT = "transaction.txt";
	private final String DISPLAY_TAX = "6%";
	
	//Initialize the Invoice and read inventory
	public BookStoreAction() {
		this.bookInvoice = new Invoice();
		if(!read) {
			ReadInventory();
			read = true;
		}
	}
	
	//Adds the current order to the invoice
	public void ProcessOrder(Book book, int quantity, int numberItems) {
		Order bookOrder = new Order();
		bookOrder.setBook(book);
		bookOrder.setQuantity(quantity);
		bookOrder.setDiscount(Float.valueOf(getDisplayDiscount(quantity)));
		bookOrder.setSubtotal(CalculateDiscount(book, quantity));
		
		this.bookInvoice.addOrder(bookOrder);
		this.bookInvoice.setSubTotal(CalculateDiscount(book, quantity));
		this.bookInvoice.setOrderTotal(this.bookInvoice.getSubTotal() + (this.bookInvoice.getSubTotal() * TAX));
		this.bookInvoice.setOrder(numberItems);	
	}
	
	//Finds a book based on the ID
	public Book FindBook(int bookID) {
		for(Book book: books) {
			if(book.getID() == bookID) {
				return book;
			}
		}
		
		return new Book();
	}
	
	//Sets the Book information field using specified pattern
	public void SetBookInfo(Book book, int quantity) {
		book.setInfo(String.valueOf(book.getID()) + " " + book.getName() + " " + String.valueOf(book.getPrice() + " " 
				+ String.valueOf(quantity) + " " + String.valueOf(getDisplayDiscount(quantity)) + "% " +
				String.valueOf(CalculateDiscount(book, quantity))));
	}
	
	//Determines the discount based on the quantity ordered
	private int getDisplayDiscount(int quantity) {
		if(quantity < 5) {
			return 0;
		} else if(quantity < 10) {
			return 10;
		} else if(quantity < 15) {
			return 15;
		} else if(quantity >= 15) {
			return 20;
		} else {
			return 0;
		}
	}
	
	//Calculate the discounted subtotal
	private float CalculateDiscount(Book book, int quantity) {
		float subtotal= book.getPrice() * (float) quantity;
		
		if(quantity < 5) {
			return subtotal;
		} else if(quantity < 10) {
			return subtotal - (subtotal * 0.1f);
		} else if(quantity < 15) {
			return subtotal - (subtotal * 0.15f);
		} else if(quantity >= 15) {
			return subtotal - (subtotal * 0.2f);
		} else { 
			return 0f;
		}
	}
	
	//Calculate subtotal for display on final invoice
	public float getDisplaySubtotal(Book book, int quantity) {
		return bookInvoice.getSubTotal() + CalculateDiscount(book, quantity);
	}
	
	//Get all the orders for display on the final invoice
	public String getDisplayOrder() {
		String viewOrder = "";
		int i = 1;
		for(Order bookOrder: bookInvoice.getOrders()) {
			viewOrder = viewOrder + String.valueOf(i) + ". " + bookOrder.getBookItems().getInfo() + "\n";
			i++;
		}
		
		return viewOrder;
	}
	
	//Writes the order to the transaction.txt file
	public void WriteInvoice() {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(FILE_OUT, true);
		} catch(IOException e) {
			System.out.println("Unable to open file for writing!");
			e.printStackTrace();
		}
		bw = new BufferedWriter(fw);
		
		for(Order bookOrder: this.bookInvoice.getOrders()) {
			try {
				bw.write(this.bookInvoice.getTransactionStamp() + ", " + bookOrder.getBookItems().getID() + ", " 
						+ bookOrder.getBookItems().getName() + ", " + bookOrder.getBookItems().getPrice() + ", "
						+ bookOrder.getQuantity() + ", " + bookOrder.getDiscount() + ", " + bookOrder.getSubtotal()
						+ ", " + this.bookInvoice.getTimeStamp());
				bw.newLine();
			} catch(IOException e) {
				System.out.println("Unable to write to file!");
				e.printStackTrace();
			}
		}
		
		try {
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Display all invoice information
	public String DisplayInvoice() {
		this.bookInvoice.setDate();
		String message = "Date: ";
		
		message = message + this.bookInvoice.getTimeStamp() + "\n\n";
		message = message + "Number of line items: " + this.bookInvoice.getOrders() + "\n\n";
		message = message + "Item# / ID / Title / Price / Qty / Disc % / Subtotal:\n\n";
		message = message + getDisplayOrder() + "\n\n";
		message = message + "Order subtotal: " + this.bookInvoice.getSubTotal() + "\n\n";
		message = message + "Tax rate:		" + DISPLAY_TAX + "\n\n";
		message = message + "Tax amount:		$" + (this.bookInvoice.getSubTotal() * TAX) + "\n\n";
		message = message + "Order total:		" + this.bookInvoice.getOrderTotal() + "\n\n";
		message = message + "Thanks for shopping at the Ye Olde Book Shoppe";
		
		return message;
	}
	
	//Read in the inventory file into Book objects and stores in ArrayList
	private void ReadInventory() {
		FileReader fr = null;
		BufferedReader br = null;
		
		books = new ArrayList<Book>();
		try {
			fr = new FileReader(FILE_IN);
		} catch(FileNotFoundException e) {
			JOptionPane box = new JOptionPane("Could not open file for reading!", JOptionPane.ERROR_MESSAGE);
			box.setVisible(true);
			e.printStackTrace();
		}
		
		try {
			br = new BufferedReader(fr);
			
			String line;
			while((line = br.readLine()) != null) {
				String [] currentBook = new String[3];
				Book book = new Book();
				
				currentBook = line.split(",", 3);
				book.setID(Integer.valueOf(currentBook[0]));
				book.setName(currentBook[1]);
				book.setPrice(Float.valueOf(currentBook[2]));
				
				books.add(book); 
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}