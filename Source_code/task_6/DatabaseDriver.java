package hw_6;

import java.io.*;
import java.util.Scanner;

/**
 * A simple driver class which provides a user interface to manage the library database.<p> 
 * 109051837<p>
 * Assignment 6 task 3<p>
 * CSE 214 R05<p>
 * Vyassa Baratham (Recitation TA)<p>
 * Ke Ma (Grading TA)<p>
 * @author Zhaoqi Li<p>
 *
 */
public class DatabaseDriver {
	final static String path_in =  "library.obj";
	final static String path_out = "library.obj";
	public static void main(String[] args){
		HashedLibrary myLibrary;
		String title, author, publisher, isbn;
		String selection = "";
		Scanner input = new Scanner(System.in);
		try{
			FileInputStream file_in = new FileInputStream(path_in);
			ObjectInputStream fin = new ObjectInputStream(file_in);
			myLibrary = (HashedLibrary) fin.readObject();
			fin.close();
			file_in.close();
			System.out.println("Database loaded from " + path_in);
		}catch(Exception e){
			System.out.println("library.obj is not found. Using a new HashedLibrary...");
			myLibrary = new HashedLibrary();
		}
		System.out.println("\n(D) Displays Books\n" +
				"(G) Get Book\n" +
				"(L) Load File\n" + 
				"(R) Record Book\n"+
				"(Q) Quit\n");
		do{
			try{
				System.out.print("\nEnter a selection: ");
				selection = input.next().toUpperCase();
				input.nextLine();
				System.out.println();
				switch(selection){
				case "D" :
					System.out.println("Book ISBN       Title                "+
							"                Author                          Publisher\n"+
							"--------------------------------------------------------"
							+ "------------------------------------------------------------------");
					myLibrary.printCatalog();
					break;
				case "G" :
					System.out.print("Enter Book ISBN: ");
					String key = input.next();
					Book temp = myLibrary.getBookByisbn(key);
					if(temp == null)
						System.out.println("Book of " + key + " does not exist in this library");
					else{
						System.out.println("Book ISBN       Title                "+
								"                Author                          Publisher\n"+
								"--------------------------------------------------------"
								+ "------------------------------------------------------------------");
						System.out.println(temp);
					}
					break;
				case "L" :
					System.out.print("Enter the file: ");
					String fileName = input.next();
					input.nextLine();
					myLibrary.addAllBookInfo(fileName);
					System.out.println();
					break;
				case "R" :
					System.out.print("Enter book title: ");
					title = input.nextLine();
					System.out.print("Enter book author: ");
					author = input.nextLine();
					System.out.print("Enter book publisher: ");
					publisher = input.nextLine();
					System.out.print("Enter book ISBN: ");
					isbn = input.next();
					input.nextLine();
					try{
						myLibrary.addBook(title, author, publisher, isbn);
					}catch (Exception e){
						System.out.println("ISBN " + isbn + " has already been added to the database...");
					}
					break;	

				case "Q":
					try {
						FileOutputStream file_out = new FileOutputStream(path_out);
						ObjectOutputStream fout = new ObjectOutputStream(file_out);
						fout.writeObject(myLibrary); 
						fout.close();
						file_out.close();
						System.out.println("HashedLibrary is saved into file "+ path_out);
					} catch (IOException e){
						System.out.println("Unable to write the library into the database, please confirm your data");
						System.out.println("Or enter q again to force quit...");
						e.printStackTrace();
						if(!(input.next().toUpperCase().equals("Q")))
							selection = "";
					}
					break;
				default:
					System.out.println("Invalid selection... please enter your selection again");
				}
			}catch(Exception e){
				System.out.println("Some errors occur, read the stack trace then enter B to reach the menu...");
				e.printStackTrace();
				while(!(input.next().toUpperCase().equals("B")));
			}
		}while(!selection.equals("Q"));
		System.out.println("Program terminating normally...");
		input.close();
	}
}
