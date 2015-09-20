package hw_6;

import java.util.*;
import java.io.File;

import big.data.*;

/**
 * A simple class to implement a library of Book objects.<p> 
 * 109051837<p>
 * Assignment 6 task 2<p>
 * CSE 214 R05<p>
 * Vyassa Baratham (Recitation TA)<p>
 * Ke Ma (Grading TA)<p>
 * @author Zhaoqi Li<p>
 *
 */
@SuppressWarnings("serial")
public class HashedLibrary implements java.io.Serializable{
	
	private Hashtable<String, Book> table;
	
	/**
	 * An default constructor which create a Hashtable to store Book objects using their isbn as keys.
	 */
	public HashedLibrary(){
		table = new Hashtable<String, Book>();
	}
	
	/**
	 * Return the Hashtable which stores Book objects.
	 * @return
	 */
	public Hashtable<String, Book> getTable(){
		return table;
	}
	
	/**
	 * Add a book object to the library by using the Hashtable put method.<p>
	 * Precondition: The book to be recorded should have a unique isbn. 
	 * @param title The title of the book to be recorded.
	 * @param author The author of the book to be recorded.
	 * @param publisher The publisher of the book to be recorded. 
	 * @param isbn The isbn of the book to be recorded.
	 * @throws IllegalArgumentException indicates that the book to be recorded has already existed in the library.
	 */
	public void addBook(String title, String author, String publisher, String isbn) throws IllegalArgumentException{
		if(table.containsKey(isbn))
			throw new IllegalArgumentException(isbn + " is already in the database...");
		else{
			Book temp = new Book(title, author, publisher, isbn);
			table.put(isbn, temp);
			System.out.printf("%s: %s by %s recorded.\n", isbn, title, author);
		}
	}
	
	/**
	 * Add a series of books to the library by reading the title from a file then connecting to a database for more info.
	 * @param fileName The file that contains the names of books to be recorded.
	 * @throws Exception indicates errors in file input.
	 */
	public void addAllBookInfo(String fileName) throws Exception{
		File bookList = new File(fileName);
		Scanner std = new Scanner(bookList);
		while(std.hasNextLine()){
			String data = std.nextLine();
			DataSource ds = DataSource.connect("http://www.cs.stonybrook.edu/~cse214/hw/hw6/" + data + ".xml").load();
			String title = ds.fetchString("title");
			String author = ds.fetchString("author");
			String publisher = ds.fetchString("publisher");
			String isbn = ds.fetchString("isbn");
			try{
				addBook(title, author, publisher, isbn);
			}catch(Exception e){
				System.out.println("ISBN " + isbn + " has already been added to the database...");
			}
		}
		std.close();
	}
	/**
	 * Receive a book from the library using the user-supplied book isbn.
	 * @param isbn The isbn of the book to look for.
	 * @return the found Book object with the provided isbn, null if not found.
	 */
	public Book getBookByisbn(String isbn){
		return (Book)table.get(isbn);
	}
	
	/**
	 * Print all the information of all the books in the library in a tabular format.
	 */
	public void printCatalog(){
		Set<String> keys = this.getTable().keySet();
		for(String key: keys)
			System.out.println(this.getBookByisbn(key));
	}
/*	
	public static void main(String[] args){
		HashedLibrary library = new HashedLibrary();
		try {
			library.addAllBookInfo("book_list1.txt");
			library.addAllBookInfo("book_list2.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Book ISBN       Title                "+
					"                Author                          Publisher\n"+
						"--------------------------------------------------------"
						+ "------------------------------------------------------------------");
		library.printCatalog();
	}
	
*/
	
}
