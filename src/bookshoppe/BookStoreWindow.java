/* Name: Kenneth Mitchell Jr.
   Course: CNT 4714 – Spring 2018
   Assignment title: Program 1 – Event-driven Programming
   Date: Sunday January 28, 2018
*/

package bookshoppe;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class BookStoreWindow {
	
	private static Book book;
	private static int currentOrderNumber;
	private static JFrame bookStoreWindow;
	private static JPanel bPanel;
	private static SpringLayout bLayout;
	private static BookStoreAction bookStoreAction;
	
	private static JTextField bNumItemsTF;
	private static JTextField bIDTF;
	private static JTextField bQuantityTF;
	private static JTextField bItemInfoTF;
	private static JTextField bSubtotalTF;
	
	private static JLabel bNumItemsL;
	private static JLabel bIDL;
	private static JLabel bQuantityL;
	private static JLabel bItemInfoL;
	private static JLabel bSubtotalL;
	
	private static JButton bProcessItem;
	private static JButton bConfirmItem;
	private static JButton bViewOrder;
	private static JButton bFinishOrder;
	private static JButton bNewOrder;
	private static JButton bExit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new BookStoreWindow().StartThread();
		currentOrderNumber = 1;
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private static void initialize() {
		bookStoreWindow = new JFrame("Ye Olde Book Shoppe");
    	bPanel  = new JPanel();

    	bPanel.setSize(700, 200);
        bookStoreWindow.setSize(1190, 423);
        
        InitializeButtons();
        
        InitializeLabels();

        InitializeText();

        InitializePlacement();
        
        bookStoreWindow.getContentPane().add(bPanel);
        bookStoreWindow.setVisible(true);

	}
	
	//Initialize the JLabels and add to the panel
	private static void InitializeLabels() {
    	bNumItemsL = new JLabel("Enter the number of items for this order:");
    	bIDL = new JLabel("Enter Book ID for item #"+String.valueOf(currentOrderNumber)+":");
    	bQuantityL = new JLabel("Enter quantity for item #"+String.valueOf(currentOrderNumber)+":");
    	bItemInfoL = new JLabel("Item #"+String.valueOf(currentOrderNumber)+" info:");
    	bSubtotalL = new JLabel("Order subtotal for "+String.valueOf(currentOrderNumber-1)+" item(s):");
    	
        bPanel.add(bNumItemsL);
        bPanel.add(bIDL);
        bPanel.add(bQuantityL);
        bPanel.add(bItemInfoL);
        bPanel.add(bSubtotalL);
	}
	
	//Initialize the JTextFelds and include listeners
	private static void InitializeText(){
    	bNumItemsTF = new JTextField(13);
    	bIDTF = new JTextField(13);
    	bQuantityTF = new JTextField(13);
    	bItemInfoTF = new JTextField(40);
    	bSubtotalTF = new JTextField(13);
    	
    	bIDTF.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				if((arg0.getKeyCode() == KeyEvent.VK_ENTER) && !bIDTF.getText().isEmpty()){
					book = bookStoreAction.FindBook(Integer.valueOf(bIDTF.getText()));
					if(!bQuantityTF.getText().isEmpty())
						bookStoreAction.SetBookInfo(book, Integer.valueOf(bQuantityTF.getText()));
					else
						bookStoreAction.SetBookInfo(book, 1);
					AutoComplete(book);
				}
				
			}});
    	bIDTF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				if(!bIDTF.getText().isEmpty()){
					book = bookStoreAction.FindBook(Integer.valueOf(bIDTF.getText()));
					
					if(!bQuantityTF.getText().isEmpty())
						bookStoreAction.SetBookInfo(book, Integer.valueOf(bQuantityTF.getText()));
					else
						bookStoreAction.SetBookInfo(book, 1);
					
					AutoComplete(book);
				}
			}
    		
    	});
    	bQuantityTF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if(!bQuantityTF.getText().isEmpty() && !bIDTF.getText().isEmpty()){
					book = bookStoreAction.FindBook(Integer.valueOf(bIDTF.getText()));

					bookStoreAction.SetBookInfo(book, Integer.valueOf(bQuantityTF.getText()));
					
					AutoComplete(book);
				}
				
			}});
    	bQuantityTF.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				if(Character.isDigit(arg0.getKeyChar())){
					book = bookStoreAction.FindBook(Integer.valueOf(bIDTF.getText()));
					bookStoreAction.SetBookInfo(book, Integer.valueOf(bQuantityTF.getText()));
					
					AutoComplete(book);
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}});
    	
    	bItemInfoTF.setEnabled(false);
    	bSubtotalTF.setEditable(false);
    	
        bPanel.add(bNumItemsTF);
        bPanel.add(bIDTF);
        bPanel.add(bQuantityTF);
        bPanel.add(bItemInfoTF);
        bPanel.add(bSubtotalTF);
    }
	
	//Initialize JButtons
	private static void InitializeButtons(){
    	bProcessItem = new JButton("Process Item #"+String.valueOf(currentOrderNumber)+"");
    	bConfirmItem = new JButton("Confirm Item #"+String.valueOf(currentOrderNumber)+"");
    	bViewOrder = new JButton("View Order");
    	bFinishOrder = new JButton("Finish Order");
    	bNewOrder = new JButton("New Order");
    	bExit = new JButton("Exit");
    	
    	bProcessItem.setEnabled(false);
    	
    	bProcessItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bookStoreAction.ProcessOrder(book, Integer.valueOf(bQuantityTF.getText()), Integer.valueOf(bNumItemsTF.getText()));
				if(currentOrderNumber < Integer.valueOf(bNumItemsTF.getText())){
					bNumItemsTF.setEditable(false);
					bNumItemsTF.setEditable(false);
					bConfirmItem.setEnabled(true);
					bProcessItem.setEnabled(false);
					
					bIDTF.setText("");
					bQuantityTF.setText("");
					currentOrderNumber++;
					RedrawLabels();
				}else{
					bConfirmItem.setEnabled(false);
					bProcessItem.setEnabled(false);
				}
			}});
    	bConfirmItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(bPanel,"Item #"+String.valueOf(currentOrderNumber)+" accepted");
				bNumItemsTF.setEditable(false);
				bProcessItem.setEnabled(true);
				bConfirmItem.setEnabled(false);
			}});
    	bViewOrder.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(bPanel, bookStoreAction.getDisplayOrder());
			}});
    	bFinishOrder.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(bPanel, bookStoreAction.DisplayInvoice());
				bookStoreAction.WriteInvoice();
				NewOrderClick();
			}});
    	bNewOrder.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				NewOrderClick();
			}});
    	bExit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bookStoreWindow.dispose();
			}});
    	
    	bPanel.add(bProcessItem);
        bPanel.add(bConfirmItem);
        bPanel.add(bViewOrder);
        bPanel.add(bFinishOrder);
        bPanel.add(bNewOrder);
        bPanel.add(bExit);
    }
    
	
	//Position the objects in the JPanel using SpringLayout
	private static void InitializePlacement(){
		bLayout = new SpringLayout();
		bLayout.putConstraint(SpringLayout.NORTH,bNumItemsL, 5, SpringLayout.NORTH,bPanel);
		bLayout.putConstraint(SpringLayout.NORTH,bNumItemsTF, 5, SpringLayout.NORTH,bPanel);
		bLayout.putConstraint(SpringLayout.WEST, bNumItemsL, 5, SpringLayout.WEST, bPanel);
		bLayout.putConstraint(SpringLayout.WEST, bNumItemsTF, 5, SpringLayout.EAST, bNumItemsL);
		
		bLayout.putConstraint(SpringLayout.NORTH,bIDL, 7, SpringLayout.SOUTH,bNumItemsL);
		bLayout.putConstraint(SpringLayout.WEST, bIDL, 5, SpringLayout.WEST, bPanel);
		bLayout.putConstraint(SpringLayout.NORTH, bIDTF, 5, SpringLayout.SOUTH, bNumItemsTF);
		bLayout.putConstraint(SpringLayout.WEST, bIDTF, 5, SpringLayout.EAST, bIDL);
		
		bLayout.putConstraint(SpringLayout.NORTH,bQuantityL, 10, SpringLayout.SOUTH,bIDL);
		bLayout.putConstraint(SpringLayout.WEST, bQuantityL, 5, SpringLayout.WEST, bPanel);
		bLayout.putConstraint(SpringLayout.NORTH, bQuantityTF, 5, SpringLayout.SOUTH, bIDTF);
		bLayout.putConstraint(SpringLayout.WEST, bQuantityTF, 5, SpringLayout.EAST, bQuantityL);
		
		bLayout.putConstraint(SpringLayout.NORTH,bItemInfoL, 10, SpringLayout.SOUTH,bQuantityL);
		bLayout.putConstraint(SpringLayout.WEST, bItemInfoL, 5, SpringLayout.WEST, bPanel);
		bLayout.putConstraint(SpringLayout.NORTH, bItemInfoTF, 5, SpringLayout.SOUTH, bQuantityTF);
		bLayout.putConstraint(SpringLayout.WEST, bItemInfoTF, 5, SpringLayout.EAST, bItemInfoL);
		
		bLayout.putConstraint(SpringLayout.NORTH,bSubtotalL, 10, SpringLayout.SOUTH,bItemInfoL);
		bLayout.putConstraint(SpringLayout.WEST, bSubtotalL, 5, SpringLayout.WEST, bPanel);
		bLayout.putConstraint(SpringLayout.NORTH, bSubtotalTF, 5, SpringLayout.SOUTH, bItemInfoTF);
		bLayout.putConstraint(SpringLayout.WEST, bSubtotalTF, 5, SpringLayout.EAST, bSubtotalL);
		
		bLayout.putConstraint(SpringLayout.NORTH, bProcessItem, 10, SpringLayout.SOUTH, bSubtotalL);
		bLayout.putConstraint(SpringLayout.NORTH, bConfirmItem, 10, SpringLayout.SOUTH, bSubtotalL);
		bLayout.putConstraint(SpringLayout.NORTH, bViewOrder, 10, SpringLayout.SOUTH, bSubtotalL);
		bLayout.putConstraint(SpringLayout.NORTH, bFinishOrder, 10, SpringLayout.SOUTH, bSubtotalL);
		bLayout.putConstraint(SpringLayout.NORTH, bNewOrder, 10, SpringLayout.SOUTH, bSubtotalL);
		bLayout.putConstraint(SpringLayout.NORTH, bExit, 10, SpringLayout.SOUTH, bSubtotalL);
		
		bLayout.putConstraint(SpringLayout.WEST, bProcessItem, 10, SpringLayout.WEST, bPanel);
		bLayout.putConstraint(SpringLayout.WEST, bConfirmItem, 10, SpringLayout.EAST, bProcessItem);
		bLayout.putConstraint(SpringLayout.WEST, bViewOrder, 10, SpringLayout.EAST, bConfirmItem);
		bLayout.putConstraint(SpringLayout.WEST, bFinishOrder, 10, SpringLayout.EAST, bViewOrder);
		bLayout.putConstraint(SpringLayout.WEST, bNewOrder, 10, SpringLayout.EAST, bFinishOrder);
		bLayout.putConstraint(SpringLayout.WEST, bExit, 10, SpringLayout.EAST, bNewOrder);
		
		bPanel.setLayout(bLayout);
	}
	
	//Auto fill if necessary
	private static void AutoComplete(Book book){
    	bItemInfoTF.setText(book.getInfo());
    	if(!bQuantityTF.getText().isEmpty())
    		bSubtotalTF.setText(String.valueOf(bookStoreAction.getDisplaySubtotal(book, Integer.valueOf(bQuantityTF.getText()))));
    	else{
    		bQuantityTF.setText("1");
    		bSubtotalTF.setText(String.valueOf(bookStoreAction.getDisplaySubtotal(book,1)));
    	}
    }
	
	//Update number of labels based on current order number
	private static void RedrawLabels(){
    	bProcessItem.setText("Process Item #"+String.valueOf(currentOrderNumber)+"");
    	bConfirmItem.setText("Confirm Item #"+String.valueOf(currentOrderNumber)+"");
    	bIDL.setText("Enter Book ID for item #"+String.valueOf(currentOrderNumber)+":");
    	bQuantityL.setText("Enter quantity for item #"+String.valueOf(currentOrderNumber)+":");
    	bItemInfoL.setText("Item #"+String.valueOf(currentOrderNumber)+" info:");
    	bSubtotalL.setText("Order subtotal for "+String.valueOf(currentOrderNumber-1)+" item(s):");
    }
	
	//Reinitialize BookStoreAction for new order
	private static void NewOrderClick(){
		new BookStoreWindow().StartThread();
    	currentOrderNumber = 1;
		
    	bIDTF.setText("");
		bQuantityTF.setText("");
		bItemInfoTF.setText("");
    	bSubtotalTF.setText("");
		bNumItemsTF.setText("");
		
		RedrawLabels();
		
		bNumItemsTF.setEditable(true);
		bConfirmItem.setEnabled(true);
		bProcessItem.setEnabled(false);
    }

	
	//Define new thread to execute the initialization of Book Store Action on its own thread
	class NewThread extends Thread{
		@Override
		public void run() {
			bookStoreAction = new BookStoreAction();
		}
	}
	
	//Start the thread.
	private void StartThread() {
		new NewThread().start();
	}
}
