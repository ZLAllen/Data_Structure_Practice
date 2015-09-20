package hw_2;
/**
 * A simple class which contains some methods to manipulate the PerformanceNode objects <p>
 * through reference (head, tail, and cursor) <p> 
 * Zhaoqi Li<p>
 * 109051837<p>
 * Assignment 2 task 2<p>
 * CSE 214 R05<p>
 * Vyassa Baratham (Recitation TA)<p>
 * Ke Ma (Grading TA)<p>
 */
public class PerformanceList {
	private PerformanceNode head, tail;
	private PerformanceNode cursor;
/**
 * A default constructor to create an empty performance list for further edition. <p>
 * The head/tail/cursor are all initialized to null at the beginning.
 */
	public PerformanceList(){
		head = tail = cursor = null;
	}
/**
 * Inserts the new performance at the end of the PerformanceList.
 * <dt>Postcondition:</dt> The current node should now be the newly created node.
 * @param newPerformance the new Performance to be added to the list
 */
	public void addToEnd(PerformanceNode newPerformance){
		if(head == null)
			head = tail = newPerformance;
		else{
			PerformanceNode nodePtr = new PerformanceNode();
			tail.setNext(newPerformance);
			newPerformance.setPrev(tail);
			newPerformance.setNext(null);
			tail = newPerformance;
			tail.setStartTime(0);
			for(nodePtr = head; nodePtr.getNext()!= null;nodePtr = nodePtr.getNext())
				tail.setStartTime(tail.getStartTime() + nodePtr.getDuration());
		}
		cursor = tail;
	}
/**
 * Inserts the new data into the PerformanceList such that the new node directly follows the current node, if it exists.<p>
 * If there is no current node (i.e., the current node is null), simply insert the node at the end of the list.
 * <dt>Postcondition:</dt> The current node should now be the newly created node. <p>
 * The starting time of all subsequent performances should be extended by the value of the duration of the newly added performance 
 * @param newPerformance the new Performance to be added to the list after the current performance.
 */
	public void addAfterCurrent(PerformanceNode newPerformance){
		if(cursor == null || cursor.getNext() == null)
			this.addToEnd(newPerformance);
		else{
			newPerformance.setPrev(cursor);
			newPerformance.setNext(cursor.getNext());
			cursor.getNext().setPrev(newPerformance);
			cursor.setNext(newPerformance);
			cursor = cursor.getNext();
			PerformanceNode nodePtr = new PerformanceNode();
			cursor.setStartTime(0);
			for(nodePtr = head; nodePtr!= cursor;nodePtr = nodePtr.getNext())
				cursor.setStartTime(cursor.getStartTime() + nodePtr.getDuration());
			nodePtr = cursor;
			while(nodePtr.getNext()!= null){
				nodePtr = nodePtr.getNext();
				nodePtr.setStartTime(nodePtr.getStartTime()+cursor.getDuration());
			}		
		}
	}
/**
 * Removes the current node, if it exists. If the node does not exist (empty list), the program will return false. <p> 
 * The return value indicates whether or not any node was removed. 
 * <dt>Postcondition:</dt> The current node should now be the node after the removed node. <p>
 * If there is no node after the one that was just removed, 
 * the current node should now be the node before the one that was just removed.
 * @return true if node is successfully removed, false otherwise.
 */
	public boolean removeCurrentNode(){
		if(cursor == null)
			return false;
		else if(head == tail)
			head = tail = cursor = null;
		else if(cursor.getNext() == null){
			tail = tail.getPrev();
			tail.setNext(null);
			cursor = tail;
		}
		else if(cursor.getPrev() == null){
			head = head.getNext();
			head.setPrev(null);
			cursor = head;
		}else{
			double difference = cursor.getDuration();
			cursor.getPrev().setNext(cursor.getNext());
			cursor.getNext().setPrev(cursor.getPrev());
			cursor = cursor.getNext();
			PerformanceNode nodePtr = cursor;
			while(nodePtr != null){
				nodePtr.setStartTime(nodePtr.getStartTime()-difference);
				nodePtr = nodePtr.getNext();
			}
		}
		return true;
	}
/**
 * Print a message to show the information of the current performance.
 * @throws NullPointerException indicates there is no current node (empty list).
 */
	public void displayCurrentPerformance() throws NullPointerException{
		PerformanceNode nodePtr = head;
		if(head == null || cursor == null)
			throw new NullPointerException("There is no current performance");
		System.out.println("\nCurrent No. Performance  \tHead Performer  \tTotal Performers  Duration\tStart time  \n" + 
				"--------------------------------------------------------------------------------------------------");
		for(int i = 0; i < this.listLength(); i++){
			if(nodePtr == cursor)
				System.out.println(String.format("   *    %-4d",i+1) + cursor.toString());
			nodePtr = nodePtr.getNext();
		}
	}
/**
 * Moves the reference of the current node forwards in the list by one position 
 * <dt>Precondition: </dt> a node exists after the current one.<p>
 * @return true if the cursor moves forward, false indicates that the cursor reach the end of the list.
 * @throws NullPointerException indicates that the list is empty.
 */
	public boolean moveCursorForward() throws NullPointerException{
		if(cursor == null)
			throw new NullPointerException("The list is empty!");
		else if(cursor.getNext() == null)
			return false;
		else{
			cursor = cursor.getNext();
			return true;
		}
	}
/**
 * Moves the reference of the current node backwards in the list by one position 
 * <dt>Precondition: </dt> a node exists after the current one.<p>
 * @return true if the cursor moves backward, false indicates that the cursor reach the beginning of the list.
 * @throws NullPointerException indicates that the list if empty.
 */
	public boolean moveCursorBackward() throws NullPointerException{
		if(cursor == null)
			throw new NullPointerException("The list is empty!");
		else if(cursor.getPrev() == null)
			return false;
		else{
			cursor = cursor.getPrev();
			return true;
		}
	}

/**
 * Moves the current node to the given position in the PerformanceList.
 * @param position the position to which the cursor is moving.
 * @return true if the cursor successfully moves to the given position, false otherwise.
 * @throws IllegalArgumentException indicates that the given position is less than 0.
 */
	public boolean jumpToPosition(int position) throws IllegalArgumentException{
		PerformanceNode nodePtr = head;
		int i = 1;
		if(position <= 0)
			throw new IllegalArgumentException("Position can not be less than zero");
		while(i<position && nodePtr != null){
			nodePtr = nodePtr.getNext();
			i++;
		}
		if(nodePtr != null)
			cursor = nodePtr;
		return(nodePtr != null);
	}
/**
 * Receive the information of all performances in the list.
 * @return a neatly formatted table of all information for all the scheduled performances.
 */
	public String toString(){
		PerformanceNode nodePtr = head;
		String s = "\nCurrent No. Performance  \tHead Performer  \tTotal Performers  Duration\tStart time  \n" + 
				"--------------------------------------------------------------------------------------------------\n";
		for(int i = 0; i < this.listLength(); i++){
			if(nodePtr == cursor)
				s += String.format("   *    %-4d",i+1) + nodePtr.toString();
			else
				s += String.format("        %-4d",i+1) + nodePtr.toString();
			nodePtr = nodePtr.getNext();
		}
		return s;	
	}
/**
 * Receive the number of performances in the current list.
 * @return the length of the current list as an integer.
 */
	public int listLength(){
		PerformanceNode nodePtr = head;
		int count = 0;
		while(nodePtr != null){
			count ++;
			nodePtr = nodePtr.getNext();
		}	
		return count;
	}
/**
 * Receive the performance node at which the cursor is pointing.
 * @return the cursor as a PerformanceNode.
 */
	public PerformanceNode getCursor(){
		return cursor;
	}
	/*public static void main(String[] args){
		PerformanceList A = new PerformanceList();
		PerformanceNode a = new PerformanceNode("aat","asf",122,30);
		PerformanceNode b = new PerformanceNode("bat","afg",122,20);
		PerformanceNode c = new PerformanceNode("cat","asd",122,30);
		PerformanceNode d = new PerformanceNode("cat","asd",122,31);
		A.addToEnd(a);
		A.addToEnd(b);
		A.displayCurrentPerformance();
	}*/
}
