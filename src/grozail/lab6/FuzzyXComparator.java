package grozail.lab6;

import java.util.Comparator;

/**
 * Created by grozail on 25.10.16.
 *
 */
public class FuzzyXComparator implements Comparator<FuzzyNumber> {

	public int compare(FuzzyNumber o1, FuzzyNumber o2) {
		return o1.getX() < o2.getX() ? -1 : o1.getX() > o2.getX() ? 1 : 0;
	}


	public Comparator<FuzzyNumber> reversed() {
		return new Comparator<FuzzyNumber>() {

			public int compare(FuzzyNumber o1, FuzzyNumber o2) {
				return o1.getX() < o2.getX() ? 1 : o1.getX() > o2.getX() ? -1 : 0;
			}
		};
	}


	public String toString() {
		return "Represents Comparator<FuzzyNumber> for sorting by X field.";
	}
}
