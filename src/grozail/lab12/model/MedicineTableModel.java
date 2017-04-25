package grozail.lab12.model;

import javax.swing.table.AbstractTableModel;
import java.util.List;
/**
 * Created by grozail
 * on 10.12.16.
 */
public class MedicineTableModel extends AbstractTableModel {
	private List<Medicine> data;

	private String[] columnNames = {
			"Name",
			"Price",
			"Dosage",
			"Color",
			"Body",
			"Indications"
	};

	public List<Medicine> getData() {
		return data;
	}

	public void setData(List<Medicine> data) {
		this.data = data;
	}

	public String getColumnName(int column) {
		return columnNames[column];
	}

	public int getRowCount() {
		return data.size();
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Medicine medicine = data.get(rowIndex);
		switch (columnIndex) {
			case 0:
				return medicine.getName();
			case 1:
				return medicine.getPrice();
			case 2:
				return medicine.getDosage();
			case 3:
				return medicine.getVisual().getColor();
			case 4:
				return medicine.getVisual().getBody();
			case 5:
				return medicine.getVisual().getIndications();
		}
		return null;
	}

}
