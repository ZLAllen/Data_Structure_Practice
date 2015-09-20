	package hw1;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * A simple program to test the data structure and methods defined
 * in the previous classes. <p>
 * Zhaoqi Li<p>
 * 109051837<p>
 * Assignment 1 task 2<p>
 * CSE 214 R05<p>
 * Vyassa Baratham (Recitation TA)<p>
 * Ke Ma (Grading TA)<p>
 */
public class CollectionManager {
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		int position;
		BaseballCard b;
		String collection_choice = "";
		CardCollection A = new CardCollection();
		CardCollection B = new CardCollection();
		String menu = "\nCard Collection Manager Menu\n\n"+
				"* Add Card  --------------------- (A) \n" +
				"* Get Card  --------------------- (G) \n" +
				"* Remove Card  ------------------ (R) \n" +
				"* Print All Card  --------------- (P) \n" +
				"* Look for Card  ---------------- (L) \n" +
				"* Size  ------------------------- (S) \n" +
				"* Value  ------------------------ (V) \n" +
				"* Update Name  ------------------ (N) \n" +
				"* Update Price  ----------------- (E) \n" +
				"* Copy Card  -------------------- (C) \n" +
				"* Trade  ------------------------ (T) \n" +
				"* Quit  ------------------------- (Q) \n" ;
		String menuChoice = "";
		BaseballCard newCard = new BaseballCard();
		do{	
			System.out.printf("%s\n",menu);
			System.out.printf("Select an operation: ");
			menuChoice = input.next().toUpperCase();
			input.nextLine();
			switch(menuChoice){
			case "A":
				try{
					newCard = construct();
					System.out.println("You have Created a new baseball card!");
					System.out.printf("Now choose the collection to add to (A / B): ");
					collection_choice = input.next().toUpperCase();
					if(collection_choice.equals("A")){
						System.out.printf("Enter the position of the card in the collection"
								+ "(Item currently in list: %d)\n(Warning: You can't add the card to position"
								+" larger than %d): ",A.size(),A.size()+1);
						position = input.nextInt();
						A.addCard(newCard,position);
					}else{
						System.out.printf("Enter the position of the card in the collection\n"
								+ "(Item currently in list: %d)\n(Warning: You can't add the card to position"
								+" larger than %d): ",B.size(),B.size()+1);
						position = input.nextInt();
						B.addCard(newCard,position);
					}
					System.out.printf("Successfully added!\n");
					System.out.printf("#  Name\t\t\tManufacturer  Year  Price   Size\n" +
							"-- ----\t\t\t------------  ----  -----   ----\n"+
							"%-3d%-21s%-14s%-6d%-8.2f%dx%d\n",position,newCard.getName(),newCard.getManufacturer(),
							newCard.getYear(),newCard.getPrice(),newCard.getSizeX(),newCard.getSizeY());						
					System.out.printf("Enter B to return to the main menu\n");
				}catch(Exception e){
					e.printStackTrace();
					System.out.printf("\nAn error occurs, Enter B to reach Main menu\n");			
				}
				while(!input.nextLine().equals("B"));
				break;
			case "G":
				try{
					System.out.printf("Choose the collection that you want to obtain card (A / B): ");
					collection_choice = input.next();
					System.out.printf("Enter the position: ");
					position = input.nextInt();
					if(collection_choice.equals("A"))
						b = A.getCard(position);
					else
						b = B.getCard(position);
					System.out.printf("#  Name\t\t\tManufacturer  Year  Price   Size\n" +
							"-- ----\t\t\t------------  ----  -----   ----\n");
					System.out.printf("%-3d%-21s%-14s%-6d%-8.2f%dx%d\n",position,b.getName(),b.getManufacturer(),
							b.getYear(),b.getPrice(),b.getSizeX(),b.getSizeY());
					System.out.printf("Enter B to return to the main menu\n");
				}catch(Exception e){
					e.printStackTrace();
					System.out.printf("\nAn error occurs, Enter B to return to the main menu\n");
				}
				while(!input.nextLine().equals("B"));
				break;
			case "R":
				try{
					System.out.printf("Choose the collection that you want to remove the card (A / B): ");
					collection_choice = input.nextLine();
					System.out.printf("Enter the position: ");
					position = input.nextInt();
					if(collection_choice.equals("A")){
						A.printACard(position);
						A.removeCard(position);
						System.out.printf("Successfully removed from collection A!\n");
					}else{
						B.printACard(position);
						B.removeCard(position);
						System.out.printf("Successfully removed from collection B!\n");
					}
					System.out.printf("Enter B to return to the main menu\n");
				}catch(Exception e){
					e.printStackTrace();
					System.out.printf("\nAn error occurs, Enter B to return to the main menu\n");
				}
				while(!input.nextLine().equals("B"));
				break;
			case "P":
				try{
					System.out.printf("Collection A\n");
					A.printAllCards();
					System.out.printf("\nCollection B\n");
					B.printAllCards();
					System.out.printf("Enter B to return to the main menu\n");
				}catch(Exception e){
					e.printStackTrace();
					System.out.printf("\nAn error occurs, Enter B to return to the main menu\n");
				}
				while(!input.nextLine().equals("B"));
				break;
			case "L":
				try{
					newCard = construct();
					if(A.exists(newCard))
						System.out.printf("Collection A has this card\n");
					else if(B.exists(newCard))
						System.out.printf("Collection B has this card\n");
					else
						System.out.printf("This card does not exist at either collection");
					System.out.printf("Enter B to return to the main menu\n");
				}catch(Exception e){
					e.printStackTrace();
					System.out.printf("\nAn error occurs, Enter B to return to the main menu\n");
				}
				while(!input.nextLine().equals("B"));
				break;
			case "S":
				System.out.printf("Collection A has %d cards\n"+ "Collection B has %d cards\n",A.size(),B.size());
				System.out.printf("Enter B to return to the main menu\n");
				while(!input.nextLine().equals("B"));
				break;
			case "V":
				double sumA,sumB;
				sumA = sumB = 0;
				if(A.size() == 0)
					sumA = 0;
				else{
					for(int i=1;i<=A.size();i++)
						sumA += A.getCard(i).getPrice();
				}
				if(A.size() == 0)
					sumB = 0;
				else{
					for(int i=1;i<=B.size();i++)
						sumB += B.getCard(i).getPrice();
				}
				System.out.printf("The total price of collection A is %.2f\n"+
						"The total price of collection B is %.2f\n",sumA,sumB);
				System.out.printf("Enter B to return to the main menu\n");
				while(!input.nextLine().equals("B"));
				break;
			case "N":
				String newName;
				String oldName;
				try{
					System.out.printf("Choose the collection to update name (A / B): ");
					collection_choice = input.next().toUpperCase();
					System.out.printf("Enter the position of the card: ");
					position = input.nextInt();
					input.nextLine();
					System.out.printf("Enter the new Name of the card: ");
					newName = input.nextLine();
					if(collection_choice.equals("A")){
						oldName = A.getCard(position).getName();
						A.getCard(position).setName(newName);
					}else{
						oldName = A.getCard(position).getName();
						B.getCard(position).setName(newName);
					}
					System.out.printf("Sucessfully update the name from %s to %s in collection %s\n", oldName, newName,
										collection_choice);
					System.out.printf("Enter B to return to the main menu\n");
				}catch(Exception e){
					e.printStackTrace();
					System.out.printf("\nAn error occurs, Enter B to return to the main menu\n");
				}
				while(!input.nextLine().equals("B"));
				break;
			case "E":
				double newPrice;
				double oldPrice;
				try{
					System.out.printf("Choose the collection to update price (A / B): ");
					collection_choice = input.next().toUpperCase();
					System.out.printf("Enter the position of the card: ");
					position = input.nextInt();
					System.out.printf("Enter the new price of the card: ");
					newPrice = input.nextDouble();
					input.nextLine();
					if(collection_choice.equals("A")){
						oldPrice = A.getCard(position).getPrice();
						A.getCard(position).setPrice(newPrice);
					}else{
						oldPrice = B.getCard(position).getPrice();
						B.getCard(position).setPrice(newPrice);
					}
					System.out.printf("Sucessfully update the price from %.2f to %.2f in collection %s\n",
									oldPrice, newPrice, collection_choice);
					System.out.printf("Enter B to return to the main menu\n");
				}catch(Exception e){
					e.printStackTrace();
					System.out.printf("\nAn error occurs, Enter B to return to the main menu\n");
				}
				while(!input.nextLine().equals("B"));
				break;
			case "C": 
				String copyFrom,copyTo;
				try{
					System.out.printf("Choose the collection to copy from (A / B): ");
					copyFrom = input.next().toUpperCase();
					System.out.printf("Enter the position of the card: ");
					position = input.nextInt();
					System.out.printf("Choose the destination collection (A / B): ");
					copyTo = input.next().toUpperCase();
					if(copyFrom.equals("A"))
						newCard = (BaseballCard)A.getCard(position).clone();
					else if(copyFrom.equals("B"))
						newCard = (BaseballCard)B.getCard(position).clone();
					else
						throw new IllegalArgumentException();
					if(copyTo.equals("A"))
						A.addCard(newCard);
					else if(copyTo.equals("B"))
						B.addCard(newCard);
					else
						throw new IllegalArgumentException();
					System.out.printf("Successfully copy\n");
					System.out.printf("%-21s%-14s%-6d%-8.2f%dx%d\n",newCard.getName(),newCard.getManufacturer(),
							newCard.getYear(),newCard.getPrice(),newCard.getSizeX(),newCard.getSizeY());
					System.out.printf("From collection %s to collection %s\n",copyFrom,copyTo);
					System.out.printf("Enter B to return to the main menu\n");
				}catch(Exception e){
					e.printStackTrace();
					System.out.printf("\nAn error occurs, Enter B to return to the main menu\n");
				}
				while(!input.nextLine().equals("B"));
				break;
			case "T":
				int positionA, positionB;
				try{
					System.out.printf("Enter the position of the card in collection A: ");
					positionA = input.nextInt();
					System.out.printf("Enter the position of the card in collection B: ");
					positionB = input.nextInt();
					A.trade(B, positionA, positionB);
					System.out.printf("Successfully trade\n");
					B.printACard(positionB);
					System.out.printf("For\n");
					A.printACard(positionA);
					System.out.printf("Enter B to return to the main menu\n");
				}catch(Exception e){
					e.printStackTrace();
					System.out.printf("\nAn error occurs, Enter B to return to the main menu\n");
				}
				while(!input.nextLine().equals("B"));
				break;
			case "Q":
				break;
			}
		}while(!menuChoice.equals("Q"));
		System.out.println("Goodbye!");
	}
/**
 * A simple program to create an BaseballCard Object according to the user inputs
 * @return A user-defined BaseballCard Object for further application
 * @throws InputMismatchException input data does not match the expected data type;
 * @throws IllegalArgumentException input price is less than 0;
 */
	public static BaseballCard construct() throws InputMismatchException,IllegalArgumentException{
		Scanner input = new Scanner(System.in);
		BaseballCard newCard = new BaseballCard();
		System.out.printf("\nEnter Name of the player: ");
		newCard.setName(input.nextLine());
		System.out.printf("Enter the Manufacturer of the card: ");
		newCard.setManufacturer(input.nextLine());
		System.out.printf("Enter the Year of the card: ");
		newCard.setYear(input.nextInt());
		System.out.printf("Enter the price of the card: ");
		newCard.setPrice(input.nextDouble());
		System.out.printf("Enter the Size of x-axis of the image: ");
		newCard.setSizeX(input.nextInt());
		System.out.printf("Enter the Size of y-axis of the image: ");
		newCard.setSizeY(input.nextInt());
		return newCard;
	}
}
