package grozail.lab8.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grozail
 * on 29.11.16.
 */
public class Database {
	private List<Student> data;

	public Database(List<Student> data) {
		this.data = data;
	}

	public Database() {
		data = new ArrayList<>();
	}

	public void add(Student student) {
		data.add(student);
	}

	public void add(List<Student> students) {
		data.addAll(students);
	}

	public List<Student> getData() {
		return data;
	}
}
