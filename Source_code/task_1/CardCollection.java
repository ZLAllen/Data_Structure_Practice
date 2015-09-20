package hw1;
/**
 * A simple class to store a list of BaseballCard Objects(up to 100) and 
 * provides an interface to interact with users. <p>
 * 
 * Zhaoqi Li<p>
 * 109051837<p>
 * Assignment 1 task 2<p>
 * CSE 214 R05<p>
 * Vyassa Baratham (Recitation TA)<p>
 * Ke Ma (Grading TA)<p>
 * 
 * @author Li
 *
 */
public class CardCollection {
	//Data Field
	private final static int MAX_CARDS = 101;
	private BaseballCard[] collection = new BaseballCard[MAX_CARDS];
	private int count;		//item_currently_in_list		
/**
 * Create an empty list of BaseballObject with blank parameter.<p>
 * Precondition: this CardCollection has been initialized to an empty list of BaseballCards.
 */
	public CardCollection(){} // Constructor
/**
 * Receive the number of items in the current array as an integer <p>
 * Preconditions: this CardCollection object has been instantiated.
 * @return the count of items in the current array.
 */
	public int size(){
		return count;
	}
/**
 * Add the input Baseball Card to a specified position of the collection.<p>
 * Preconditions: This CardCollection object has been instantiated and 1 < position < items_currently_in_list + 1.
 * The number of BaseballCard objects in this Menu is less than MAX_CARDS.<p>
 * Postcondition:The new BaseballCard is now stored at the desired position in the CardCollection.
 * All cards that were originally in positions greater than or equal to position are moved back one position. 
 * @param newCard the new baseball card object to be added.
 * @param position the position where the baseball card is added to in the collection.
 * @throws IllegalArgumentException the specified position is not in the range of count.
 * @throws FullCollectionException the specified position is greater than the size of the collection.
 */
	public void addCard(BaseballCard newCard, int position) throws IllegalArgumentException,FullCollectionException{
		if(position < 0 || position > count+1)
			throw new IllegalArgumentException();
		if(count+1 > MAX_CARDS-1)
			throw new FullCollectionException(count+1);  //Check condition
		//add card
		++count;
		System.arraycopy(collection,position,collection,position+1,count-position);
		collection[position] = newCard;
	}
/**
 * A simple program to add the baseball card to the end of the collection.
 * @param newCard the new baseball card object to be added.
 */
	public void addCard(BaseballCard newCard){
		try {
			addCard(newCard, count+1);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
/**
 * A simple program to remove the baseball card from the specified position in the collection.<p>
 * Preconditions:This CardCollection object has been instantiated and 1 < position < items_currently_in_list.<p>
 * Postcondition:The card at the desired position in the collection has been removed. 
 * All acrds that were originally in positions greater than or equal to position are moved forward one position. 
 * @param position the position where the baseball card is wished to be removed.
 * @throws IllegalArgumentException when the specified position is not in range of the current item count.
 */
	public void removeCard(int position) throws IllegalArgumentException{
		if(position < 0 || position > count)
			throw new IllegalArgumentException();
		//remove card
		System.arraycopy(collection, position+1, collection,position,count-position);
		--count;
	}
/**
 * A simple program to return the baseball card at the specified location of the collection.<p>
 * Preconditions: This CardCollection object has been instantiated and 1 < position < items_currently_in_list.
 * @param position the location of the baseball card to be returned.
 * @return return the baseball card at the specified location.
 * @throws IllegalArgumentException the specified location is not in range of the current item count.
 */
	public BaseballCard getCard(int position) throws IllegalArgumentException{
		if(position < 0 || position > count)
			throw new IllegalArgumentException();
		//get card
		return collection[position];
	}
/**
 * A simple program to trade the baseball card with another collection.<p>
 * Preconditions:Both CardCollection objects have been instantiated and 1 < myPosition < items_currently_in_this_list 
 * and 1 < theirPosition < items_currently_in_other_list.
 * @param other the other collection to trade with.
 * @param myPosition the location of the baseball card in this collection.
 * @param theirPosition the location of the baseball card in the other collection.
 * @throws IllegalArgumentException when either position in the corresponding collection is invalid.
 */
	public void trade(CardCollection other, int myPosition, int theirPosition) throws IllegalArgumentException{
		if(myPosition < 0 || myPosition > count || theirPosition < 0 || theirPosition > other.size())
			throw new IllegalArgumentException();
		BaseballCard temp = collection[myPosition];
		collection[myPosition] = other.getCard(theirPosition);
		try {
			other.removeCard(theirPosition);
			other.addCard(temp,theirPosition);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/**
 * A simple program to check whether a baseball card exist in this collection.<p>
 * Preconditions: This CardCollection and the BaseballCard object have both been instantiated
 * @param card the baseball card to be checked.
 * @return true if the baseball card exists in this collection, false otherwise.
 */
	public boolean exists(BaseballCard card){
		for(int i=1; i<= count;i++){
			if(collection[i].equals(card))
				return true;
		}
		return false;
	}
/**
 * A simple program to print the information of all cards in this collection;<p>
 * The program will simply call the toString method to finish the print job.
 * Preconditions: This CardCollection object has been instantiated.<p>
 * Postcondition:A neatly formatted table of each card in the collection on its own line 
 * with its position number has been displayed to the user.<p>
 */
	public void printAllCards(){
		System.out.printf("%s",toString());
	}
/**
 * A simple program to print the information of a baseball card at the specified location.<p>
 * Preconditions: This CardCollection object has been instantiated and 1 < position < items_currently_in_list.
 * @param position The location of the card to be printed.
 * @throws IllegalArgumentException Indicates that the specified position is invalid.
 */
	public void printACard(int position) throws IllegalArgumentException{
		if(position < 0 || position > count)
			throw new IllegalArgumentException();
		BaseballCard b = collection[position];
		System.out.printf("%-3d%-21s%-14s%-6d%-8.2f%dx%d\n",position,b.getName(),b.getManufacturer(),b.getYear(),
				b.getPrice(),b.getSizeX(),b.getSizeY());
	}
/**
 * A simple program to return a String which contain the information of all cards in this collection.
 * @return return the information of all cards concatenated in a String.
 */
	public String toString(){
		String s = "#  Name\t\t\tManufacturer  Year  Price   Size\n" + "-- ----\t\t\t------------  ----  -----   ----\n";
		for(int i=1; i<=count;i++){
			BaseballCard b = collection[i];
			s += String.format("%-3d%-21s%-14s%-6d%-8.2f%dx%d\n",i,b.getName(),b.getManufacturer(),b.getYear(),
							b.getPrice(),b.getSizeX(),b.getSizeY());
		}
		return s;
	}
	
}
/**
 * A simple class which contains the definition of FullCollectionException 
 * @throws FullCollectionException indicating that the current item in the collection exceeds the maximum capacity.
 * @author Li
 */
class FullCollectionException extends Exception{
	public FullCollectionException(int count){
		System.out.println("item_currently_in_list: (" + count + ") There are no more room in the collection");
	}
	
}
