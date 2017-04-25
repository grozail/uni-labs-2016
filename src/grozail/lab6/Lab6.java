package grozail.lab6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by grozail on 24.10.16.
 *
 */
public class Lab6 {
	public Lab6() {
		try {
			Random random = new Random(1377);
			FuzzyNumber fn1 = new FuzzyNumber(1,2,1);
			System.out.println(fn1);
			FuzzyNumber fn2 = new FuzzyNumber("<4,5,6>");
			System.out.println(fn2);
			System.out.println("adding result: " + fn2.add(fn1));
			System.out.println("subtraction result: " + fn2.sub(fn1));
			System.out.println("multiplication result: " + fn2.mul(fn1));
			System.out.println("division result: " + fn2.div(fn1));
			FuzzyNumber fnc = new FuzzyNumber(fn2);
			System.out.println("inversion result: " + fnc.invert());
			List<FuzzyNumber> fuzzyNumberArrayList = new ArrayList<>();
			System.out.println("======UNSORTED NUMBERS======");
			for (int i = 0; i < 10; i++) {
				fuzzyNumberArrayList.add(new FuzzyNumber(random.nextDouble() * 10, random.nextDouble() * 100, random.nextDouble() * 10));
				System.out.println(fuzzyNumberArrayList.get(i));
			}
			System.out.println();
			System.out.println("======SORTED BY COMPARABLE======");
			Collections.sort(fuzzyNumberArrayList);
			for (FuzzyNumber fuzzyNumber : fuzzyNumberArrayList) {
				System.out.println(fuzzyNumber);
			}
			System.out.println();
			System.out.println("======SORTED BY UL COMPARATOR======");
			fuzzyNumberArrayList.sort(new FuzzyUlComparator());
			for (FuzzyNumber fuzzyNumber : fuzzyNumberArrayList) {
				System.out.println(fuzzyNumber);
			}
			System.out.println();
			System.out.println("======SORTED BY X COMPARATOR======");
			fuzzyNumberArrayList.sort(new FuzzyXComparator());
			for (FuzzyNumber fuzzyNumber : fuzzyNumberArrayList) {
				System.out.println(fuzzyNumber);
			}
			System.out.println();
			System.out.println("======SORTED BY UR COMPARATOR======");
			fuzzyNumberArrayList.sort(new FuzzyUrComparator());
			for (FuzzyNumber fuzzyNumber : fuzzyNumberArrayList) {
				System.out.println(fuzzyNumber);
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
}
