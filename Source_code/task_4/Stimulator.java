package hw_4;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * A simple class which creates a simulator to perform the particular simulation based on the input parameter.
 * User will be prompt to enter the number of in route busses, number of out route busses, the bus capacity, the
 * arrival probability of passenger at each bus stop, the maximum and minimum size of the passenger group, and finally
 * the total simulation time. The program will calculate the groups of passengers served during the simulation and the average
 * waited time base on this particular simulation. The average wait time of the simulation tends to be stable when the simulation
 * time is large (greater than 120 in general).<p>
 * Zhaoqi Li<p>
 * 109051837<p>
 * Assignment 4 task 5<p>
 * CSE 214 R05<p>
 * Vyassa Baratham (Recitation TA)<p>
 * Ke Ma (Grading TA)<p>
 */
public class Stimulator {
	/**
	 * Data field
	 * <dt> Basic: numInBusses, numOutBusses, minGroupSize, maxGroupSize, capacity, arrivalProb </dt><p>
	 * groupsServed: incremented for each successfully enqueued of passenger group into the bus.<p>
	 * totalTimeWaited: collect and add up the waited time of each passenger group on board.<p>
	 * goToRest: a boolean array of 8 which stores the flags indicating whether a bus has completed the trip. True will set the bus to rest, false otherwise.<p>
	 * NUM_BUS_STOPS: the number of bus stop in this simulation model. <p>
	 * busStops: an array of PassengerQueue which stores the passenger groups arrived at each bus stop.<p>
	 * inBusses: an arrayList of in route busses created.<p>
	 * outBusses: an arrayList of out route busses created.<p>
	 * inRoute: the names of bus stops for the in route.<p>
	 * outRoute: the names of bus stops for the out route.<p>
	 */
	int numInBusses, numOutBusses;
	int minGroupSize, maxGroupSize;
	int capacity;
	double arrivalProb;
	int groupServed;
	int totalTimeWaited;
	static boolean[] goToRest = new boolean[8];
	final int NUM_BUS_STOPS = 8;
	PassengerQueue[] busStops = new PassengerQueue[NUM_BUS_STOPS]; // NUM_BUS_STOPS = 8
	ArrayList<Bus> inBusses = new ArrayList<Bus>();
	ArrayList<Bus> outBusses = new ArrayList<Bus>();

	// Add passengers to the bus stop queues 
	String inRoute[]  = {"South P", "West", "SAC", "Chapin"};
	String outRoute[] = {"South P", "PathMart", "Walmart", "Target"};
	/**
	 * A constructor which creates a simulator object with the following parameter.
	 * @param a the number of in route busses.
	 * @param b the number of out route busses.
	 * @param c the minimum size of the passenger group.
	 * @param d the maximum size of the passenger group.
	 * @param e the capacity of the bus.
	 * @param f the arrival probability of passenger group.
	 * @throws IllegalArgumentException indicating the violation of parameter rule.<p>
	 * minGroupSize can not exceed the maxGroupSize;; maxGroupSize cannot exceed the capacity;; arrival probability needs to be between 0 and 1. <p>
	 */
	public Stimulator(int a, int b, int c, int d, int e,
						double f) throws IllegalArgumentException{
		if(f < 0.0 || f > 1.0)
			throw new IllegalArgumentException("Incorrect Probability");
		if(c > d )
			throw new IllegalArgumentException("Minimum passenger cannot exceed Maximum passenger");
		if(d > e)
			throw new IllegalArgumentException("Maximum passenger cannot exceed Capacity of the bus");
		numInBusses = a;
		numOutBusses = b;
		minGroupSize = c;
		maxGroupSize = d;
		capacity = e;
		arrivalProb = f;
	}
	/**
	 * A method to invoke the simulation process. This method will call several private helper methods to constructor
	 * a transportation system model and perform the main simulation. The length of the simulation is determined by the
	 * parameter duration. The method will return relevant data to the program at the end of the simulation.
	 * @param duration	The duration of the simulation.
	 * @return	a double array consisting of the groups of passengers served during the simulation and their average wait time.
	 */
	public double[] stimulate(int duration){
		initialize();
		for(int currentTime = 1; currentTime <= duration; currentTime++){
			System.out.println("\nMinute " + currentTime);
			addPassenger(currentTime);
			for(int busCount = 0; busCount < numInBusses; busCount++){
				busFlow(currentTime,busCount, inBusses.get(busCount));
			}
			for(int busCount = 0; busCount < numOutBusses; busCount++){
				busFlow(currentTime,busCount, outBusses.get(busCount));
			}
			for(int i = 0; i < NUM_BUS_STOPS; i++){
				 System.out.printf(i + " (" + ((i < inRoute.length) ? inRoute[i] : outRoute[i - inRoute.length]) + "): ");
				 for(int j = 0; j < busStops[i].getSize(); j++){
					 Passenger arrived = busStops[i].get(j);
					 int dest = arrived.getDestination();
					 System.out.printf("[%d, %d (%s), %d] ",arrived.getSize(), dest,
							 (i < inRoute.length) ? inRoute[dest]:outRoute[dest],arrived.getTimeArrived());
				 }
				 System.out.println();
			}
		}
		double[] data = new double[2];
		data[0] = groupServed;
		data[1] = ((double)totalTimeWaited)/groupServed;
		return data;
	}
	
