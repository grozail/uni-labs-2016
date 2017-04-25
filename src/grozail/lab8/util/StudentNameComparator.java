package grozail.lab8.util;

import grozail.lab8.data.Student;

import java.util.Comparator;

/**
 * Created by grozail
 * on 30.11.16.
 */
public class StudentNameComparator implements Comparator<Student> {
	public int compare(Student o1, Student o2) {
		return o1.getSurname().compareTo(o2.getSurname());
	}
}
