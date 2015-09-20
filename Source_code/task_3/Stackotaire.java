package hw_3;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 * A simple class which provides the basic interface of the card game. <p>
 * <dt>In term of memory, there are 13 stacks and 1 arrayList in this program:</dt><p>
 * 	 <dt>tableau T1 ~ T7</dt> stored in the array tableau <p>
 * 	 <dt>foundation F1 ~ F4</dt> stored in the array foundation<p>
 *  <dt> stock</dt> store as CardStack stock.<p>
 *  <dt> waste</dt> store as CardStock waste. <p>
 *   <dt>deck</dt> store as ArrayList with 52 Card objects. <p> 
 *   
 *   <dt>Instruction:</dt><p> <dt>quit</dt><p> <dt>restart</dt><p> <dt>draw</dt>when stock is empty, all unused card in waste will be recycled back to stock 
 *   in an order<p>
 *  <dt> move</dt>from W1 to Fn, Tn to Fn, Fn to Tn, 2 arguments after the instruction <p>
 *   <dt>moven</dt>from Tn to Tn, 3 arguments after the instruction, the last argument represent the number of cards to move<p>
 *   <dt>C</dt> after each failure of operation, user must enter C to continue the game (in order to check exception or error)<p>
 *   
 *   Extra Credit : automove is available. Enter automove in command to toggle on and off<p>
 * @author Zhaoqi Li<p>
 * 109051837<p>
 * Assignment 3 task 3<p>
 * CSE 214 R05<p>
 * Vyassa Baratham (Recitation TA)<p>
 * Ke Ma (Grading TA)<p>
 */

