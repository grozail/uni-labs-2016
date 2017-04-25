package grozail.cwpreparation.model.longnumber;

import java.util.Comparator;

/**
 * Created by grozail
 * on 27.12.16.
 */
public class MaxLongNumberFormer implements Comparator<Character> {
	public int compare(Character o1, Character o2) {
		return -o1.compareTo(o2);
	}
}
