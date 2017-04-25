package grozail.lab11.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by grozail
 * on 6.12.16.
 */
public class PanelOne extends JPanel {
	private DefaultListModel model1, model2;
	private JList list1, list2;
	private JPanel center, west, east;
	private JButton button1, button2;
	public PanelOne() {
		setLayout(new BorderLayout());
		//center panel
		center = new JPanel();
		center.setLayout(new BorderLayout());
		add(center, BorderLayout.CENTER);
		{
			//button1
			button1 = new JButton(">>");
			button1.addActionListener(e -> {
				if (list1.getSelectedIndices().length > 0) {
					for (java.lang.Object o : list1.getSelectedValuesList()) {
						model2.addElement(o);
						model1.removeElement(o);
					}
				}
			});
			button1.setEnabled(false);
			center.add(button1, BorderLayout.NORTH);
			//button2
			button2 = new JButton("<<");
			button2.addActionListener(e -> {
				if (list2.getSelectedIndices().length > 0) {
					for (java.lang.Object o : list2.getSelectedValuesList()) {
						model1.addElement(o);
						model2.removeElement(o);
					}
				}
			});
			button2.setEnabled(false);
			center.add(button2, BorderLayout.SOUTH);
		}
		//west panel
		west = new JPanel();
		west.setLayout(new BorderLayout());
		add(west, BorderLayout.WEST);
		{
			model1 = new DefaultListModel();
			for (int i = 0; i < 6; i++) {
				model1.addElement(String.valueOf(i));
			}
			list1 = new JList(model1);
			list1.setPreferredSize(new Dimension(200, 800));
			list1.addListSelectionListener(e -> {
				if (!e.getValueIsAdjusting()) {
					if (list1.getSelectedIndex() == -1) {
						button1.setEnabled(false);
					}
					else {
						button1.setEnabled(true);
					}
				}
			});
			west.add(new JScrollPane(list1), BorderLayout.CENTER);
		}
		//east panel
		east = new JPanel();
		east.setLayout(new BorderLayout());
		add(east, BorderLayout.EAST);
		{
			model2 = new DefaultListModel();
			for (int i = 6; i < 12; i++) {
				model2.addElement(String.valueOf(i));
			}
			list2 = new JList(model2);
			list2.setPreferredSize(new Dimension(200, 800));
			list2.addListSelectionListener(e -> {
				if (!e.getValueIsAdjusting()) {
					if (list2.getSelectedIndex() == -1) {
						button2.setEnabled(false);
					}
					else {
						button2.setEnabled(true);
					}
				}
			});
			east.add(new JScrollPane(list2), BorderLayout.CENTER);
		}
	}
}
