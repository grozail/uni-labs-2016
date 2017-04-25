package grozail.lab8.forms.main.panels.table;

import grozail.lab8.data.Student;
import grozail.lab8.util.StudentTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by grozail on 19.11.16.
 * mcp
 */
public class TablePanel extends JPanel {
	private JTable table;
	private StudentTableModel tableModel;

	public TablePanel() {

		setLayout(new BorderLayout());

		tableModel = new StudentTableModel();
		table = new JTable(tableModel);
		add(new JScrollPane(table), BorderLayout.CENTER);
	}

	public void setData(List<Student> data) {
		tableModel.setData(data);
	}

	public void refresh() {
		tableModel.fireTableDataChanged();
	}
}
