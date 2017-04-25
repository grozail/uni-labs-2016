package grozail.lab8.util;

import grozail.lab8.data.Student;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by grozail on 29.11.16.
 * stm
 */
public class StudentTableModel extends AbstractTableModel {
	private String[] columnNames = {"Id", "Surname", "Cource", "Group"};
	private List<Student> data;

	public void setData(List<Student> data) {
		this.data = data;
	}

	public String getColumnName(int column) {
		return columnNames[column];
	}

	public int getRowCount() {
		return data.size();
	}

	public int getColumnCount() {
		return 4;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Student student = data.get(rowIndex);
		switch (columnIndex) {
			case 0:
				return student.getId();
			case 1:
				return student.getSurname();
			case 2:
				return student.getCource();
			case 3:
				return student.getGroup();
		}
		return null;
	}
}
