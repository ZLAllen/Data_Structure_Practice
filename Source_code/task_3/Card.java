package hw_3;
/**
 * A simple class which creates a card object with access to its suit, value and face up or down <p> 
 * Zhaoqi Li<p>
 * 109051837<p>
 * Assignment 3 task 1<p>
 * CSE 214 R05<p>
 * Vyassa Baratham (Recitation TA)<p>
 * Ke Ma (Grading TA)<p>
 */
public class Card {
	/**
	 * Data field for the Card Object<p>
	 * Suit (int): symbol of the card :{' ', '♦', '♣','♥', '♠'}  <p>
	 * Value (int): value of the card {" ","A","2","3","4","5","6","7","8","9","10","J","Q","K"}<p>
	 * faceUp (boolean): the status of card (face up or down) <p>
	 * values[] (String array): table lookup for value of the card, containing a dummy head.<p>
	 * suits[] (char array): table lookup for the suit of the card, containing a dummy head.<p>
	 */
	private int suit;
	private int value;
	private boolean faceUp;
	private static String values[] = {" ","A","2","3","4","5","6","7","8","9","10","J","Q","K"};
	private static char suits[]    = {' ', '\u2666', '\u2663','\u2665', '\u2660'};   // {' ', '♦', '♣','♥', '♠'}
	
	
	public Card(){}
/**
 * Create a Card object and assign the given parameters to the data field.
 * <dt>Precondition</dt> Value ranges from 1 to 13. <p> suit ranges from 1 to 4.<p> faceUp takes only boolean value.	
 * @param value the value of the card.
 * @param suit  the symbol of the card.
 * @param faceUp determine whether the card is facing up or facing down.
 */
	public Card(int value, int suit, boolean faceUp){
		this.suit = suit;
		this.value = value;
		this.faceUp = faceUp;
	}
	/**
	 * Receive the suit of the card.
	 * @return suit as an integer.
	 */
	public int getSuit(){
		return suit;
	}
	/**
	 * Change the suit of the card to the given suit.
	 * <dt>Precondition</dt> 0 < suit < 5.
	 * @param suit the new suit of the card.
	 * @throws IllegalArgumentException indicates that the given suit is not in the valid range.
	 */
	public void setSuit(int suit) throws IllegalArgumentException{
		if(suit < 1 || suit > 4)
			throw new IllegalArgumentException("There is no such suit");
		this.suit = suit;
	}
	/**
	 * Receive the value of the card.
	 * @return value as an integer.
	 */
	public int getValue(){
		return value;
	}
	/**
	 * Change the value of the card to the given value.
	 * <dt>Precondition</dt> 0 < value < 14.
	 * @param value the new value of the card.
	 * @throws IllegalArgumentException indicates that the given value is not in the valid range.
	 */
	public void setValue(int value) throws IllegalArgumentException{
		if(value < 1 || value > 13)
			throw new IllegalArgumentException("There is no such value");
		this.value = value;
	}
	/**
	 * Receive the status of the card (facing up or down). 
	 * @return faceUp as a boolean value.
	 */
	public boolean isFaceUp(){
		return faceUp;
	}
	/**
	 * Change the status of the change to the given parameter.
	 * @param faceUp the new status of the card.
	 */
	public void setFaceUp(boolean faceUp){
		this.faceUp = faceUp;
	}
	/**
	 * Receive whether the card is red or not.
	 * @return true if suit is odd, false otherwise.
	 */
	public boolean isRed(){
		return suit%2 == 1;
	}
	/**
	 * Return a string which includes the suit and value of the card.
	 */
	public String toString(){
		return String.format("[%s%c]",values[value], suits[suit]);
	}
}
