package hw_7;

/*
 * imported files
 */
import java.util.*;
import java.io.*;

import latlng.LatLng;

import com.google.code.geocoder.*;
import com.google.code.geocoder.model.*;
/**
 * A simple class to implement an airline connections.<p> 
 * 109051837<p>
 * Assignment 7 task 2<p>
 * CSE 214 R05<p>
 * Vyassa Baratham (Recitation TA)<p>
 * Ke Ma (Grading TA)<p>
 * @author Zhaoqi Li<p>
 *
 */
@SuppressWarnings("serial")
public class SigmaAir implements Serializable{
	/*
	 * Data Field
	 */
	private ArrayList<City> cities;
	private double[][] connections;
	
	/*
	 * Constants
	 */
	public static final int MAX_CITIES = 100;
	
	/*
	 * Constructors, getters, additional methods...
	 */
	/**
	 * An empty constructor which creates a SigmaAir object with a new ArrayList of City object and a 2D table of 
	 * interconnections between the City objects. The constructor will be using a nested for loop to initialize all
	 * connections to be infinity at the beginning (0 for connections between the same City in the table).
	 */
	public SigmaAir(){
		cities = new ArrayList<City>(MAX_CITIES);
		connections = new double[MAX_CITIES][MAX_CITIES];
		for(int i = 0; i < MAX_CITIES; i++){		//Initialize all connections to infinity
			for(int j = 0; j < MAX_CITIES; j++){
				if(i == j)
					connections[i][j] = 0;
				else
					connections[i][j] = Double.POSITIVE_INFINITY;
			}
		}
	}
	/**
	 * Add a City Object to the ArrayList of the SigmaAir. The method will use the given city name to retrieve information from
	 * Google map. A new City object will be created using these informations then added to the ArrayList.
	 * @param city the name of the City object to be added to the List.
	 * @throws IllegalArgumentException indicates that a City object with the same name has already existed.
	 */
	public void addCity(String city) throws IllegalArgumentException{
		if(!cities.isEmpty()){
			for(City temp : cities){
				if (temp.getCity().equals(city))
					throw new IllegalArgumentException("Error: city " + city + " is duplicated in the list..." );
			}
		}
		try{
			cities.add(createCity(city));
			City result = searchCity(city);
			if(result != null){
				System.out.printf("%s has been added: (%f, %f)\n", city, result.getLocation().getLat(),
						result.getLocation().getLng());
			}else
				System.out.println("Unable to add " + city);
		}catch(Exception error){
			throw error;
		}
	}
	/**
	 * Assert the connection between two cities and store the distance between the two cities in the proper location.
	 * @param cityFrom The city at which the connections starts.
	 * @param cityTo The city at which the connection ends.
	 * @throws IllegalAccessException indicates that the currents ArrayList is empty.
	 * @throws IllegalArgumentException indicates that one or more targets city in the connection does not exist in the List
	 */
	public void addConnection(String cityFrom, String cityTo) throws IllegalAccessException, IllegalArgumentException{
		
		if(cityFrom.equals(cityTo))
			return;
		
		int[] index = getIndex(cityFrom, cityTo);
		City pos_from = cities.get(index[0]);
		City pos_to = cities.get(index[1]);	
		int index_0, index_1;
		
		connections[pos_from.getIndexPos()][pos_to.getIndexPos()] =
				LatLng.calculateDistance(pos_from.getLocation(), pos_to.getLocation());
		index_0 = pos_from.getIndexPos();
		index_1 = pos_to.getIndexPos();
		System.out.printf("%s --> %s added: %f\n", cityFrom, cityTo, connections[index_0][index_1]);
	}
	
	/**
	 * Remove the connection between 2 given cities from the connection table, the program will return a delete message even
	 * though the intended connections does not exist.
	 * @param cityFrom The city at which the connection starts.
	 * @param cityTo The city at which the connection ends.
	 * @throws IllegalArgumentException that the currents ArrayList is empty.
	 * @throws IllegalAccessException indicates that one or more targets city in the connection does not exist in the List
	 */
	public void removeConnection(String cityFrom, String cityTo) throws IllegalArgumentException, IllegalAccessException{
		
		if(cityFrom.equals(cityTo))
			return;
		
		int[] index = getIndex(cityFrom, cityTo);
		City pos_from = cities.get(index[0]);
		City pos_to = cities.get(index[1]);
		
		connections[pos_from.getIndexPos()][pos_to.getIndexPos()] = Double.POSITIVE_INFINITY;
		System.out.println("Connection from " + cityFrom + " to " + cityTo + " has been removed!");
	}
	
