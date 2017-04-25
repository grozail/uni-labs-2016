package grozail.lab12.view.frames.main.panels;

import grozail.lab12.controller.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * Created by grozail
 * on 10.12.16.
 */
public class TablePanel extends JPanel {
	private JTable table;
	private Controller controller;
	public TablePanel() {
		setLayout(new BorderLayout());
		controller = Controller.getInstance();
		table = new JTable(controller.getMedicineTableModel());
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
}
