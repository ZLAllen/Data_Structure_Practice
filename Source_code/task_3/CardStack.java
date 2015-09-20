package hw_3;
import java.util.Stack;
/**
 * A simple class which creates a collection of card objects using the Stack class <p> 
 * Zhaoqi Li<p>
 * 109051837<p>
 * Assignment 3 task 2<p>
 * CSE 214 R05<p>
 * Vyassa Baratham (Recitation TA)<p>
 * Ke Ma (Grading TA)<p>
 */
public class CardStack{
	/**
	 * Data Field for the Card stack<p>
	 * type(char): specify the type of the Card stack created.<p>
	 * size(int): keep tracking the number of objects inside the stack.<p>
	 * newStack(Stack<Card>): declare a new stack to store the card objects.
	 */
	private char type;
	private int size;
	Stack<Card> newStack;
	
	/**
	 * A constructor that defines a new Card Stack with no type restriction.
	 */
	public CardStack(){
		newStack = new Stack<Card>();
	}
	/**
	 * A constructor that defines a new Card Stack with a specified type.
	 * <dt>Precondition</dt> the valid types are 's' for stock, 't' for tableau, 'f' for foundation, 'w' for waste.<p>
	 * @param type the type of the stack.
	 */
	public CardStack(char type){
		this.type = type;
		newStack = new Stack<Card>();
	}
	/**
	 * Push the given parameter into the card stack using the stack method.
	 * <dt>Postcondition</dt> the size of the stack will be incremented by 1 after the push.<p>
	 * @param newCard the new card object to be pushed into the stack.
	 */
	public void push(Card newCard){
		newStack.push(newCard);
		size ++;
	}
	/**
	 * Push the given parameter into the card stack by calling the previously defined push method. <p>
	 * However, the adding of a new card will be restricted by the type of the stacks
	 * <dt>Precondition</dt> type 't': Only King is allowed to be pushed into an empty stack, throws exception otherwise.
	 * if the stack is not empty, the new card to be pushed into the stack must have a different color from that of the top
	 * card and be 1 less than the value of the top card, throws exception otherwise<p>
	 * type 'f': Only Ace is allowed to be pushed into an empty stack, throws exception otherwise.
	 * if the stack is not empty, the new card to be pushed into the stack must have the same suit as that of the top
	 * card and be 1 larger than the value of the top card, throws exception otherwise<p>									 
	 * @param newCard the new card object to be pushed into the stack.
	 * @throws IllegalArgumentException indicates that adding the new card object violates the rules of the game.
	 */
	public void inGamePush(Card newCard) throws IllegalArgumentException{
		Card temp;
		switch(type){
			case 's':
			case 'w':
				push(newCard);
				break;
			case 't':
				if(newStack.isEmpty()){
					if(newCard.getValue() != 13)
						throw new IllegalArgumentException("Only King can be the first card of an empty tableau pile");
				}else{
					temp = newStack.peek();
					if(temp.isRed() == newCard.isRed() || temp.getValue() - 1 != newCard.getValue()){
						throw new IllegalArgumentException("This card cannot be added to this pile!");}
				}
				push(newCard);
				break;
			case 'f':
				if(newStack.isEmpty()){
					if(newCard.getValue() != 1)
						throw new IllegalArgumentException("Only Ace can be the first card of an empty foundation pile");
				}else{
					temp = newStack.peek();
					if(temp.getSuit() != newCard.getSuit() || (temp.getValue() + 1 != newCard.getValue()))
						throw new IllegalArgumentException("This card cannot be added to this pile!");
				}
				newCard.setFaceUp(true);
				push(newCard);
		}
	}
	/**
	 * Receive the card on top of the stack using the pop method of Stack class
	 * @return Card object popped by the stack.
	 */
	public Card pop(){
		Card newStackCard = newStack.pop();
		size --;
		return newStackCard;
	}
	/**
	 * Determine whether the stack is empty.
	 * @return true if the stack is empty, false otherwise.
	 */
	public boolean isEmpty(){
		return newStack.empty();
	}
	/**
	 * Receive the number of card objects in the stack.
	 * @return size as an integer.
	 */
	public int size(){
		return size;
	}
	/**
	 * Receive the top card object of the stack without popping it using the peek method of the stack class.
	 * @return Card object peeked by the stack.
	 */
	public Card peek(){
		return newStack.peek();
	}
	/**
	 * Print the information about card objects in the stack, the print manner will vary by type of the stack.<p>
	 * Type 's': Print the top card of the stack facing down - [XX]<p>
	 * Type 'w': Print the top card of the stack facing up - [9♠]<p>
	 * Type 't': Print the whole stack on a line - [XX][XX][XX][4♠][3♥]<p>
	 * Type 'f': Print the top card of the stack facing up - [A♥]
	 */
	public void printStack(){
		switch(type){
			case 's':
				System.out.printf("%-6s","[XX]");
				break;
			case 'w':
				if(newStack.isEmpty())
					System.out.print("[  ]");
				else{
					newStack.peek().setFaceUp(true);
					System.out.printf("%-6s", newStack.peek());
				}
				break;
			case 't':
				if(newStack.isEmpty())
					System.out.print("[  ]");
				else{
					Stack<Card> temp = new Stack<Card>();
					newStack.peek().setFaceUp(true);
					while(!newStack.isEmpty())
						temp.push(newStack.pop());
					while(!temp.isEmpty()){
						if(temp.peek().isFaceUp())
							System.out.printf("%-6s", temp.peek());
						else
							System.out.printf("%-6s","[XX]");
							newStack.push(temp.pop());
						}
				}
				break;
			case 'f':
				System.out.printf("%-6s", newStack.peek());
				break;				
		}
	}
}