	/**
	 * Find the shortest path between the two given cities. Return a message to indicate that the shortest path does not exist if connections
	 * between two cities does not exist.
	 * @param cityFrom The city at which the path starts.
	 * @param cityTo The city at which the path ends.
	 * @return return a string to indicates the existence of a shortest path
	 * @throws IllegalArgumentException indicates that the currents ArrayList is empty.
	 * @throws IllegalAccessException indicates that one or more targets city in the connection does not exist in the List
	 */
	public String shortestPath(String cityFrom, String cityTo) throws IllegalArgumentException, IllegalAccessException{
		if(cityFrom.equals(cityTo))
			throw new IllegalArgumentException("Error: source and destination cannot be the same...");
		
		int[] index = getIndex(cityFrom, cityTo);
		City pos_from = cities.get(index[0]);
		City pos_to = cities.get(index[1]);
		
		City[][] next = new City[MAX_CITIES][MAX_CITIES];
		double[][] dist = new double[MAX_CITIES][MAX_CITIES];
		
		for(int i = 0; i < MAX_CITIES; ++i){
			for(int j = 0; j < MAX_CITIES; ++j){
				dist[i][j] = connections[i][j];
				if(j < cities.size())
					next[i][j] = cities.get(j);
				else
					next[i][j] = null;
			}
		}
		
		for(int k = 0; k < MAX_CITIES; ++k){
			for(int i = 0; i < MAX_CITIES; ++i){
				for(int j = 0; j < MAX_CITIES; ++j){
					if(dist[i][k] + dist[k][j] < dist[i][j]){
						dist[i][j] = dist[i][k] + dist[k][j];
						next[i][j] = next[i][k];
					}
				}
			}
		}
		String path = "";
		double distance = 0;
		if(next[pos_from.getIndexPos()][pos_to.getIndexPos()] == null)
			return "No Shortest Path is available...";
		if(next[pos_from.getIndexPos()][pos_to.getIndexPos()] == pos_to && 
				dist[pos_from.getIndexPos()][pos_to.getIndexPos()] == Double.POSITIVE_INFINITY)
			return "There is no shortest path from " + pos_from.getCity() + " to " + pos_to.getCity();
		path = pos_from.getCity();
		while(pos_from != pos_to){
			distance += LatLng.calculateDistance(pos_from.getLocation(), 
					next[pos_from.getIndexPos()][pos_to.getIndexPos()].getLocation());
			pos_from = next[pos_from.getIndexPos()][pos_to.getIndexPos()];
			path += String.format(" --> %s", pos_from.getCity()); 
		}
		return String.format("%s : %f", path, distance);
	}
	
	/**
	 * Print a table of all cities in a user-preferred format
	 * @param comp The format in which the cities objects are sorted.
	 */
	public void printAllCities(Comparator<City> comp){
		ArrayList<City> copy = new ArrayList<City>();
		for(City temp : cities)
			copy.add(temp);
		Collections.sort(copy, comp);
		System.out.println("Cities: \nCity Name                   Latitude        Longitude\n" +
							"-------------------------------------------------------------");
		for(City temp : copy)
			System.out.println(temp);
	}
	
	/**
	 * print all existing connections between cities.
	 */
	public void printAllConnections(){
		System.out.println("Connections:\n"
				+ "Route                               Distance\n" +
					"----------------------------------------------------------");
		for(int i = 0; i < cities.size(); ++i){
			for(int j = 0; j < cities.size(); ++j){
				if(connections[i][j] != 0 && connections[i][j] != Double.POSITIVE_INFINITY){
					System.out.printf("%-36s%f\n", cities.get(i).getCity() +
							" --> " + cities.get(j).getCity(), connections[i][j]);
				}
			}
		}
	}
	
