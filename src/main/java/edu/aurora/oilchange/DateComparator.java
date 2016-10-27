package edu.aurora.oilchange;
import java.util.Comparator;

public class DateComparator<T extends Date> implements Comparator<T>
{

	@Override
	public int compare(Date d1, Date d2) {
		return d1.compareTo(d2);
	}

}
