package hw1;
/**
 * A simple class which defines a baseball card object with the clone and equals method. <p> 
 * Zhaoqi Li<p>
 * 109051837<p>
 * Assignment 1 task 2<p>
 * CSE 214 R05<p>
 * Vyassa Baratham (Recitation TA)<p>
 * Ke Ma (Grading TA)<p>
 */

public class BaseballCard{
//Data field
	private String name,manufacturer;
	private int year;
	private double price;
	private int[] imageSize = new int[2];
/**
 * Create a new BaseballCard Object with blank parameters
 */
	public BaseballCard(){};
/**
 * Create a new BaseballCard Object for custom testing
 * @param name the player's name
 * @param manufacturer the card's manufacturer
 * @param year year of the manufacture	
 * @param price	price of the card	
 * @param a size of the card along the x-axis
 * @param b size of the card along the y-axis
 */
	public BaseballCard(String name,String manufacturer, 
						int year,double price,int a, int b){
		this.name = name;
		this.manufacturer = manufacturer;
		this.year = year;
		this.price = price;
		this.imageSize[0] = a;
		this.imageSize[1] = b;
	}				
// Mutators and Accessors 
/**
 * Receive the name of a particular player on the card
 * @return the name of the player as a string
 */
	public String getName(){
		return name;
	}
/**
 * Changes the player name to the specified name
 * @param name new player name
 */
	public void setName(String name){
		this.name = name;
	}
/**
 * Receive the name of the manufacturer
 * @return the name of the manufacturer as a string
 */
	public String getManufacturer(){
		return manufacturer;
	}
/**
 * Change the manufacturer to the specified manufacturer
 * @param manufacturer new manufacturer of the card
 */
	public void setManufacturer(String manufacturer){
		this.manufacturer = manufacturer;
	}
/**
 * Receive the year of the card
 * @return the year of the card as a integer
 */
	public int getYear(){
		return year;
	}
/**
 * Change the year of the card to the specified year
 * @param year new year of the card
 */
	public void setYear(int year){
		this.year = year;
	}
/**
 * Receive the price of the card 
 * @return the price of the card as a double
 */
	public double getPrice(){
		return price;
	}
/**
 * Change the price of the card to the specified price
 * <dt><b>Preconditon: the input price should be greater or equal to 0</b></dt>
 * @param price new price of the card
 * @throws InvalidInputException when the input price is less than 0
 */
	public void setPrice (double price) throws IllegalArgumentException{
		if(price < 0)
			throw new IllegalArgumentException("Price cannot be less than 0");
		this.price = price;
	}
/**
 * Receive the Size (X and Y) of the image as an integer Array.
 * @return the size (X and Y) in an array
 */
	public int[] getImageSize(){
		return imageSize;
	}
/**
 * Receive the length of the x-axis of the image as an integer
 * @return the first value in the image size array (X)
 */
	public int getSizeX(){
		return imageSize[0];
	}
/**
 * Change the value of the x-axis to the specified value
 * @param sizeX the new value of the x-axis of the image size
 */
	public void setSizeX(int sizeX){
		imageSize[0] = sizeX;
	}
/**
 * Receive the length of the y-axis of the image as an integer
 * @return the second value in the image size array (Y)
 */
	public int getSizeY(){
		return imageSize[1];
	}
/**
 * Change the value of the y-axis to the specified value
 * @param sizeY the new value of the y-axis of the image size
 */
	public void setSizeY(int sizeY){
		imageSize[1] = sizeY;
	}
/**
 * Copies all data from a particular BaseballCard Object into a new Baseball reference;
 * Changing the original will not change the copy.
 * @return a copy of a BaseballCard as an **Object**.
 */
	public Object clone(){
		BaseballCard b = new BaseballCard ();
		b.setName(this.name);
		b.setManufacturer(this.manufacturer);
		b.setYear(this.year);
		try{
			b.setPrice(this.price);
		}catch(Exception e){
			e.printStackTrace();
		}
		b.setSizeX(this.imageSize[0]);
		b.setSizeY(this.imageSize[1]);
        return b;
}
/**
 * COmpares a particular object with a BaseballCard object;checks for logical equivalence
 * @param obj the object to be compared with the current BaseballCard
 * @return true if logically equivalent, false otherwise
 */
	public boolean equals(Object obj){
		if(!(obj instanceof BaseballCard))
			return false;
		BaseballCard b = (BaseballCard) obj;
		if(!this.name.equals(b.getName()))
			return false;
		else if(!this.manufacturer.equals(b.getManufacturer()))
			return false;
		else if(this.price != b.getPrice())
			return false;
		else if(this.year != b.getYear())
			return false;
		else if(!imageSizeCompare(this.imageSize,b.getImageSize()))
			return false;
		return true;
	}
/**
 * Compare the content of two imageSize array;
 * @param size1 the X-axis of the image
 * @param size2 the Y-axis of the image
 * @return true if logically equivalent, false otherwise
 */
	private boolean imageSizeCompare(int[] size1, int[] size2){
		for(int i=0;i<2;i++){
			if(size1[i]!=size2[i])
				return false;
		}
		return true;
	}

}