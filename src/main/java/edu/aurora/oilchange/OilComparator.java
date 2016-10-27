package edu.aurora.oilchange;

import java.util.Comparator;

public class OilComparator<T extends Oil> implements Comparator<T> {

	@Override
	public int compare(Oil o1, Oil o2) {
		return o1.compareTo(o2);
	}

}