	/**
	 * A helper method to initialize the simulation model and creates all buses, bus stops as required by the users.
	 * This method will initialize the contents of all busses.
	 */
	private void initialize(){
		//create passenger queue for each bus stop
		for(int i = 0; i < NUM_BUS_STOPS; i++) {
		    busStops[i] = new PassengerQueue();		
		}
		
		//Create Busses ; 4 is uninitialized nextstop
		for(int i = 0; i < numInBusses; i++){
			inBusses.add(new Bus());
			inBusses.get(i).setRoute(0);
			inBusses.get(i).setCurrentStop(4); 
			inBusses.get(i).setNextStop(0);
			inBusses.get(i).setToNextStop(1);
			inBusses.get(i).setCapacity(capacity);
			if(i == 0)
				inBusses.get(i).setTimeToRest(1);	// start loading at minute 1, resting at 0;
			else
				inBusses.get(i).setTimeToRest(i*30);	// start loading when timer reach 0
		}
		
		for(int i = 0; i < numOutBusses; i++){
			outBusses.add(new Bus());
			outBusses.get(i).setRoute(1);
			outBusses.get(i).setCurrentStop(4); 
			outBusses.get(i).setNextStop(0);
			outBusses.get(i).setToNextStop(1);
			outBusses.get(i).setCapacity(capacity);
			if(i == 0)
				outBusses.get(i).setTimeToRest(1);	// start loading at minute 1, resting at 0;
			else
				outBusses.get(i).setTimeToRest(i*30);	// start loading when timer reach 0
		}
		for(int i = 0; i < 8; i++){
			goToRest[i] = false;
		}
	}
	/**
	 * A simple helper method to determine the arrival probability and size of the passenger group. if a passenger object
	 * is created, the method will add it to the bus stop. All passenger objects generation will be performed for each bus stop. 
	 * @param timeArrived each minute of the simulation time. It will become the timeArrived of the passenger group if one is generated.
	 */
	private void addPassenger(int timeArrived){
		int destination = 0, numPassenger;
		for(int i = 0; i < NUM_BUS_STOPS; i++){
			if(passengerEnqueue()){
				if (i == 0 || i == inRoute.length)
					destination = (int)((inRoute.length - 1)*Math.random() + 1);
				else if(i < inRoute.length)
					destination = ((int)((inRoute.length - i)*Math.random() + i + 1)) % 4;
				else
					destination = ((int)((outRoute.length - (i- inRoute.length))*Math.random() + i - inRoute.length + 1)) %4;
				numPassenger = (int)(Math.random()*(maxGroupSize - minGroupSize + 1) + minGroupSize);
				busStops[i].enqueue(new Passenger(numPassenger, destination, timeArrived));
				System.out.printf("A group of %d passengers arrived at %s heading to %s\n", numPassenger,
						i < inRoute.length? inRoute[i]:outRoute[i - inRoute.length], 
						i < inRoute.length? inRoute[destination]:outRoute[destination]);
			}
		}
	}
	/**
	 * The boolean source of the arrival possibility of passenger groups.
	 * @return true if passenger will arrive, false otherwise.
	 */
	private boolean passengerEnqueue(){
		return (Math.random() < arrivalProb);
	}
	/**
	 * The main helper method that controls the flow of all busses in the transportation system. At each minute, the 
	 * stimulate method will call this bus flow for each bus to determine their actions in this particular minute.
	 * @param i the current time (clock) of the simulation time.
	 * @param j	the current number of bus (the type is identified by their data filed route.
	 * @param tempBus the current bus to run.
	 */
	private void busFlow(int i, int j, Bus tempBus){
		int passenger_on = 0, passenger_off = 0;
		String busType = tempBus.getRoute() == 0? "In Route Bus":"Out Route Bus";
		String[] busStop = tempBus.getRoute() == 0? inRoute: outRoute;
		int rest_index = tempBus.getRoute() == 0? 0:4;
		if(tempBus.getTimeToRest() == 1){
			if(tempBus.getToNextStop() == 1){		//reach a stop or get on duty	
				tempBus.setCurrentStop(tempBus.getNextStop());
				if(goToRest[j + rest_index]){
					tempBus.setNextStop(4);
					tempBus.setTimeToRest(30);
					System.out.printf("%s %d arrives at %s.", busType, j+1, busStop[tempBus.getCurrentStop()]);
					passenger_off = tempBus.getBusQueue().unload(tempBus.getCurrentStop());
					System.out.printf("Drop off %d Passengers.\n",passenger_off);
					System.out.printf("%s %d start to rest...\n",busType, j+1);
					tempBus.setCurrentStop(4);
					tempBus.setNextStop(0);
					goToRest[j + rest_index] = false; //toggle value to prepare for next cycle
				}else{
					tempBus.setNextStop((tempBus.getCurrentStop() + 1) % 4); 
					System.out.printf("%s %d arrives at %s.", busType, j+1, busStop[tempBus.getCurrentStop()]);
					passenger_off = tempBus.getBusQueue().unload(tempBus.getCurrentStop()); //unload Passenger
					try{													//add Passenger
						while(tempBus.getBusQueue().size() < tempBus.getCapacity()){
							Passenger temp = busStops[tempBus.getCurrentStop() + rest_index].dequeue(tempBus);
							tempBus.getBusQueue().enqueue(temp);
							passenger_on += temp.getSize();
							groupServed ++;
							totalTimeWaited += i - temp.getTimeArrived();
						}
					}catch (Exception e){}
					System.out.printf("Drop off %d Passengers. ",passenger_off);
					System.out.printf("Pick up %d Passengers\n", passenger_on);
					if(tempBus.getNextStop() == 0 && tempBus.getCurrentStop() == 3){	//Timer for next stop
						tempBus.setToNextStop(60);	
						goToRest[j + rest_index] = true;
					}else
						tempBus.setToNextStop(20);
				}
			}else{							 
				System.out.printf("%s %d is moving toward %s. ", busType, j+1, busStop[tempBus.getNextStop()]);
				tempBus.setToNextStop(tempBus.getToNextStop() - 1);
				System.out.printf("Arrive in %d minutes\n", tempBus.getToNextStop());
			}
		}else{
			tempBus.setTimeToRest(tempBus.getTimeToRest() - 1);
			System.out.printf("%s %d is resting at South P for %d minutes\n",busType, j+1, tempBus.getTimeToRest());
		}
	}
	/**
	 * The Main method which contains a simple user interface to receive all essential parameters for the stimulation. <p>
	 * The Main method will repeatedly call the simulate method to perform simulation until user decides to terminate the program.
	 * @param args	...
	 */
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		int numInBus, numOutBus, minPass, maxPass, cap, duration;
		double prob;
		String choice = "";
		do{
			try{
			System.out.println("Enter information in the following order");
			System.out.print("Number of in route busses: ");
			numInBus = input.nextInt();
			System.out.print("Number of out route busses: ");
			numOutBus = input.nextInt();
			System.out.print("Minimum Passenger: ");
			minPass = input.nextInt();
			System.out.print("Maximum Passenger: ");
			maxPass = input.nextInt();
			System.out.print("Capacity of bus: ");
			cap = input.nextInt();
			System.out.print("Probability of arrival: ");
			prob = input.nextDouble();
			System.out.print("Enter the duration of the simulation: ");
			duration = input.nextInt();
			input.nextLine();
			Stimulator test = new Stimulator(numInBus, numOutBus, minPass, maxPass,cap, prob);
			double[] data = new double[2];
			data = test.stimulate(duration);
			System.out.println();
			if(data[0] == 0.0)
				System.out.println("0 groups of passenger served, Average wait time is Nah!");
			else
				System.out.printf("%d groups of passenger served. Average wait time is %.2f minutes\n", (int)data[0], data[1]);
			System.out.println("Preform another simulation(Y/N): ");
			choice = input.next().toUpperCase();
			input.nextLine();
			}catch (Exception e){
				System.out.print("An error occur, do you want to start a new stimulation?(Y/N): ");
				e.printStackTrace();
				choice = input.next();
				input.nextLine();
			}
		}while(!choice.equals("N"));
		System.out.println("Program Terminated...");
		input.close();
	}
}
