package grozail.lab6;

import java.util.Comparator;
import java.util.function.Function;

/**
 * Created by grozail on 25.10.16.
 *
 */
public class FuzzyUlComparator implements Comparator<FuzzyNumber> {

	public int compare(FuzzyNumber o1, FuzzyNumber o2) {
		return o1.getUl() < o2.getUl() ? -1 : o1.getUl() > o2.getUl() ? 1 : 0;
	}


	public Comparator<FuzzyNumber> reversed() {
		return new Comparator<FuzzyNumber>() {

			public int compare(FuzzyNumber o1, FuzzyNumber o2) {
				return o1.getUl() < o2.getUl() ? 1 : o1.getUl() > o2.getUl() ? -1 : 0;
			}
		};
	}


	public String toString() {
		return "Represents Comparator<FuzzyNumber> for sorting by UL field.";
	}
}
