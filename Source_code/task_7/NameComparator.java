package hw_7;

import java.util.Comparator;

public class NameComparator implements Comparator <City> {
	public int compare(City c1, City c2){
		return (c1.getCity().compareTo(c2.getCity()));
	}
}
