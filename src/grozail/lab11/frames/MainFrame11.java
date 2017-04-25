package grozail.lab11.frames;

import grozail.lab11.panels.PanelOne;
import grozail.lab11.panels.PanelTwo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by grozail
 * on 6.12.16.
 */
public class MainFrame11 extends JFrame {
	private final int DEFAULT_WIDTH = 600;
	private final int DEFAULT_HEIGHT = 800;
	private Dimension defaultAppDimension = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	private JTabbedPane tabbedPane;
	private PanelOne panelOne;
	private PanelTwo panelTwo;
	public MainFrame11() {
		super("Lab 11");
		setSize(defaultAppDimension);
		setPreferredSize(defaultAppDimension);
		setMinimumSize(new Dimension(((int) defaultAppDimension.getWidth()), ((int) defaultAppDimension.getHeight()) / 2));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setLayout(new BorderLayout());

		//TABS
		tabbedPane = new JTabbedPane();
		panelOne = new PanelOne();
		tabbedPane.addTab("Task 1", panelOne);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		panelTwo = new PanelTwo();
		tabbedPane.addTab("Task 2", panelTwo);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);


		add(tabbedPane);
		pack();
		setVisible(true);
	}
}
