package hw_7;

import java.util.Comparator;

public class LatComparator implements Comparator <City> {
	public int compare(City c1, City c2){
		return Double.compare(c1.getLocation().getLat(), c2.getLocation().getLat());
	}
}
