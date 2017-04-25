package grozail.cwpreparation.model.longnumber;

import java.util.*;

/**
 * Created by grozail
 * on 27.12.16.
 */
public class LongNumber {
	private List<Character> number;
	private static final MaxLongNumberFormer MAX_LONG_NUMBER_FORMER = new MaxLongNumberFormer();

	private LongNumber(String number) {
		this.number = new ArrayList<>();
		for (Character c : number.toCharArray()) {
			this.number.add(c);
		}
	}

	public static LongNumber create(String number) throws IllegalArgumentException {
		if (number != null && number.matches("\\d+")) {
			return new LongNumber(number);
		}
		throw new IllegalArgumentException("Something wrong with number");
	}

	public void formMaxNumber() {
		number.sort(MAX_LONG_NUMBER_FORMER);
	}

	public List<Character> getNumber() {
		return number;
	}

	public String toString() {
		StringBuilder stringBuilder = new StringBuilder(number.toString().replaceAll(",\\s",""));
		stringBuilder.deleteCharAt(0);
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		return stringBuilder.toString();
	}

	public String coloredNumber() {
		StringBuilder stringBuilder = new StringBuilder(toString());
		stringBuilder.insert(0,"<html><font color=\"navy\">");
		return stringBuilder.toString();
	}
}
