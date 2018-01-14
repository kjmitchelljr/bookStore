package bookshoppe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

public class BookStoreWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookStoreWindow window = new BookStoreWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BookStoreWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Ye Olde Book Shoppe");
		frame.setBounds(100, 100, 1120, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel numberOfItems = new JLabel("Enter number of items in this order:");
		numberOfItems.setFont(new Font("Tahoma", Font.PLAIN, 24));
		numberOfItems.setBounds(175, 99, 396, 33);
		frame.getContentPane().add(numberOfItems);
		
		JLabel bookID = new JLabel("Enter Book ID for item #:");
		bookID.setFont(new Font("Tahoma", Font.PLAIN, 24));
		bookID.setBounds(287, 149, 274, 33);
		frame.getContentPane().add(bookID);
		
		JLabel lblNewLabel = new JLabel("Enter quantity for Item #:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(287, 187, 278, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Item # info:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(437, 229, 130, 33);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Order subtotal for item(s):");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_2.setBounds(287, 270, 282, 33);
		frame.getContentPane().add(lblNewLabel_2);
	}
}
