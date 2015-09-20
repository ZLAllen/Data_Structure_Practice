package hw_2;
import java.util.Scanner;
/**
 * A simple class which to provide an interface to users to manipulate performances in a schedule.<p> 
 * Zhaoqi Li<p>
 * 109051837<p>
 * Assignment 2 task 2<p>
 * CSE 214 R05<p>
 * Vyassa Baratham (Recitation TA)<p>
 * Ke Ma (Grading TA)<p>
 *
 */
public class PerformanceScheduler {
	static Scanner input = new Scanner(System.in);
	public static void main(String[] args){
		String menuChoice = "";
		PerformanceList A = new PerformanceList();
		String menu = "Performance Scheduler\n"+
					  "A) Add to end\n"+
					  "B) Move current node backward\n"+ 
					  "C) Display current node\n"+
					  "D) Display all nodes\n"+
					  "F) Move current node forward\n"+
					  "I) Insert after current node\n"+
					  "J) Jump to position\n"+
					  "R) Remove current node\n"+
					  "Q) Exit";
		System.out.println(menu);
		do{
			System.out.print("\nSelect an operation: ");
			menuChoice = input.nextLine().toUpperCase();
			System.out.println();
			PerformanceNode newNode;
			try{
				switch(menuChoice){
				case "A":
					newNode = newPerformance();
					A.addToEnd(newNode);
					System.out.printf("New performance %s is added to the end of the list!\n", newNode.getPerformance());
					break;
				case "I":
					newNode = newPerformance();
					A.addAfterCurrent(newNode);
					System.out.printf("New performance %s is inserted after the current performance!\n", newNode.getPerformance());
					break;
				case "D":
					System.out.println("Schedule: \n");
					System.out.println(A);
					break;
				case "B":
					if(A.moveCursorBackward())
						System.out.println("Cursor has been moved backward");
					else
						System.out.println("Cursor reaches the beginning of the list!");
					break;
				case "C":
					A.displayCurrentPerformance();
					break;
				case "F":
					if(A.moveCursorForward())
						System.out.println("Cursor has been moved forward");
					else
						System.out.println("Cursor reaches the end of the list!");
					break;
				case "J":
					System.out.print("Enter the position: ");
					int position = input.nextInt();
					input.nextLine();
					if(A.jumpToPosition(position))
						System.out.printf("Curson has been moved to position %d\n", position);
					else
						System.out.println("The desired position does not exist in the list!");
					break;
				case "R":
					newNode = A.getCursor();
					if(A.removeCurrentNode())
						System.out.println("Performance " + newNode.getPerformance() + "has been removed!");
					else
						System.out.println("This is an empty list! ");
					break;
				case "Q":
					break;
				}
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("An error occur, Press E to return to the menu");
				while(!input.next().toUpperCase().equals("E"));
				input.nextLine();
			}
		}while(!menuChoice.equals("Q"));
		System.out.println("Program terminating normally...");
	}
/**
 * A helper method to create an performance object with user-supplied parameters.
 * @return A PerformanceNode Object created by the user will be returned to the main method.
 */
	private static PerformanceNode newPerformance(){
		PerformanceNode newNode = new PerformanceNode();
		System.out.print("Enter name of performance: ");
		newNode.setPerformance(input.nextLine());
		System.out.print("Enter name of lead performer: ");
		newNode.setPerformer(input.nextLine());
		System.out.print("Enter the total participants: ");
		newNode.setTotalPerformance(input.nextInt());
		System.out.print("Enter the duration of the performance: ");
		newNode.setDuration(input.nextDouble());
		input.nextLine();
		return newNode;
	}
}
