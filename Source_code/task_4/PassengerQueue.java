package hw_4;
import java.util.LinkedList;
/**
 * A simple class which creates a passenger queue to take in or send out many groups of passenger, 
 * the functionality of the queue will be dependent on the bus or bus stop type<p> 
 * This subclass extends LinkedList and uses the add/size/remove/isEmpty/getFirst methods. <p>
 * Zhaoqi Li<p>
 * 109051837<p>
 * Assignment 4 task 2<p>
 * CSE 214 R05<p>
 * Vyassa Baratham (Recitation TA)<p>
 * Ke Ma (Grading TA)<p>
 */
@SuppressWarnings("serial")
public class PassengerQueue extends LinkedList<Passenger>{
/**
 * Call the super constructor to create an empty LinkedList with generic type of Passenger
 */
	public PassengerQueue(){
		super();
	}
/**
 * call the add to Last method to add a passenger object to the last of the linkedList.
 * @param p the new Passenger object to te added to the linkedList.
 */
	public void enqueue(Passenger p){
		super.addLast(p);
	}
/**
 * call the removed method to dequeue the proper passenger group from the bus stop queue.
 * @param temp the bus object to determine the passenger group to dequeue.
 * @return	the removed passenger group as a passenger object
 * @throws FullBusException indicates that no passenger object is able to dequeue.
 */
	public Passenger dequeue(Bus temp) throws FullBusException{
		Passenger onBoard = new Passenger();
		for(int i=0; i< super.size();i++){
			if(super.get(i).getSize() + temp.getBusQueue().size() <= temp.getCapacity()){
				onBoard = super.remove(i);
				return onBoard;
			}
		}
		throw new FullBusException("This bus is full" + temp.getBusQueue().size());
	}
/**
 * Unload the passenger groups that reach the given destination.
 * @param dest the destination at which the bus arrives.
 * @return return the number of passenger got off.
 */
	public int unload(int dest){
		int nPassenger = 0;
		int index = 0;
		while(index < super.size()){
			if(super.get(index).getDestination() == dest){
				nPassenger += super.remove(index).getSize();
				index = 0;
			}else
				index++;
		}
		return nPassenger;
	}
/**
 * Looping through the passenger queue to receive the number of passengers in the queue.
 * @return nPassenger as an integer.
 */
	public int size(){
		int nPassenger = 0;
		for(int i=0; i< super.size();i++)
			nPassenger += super.get(i).getSize();
		return nPassenger;
	}
/**
 * Received the size of the passenger queue.
 * @return super.size() as an integer
 */
	public int getSize(){
		return super.size();
	}
/**
 * Receive the first passenger object of the list.
 * @return call the super.getFirst() method and return the first element in the list.
 */
	public Passenger peek(){
		return super.getFirst();
	}
/**
 * Receive a boolean value indicating whether the queue is empty.
 * @return true if the queue is empty, false otherwise.	
 */
	public boolean isEmpty(){
		return super.isEmpty();
	}
	
}
/**
 * An self-defined exception class to indicate that the passenger queue is not able to 
 * unload any passenger from the queue. The information will be received and suppressed by the 
 * Simulator which terminates the process of loading passengers to the bus.
 * @author Zhaoqi
 *
 */
@SuppressWarnings("serial")
final class FullBusException extends Exception{
	public FullBusException(String Message){}
}