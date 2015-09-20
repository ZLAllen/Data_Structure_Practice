package hw_7;

/*
 * imported files
 */
import latlng.LatLng;
/**
 * A simple class to implement a city.<p> 
 * 109051837<p>
 * Assignment 7 task 1<p>
 * CSE 214 R05<p>
 * Vyassa Baratham (Recitation TA)<p>
 * Ke Ma (Grading TA)<p>
 * @author Zhaoqi Li<p>
 *
 */

@SuppressWarnings("serial")
public class City implements java.io.Serializable{
	/*
	 * Data Field
	 */
	private String city;
	private LatLng location;
	private int indexPos;
	private static int cityCount;
	
	/*
	 * Constructors, getters, and setters.
	 */
	
	/**
	 * An empty constructor which creates a City object with initialized indexPos, then increment the cityCount.
	 */
	public City(){
		indexPos = cityCount;
		++cityCount;
	};
	
	/**
	 * An custom constructor which creates a City object with initialized name, location data field. The object will follow
	 * the same procedure to initialize the indexPos and increment the cityCount.
	 * @param name Name of the city object
	 * @param location location of the city object(Lat, Lng)
	 */
	public City(String city, LatLng location){
		this.city = city;
		this.location = location;
		indexPos = cityCount;
		++cityCount;
	}
	
	/**
	 * Receive the Name of the City 
	 * @return city as a string
	 */
	public String getCity(){
		return city;
	}
	
	/**
	 * Update the name of the City.
	 * @param city the new name of the City.
	 */
	public void setCity(String city){
		this.city = city;
	}
	
	/**
	 * Receive the location of the City.
	 * @return location as an LatLng Object.
	 */
	public LatLng getLocation(){
		return location;
	}
	
	/**
	 * Update the location of the City.
	 * @param location the new location of the City. 
	 */
	public void setLocation(LatLng location){
		this.location = location;
	}
	
	/**
	 * Receive the Index position of the City in the table.
	 * @return indexPos as an Integer.
	 */
	public int getIndexPos(){
		return indexPos;
	}
	
	/**
	 * Receive the number of city objects created by the driver.
	 * @return cityCount as a static Integer.
	 */
	public int getCityCount(){
		return City.cityCount;
	}
	
	/**
	 * Receive a String about the name, Latitude, and Longitude of the City.
	 */
	public String toString(){
		return String.format("%-28s%-16f%f", city, location.getLat(), location.getLng());
	}
	
}
