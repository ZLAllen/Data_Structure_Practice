package hw_6;
/**
 * A simple class to implement a book.<p> 
 * 109051837<p>
 * Assignment 6 task 1<p>
 * CSE 214 R05<p>
 * Vyassa Baratham (Recitation TA)<p>
 * Ke Ma (Grading TA)<p>
 * @author Zhaoqi Li<p>
 *
 */
@SuppressWarnings("serial")
public class Book implements java.io.Serializable{
	private String title, author, publisher, isbn;
	/**
	 * Create an empty Book object.
	 */
	public Book(){}
	
	/**
	 * Create a Book object with initialized data field.
	 * @param title	The title of the book.
	 * @param author The author of the book.
	 * @param publisher	The publisher of the book. 
	 * @param isbn The isbn number of the book
	 */
	public Book(String title, String author, String publisher, String isbn){
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.isbn = isbn;
	}
	/**
	 * Return the title of the book.
	 * @return Title as a String
	 */
	public String getTitle(){
		return title;
	}
	
	/**
	 * Change the title of the book.
	 * @param title the new title of the book.
	 */
	public void setTitle(String title){
		this.title = title;
	}
	
	/**
	 * Return the author of the book.
	 * @return author as a String.
	 */
	public String getAuthor(){
		return author;
	}
	
	/**
	 * Change the author of the book.
	 * @param author the new author of the book.
	 */
	public void setAuthor(String author){
		this.author = author;
	}
	
	/**
	 * Return the publisher of the book.
	 * @return publisher as a String.
	 */
	public String getPublisher(){
		return publisher;
	}
	
	/**
	 * Change the publisher of the book.
	 * @param publisher the new publisher of the book.
	 */
	public void setPublisher(String publisher){
		this.publisher = publisher;
	}
	
	/**
	 * Return the isbn of the book.
	 * @return isbn as a String.
	 */
	public String getISBN(){
		return isbn;
	}
	
	/**
	 * Change the isbn of the book.
	 * @param isbn the new isbn of the book.
	 */
	public void setISBN(String isbn){
		this.isbn = isbn;
	}
	
	/**
	 * Return a String describing the isbn, title, author, and publisher of the current book object.
	 */
	public String toString(){
		return String.format("%-16s%-38s%-32s%-30s", isbn, title, author, publisher);
	}
	
}