	/**
	 * Read all cities from a given file path and add them to the current ArrayList.
	 * @param filename the name of the file from which the data is loaded.
	 * @throws FileNotFoundException indicates that the given file does not exist in the path.
	 */
	public void loadAllCities(String filename) throws FileNotFoundException{
		File file = new File(filename);
		Scanner fin = new Scanner(file);
		while(fin.hasNextLine()){
			String temp = fin.nextLine();
			try{
				this.addCity(temp);
			}catch(Exception e){
				System.out.println("Error adding City:  " + temp);
			}
		}
		fin.close();
		
	}
	
	/**
	 * Read all connections from a given file path and place the data in the connection table.
	 * @param filename the name of the file from which the data is loaded.
	 * @throws FileNotFoundException indicates that the given file does not exist in the path.
	 */
	public void loadAllConnections(String filename) throws FileNotFoundException{
		File file = new File(filename);
		Scanner std_fin = new Scanner(file);
		while(std_fin.hasNextLine()){
			String temp = std_fin.nextLine();
			String[] hold = temp.split(",");
			try{
				this.addConnection(hold[0], hold[1]);
			}catch(Exception e){
				System.out.printf("Error adding connection: %s --> %s\n", hold[0], hold[1]);
			}
		}
		std_fin.close();
	}
	
	/**
	 * Return the index if the City object corresponding to the given name is found.
	 * @param cityFrom The name of the first City to search.
	 * @param cityTo The name of the second City to search.
	 * @return An index array which store the first index in the first element and the second index in the second element.
	 * @throws IllegalAccessException indicates that the currents ArrayList is empty.
	 * @throws IllegalArgumentException indicates that one or more targets city in the connection does not exist in the List
	 */
	private int[] getIndex(String cityFrom, String cityTo) throws IllegalAccessException, IllegalArgumentException{
		int[] index = {MAX_CITIES,MAX_CITIES};
		
		if(cities.isEmpty())
			throw new IllegalAccessException("Error: the list is empty...");

			
		for(int i = 0; i < cities.size(); i++){
			if(cities.get(i).getCity().toUpperCase().equals(cityFrom.toUpperCase())){
				index[0] = i;
			}
			if(cities.get(i).getCity().toUpperCase().equals(cityTo.toUpperCase())){
				index[1] = i;
			}
		}
		
		if(index[0] == MAX_CITIES || index[1] == MAX_CITIES)
			throw new IllegalArgumentException("Error: one or More Cities are not found in the list....");
		
		return index;
	}
	/**
	 * Use the name of the City to Search the list and return the City object if found.
	 * @param city the name of the City to look for in the list.
	 * @return the found City object which corresponds to the provided name, return null otherwise.
	 */
	private City searchCity(String city){
		for(City temp : cities){
			if(temp.getCity().equals(city))
				return temp;
		}
		return null;
	}
	
	/**
	 * Create an City object: use the provided city name to load valid data from Google maps and assigning the data to the 
	 * City object.
	 * @param city The name of the city of the new City object.
	 * @return the City object if one has been created.
	 * @throws IllegalArgumentException indicates that the provided city name does not match any city in the Google maps.
	 */
	private City createCity(String city) throws IllegalArgumentException{
		try {
		    Geocoder geocoder = new Geocoder();
		    GeocoderRequest geocoderRequest;
		    GeocodeResponse geocodeResponse;
		    //String addr;
		    double lat;
		    double lng;
		    
		    geocoderRequest = new GeocoderRequestBuilder().setAddress(city).getGeocoderRequest();
		    geocodeResponse = geocoder.geocode(geocoderRequest);
		   // addr = geocodeResponse.getResults().get(0).getFormattedAddress();
		    lat = geocodeResponse.getResults().get(0).getGeometry().getLocation().getLat().doubleValue();
		    lng = geocodeResponse.getResults().get(0).getGeometry().getLocation().getLng().doubleValue();
		   // System.out.println(addr + " " + lat + " " + lng);
		    return new City(city, new LatLng(lat, lng));
		} catch (Exception e) {
		    throw new IllegalArgumentException("Error: this city does not exist...");
		}
	}
}