public class Stackotaire {
	/**
	 * Globe data for all methods.
	 */
		static CardStack[] tableau;
		static CardStack[] foundation;
		static CardStack stock;
		static CardStack waste;
		static ArrayList <Card> deck;
	/**
	 * Main Function...
	 * @param args
	 */
		public static void main(String[] args){
			String[] command;
			String instruction, target, destination;
			int pile1, pile2;
			boolean automove = false;
			Card temp;
			CardStack tempStack = new CardStack();
			Scanner input = new Scanner(System.in);
			init_game();
			do{
				boolean result = check_win();
				if(automove){
					move_to_foundation();
				}
				System.out.println();
				display_game();
				if(result){
					System.out.println("You win! \nEnter commands to restart or quit");
				}
				System.out.print("\nEnter your command: ");
				command = input.nextLine().split(" ");
				instruction = command[0].toLowerCase();
				try{
					switch(instruction){
					case "draw":
						if(stock.isEmpty()){
							while(!waste.isEmpty())
								stock.push(waste.pop());
						}else
							waste.push(stock.pop());
						break;
					case "restart":
						System.out.print("Do you want to start a new game? (Y/N): ");
						if(input.next().toUpperCase().equals("Y")){
							System.out.println("\nSorry, you lose. Starting new game...\n");
							init_game();
						}
						input.nextLine();
						break;
					case "quit":
						System.out.print("Do you want to quit? (Y/N): ");
						if(input.next().toUpperCase().equals("Y"))
							System.out.println("\nSorry, you lose...");
						else
							instruction = "";
						input.nextLine();
						break;
					case "automove":
						automove = !automove;
						break;
					case "move":
						target = command[1].toLowerCase().charAt(0) + "";
						destination = command[2].toLowerCase().charAt(0) + "";
						pile1 = command[1].charAt(1) - '0';
						pile2 = command[2].charAt(1) - '0';
						switch(target){
						case "w" :
							temp = waste.peek();
							if(destination.equals("f")){
								foundation[pile2].inGamePush(temp);
								waste.pop();
							}
							else if(destination.equals("t")){
								tableau[pile2].inGamePush(temp);
								waste.pop();
							}else
								throw new IllegalArgumentException("No such instruction");
							break;
						case "t" :
							if(!destination.equals("f"))
								throw new IllegalArgumentException("No such instruction");
							temp = tableau[pile1].peek();
							if(temp.isFaceUp()){
								foundation[pile2].inGamePush(temp);
								tableau[pile1].pop();
							}else
								throw new IllegalArgumentException("Card facing down cannot be moved");
							break;
						case "f":
							if(!destination.equals("t"))
								throw new IllegalArgumentException("No such instruction");
							temp = foundation[pile1].peek();
							tableau[pile2].inGamePush(temp);
							foundation[pile1].pop();
							break;
						}
						break;
					case "moven":
						pile1 = command[1].charAt(1) - '0';
						pile2 = command[2].charAt(1) - '0';
						int num = command[3].charAt(0) - '0';
						for(int i=0; i< num;i++){
							tempStack.push(tableau[pile1].pop());
							if(!tempStack.peek().isFaceUp()){
								while(!tempStack.isEmpty())
									tableau[pile1].push(tempStack.pop());
								throw new IllegalArgumentException("Card facing down cannot be moved!");
							}
						}
						try{
							tableau[pile2].inGamePush(tempStack.peek());
							tableau[pile2].pop();
							while(!tempStack.isEmpty())
								tableau[pile2].push(tempStack.pop());
						}catch (Exception e){
							while(!tempStack.isEmpty())
								tableau[pile1].push(tempStack.pop());
							throw e;
						}
						break;
					default:
						System.out.println("Not a valid instruction...");
					}
				}catch(Exception e){
					System.out.println("\nAn error occurs, Enter C to continue to play the game.");
					e.printStackTrace();
					while(!input.next().toUpperCase().equals("C"));
					input.nextLine();
				}
			}while(!instruction.equals("quit"));
			
			System.out.println("\nProgram terminated...");	
			input.close();
			
		}
		/**
		 * Initialize game, create 52 Card objects in the deck, shuffle, and assign to the desired stacks
		 */
		private static void init_game(){
			
			tableau = new CardStack[8];
			foundation = new CardStack[5];
			stock = new CardStack('s');
			waste = new CardStack('w');
			deck = new ArrayList<Card>();
			
			for(int i= 1; i < 8; i++)
				tableau[i] = new CardStack('t');
			
			for(int i= 1; i < 5; i++)
				foundation[i] = new CardStack('f');
			
			for(int i = 1; i < 14; i++){
				for(int j = 1; j<5; j++)
					deck.add(new Card(i,j,false));
			}
			Collections.shuffle(deck);
			int count = 0;
			while (count < 28 ){
				for (int i = 1; i < 8; i++){
					for(int j = 1; j <= i; j++)
						tableau[i].push(deck.get(count++));
				}
			}
			for(int i = 28; i < 52; i++)
				stock.push(deck.get(i));
		}
		/**
		 * Print the basic interface of the game by calling the printStack method of each stack.
		 */
		private static void display_game(){
			for(int i = 1; i < 5; i++){
				if(foundation[i].isEmpty())
					System.out.printf("[F" + i + "]  ");
				else
					foundation[i].printStack();
			}
			System.out.print("\tW1");
			waste.printStack();
			System.out.print("  \t");
			stock.printStack();
			System.out.println(stock.size());
			for(int i = 7; i > 0; i--){
				System.out.print("T" + i + "  ");
				tableau[i].printStack();
				System.out.println();
			}
			
		}
		/**
		 * Check all cards in the deck to determine the victory.
		 * @return true if all cards are facing up, false otherwise.
		 */
		private static boolean check_win(){
			for(int i=0; i < deck.size(); i++){
				if(!deck.get(i).isFaceUp())
					return false;
			}
			return true;
		}
		/**
		 * implement the auto move function by attempting to add all face-up cards to each foundation.
		 */
		private static void move_to_foundation(){
			int repeat = 1;
			do{
				repeat = 0;
				for(int i = 1; i < 5; i++){
					try{
						foundation[i].inGamePush(waste.peek());
						waste.pop();
						System.out.println("move W1 F" + i);
					}catch(Exception e){}
					for(int j = 1; j < 8; j++){
						try{
							foundation[i].inGamePush(tableau[j].peek());
							tableau[j].pop();
							System.out.println("move T" + j + " F" + i);
							repeat ++ ;
						}catch (Exception e){}
					}
				}
			}while(repeat != 0);
		}
}
