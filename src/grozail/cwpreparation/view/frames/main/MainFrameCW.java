package grozail.cwpreparation.view.frames.main;

import grozail.cwpreparation.controller.Controller;
import grozail.cwpreparation.model.longnumber.LongNumber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by grozail
 * on 25.12.16.
 */
public class MainFrameCW extends JFrame {
	private Controller controller;
	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 600;
	public static final Dimension DEFAULT_APP_DIMENSION = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	private JFileChooser fileChooser;
	private static File workingDir = new File("/opt/!ProjectsJava/UniLabs/src/grozail/cwpreparation/source");
	private TextPanel textPanel;
	private NumberPanel numberPanel;
	JLabel labelNumber;
	public MainFrameCW() {
		super("exam");
		setWindowParameters();
		setWindowComponents();
		setJMenuBar(createMenuBar());
		controller = Controller.getInstance();
	}

	private void setWindowParameters() {
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0); //TODO delete this line after debug
				int action = JOptionPane.showConfirmDialog(MainFrameCW.this, "Do you really want to exit?", "Confirm exit", JOptionPane.OK_CANCEL_OPTION);
				if (action == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
				setVisible(true);
			}
		});
		setSize(DEFAULT_APP_DIMENSION);
		setPreferredSize(DEFAULT_APP_DIMENSION);
		setLocationByPlatform(true);
		setLayout(new BorderLayout());
		setVisible(true);
	}

	private void setWindowComponents() {
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(workingDir);
		numberPanel = new NumberPanel();
		add(numberPanel, BorderLayout.CENTER);
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(createFileMenu());
		menuBar.add(createWindowMenu());
		return menuBar;
	}

	private JMenu createFileMenu() {
		JMenu menuFile = new JMenu("File");
		JMenuItem itemOpenFile = new JMenuItem("Open File...");
		JMenuItem itemSaveFile = new JMenuItem("Save File...");
		menuFile.setMnemonic('F');
		itemOpenFile.setMnemonic('O');
		itemSaveFile.setMnemonic('S');
		itemOpenFile.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
		itemSaveFile.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
		itemOpenFile.addActionListener(event -> {
			if (fileChooser.showOpenDialog(MainFrameCW.this) == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
			}
		});
		menuFile.add(itemOpenFile);
		menuFile.add(itemSaveFile);
		return menuFile;
	}

	private JMenu createWindowMenu() {
		JMenu menuWindow = new JMenu("Window");
		JMenuItem itemEnterNumber = new JMenuItem("Enter Number");
		JMenuItem itemFormMaxNumber = new JMenuItem("Form Max Number");
		JMenuItem itemDrawNumber = new JMenuItem("Show Number");
		itemEnterNumber.addActionListener(event -> {
			controller.setLongNumber(LongNumber.create(JOptionPane.showInputDialog(MainFrameCW.this, "Long number:", "Enter long number", JOptionPane.INFORMATION_MESSAGE)));
		});
		itemFormMaxNumber.addActionListener(event -> {
			controller.getLongNumber().formMaxNumber();
			numberPanel.repaint();
		});
		itemDrawNumber.addActionListener(event -> {
			numberPanel.repaint();
		});
		menuWindow.add(itemEnterNumber);
		menuWindow.add(itemFormMaxNumber);
		menuWindow.add(itemDrawNumber);
		return menuWindow;
	}
}
