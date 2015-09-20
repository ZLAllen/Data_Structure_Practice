package hw_5;
import java.util.Scanner;
import java.io.File;
/**
 * A kiosk class which provides a simple user interface for user to add menu, print menu, and start service sessions.<p> 
 * Zhaoqi Li<p>
 * 109051837<p>
 * Assignment 5 task 3<p>
 * CSE 214 R05<p>
 * Vyassa Baratham (Recitation TA)<p>
 * Ke Ma (Grading TA)<p>
 */
public class Kiosk {
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.print("L) Load a Tree\n"
				+ "P) Print menu\n"
				+ "S) Start service\n"
				+ "Q) Quit\n");
		String choice = "";
		Tree menu = new Tree();
		String name, selection, message, target;
		int number;
		do{
			try{
				System.out.print("\nChoice: ");
				choice = input.next().toUpperCase();
				input.nextLine();
				switch(choice){
				case "L":
					System.out.print("Enter the name of the file: ");
					File fileName = new File(input.nextLine());
					Scanner file = new Scanner(fileName);
					name = file.nextLine();
					selection = file.nextLine();
					message = file.nextLine();
					menu.setRoot(new TreeNode(name, selection, message));
					while(file.hasNextLine()){
						target = file.next();
						number = file.nextInt();
						file.nextLine();
						for(int i = 0; i < number; i++){
							name = file.nextLine();
							selection = file.nextLine();
							message = file.nextLine();
							if(!menu.addNode(name, selection, message, target))
								throw new IllegalArgumentException("Illegal data formate...");
						}
					}
					System.out.println("Menu loading completed...");
					break;
				case "P":
					System.out.println("Menu: \n" 
							+"Dining          Selection         		                           Price\n"
							+"-----------------------------------------------------------------------------------");
					if(menu.getRoot().getLeft() != null)
						menu.printMenu(menu.getRoot().getLeft().getName());
					if(menu.getRoot().getMiddle() != null)
						menu.printMenu(menu.getRoot().getMiddle().getName());
					if(menu.getRoot().getRight() != null)
						menu.printMenu(menu.getRoot().getRight().getName());
					break;
				case "S":
					System.out.println("Help session started...");
					menu.beginSession();
					break;
				case "Q":
					break;
				default:
					System.out.println("Invalid selection...");
				}
			}catch(Exception e){
				System.out.println("An error occur, Enter B to return to menu...");
				e.printStackTrace();
				while(!input.next().toUpperCase().equals("B"));
				input.nextLine();
			}
		}while(!choice.equals("Q"));
		System.out.println("Session terminated...");
		input.close();
	}
}
