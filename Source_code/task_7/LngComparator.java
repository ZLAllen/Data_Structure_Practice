package hw_7;

import java.util.Comparator;

public class LngComparator implements Comparator<City>{
	public int compare(City c1, City c2){
		return Double.compare(c1.getLocation().getLng(), c2.getLocation().getLng());
	}

}
