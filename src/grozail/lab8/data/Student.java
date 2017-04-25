package grozail.lab8.data;

import grozail.lab8.util.StudentNameComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by grozail on 20.11.16.
 * student
 */
public class Student {
	private static Pattern pattern = Pattern.compile("<(\\d+)><((\\w+-?)+)><(\\d+)><(\\d+)>");
	private int id;
	private String surname;
	private byte cource;
	private byte group;
	private static StudentNameComparator nameComparator = new StudentNameComparator();

	public Student(int id, String surname, byte cource, byte group) {
		this.id = id;
		this.surname = surname;
		this.cource = cource;
		this.group = group;
	}

	public int getId() {
		return id;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public byte getCource() {
		return cource;
	}

	public void setCource(byte cource) {
		this.cource = cource;
	}

	public byte getGroup() {
		return group;
	}

	public void setGroup(byte group) {
		this.group = group;
	}

	public static Pattern getPattern() {
		return pattern;
	}

	public static StudentNameComparator getNameComparator() {
		return nameComparator;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Student) {
			if (id == ((Student) obj).id && surname.equals(((Student) obj).surname) && cource == ((Student) obj).cource && group == ((Student) obj).group) {
				return true;
			}
		}
		return false;
	}

	public int hashCode() {
		return id;
	}

	public String toString() {
		return "Student #" + id + "\n" + surname + "\nCource: " + cource + " <> Group: " + group + "\n\n";
	}

	public static List<Student> tryParse(String toParse) {
		List<Student> students = new ArrayList<>();
		Matcher matcher = pattern.matcher(toParse);
		while (matcher.find()) {
			students.add(new Student(Integer.valueOf(matcher.group(1)), matcher.group(2), Byte.valueOf(matcher.group(4)), Byte.valueOf(matcher.group(5))));
		}
		return students;
	}
}
